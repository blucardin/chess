import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "p";
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        
        // if the pawn is white, check the square above it to see if it is empty
        if (isWhite) {
            if (y > 0 && ChessGame.board.getPieces().get(x).get(y - 1) == null) {
                possibleMoves.add(new int[]{x, y - 1});
            }
            // if the pawn is on the starting row, check the square two above it to see if it is empty
            if (y == 6 && ChessGame.board.getPieces().get(x).get(y - 2) == null) {
                possibleMoves.add(new int[]{x, y - 2});
            }
            // if the pawn is on the left side of the ChessGame.board, check the square above and to the right to see if it is occupied by an enemy piece
            if (x > 0 && y > 0 && ChessGame.board.getPieces().get(x - 1).get(y - 1) != null && !ChessGame.board.getPieces().get(x - 1).get(y - 1).isWhite()) {
                possibleMoves.add(new int[]{x - 1, y - 1});
            }
            // if the pawn is on the right side of the ChessGame.board, check the square above and to the left to see if it is occupied by an enemy piece
            if (x < 7 && y > 0 && ChessGame.board.getPieces().get(x + 1).get(y - 1) != null && !ChessGame.board.getPieces().get(x + 1).get(y - 1).isWhite()) {
                possibleMoves.add(new int[]{x + 1, y - 1});
            }
        } else {
            // if the pawn is black, check the square below it to see if it is empty
            if (y < 7 && ChessGame.board.getPieces().get(x).get(y + 1) == null) {
                possibleMoves.add(new int[]{x, y + 1});
            }
            // if the pawn is on the starting row, check the square two below it to see if it is empty
            if (y == 1 && ChessGame.board.getPieces().get(x).get(y + 2) == null) {
                possibleMoves.add(new int[]{x, y + 2});
            }
            // if the pawn is on the left side of the ChessGame.board, check the square below and to the right to see if it is occupied by an enemy piece
            if (x > 0 && y < 7 && ChessGame.board.getPieces().get(x - 1).get(y + 1) != null && ChessGame.board.getPieces().get(x - 1).get(y + 1).isWhite()) {
                possibleMoves.add(new int[]{x - 1, y + 1});
            }
            // if the pawn is on the right side of the ChessGame.board, check the square below and to the left to see if it is occupied by an enemy piece
            if (x < 7 && y < 7 && ChessGame.board.getPieces().get(x + 1).get(y + 1) != null && ChessGame.board.getPieces().get(x + 1).get(y + 1).isWhite()) {
                possibleMoves.add(new int[]{x + 1, y + 1});
            }
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return "Pawn [isWhite=" + isWhite + "]";
    }


}
