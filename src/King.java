import java.util.ArrayList;

public class King extends Piece {

    public King(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "k"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Define the eight possible directions the King can move
        ArrayList<int[]> directions = getKillMoves(x, y);

        // Check each direction for possible movess
        for (int[] direction : directions) {
            int newX = direction[0];
            int newY = direction[1];

            // Check if the new position is within the bounds of the chessboard
            if (isValidPosition(newX, newY)) {

                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);

                // if the position is not empty and is occupied a piece of the same color, go to
                // the next direction
                if (targetPiece != null) {
                    if (targetPiece.isWhite() == isWhite) {
                        continue;
                    }
                }

                possibleMoves.add(new int[] { newX, newY });

            }
        }

        // Castling
        ArrayList<ArrayList<Piece>> board = ChessGame.board.getPieces();
        // Insert logic here
        if (isWhite) {
            if (ChessGame.board.getWhiteCanCastle()[0]) {
                if (board.get(1).get(7) == null && board.get(2).get(7) == null && board.get(3).get(7) == null) {
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 7, 3, 7) && !ChessGame.board.willThisMoveCauseCheck(4, 7, 2, 7) && !ChessGame.board.willThisMoveCauseCheck(4, 7, 1, 7)) {
                        possibleMoves.add(new int[]{2, 7});
                    }
                }
            }
            if (ChessGame.board.getWhiteCanCastle()[1]) {
                if (board.get(5).get(7) == null && board.get(6).get(7) == null) {
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 7, 5, 7) && !ChessGame.board.willThisMoveCauseCheck(4, 7, 6, 7)) {
                        possibleMoves.add(new int[]{6, 7});
                    }
                }
            }
        }
        else {
            if (ChessGame.board.getBlackCanCastle()[0]) {
                if (board.get(1).get(0) == null && board.get(2).get(0) == null && board.get(3).get(0) == null) {
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 0, 3, 0) && !ChessGame.board.willThisMoveCauseCheck(4, 0, 2, 0) && !ChessGame.board.willThisMoveCauseCheck(4, 0, 1, 0)) {
                        possibleMoves.add(new int[]{2, 0});
                    }
                }
            }
            if (ChessGame.board.getBlackCanCastle()[1]) {
                if (board.get(5).get(0) == null && board.get(6).get(0) == null) {
                    if (!ChessGame.board.willThisMoveCauseCheck(4, 0, 5, 0) && !ChessGame.board.willThisMoveCauseCheck(4, 0, 6, 0)) {
                        possibleMoves.add(new int[]{6, 0});
                    }
                }
            }
        }


        return possibleMoves;
    }

    public static ArrayList<int[]> getKillMoves(int x, int y) {
        ArrayList<int[]> killMoves = new ArrayList<>();
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
            killMoves.add(new int[] { newX, newY });
        }

        return killMoves;
    }

    @Override
    public String toString() {
        return "King [isWhite=" + isWhite + "]";
    }
}
