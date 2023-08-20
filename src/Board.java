import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();
    private ArrayList<int[]> highlighted = new ArrayList<>();

    private boolean whiteTurn = true;
    private int[] selectedPiece = null;

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
            while ((line = br.readLine()) != null) {
                String[] rowPieces = line.split(" ");
                ArrayList<Piece> row = new ArrayList<>();
                for (int j = 0; j < WIDTH; j++) {

                    Piece piece = null;
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
                this.pieces.add(row);
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
        return fileString;
    }

    public void movePiece(int x, int y, int newX, int newY) {
        pieces.get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        pieces.get(x).set(y, null);
    }

    public void movePiece(int x, int y, int newX, int newY, Piece fillPiece) {
        pieces.get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        pieces.get(x).set(y, fillPiece);
    }
}
