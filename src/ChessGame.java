/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * All panels revolving the game
 */

import javax.swing.*;
import java.awt.*;
// chess game main which manipulates all the panels
public class ChessGame extends JFrame {

    // creates variables
    public static CardLayout cardLayout;
    public static JPanel cardPanel;
    private StartPane1 startPane1;
    private ChessBoardPanel chessBoardPanel;
    private HelpScreenPanel helpScreenPanel;
    private PieceHelpPanel pieceHelpPanel;
    public static newGame newGame = new newGame();
    // sets default board
    public static Board board = new Board("defaultBoard.txt");

    public ChessGame() {
        // Create the JFrame
        super("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setResizable(false);

        // Initialize the CardLayout and the cardPanel to hold the two panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create instances of panels
        startPane1 = new StartPane1();
        chessBoardPanel = new ChessBoardPanel();
        helpScreenPanel = new HelpScreenPanel();
        pieceHelpPanel = new PieceHelpPanel();

        // Add panels to the cardPanel
        cardPanel.add(startPane1, "StartPanel");
        cardPanel.add(chessBoardPanel, "ChessBoardPanel");
        cardPanel.add(helpScreenPanel, "HelpScreenPanel");
        cardPanel.add(pieceHelpPanel, "PieceHelpPanel");
        cardPanel.add(newGame, "newGame");

        // Add the cardPanel to the JFrame
        add(cardPanel);

        // Initially, show the startPane1
        cardLayout.show(cardPanel, "StartPanel");
    }
    // main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessGame().setVisible(true);
            }
        });
    }
}
