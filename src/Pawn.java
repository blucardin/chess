import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "p"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Define the pawn's move directions based on its color
        int moveDirection = isWhite ? -1 : 1;

        // Check if the square directly in front of the pawn is empty
        int newX = x;
        int newY = y + moveDirection;
        if (isValidPosition(newX, newY) && ChessGame.board.getPieces().get(newX).get(newY) == null) {
            possibleMoves.add(new int[] { newX, newY });

            // If pawn is on its starting position, check if it can move two squares forward
            if ((isWhite && y == 6) || (!isWhite && y == 1)) {
                newY = y + 2 * moveDirection;
                if (ChessGame.board.getPieces().get(newX).get(newY) == null) {
                    possibleMoves.add(new int[] { newX, newY });
                }
            }
        }

        // Check diagonal captures
        int[] captureOffsets = { -1, 1 };
        for (int offset : captureOffsets) {
            newX = x + offset;
            newY = y + moveDirection;
            if (isValidPosition(newX, newY)) {
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);
                if (targetPiece != null && targetPiece.isWhite() != isWhite) {
                    possibleMoves.add(new int[] { newX, newY });
                }
            }
        }

        // Check for en passant
        if (ChessGame.board.getEnPassant() != null) {
            newX = ChessGame.board.getEnPassant()[0];
            newY = ChessGame.board.getEnPassant()[1];
            if (newX == x + 1 || newX == x - 1) {
                if (newY == y + moveDirection) {
                    possibleMoves.add(new int[] { newX, newY });
                }
            }
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return "Pawn [isWhite=" + isWhite + "]";
    }
}
