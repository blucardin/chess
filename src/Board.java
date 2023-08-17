import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();
    private ArrayList<int[]> highlighted = new ArrayList<>();

    private boolean whiteTurn = true;
    private int[] selectedPiece = null;

    private static final int width = 8, height = 8;

    public Board() {
        for (int i = 0; i < width; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                row.add(null);
            }
            this.pieces.add(row);
        }
        clearHighlighted();
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
}
