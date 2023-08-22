import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Board {
    private ArrayList<ArrayList<Piece>> pieces = new ArrayList<>(); // Array List
    private ArrayList<int[]> highlighted = new ArrayList<>(); // Array list

    private ArrayList<ArrayList<Piece>> takenPieces = new ArrayList<>();

    {
        for (int i = 0; i < 2; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            takenPieces.add(row);
        }
    }

    private boolean whiteTurn = true;
    private int[] selectedPiece = null;
    private int[] promotion, enPassant;
    private boolean paused = false;

    private boolean[] whiteCanCastle = {true, true}, blackCanCastle = {true, true};

    private String ending = "";

    private static String nullString = "__";

    private static final int WIDTH = 8, HEIGHT = 8;

    public Board() {
        for (int i = 0; i < WIDTH; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j = 0; j < HEIGHT; j++) {
                row.add(null);
            }
            this.pieces.add(row);
        }
    }

    // constructor for loading a board from a file
    public Board(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            for (int i = 0; i < HEIGHT + 2; i++) {
                line = br.readLine();
                if (line == null || line.equals("")) {
                    continue;
                }
                String[] rowPieces = line.split(" ");
                ArrayList<Piece> row = new ArrayList<>();
                for (int j = 0; j < rowPieces.length; j++) {

                    Piece piece = null;
                    if (rowPieces[j].equals(nullString)) {
                        row.add(null);
                        continue;
                    }
                    switch (rowPieces[j].charAt(1)) {
                        case 'p':
                            piece = new Pawn(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'r':
                            piece = new Rook(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'n':
                            piece = new Knight(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'b':
                            piece = new Bishop(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'q':
                            piece = new Queen(rowPieces[j].charAt(0) == 'w');
                            break;
                        case 'k':
                            piece = new King(rowPieces[j].charAt(0) == 'w');
                            break;
                    }
                    row.add(piece);
                }
                if (i < HEIGHT) {
                    this.pieces.add(row);
                } else {
                    takenPieces.get(i - HEIGHT).addAll(row);
                }
            }
            line = br.readLine();
            if (line.equals("w")) {
                whiteTurn = true;
            } else {
                whiteTurn = false;
            }

        } catch (IOException e) {
            System.out.println("Error: file not found");
        }
    }

    public boolean checkForCheck() {
        int kingX = -1;
        int kingY = -1;

        // find the king
        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                if (pieces.get(i).get(j) instanceof King && pieces.get(i).get(j).isWhite() == whiteTurn) {
                    kingX = i;
                    kingY = j;
                }
            }
        }

        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                Piece piece = ChessGame.board.getPieces().get(i).get(j);

                if (piece != null && piece.isWhite() != whiteTurn) {

                    ArrayList<int[]> possibleMovesOfOpponent;

                    if (piece instanceof King) {
                        possibleMovesOfOpponent = King.getKillMoves(i, j);
                    } else {
                        possibleMovesOfOpponent = piece.getPossibleMoves(i, j);
                    }

                    for (int[] possibleMoveOfOpponent : possibleMovesOfOpponent) {
                        if (possibleMoveOfOpponent[0] == kingX && possibleMoveOfOpponent[1] == kingY) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean willThisMoveCauseCheck(int x, int y, int newX, int newY) {
        Piece pastPiece = removePiece(newX, newY);
        movePiece(x, y, newX, newY);
        boolean check = checkForCheck();
        movePiece(newX, newY, x, y, pastPiece);
        return check;
    }

    public boolean canAMoveBeMade() {
        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                Piece piece = pieces.get(i).get(j);

                if (piece != null && piece.isWhite() == whiteTurn) {

                    ArrayList<int[]> possibleMoves = piece.getPossibleMoves(i, j);

                    for (int[] possibleMove : possibleMoves) {
                        if (!willThisMoveCauseCheck(i, j, possibleMove[0], possibleMove[1])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean getPaused(){
        return paused;
    }

    public void flipPaused(){
        paused = !paused;
    }

    public void addTakenPiece(Piece piece, boolean isWhite) {
        if (isWhite) {
            takenPieces.get(0).add(piece);
        } else {
            takenPieces.get(1).add(piece);
        }
    }

    public ArrayList<ArrayList<Piece>> getTakenPieces() {
        return takenPieces;
    }

    public int[] getPromotion() {
        return promotion;
    }

    public void setPromotion(int[] promotion) {
        this.promotion = promotion;
    }

    public int[] getEnPassant() {
        return enPassant;
    }

    public void setEnPassant(int[] enPassant) {
        this.enPassant = enPassant;
    }

    public boolean[] getWhiteCanCastle() {
        return whiteCanCastle;
    }

    public void setWhiteCanCastle(boolean[] whiteCanCastle) {
        this.whiteCanCastle = whiteCanCastle;
    }

    public boolean[] getBlackCanCastle() {
        return blackCanCastle;
    }

    public void setBlackCanCastle(boolean[] blackCanCastle) {
        this.blackCanCastle = blackCanCastle;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void changeTurn() {
        this.whiteTurn = !this.whiteTurn;
    }

    public int[] getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(int[] selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void clearHighlighted() {
        this.highlighted.clear();
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getEnding() {
        return ending;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public ArrayList<int[]> getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(ArrayList<int[]> highlighted) {
        this.highlighted = highlighted;
    }

    public ArrayList<ArrayList<Piece>> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<ArrayList<Piece>> pieces) {
        this.pieces = pieces;
    }

    public static void initializePieces() {

    }

    public void addPiece(Piece piece, int x, int y) {
        pieces.get(x).set(y, piece);
    }

    public Piece removePiece(int x, int y) {
        Piece pastPiece = pieces.get(x).get(y);
        pieces.get(x).set(y, null);
        return pastPiece;
    }

    @Override
    public String toString() {
        return "Board [pieces=" + pieces + ", highlighted=" + highlighted + ", whiteTurn=" + whiteTurn
                + ", selectedPiece=" + Arrays.toString(selectedPiece) + "]";
    }

    public String toFileString() {
        String fileString = "";
        for (int i = 0; i < pieces.size(); i++) {
            for (int j = 0; j < pieces.get(i).size(); j++) {
                if (pieces.get(i).get(j) != null) {
                    fileString += pieces.get(i).get(j).getFileString() + " ";
                } else {
                    fileString += nullString + " ";
                }
            }
            fileString += "\n";
        }

        // encode the taken pieces
        for (int i = 0; i < takenPieces.size(); i++) {
            for (int j = 0; j < takenPieces.get(i).size(); j++) {
                fileString += takenPieces.get(i).get(j).getFileString() + " ";
            }
            fileString += "\n";
        }

        // encode the turn 
        if (whiteTurn) {
            fileString += "w";
        } else {
            fileString += "b";
        }

        return fileString;
    }

    public void movePiece(int x, int y, int newX, int newY) {
        Piece pastPiece = pieces.get(newX).get(newY);
        pieces.get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        pieces.get(x).set(y, null);
        if (pastPiece != null) {
            addTakenPiece(pastPiece, pastPiece.isWhite());
        }
    }

    public void movePiece(int x, int y, int newX, int newY, Piece fillPiece) {
        pieces.get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        pieces.get(x).set(y, fillPiece);
    }

    public void saveGame() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
        Date date = new Date();  
        String fileName = "src/savedGames/" + formatter.format(date) + ".txt";

        // use toFileString to save the board to a file

        // create a new file with the name fileName
        // write the board to the file

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(toFileString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
