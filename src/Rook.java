/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Rook piece w/ castling
 */

import java.util.ArrayList;

// rook class which extends the piece abstract class
public class Rook extends Piece {

    // stores the iswhite variable
    public Rook(boolean isWhite) {
        // stores it for the rook object
        this.isWhite = isWhite;
        // file prei for image file name
        filePrefix = "r";
    }

    // overrides abstract method
    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        // creates an array list of all the possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        // Check all the squares in the same row
        for (int i = x - 1; i >= 0; i--) {
            // checks if it is empty
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, y});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, y});
                }
                // breaks loop as it can not go past the piece
                break;
            }
        }
        // checking the right side 
        for (int i = x + 1; i < 8; i++) {
            // checks if square is empty
            if (ChessGame.board.getPieces().get(i).get(y) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{i, y});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(i).get(y).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{i, y});
                }
                // breaks loop as it can not go past the piece
                break;
            }
        }
        // Check all the squares in the bottom column
        for (int j = y - 1; j >= 0; j--) {
            // checks if it is empty
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{x, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{x, j});
                }
                // breaks the loop as it cant go past the piece
                break;
            }
        }
        // checks the top column 
        for (int j = y + 1; j < 8; j++) {
            // checks if the square is empty
            if (ChessGame.board.getPieces().get(x).get(j) == null) {
                // adds to possible moves
                possibleMoves.add(new int[]{x, j});
            } else {
                // checks if it is occupied
                if (ChessGame.board.getPieces().get(x).get(j).isWhite() != isWhite) {
                    // adds to possible moves
                    possibleMoves.add(new int[]{x, j});
                }
                // breaks the loop as it cant go past the piece
                break;
            }
        }
        // returns the possible moves
        return possibleMoves;
    }

    // to string method
    @Override
    public String toString() {
        return "Rook [isWhite=" + isWhite + "]";
    }
}
