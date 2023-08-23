/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Knight piece
 */

import java.util.ArrayList;
// knight piece class which extends the piece abstract method
public class Knight extends Piece {

    // stores the iswhite variable
    public Knight(boolean isWhite) {
        // stores it for the knight object
        this.isWhite = isWhite;
        // file prefix for image file name
        filePrefix = "n";
    }

    // overrides the abstract method
    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        // creates an array list of all the possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        // Define the eight possible knight move offsets
        int[][] knightMoveOffsets = {
            {-2, -1}, {-1, -2},
            {-2, 1}, {-1, 2},
            {2, -1}, {1, -2},
            {2, 1}, {1, 2}
        };
        // Check each knight move offset for possible moves
        for (int[] offset : knightMoveOffsets) {
            // checks the x moves in the 2d array with their current position
            int newX = x + offset[0];
            // checks the y moves in the 2d array with their current position
            int newY = y + offset[1];
            // Check if the new position is within the bounds of the chessboard
            if (isValidPosition(newX, newY)) {
                // gets the piece with the new cords
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);
                // Check if the new position is empty or occupied by an opponent's piece
                if (targetPiece == null || targetPiece.isWhite() != isWhite) {
                    // adds to the possible moves array list
                    possibleMoves.add(new int[]{newX, newY});
                }
            }
        }
        // returns the possible moves array list
        return possibleMoves;
    }

    // to string method
    @Override
    public String toString() {
        return "Knight [isWhite=" + isWhite + "]";
    }
}
