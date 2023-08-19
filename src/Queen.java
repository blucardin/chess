import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "q"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Check horizontal and vertical moves (similar to Rook)
        // Check leftwards (decreasing x)
        for (int i = x - 1; i >= 0; i--) {
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                possibleMoves.add(new int[] { i, y });
            } else {
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { i, y });
                }
                break;
            }
        }
        // Check rightwards (increasing x)
        for (int i = x + 1; i < 8; i++) {
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                possibleMoves.add(new int[] { i, y });
            } else {
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { i, y });
                }
                break;
            }
        }
        // Check upwards (decreasing y)
        for (int j = y - 1; j >= 0; j--) {
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                possibleMoves.add(new int[] { x, j });
            } else {
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { x, j });
                }
                break;
            }
        }
        // Check downwards (increasing y)
        for (int j = y + 1; j < 8; j++) {
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                possibleMoves.add(new int[] { x, j });
            } else {
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { x, j });
                }
                break;
            }
        }

        // Check diagonal moves (similar to Bishop)
        // Check top-left diagonal
        int i = x - 1;
        int j = y - 1;
        while (i >= 0 && j >= 0) {
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                possibleMoves.add(new int[] { i, j });
            } else {
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { i, j });
                }
                break;
            }
            i--;
            j--;
        }

        // Check top-right diagonal
        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0) {
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                possibleMoves.add(new int[] { i, j });
            } else {
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { i, j });
                }
                break;
            }
            i++;
            j--;
        }

        // Check bottom-left diagonal
        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8) {
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                possibleMoves.add(new int[] { i, j });
            } else {
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { i, j });
                }
                break;
            }
            i--;
            j++;
        }

        // Check bottom-right diagonal
        i = x + 1;
        j = y + 1;
        while (i < 8 && j < 8) {
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                possibleMoves.add(new int[] { i, j });
            } else {
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    possibleMoves.add(new int[] { i, j });
                }
                break;
            }
            i++;
            j++;
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return "Queen [isWhite=" + isWhite + "]";
    }
}
