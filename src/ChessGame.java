import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ChessGame {

    static Board board = new Board();

    public static void main(String[] args) {

        for (int i = 0; i < 8; i++) {
            board.addPiece(new Pawn(false), i, 1);
            board.addPiece(new Pawn(true), i, 6);
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
