import java.lang.reflect.Array;
import java.util.ArrayList;

public class King extends Piece {

    public King(boolean isWhite) {
        this.isWhite = isWhite;
        filePrefix = "k"; // File prefix for image file name
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();

        // Define the eight possible directions the King can move
        ArrayList<int[]> directions = getKillMoves(x, y);

        // Check each direction for possible movess
        for (int[] direction : directions) {
            int newX = direction[0];
            int newY = direction[1];

            // Check if the new position is within the bounds of the chessboard
            if (isValidPosition(newX, newY)) {

                Piece targetPiece = ChessGame.board.getPieces().get(newX).get(newY);

                // if the position is not empty and is occupied a piece of the same color, go to the next direction
                if (targetPiece != null) {
                    if (targetPiece.isWhite() == isWhite) {
                        continue;
                    }
                }

                System.out.print("Checking if possible move of opponent: " + newX + ", " + newY);
                if (isPossibleMoveOfOpponent(x, y, newX, newY)) {
                    continue;
                }

                possibleMoves.add(new int[]{newX, newY});

            }
        }
        for (int[] possibleMove : possibleMoves) {
            System.out.println("Possible move: " + possibleMove[0] + ", " + possibleMove[1]);
        }
        return possibleMoves;
    }

    
    public boolean isPossibleMoveOfOpponent(int x, int y, int newX, int newY ) {
        // move the king
        Piece pastPiece = ChessGame.board.getPieces().get(newX).get(newY);

        ChessGame.board.getPieces().get(newX).set(newY, ChessGame.board.getPieces().get(x).get(y));
        ChessGame.board.getPieces().get(x).set(y, null);

        // check if the king is in check
        for (int i = 0; i < Board.getHeight(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                Piece piece = ChessGame.board.getPieces().get(i).get(j);

                if (piece != null && piece.isWhite() != isWhite) {

                    ArrayList<int[]> possibleMovesOfOpponent;  

                    if (piece instanceof King) {
                        possibleMovesOfOpponent = getKillMoves(i, j);
                    }
                    else {
                        possibleMovesOfOpponent = piece.getPossibleMoves(i, j);
                    }

                    for (int[] possibleMoveOfOpponent : possibleMovesOfOpponent) {
                        if (possibleMoveOfOpponent[0] == newX && possibleMoveOfOpponent[1] == newY) {
                            System.out.println("Possible move of opponent: " + newX + ", " + newY);
                            ChessGame.board.getPieces().get(x).set(y, ChessGame.board.getPieces().get(newX).get(newY));
                            ChessGame.board.getPieces().get(newX).set(newY, pastPiece);
                            return true;
                        }
                    }
                }
            }
        }

        // move the king back
        ChessGame.board.getPieces().get(x).set(y, ChessGame.board.getPieces().get(newX).get(newY));
        ChessGame.board.getPieces().get(newX).set(newY, pastPiece);

        return false; 
    }

    public ArrayList<int[]> getKillMoves(int x, int y){
        ArrayList<int[]> killMoves = new ArrayList<int[]>();
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
            killMoves.add(new int[]{newX, newY});
        }

        return killMoves;
    }


    // Helper method to check if a position is valid on the chessboard
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @Override
    public String toString() {
        return "King [isWhite=" + isWhite + "]";
    }
}
