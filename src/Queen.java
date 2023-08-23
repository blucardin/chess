/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Queen piece w/ castling
 */
import java.util.ArrayList;

// queen class which extends the piece abstract class
public class Queen extends Piece {

    // stores the iswhite variable
    public Queen(boolean isWhite) {
        // stores it for the queen object
        this.isWhite = isWhite;
        // File prefix for image file name
        filePrefix = "q";
    }

    // overides the abstract method
    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        // creates an array list of all the possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        // Check horizontal and vertical moves
        // Check leftwards (decreasing x)
        for (int i = x - 1; i >= 0; i--) {
            // makes sure square is empty
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, y});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, y});
                }
                // breaks loop because it cant go past the piece
                break;
            }
        }
        // Check rightwards (increasing x)
        for (int i = x + 1; i < 8; i++) {
            // makes sure square is empty
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, y});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, y});
                }
                // breaks the loop because it cant go past the piece
                break;
            }
        }
        // Check upwards (decreasing y)
        for (int j = y - 1; j >= 0; j--) {
            // makes sure square is empty
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{x, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{x, j});
                }
                // breaks the loop because it cant go past the piece
                break;
            }
        }
        // Check downwards (increasing y)
        for (int j = y + 1; j < 8; j++) {
            // makes sure square is empty
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{x, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{x, j});
                }
                // breaks the loop because it cant go past the piece
                break;
            }
        }
        // Check diagonal moves (similar to Bishop)
        // Check top-left diagonal
        // subtracts 1 from x and y
        int i = x - 1;
        int j = y - 1;
        // while it stays in the bounds of board
        while (i >= 0 && j >= 0) {
            //checks if it is empty 
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, j});
                }
                // breaks loop because it cant go past the piece
                break;
            }
            // continues checking by subtracting
            i--;
            j--;
        }

        // Check top-right diagonal
        // adds one to the temp x cord
        i = x + 1;
        // subtracts one to the temp y cord
        j = y - 1;
        // while it is in the bounds of the board
        while (i < 8 && j >= 0) {
            //checks if it is empty 
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, j});
                }
                // break loop as it can not go past a piece
                break;
            }
            // keeps adding further diagonal
            i++;
            j--;
        }
        // Check bottom-left diagonal
        // subtracts one from temp x cord 
        i = x - 1;
        // adds one to temp y cord
        j = y + 1;
        // while it is in the bounds of the board
        while (i >= 0 && j < 8) {
            // checks if it is empty
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    // adds to to possible movies
                    possibleMoves.add(new int[]{i, j});
                }
                // breaks loop as it can not go past the piece
                break;
            }
            // keeps adding the diagonal
            i--;
            j++;
        }
        // Check bottom-right diagonal
        // adds temporary x cord
        i = x + 1;
        // adds temp y cord
        j = y + 1;
        // while it is in bounds of the board
        while (i < 8 && j < 8) {
            // checks if it is empty
            if (ChessGame.board.getPieces().get(i).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, j});
                }
                // breaks loop because can not go past piece
                break;
            }
            // keeps adding to diagonal
            i++;
            j++;
        }
        // returns the possible moves
        return possibleMoves;
    }

    // to string method
    @Override
    public String toString() {
        return "Queen [isWhite=" + isWhite + "]";
    }
}
