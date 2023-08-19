import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ChessGame {

    static Board board = new Board("defaultBoard.txt");

    public static void main(String[] args) {

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
