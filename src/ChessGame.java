import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ChessGame {
    public static void main(String[] args) {

        // create the pieces
        Board.initializePieces();

        for (int i = 0; i < 8; i++) {
            Board.addPiece(new Pawn(true), i, 1);
            Board.addPiece(new Pawn(false), i, 6);
        }

        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ChessBoardPanel chessPanel = new ChessBoardPanel();
            frame.add(chessPanel);

            frame.pack();
            frame.setVisible(true);
        });
    }
    
}
