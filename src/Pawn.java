/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Front screen of saved games or new game
 */
import java.util.ArrayList;
// pawn class which extends the piece abstract class
public class Pawn extends Piece {

    // stores the iswhite variable
    public Pawn(boolean isWhite) {
        // stores it for the pawn object
        this.isWhite = isWhite;
        // file prefix for image file name 
        filePrefix = "p";
    }

    // overides the abstract method
    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        // creates an array list of all the possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        // Define the pawn's move directions based on its color
        int moveDirection = isWhite ? -1 : 1;
        // stores the x cord
        int newX = x;
        // adds the direction the the y cord
        int newY = y + moveDirection;
        // Check if the square directly in front of the pawn is empty
        if (isValidPosition(newX, newY) && ChessGame.board.getPieces().get(newX).get(newY) == null) {
            // adds to possible moves
            possibleMoves.add(new int[]{newX, newY});
            // If pawn is on its starting position, check if it can move two squares forward
            if ((isWhite && y == 6) || (!isWhite && y == 1)) {
                newY = y + 2 * moveDirection;
                // checks if the square is empty
                if (ChessGame.board.getPieces().get(newX).get(newY) == null) {
                    // adds to the possible moves
                    possibleMoves.add(new int[]{newX, newY});
                }
            }
        }
        // Check diagonal captures
        int[] captureOffsets = {-1, 1};
        // goes through the array 
        for (int offset : captureOffsets) {
            // adds the offset to the x cord
            newX = x + offset;
            // adds the direction to the y cord
            newY = y + moveDirection;
            // checks if the position is valid
            if (isValidPosition(newX, newY)) {
                // gets the piece with the new cords
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);
                // Check if the new position is occupied by an opponent's piece
                if (targetPiece != null && targetPiece.isWhite() != isWhite) {
                    // adds to the possible moves
                    possibleMoves.add(new int[]{newX, newY});
                }
            }
        }
        // Check for en passant
        if (ChessGame.board.getEnPassant() != null) {
            // checks if it is valid for  and y
            newX = ChessGame.board.getEnPassant()[0];
            newY = ChessGame.board.getEnPassant()[1];
            // checkf if new x can equal cord +- 1
            if (newX == x + 1 || newX == x - 1) {
                // checks if temp y can move in direction
                if (newY == y + moveDirection) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{newX, newY});
                }
            }
        }
        // returns the possible moves
        return possibleMoves;
    }

    // to string method
    @Override
    public String toString() {
        return "Pawn [isWhite=" + isWhite + "]";
    }
}
