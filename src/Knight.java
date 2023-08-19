import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "n"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Define the eight possible knight move offsets
        int[][] knightMoveOffsets = {
            { -2, -1 }, { -1, -2 },
            { -2, 1 }, { -1, 2 },
            { 2, -1 }, { 1, -2 },
            { 2, 1 }, { 1, 2 }
        };

        // Check each knight move offset for possible moves
        for (int[] offset : knightMoveOffsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];

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



    @Override
    public String toString() {
        return "Knight [isWhite=" + isWhite + "]";
    }
}
