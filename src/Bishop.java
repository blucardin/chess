/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Bishop piece 
 */

import java.util.ArrayList;
// bishop class which is a piece
public class Bishop extends Piece {

    // stores the iswhite variable
    public Bishop(boolean isWhite) {
        // stores it for the bishop object
        this.isWhite = isWhite;
        // file prefix for image file name 
        filePrefix = "b";
    }

    // overides the abstract method
    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        // creates an array list of all the possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        // Define the four diagonal directions the Bishop can move
        int[][] diagonalDirections = {
            {-1, -1}, {-1, 1},
            {1, -1}, {1, 1}
        };
        // Check each diagonal direction for possible moves
        for (int[] direction : diagonalDirections) {
            // checks the x moves in the 2d array with their current position
            int newX = x + direction[0];
            // checks the y moves in the 2d array with their current position
            int newY = y + direction[1];
            // Continue checking in the same direction until the edge of the board is reached
            while (isValidPosition(newX, newY)) {
                // gets the piece with the new cords
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);
                // Check if the new position is empty or occupied by an opponent's piece
                if (targetPiece == null) {
                    // if there is no piece there, it adds to the possible moves
                    possibleMoves.add(new int[]{newX, newY});
                } else if (targetPiece.isWhite() != isWhite) {
                    // if the cord is  occupied by an opponent's piece, it adds to the possible moves
                    possibleMoves.add(new int[]{newX, newY});
                    // stop in this direction if an opponent's piece is encountered
                    break;
                } else {
                    // stop in this direction if own piece is encountered
                    break;
                }
                // further adds to new x cord
                newX += direction[0];
                // further adds to the new y cord
                newY += direction[1];
            }
        }
        // checks the possible moves it may move
        return possibleMoves;
    }

    // tostring method
    @Override
    public String toString() {
        return "Bishop [isWhite=" + isWhite + "]";
    }
}
