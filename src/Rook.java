
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "r";
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Check all the squares in the same row
        for (int i = x - 1; i >= 0; i--) {
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                possibleMoves.add(new int[]{i, y});
            } else {
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    possibleMoves.add(new int[]{i, y});
                }
                break;
            }
        }
        for (int i = x + 1; i < 8; i++) {
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                possibleMoves.add(new int[]{i, y});
            } else {
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    possibleMoves.add(new int[]{i, y});
                }
                break;
            }
        }

        // Check all the squares in the same column
        for (int j = y - 1; j >= 0; j--) {
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                possibleMoves.add(new int[]{x, j});
            } else {
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[]{x, j});
                }
                break;
            }
        }
        for (int j = y + 1; j < 8; j++) {
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                possibleMoves.add(new int[]{x, j});
            } else {
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[]{x, j});
                }
                break;
            }
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return "Rook [isWhite=" + isWhite + "]";
    }
}
