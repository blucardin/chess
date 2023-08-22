/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class chessBot {

    Board board;

    public chessBot(Board board){
        this.board = new Board(board);
    }
    

    public int[] getBestMove(){
        // get the best move for the bot
        ArrayList<int[]> moves = getMoves();
        int[] bestMove = moves.get(0);
        int bestValue = 0;

        for (int i = 0; i < moves.size(); i++){
            int[] move = moves.get(i);
            Piece pastPiece = board.removePiece(move[2], move[3]);

            board.movePiece(move[0], move[1], move[2], move[3]);

            int value = getValueOfBoard();


            if (value > bestValue){
                bestMove = move;
                bestValue = value;
            }

            board.movePiece(move[0], move[1], move[2], move[3], pastPiece);

        }

        return bestMove;
    }

    public ArrayList<int[]> getMoves(){
        // get all possible moves for each piece
        ArrayList<int[]> moves = new ArrayList<>();
        ArrayList<ArrayList<Piece>> pieces = board.getPieces();
        
        for (int i = 0; i < pieces.size(); i++){
            for (int j = 0; j < pieces.get(i).size(); j++){
                if (pieces.get(i).get(j) != null) {
                    if (pieces.get(i).get(j).isWhite() != board.isWhiteTurn()){
                        continue;
                    }
                    ArrayList<int[]> possibleMoves = pieces.get(i).get(j).getPossibleMoves(i, j);
                    for (int k = 0; k < possibleMoves.size(); k++){
                        int[] move = {i, j, possibleMoves.get(k)[0], possibleMoves.get(k)[1]};
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    public int getValueOfPiece(int x, int y) {
        int value = 0;
        Piece piece = board.getPieces().get(x).get(y);
    
        if (piece instanceof Pawn) {
            value = evaluatePawn(x, y);
        } else if (piece instanceof Knight) {
            value = evaluateKnight(x, y);
        } else if (piece instanceof Bishop) {
            value = evaluateBishop(x, y);
        } else if (piece instanceof Rook) {
            value = evaluateRook(x, y);
        } else if (piece instanceof Queen) {
            value = evaluateQueen(x, y);
        } else if (piece instanceof King) {
            value = evaluateKing(x, y);
        }
    
        if (piece.isWhite()) {
            value = value * -1;
        }
    
        return value;
    }
    
    private int evaluatePawn(int x, int y) {
        int[][] pawnTable = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        return pawnTable[x][y];
    }
    
    private int evaluateKnight(int x, int y) {
        int[][] knightTable = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 15, 20, 20, 15, 0, -30},
            {-30, 5, 10, 15, 15, 10, 5, -30},
            {-40, -20, 0, 5, 5, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}
        };
        
        return knightTable[x][y];
    }
    
    private int evaluateBishop(int x, int y) {
        int[][] bishopTable = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
        };
        
        return bishopTable[x][y];
    }
    
    private int evaluateRook(int x, int y) {
        int[][] rookTable = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 10, 10, 10, 10, 10, 10, 5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {0, 0, 0, 5, 5, 0, 0, 0}
        };
        
        return rookTable[x][y];
    }
    
    private int evaluateQueen(int x, int y) {
        int[][] queenTable = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}
        };
        
        return queenTable[x][y];
    }
    
    private int evaluateKing(int x, int y) {
        int[][] kingTable = {
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {20, 30, 10, 0, 0, 10, 30, 20}
        };

        return kingTable[x][y];
    }
    

    public int getValueOfBoard(){
        // determine the value of the board
        int value = 0;
        ArrayList<ArrayList<Piece>> pieces = board.getPieces();

        for (int i = 0; i < pieces.size(); i++){
            for (int j = 0; j < pieces.get(i).size(); j++){
                if (pieces.get(i).get(j) != null){
                    value += getValueOfPiece(i, j);
                }
            }
        }

        return value;
    }
    
    /*
    Pawns
For pawns we simply encourage the pawns to advance. Additionally we try to discourage the engine from leaving central pawns unmoved. The problem I could see here is that this is contradictory to keeping pawns in front of the king. We also ignore the factor whether the pawn is passed or not. So more advanced evaluation is called for, especially that "pawns are soul of the game".

// pawn
 0,  0,  0,  0,  0,  0,  0,  0,
50, 50, 50, 50, 50, 50, 50, 50,
10, 10, 20, 30, 30, 20, 10, 10,
 5,  5, 10, 25, 25, 10,  5,  5,
 0,  0,  0, 20, 20,  0,  0,  0,
 5, -5,-10,  0,  0,-10, -5,  5,
 5, 10, 10,-20,-20, 10, 10,  5,
 0,  0,  0,  0,  0,  0,  0,  0
OK, let's comment this table. Firstly the shelter in front of white short castle (long castle - it's symmetrical) - pawns at f2, g2 and h2 get bonuses. Additionally we set negative values for f3 and smaller for g3 which both create holes around king. Pawn h2 have the same value on h2 and h3, so the engine may create the hole if the situation calls for it. Moreover - if it gets position with a pawn at g3 and a bishop at g2, then it still may play h3 or not. Therefore h3 has the same value as h2.

Zero value on f4, g4, h4 prevents playing with pawns in front of the king. Moving these pawns to f5, g5, h5 still brings nothing, but at this moment we have the same values as on rank 2.

In the centre we have the most negative values on d2 and e2. We don't like these pawns. d3 and e3 aren't good either. Only d4 and e4 in the centre. Even better on d5 and e5.

Beginning with rank 6th we give bonus for advanced pawns. On rank 7th even bigger.

Knights
With knights we simply encourage them to go to the center. Standing on the edge is a bad idea. Standing in the corner is a terrible idea. Probably it was Tartakover who said that "one piece stands badly, the whole game stands badly". And knights move slowly.

// knight
-50,-40,-30,-30,-30,-30,-40,-50,
-40,-20,  0,  0,  0,  0,-20,-40,
-30,  0, 10, 15, 15, 10,  0,-30,
-30,  5, 15, 20, 20, 15,  5,-30,
-30,  0, 15, 20, 20, 15,  0,-30,
-30,  5, 10, 15, 15, 10,  5,-30,
-40,-20,  0,  5,  5,  0,-20,-40,
-50,-40,-30,-30,-30,-30,-40,-50,
As you can see I would happily trade for three pawns any knight standing on the edge. Additionally I put slight bonuses for e2, d2, b5, g5, b3 and g3. Then there are bonuses for being in the center.

Bishops
// bishop
-20,-10,-10,-10,-10,-10,-10,-20,
-10,  0,  0,  0,  0,  0,  0,-10,
-10,  0,  5, 10, 10,  5,  0,-10,
-10,  5,  5, 10, 10,  5,  5,-10,
-10,  0, 10, 10, 10, 10,  0,-10,
-10, 10, 10, 10, 10, 10, 10,-10,
-10,  5,  0,  0,  0,  0,  5,-10,
-20,-10,-10,-10,-10,-10,-10,-20,
We avoid corners and borders. Additionally we prefer squares like b3, c4, b5, d3 and the central ones. Moreover, I wouldn't like to exchange white bishop at d3 (or c3) for black knight at e4, so squares at c3 (f3) have value of 10. As a result white bishop at d3 (c3) is worth (330+10) and black knight at e4 is worth (320+20). So the choice of whether to exchange or not should depend on other issues. On the contrary white bishop at e4 (330+10) would be captured by black knight from f6 (320+10). White bishop at g5 (330+5) won't capture black knight at f6 (320+10).

Rooks
rook
  0,  0,  0,  0,  0,  0,  0,  0,
  5, 10, 10, 10, 10, 10, 10,  5,
 -5,  0,  0,  0,  0,  0,  0, -5,
 -5,  0,  0,  0,  0,  0,  0, -5,
 -5,  0,  0,  0,  0,  0,  0, -5,
 -5,  0,  0,  0,  0,  0,  0, -5,
 -5,  0,  0,  0,  0,  0,  0, -5,
  0,  0,  0,  5,  5,  0,  0,  0
The only ideas which came to my mind was to centralize, occupy the 7th rank and avoid a, h columns (in order not to defend pawn b3 from a3). So generally this is Gerbil like.

Queen
//queen
-20,-10,-10, -5, -5,-10,-10,-20,
-10,  0,  0,  0,  0,  0,  0,-10,
-10,  0,  5,  5,  5,  5,  0,-10,
 -5,  0,  5,  5,  5,  5,  0, -5,
  0,  0,  5,  5,  5,  5,  0, -5,
-10,  5,  5,  5,  5,  5,  0,-10,
-10,  0,  5,  0,  0,  0,  0,-10,
-20,-10,-10, -5, -5,-10,-10,-20
Generally with queen I marked places where I wouldn't like to have a queen. Additionally I slightly marked central squares to keep the queen in the centre and b3, c2 squares (Paweł's suggestion). The rest should be done by tactics.

King
king middle game
-30,-40,-40,-50,-50,-40,-40,-30,
-30,-40,-40,-50,-50,-40,-40,-30,
-30,-40,-40,-50,-50,-40,-40,-30,
-30,-40,-40,-50,-50,-40,-40,-30,
-20,-30,-30,-40,-40,-30,-30,-20,
-10,-20,-20,-20,-20,-20,-20,-10,
 20, 20,  0,  0,  0,  0, 20, 20,
 20, 30, 10,  0,  0, 10, 30, 20
    */
    
}
