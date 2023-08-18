import java.util.ArrayList;

public class King extends Piece {

    public King(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "k"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();

        // Define the eight possible directions the King can move
        int[][] directions = {
            { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, -1 }, /* [x, y] */ { 0, 1 },
            { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        // Check each direction for possible moves
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Check if the new position is within the bounds of the chessboard
            if (isValidPosition(newX, newY)) {
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);

                // Check if the new position is empty or occupied by an opponent's piece
                if (targetPiece == null || targetPiece.isWhite() != isWhite) {
                    possibleMoves.add(new int[]{newX, newY});
                }
            }
        }

        return possibleMoves;
    }

    // Helper method to check if a position is valid on the chessboard
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @Override
    public String toString() {
        return "King [isWhite=" + isWhite + "]";
    }
}
