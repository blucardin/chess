/*
 * Noah, Harjosh
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * All of the physical board
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

// board class
public class Board {

    // array list for the pieces
    private ArrayList<ArrayList<Piece>> pieces = new ArrayList<>(); // Array List
    // array list for the highlighted
    private ArrayList<int[]> highlighted = new ArrayList<>(); // Array list
    // array list for the pieces that are taken
    private ArrayList<ArrayList<Piece>> takenPieces = new ArrayList<>();

    {
        // helps add the array list in the array list
        for (int i = 0; i < 2; i++) {
            // creates a row array list
            ArrayList<Piece> row = new ArrayList<>();
            // adds the row to taken pieces
            takenPieces.add(row);
        }
    }
    // declares and creates variables which will change depending on turn 
    private boolean whiteTurn = true;
    private int[] selectedPiece = null;
    private int[] promotion, enPassant;
    private boolean paused = false;
    private String ending = "";
    private static String nullString = "__";
    // creates boolean array which will store if can castle on both sides
    private boolean[] whiteCanCastle = {true, true}, blackCanCastle = {true, true};
    // sets width and height of board as 8 as default
    private static final int WIDTH = 8, HEIGHT = 8;

    // board constructor
    public Board() {
        // goes through all board
        for (int i = 0; i < WIDTH; i++) {
            // creates a array list for row
            ArrayList<Piece> row = new ArrayList<>();
            // goes through the height of board
            for (int j = 0; j < HEIGHT; j++) {
                // adds null to row
                row.add(null);
            }
            // adds row to the board's pieces
            this.pieces.add(row);
        }
    }

    // constructor for loading a board from a file
    public Board(String fileName) {
        // reads the file of games
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // loop through rows of the board
            for (int i = 0; i < HEIGHT + 2; i++) {
                // read a line from the file
                line = br.readLine();
                // skip empty lines
                if (line == null || line.equals("")) {
                    continue;
                }
                // split the line to get piece information
                String[] rowPieces = line.split(" ");
                ArrayList<Piece> row = new ArrayList<>();
                // loop through pieces in the row
                for (int j = 0; j < rowPieces.length; j++) {
                    Piece piece = null;
                    // check for empty cell
                    if (rowPieces[j].equals(nullString)) {
                        // add null for an empty cell
                        row.add(null);
                        continue;
                    }
                    // determine the piece type based on the code
                    switch (rowPieces[j].charAt(1)) {
                        case 'p':
                            // create a Pawn piece
                            piece = new Pawn(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'r':
                            // create a Rook piece
                            piece = new Rook(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'n':
                            // create a Knight piece
                            piece = new Knight(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'b':
                            // create a Bishop piece
                            piece = new Bishop(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'q':
                            // create a Queen piece
                            piece = new Queen(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'k':
                            // create a King piece
                            piece = new King(rowPieces[j].charAt(0) == 'w');
                            break;
                    }
                    // add the created piece to the row
                    row.add(piece);
                }
                // add row to pieces or takenPieces based on row index
                if (i < HEIGHT) {
                    // add row to the board layout
                    this.pieces.add(row);
                } else {
                    // add row to takenPieces for captured pieces
                    takenPieces.get(i - HEIGHT).addAll(row);
                }
            }
            // read and set the current player's turn
            line = br.readLine();
            // set the turn based on the read line
            if (line.equals("w")) {
                whiteTurn = true;
            } else {
                whiteTurn = false;
            }
        } catch (IOException e) {
            // handle file not found error
            System.out.println("Error: file not found");
        }
    }

    // checks if the current side is in check
    public boolean checkForCheck() {
        // initialize variables to hold king's position
        int kingX = -1;
        int kingY = -1;
        // find the king's position
        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                // find the position of the king
                if (pieces.get(i).get(j) instanceof King && pieces.get(i).get(j).isWhite() == whiteTurn) {
                    kingX = i;
                    kingY = j;
                }
            }
        }
        // iterate through opponent's pieces and their possible moves
        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                // get the piece at the current position
                Piece piece = ChessGame.board.getPieces().get(i).get(j);
                // check opponent's pieces
                if (piece != null && piece.isWhite() != whiteTurn) {
                    ArrayList<int[]> possibleMovesOfOpponent;
                    // get possible moves of opponent's pieces
                    if (piece instanceof King) {
                        possibleMovesOfOpponent = King.getKillMoves(i, j);
                    } else {
                        possibleMovesOfOpponent = piece.getPossibleMoves(i, j);
                    }

                    // check if opponent's piece can attack the king
                    for (int[] possibleMoveOfOpponent : possibleMovesOfOpponent) {
                        // check if the opponent's piece threatens the king
                        if (possibleMoveOfOpponent[0] == kingX && possibleMoveOfOpponent[1] == kingY) {
                            return true;
                        }
                    }
                }
            }
        }
        // no threat detected
        return false;
    }

    // checks if a move would cause a king to be in check
    public boolean willThisMoveCauseCheck(int x, int y, int newX, int newY) {
        // temporarily remove the piece at the destination
        Piece pastPiece = removePiece(newX, newY);
        // move the piece to the new position
        movePiece(x, y, newX, newY);
        // check for check after the move
        boolean check = checkForCheck();
        // undo the move
        movePiece(newX, newY, x, y, pastPiece);
        // return the check status
        return check;
    }

    // checks if there's any valid move that can be made
    public boolean canAMoveBeMade() {
        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                // get the piece at the current position
                Piece piece = pieces.get(i).get(j);
                // check the pieces for valid moves
                if (piece != null && piece.isWhite() == whiteTurn) {
                    ArrayList<int[]> possibleMoves = piece.getPossibleMoves(i, j);
                    // check if any move avoids putting the own king in check
                    for (int[] possibleMove : possibleMoves) {
                        // check if this move doesn't result in check
                        if (!willThisMoveCauseCheck(i, j, possibleMove[0], possibleMove[1])) {
                            return true;
                        }
                    }
                }
            }
        }
        // no valid move found
        return false;
    }

    // gets the paused state of the game
    public boolean getPaused() {
        return paused;
    }

    // toggles the paused state of the game
    public void flipPaused() {
        paused = !paused;
    }

    // adds a taken piece to the appropriate list
    public void addTakenPiece(Piece piece, boolean isWhite) {
        if (isWhite) {
            takenPieces.get(0).add(piece);
        } else {
            takenPieces.get(1).add(piece);
        }
    }

    // returns captured pieces
    public ArrayList<ArrayList<Piece>> getTakenPieces() {
        return takenPieces;
    }

    // returns pawn promotion status
    public int[] getPromotion() {
        return promotion;
    }

    // sets pawn promotion status
    public void setPromotion(int[] promotion) {
        this.promotion = promotion;
    }

    // returns en passant status
    public int[] getEnPassant() {
        return enPassant;
    }

    // sets en passant status
    public void setEnPassant(int[] enPassant) {
        this.enPassant = enPassant;
    }

    // returns white's castling status
    public boolean[] getWhiteCanCastle() {
        return whiteCanCastle;
    }

    // sets white's castling status
    public void setWhiteCanCastle(boolean[] whiteCanCastle) {
        this.whiteCanCastle = whiteCanCastle;
    }

    // returns black's castling status
    public boolean[] getBlackCanCastle() {
        return blackCanCastle;
    }

    // sets black's castling status
    public void setBlackCanCastle(boolean[] blackCanCastle) {
        this.blackCanCastle = blackCanCastle;
    }

    // returns true if it's white's turn
    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    // switches the turn between white and black
    public void changeTurn() {
        this.whiteTurn = !this.whiteTurn;
    }

    // returns coordinates of the selected piece
    public int[] getSelectedPiece() {
        return selectedPiece;
    }

    // sets coordinates of the selected piece
    public void setSelectedPiece(int[] selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    // clears the list of highlighted positions
    public void clearHighlighted() {
        this.highlighted.clear();
    }

    // sets the game's ending status
    public void setEnding(String ending) {
        this.ending = ending;
    }

    // returns the game's ending status
    public String getEnding() {
        return ending;
    }

    // returns the width of the board
    public static int getWidth() {
        return WIDTH;
    }

    // returns the height of the board
    public static int getHeight() {
        return HEIGHT;
    }

    // returns list of highlighted positions
    public ArrayList<int[]> getHighlighted() {
        return highlighted;
    }

    // sets list of highlighted positions
    public void setHighlighted(ArrayList<int[]> highlighted) {
        this.highlighted = highlighted;
    }

    // returns the current layout of pieces on the board
    public ArrayList<ArrayList<Piece>> getPieces() {
        return pieces;
    }

    // sets the layout of pieces on the board
    public void setPieces(ArrayList<ArrayList<Piece>> pieces) {
        this.pieces = pieces;
    }

    // placeholder for initializing the pieces on the board
    public static void initializePieces() {

    }

    // adds pieces and its coordinates
    public void addPiece(Piece piece, int x, int y) {
        pieces.get(x).set(y, piece);
    }

    // recmoves piece at a coordinate
    public Piece removePiece(int x, int y) {
        // gets piece from board
        Piece pastPiece = pieces.get(x).get(y);
        // sets piece to null
        pieces.get(x).set(y, null);
        // returns the piece
        return pastPiece;
    }

    // to string method
    @Override
    public String toString() {
        return "Board [pieces=" + pieces + ", highlighted=" + highlighted + ", whiteTurn=" + whiteTurn
                + ", selectedPiece=" + Arrays.toString(selectedPiece) + "]";
    }

    // puts board into a string
    public String toFileString() {
        // will hold the string
        String fileString = "";
        // goes through all of pieces
        for (int i = 0; i < pieces.size(); i++) {
            // goes through all of pieces
            for (int j = 0; j < pieces.get(i).size(); j++) {
                // checks if there is no piece
                if (pieces.get(i).get(j) != null) {
                    // adds piece to string
                    fileString += pieces.get(i).get(j).getFileString() + " ";
                } else {
                    // adds _ to string
                    fileString += nullString + " ";
                }
            }
            // creates new line
            fileString += "\n";
        }
        // encode the taken pieces
        for (int i = 0; i < takenPieces.size(); i++) {
            // go through all of taken pieces
            for (int j = 0; j < takenPieces.get(i).size(); j++) {
                // adds the taken moves into file
                fileString += takenPieces.get(i).get(j).getFileString() + " ";
            }
            // adds new line
            fileString += "\n";
        }
        // checks which turn it is
        if (whiteTurn) {
            // adds to file
            fileString += "w";
        } else {
            // if not adds black
            fileString += "b";
        }
        // return the string
        return fileString;
    }

    // move piece method
    public void movePiece(int x, int y, int newX, int newY) {
        // creates a temp piece
        Piece pastPiece = pieces.get(newX).get(newY);
        // gets piece at tile
        pieces.get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        // gets a null piece
        pieces.get(x).set(y, null);
        // if it is not null
        if (pastPiece != null) {
            // adds to taken piece
            addTakenPiece(pastPiece, pastPiece.isWhite());
        }
    }

    // move piece  method (overloaded)
    public void movePiece(int x, int y, int newX, int newY, Piece fillPiece) {
        // gets piece at index
        pieces.get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        // sets to new place
        pieces.get(x).set(y, fillPiece);
    }

    // save game method
    public void saveGame() {
        // formats date for save
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // gets the date
        Date date = new Date();
        // saves the file
        String fileName = "src/savedGames/" + formatter.format(date) + ".txt";

        // use toFileString to save the board to a file
        // create a new file with the name fileName
        // write the board to the file
        // writes to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(toFileString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
