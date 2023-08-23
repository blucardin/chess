/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * King piece
 */
import java.util.ArrayList;
// king piece which extends the piece abstract class
public class King extends Piece {

    // stores the iswhite variable
    public King(boolean isWhite) {
        // stores it for the king object
        this.isWhite = isWhite;
        // file prefix for image file name 
        filePrefix = "k";
    }

    // overrides the abstract method
    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        // creates an array list of all the possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        // Define the eight possible directions the King can move
        ArrayList<int[]> directions = getKillMoves(x, y);
        // Check each direction for possible movess
        for (int[] direction : directions) {
            // creates new x cord to check if possible
            int newX = direction[0];
            // creates new y cord to check if possible
            int newY = direction[1];
            // Check if the new position is within the bounds of the chessboard
            if (isValidPosition(newX, newY)) {
                // gets the piece with the new cords
                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);
                // if the position is not empty and is occupied a piece of the same color, go to the next direction
                if (targetPiece != null) {
                    if (targetPiece.isWhite() == isWhite) {
                        continue;
                    }
                }
                // add the position to the possible moves as it is valid
                possibleMoves.add(new int[]{newX, newY});
            }
        }
        // puts the whole board into an array list
        ArrayList<ArrayList<Piece>> board = ChessGame.board.getPieces();
        // Castling logic
        // checks if it is white
        if (isWhite) {
            // checks the castle list for left
            if (ChessGame.board.getWhiteCanCastle()[0]) {
                // checks if the spaces are empty
                if (board.get(1).get(7) == null && board.get(2).get(7) == null && board.get(3).get(7) == null) {
                    // make sure it will not cause check
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 7, 3, 7) && !ChessGame.board.willThisMoveCauseCheck(4, 7, 2, 7) && !ChessGame.board.willThisMoveCauseCheck(4, 7, 1, 7)) {
                        // adds to possible move
                        possibleMoves.add(new int[]{2, 7});
                    }
                }
            }
            // checks the castle for right
            if (ChessGame.board.getWhiteCanCastle()[1]) {
                // cehcks if spaces are empty
                if (board.get(5).get(7) == null && board.get(6).get(7) == null) {
                    // checks if it will add check
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 7, 5, 7) && !ChessGame.board.willThisMoveCauseCheck(4, 7, 6, 7)) {
                        // adds to possible moves
                        possibleMoves.add(new int[]{6, 7});
                    }
                }
            }
        } else {
            // checks if black can castle left
            if (ChessGame.board.getBlackCanCastle()[0]) {
                // check if spaces are empty
                if (board.get(1).get(0) == null && board.get(2).get(0) == null && board.get(3).get(0) == null) {
                    // check if it will cause check
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 0, 3, 0) && !ChessGame.board.willThisMoveCauseCheck(4, 0, 2, 0) && !ChessGame.board.willThisMoveCauseCheck(4, 0, 1, 0)) {
                        // adds to possible moves
                        possibleMoves.add(new int[]{2, 0});
                    }
                }
            }
            // checks if black can castle right
            if (ChessGame.board.getBlackCanCastle()[1]) {
                // checks if spaces are empty
                if (board.get(5).get(0) == null && board.get(6).get(0) == null) {
                    // check if it will cause check
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 0, 5, 0) && !ChessGame.board.willThisMoveCauseCheck(4, 0, 6, 0)) {
                        // adds to possible moves
                        possibleMoves.add(new int[]{6, 0});
                    }
                }
            }
        }
        // returns the possible moves
        return possibleMoves;
    }

    // will get the kill moves of the king
    public static ArrayList<int[]> getKillMoves(int x, int y) {
        // creates new kill moves array list
        ArrayList<int[]> killMoves = new ArrayList<>();
        // Define the eight possible directions the King can move
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        // Check each direction for possible moves
        for (int[] direction : directions) {
            // checks the x moves in the 2d array with their current position
            int newX = x + direction[0];
            // checks the y moves in the 2d array with their current position
            int newY = y + direction[1];
            // adds the new move in the array list
            killMoves.add(new int[]{newX, newY});
        }
        // returns the array list
        return killMoves;
    }

    // to string method
    @Override
    public String toString() {
        return "King [isWhite=" + isWhite + "]";
    }
}
