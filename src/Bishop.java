import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "b"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Define the four diagonal directions the Bishop can move
        int[][] diagonalDirections = {
            { -1, -1 }, { -1, 1 },
            { 1, -1 }, { 1, 1 }
        };

        // Check each diagonal direction for possible moves
        for (int[] direction : diagonalDirections) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Continue checking in the same direction until the edge of the board is reached
            while (isValidPosition(newX, newY)) {
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);

                // Check if the new position is empty or occupied by an opponent's piece
                if (targetPiece == null) {
                    possibleMoves.add(new int[]{newX, newY});
                } else if (targetPiece.isWhite() != isWhite) {
                    possibleMoves.add(new int[]{newX, newY});
                    break; // Stop in this direction if an opponent's piece is encountered
                } else {
                    break; // Stop in this direction if own piece is encountered
                }

                newX += direction[0];
                newY += direction[1];
            }
        }

        return possibleMoves;
    }



    @Override
    public String toString() {
        return "Bishop [isWhite=" + isWhite + "]";
    }
}
