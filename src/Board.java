import java.util.ArrayList;

public class Board {
    private static ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();

    public static ArrayList<ArrayList<Piece>> getPieces() {
        return pieces;
    }

    public static void setPieces(ArrayList<ArrayList<Piece>> pieces) {
        Board.pieces = pieces;
    }

    public static void initializePieces() {
        for (int i = 0; i < 8; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
            pieces.add(row);
        }
    }

    public static void addPiece(Piece piece, int x, int y) {
        pieces.get(x).set(y, piece);
    }

    public static Piece removePiece(int x, int y) {
        Piece pastPiece = pieces.get(x).get(y);
        pieces.get(x).set(y, null);
        return pastPiece;
    }
}
