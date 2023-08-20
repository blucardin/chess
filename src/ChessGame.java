import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame {
    public static CardLayout cardLayout;
    public static JPanel cardPanel;
    private StartPane1 startPane1;
    private ChessBoardPanel chessBoardPanel;
    private HelpScreen helpScreen; 
    private LoginPanel loginPanel; 
    private MainMenu mainMenu; 

    public static Board board = new Board("defaultBoard.txt");
    public static Session session = new Session(); 

    public ChessGame() {
        // Create the JFrame
        super("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        // Initialize the CardLayout and the cardPanel to hold the two panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create instances of panels
        startPane1 = new StartPane1();
        chessBoardPanel = new ChessBoardPanel();
        helpScreen = new HelpScreen(); 
        loginPanel = new LoginPanel(); 
        mainMenu = new MainMenu(); 

        // Add panels to the cardPanel
        cardPanel.add(startPane1, "StartPanel");
        cardPanel.add(chessBoardPanel, "ChessBoardPanel");
        cardPanel.add( helpScreen, "HelpScreen");
        cardPanel.add(loginPanel, "LoginPanel");
        cardPanel.add(mainMenu, "MainMenu"); 

        // Add the cardPanel to the JFrame
        add(cardPanel);

        // Initially, show the startPane1
        cardLayout.show(cardPanel, "StartPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessGame().setVisible(true);
            }
        });
    }
}
