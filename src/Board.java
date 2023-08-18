import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();
    private ArrayList<int[]> highlighted = new ArrayList<>();

    private boolean whiteTurn = true;
    private int[] selectedPiece = null;
    private String nullString = "__";

    private static final int width = 8, height = 8;

    public Board() {
        for (int i = 0; i < width; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                row.add(null);
            }
            this.pieces.add(row);
        }
    }

    //constructor for loading a board from a file
    public Board(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] pieces = line.split(" ");
                ArrayList<Piece> row = new ArrayList<>();
                for (int j = 0; j < width; j++) {

                    Piece piece = null;
                    switch (pieces[j].charAt(1)) {
                        case 'p':
                            piece = new Pawn(pieces[j].charAt(0) == 'w');
                            break;
                        case 'r':
                            piece = new Rook(pieces[j].charAt(0) == 'w');
                            break;
                        case 'n':
                            piece = new Knight(pieces[j].charAt(0) == 'w');
                            break;
                        case 'b':
                            piece = new Bishop(pieces[j].charAt(0) == 'w');
                            break;
                        case 'q':
                            piece = new Queen(pieces[j].charAt(0) == 'w');
                            break;
                        case 'k':
                            piece = new King(pieces[j].charAt(0) == 'w');
                            break;
                        }
                        row.add(piece);
                }
                this.pieces.add(row);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error: file not found");
            e.printStackTrace();
        }
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

    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
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
                + ", selectedPiece=" + selectedPiece + "]";
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
