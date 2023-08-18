import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoardPanel extends JPanel {
    // Override the paintComponent method for custom drawing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the current width and height of the window
        int width = getWidth();
        int height = getHeight();

        // set the color to a low intensity green
        g.setColor(new Color(0, 128, 0));


        int boardWidth = Board.getWidth(); 
        int boardHeight = Board.getHeight(); 

        // create the dark squares
        for (int i = 0; i < boardWidth; i+=2) {
            for (int j = 1; j < boardHeight; j+=2) {
                g.fillRect(i * width / boardWidth, j * height / boardHeight, width / boardWidth, height / boardHeight);
            }
        }

        for (int i = 1; i < boardWidth; i+=2) {
            for (int j = 0; j < boardHeight; j+=2) {
                g.fillRect(i * width / boardWidth, j * height / boardHeight, width / boardWidth, height / boardHeight);
            }
        }

        // display the highlighted squares
        g.setColor(new Color(255, 255, 0, 128));
        for (int[] square : ChessGame.board.getHighlighted()) {
            g.fillRect(square[0] * width / 8, square[1] * height / 8, width / 8, height / 8);
        }

        // display the pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessGame.board.getPieces().get(i).get(j) != null) {
                    g.drawImage(ChessGame.board.getPieces().get(i).get(j).getImage(), i * width / 8, j * height / 8, width / 8, height / 8, null);
                }
            }
        }

    }

    // Override the getPreferredSize method to return the desired width and height
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    public ChessBoardPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // This method will be called when the canvas is clicked
                int mouseX = e.getX();  // X-coordinate of the mouse click
                int mouseY = e.getY();  // Y-coordinate of the mouse click

                int width = getWidth();
                int height = getHeight();

                int boardWidth = Board.getWidth();
                int boardHeight = Board.getHeight();

                int x = mouseX / (width / boardWidth);
                int y = mouseY / (height / boardHeight);

                int[] selectedPiece = ChessGame.board.getSelectedPiece();
                
                if (ChessGame.board.getPieces().get(x).get(y) == null){ // if the square clicked is empty
                    ChessGame.board.clearHighlighted(); // clear the highlighted squares, if the selected piece is not null, move it to the square clicked
                    if (selectedPiece != null) { 
                        tryToMovePiece(selectedPiece, x, y); 
                    }
                } 
                else if (ChessGame.board.getPieces().get(x).get(y).isWhite() == ChessGame.board.isWhiteTurn()) { // if the square clicked has a piece of the same color as the current turn

                    ChessGame.board.clearHighlighted(); // clear the highlighted squares, highlight the possible moves of the piece clicked, set the selected piece to the piece clicked
                    ArrayList<int[]> highLighted = ChessGame.board.getPieces().get(x).get(y).getPossibleMoves(x, y);
                    highLighted.add(new int[]{x, y});
                    ChessGame.board.setHighlighted(highLighted);

                    ChessGame.board.setSelectedPiece(new int[]{x, y});
                }
                else if (selectedPiece != null) { // if the square clicked has a piece of the opposite color as the current turn
                    tryToMovePiece(selectedPiece, x, y); // move the selected piece to the square clicked
                }

                // redraw the board
                repaint();

            }
        });
    }

    public static void tryToMovePiece(int[] selectedPiece, int x, int y){
        boolean validMove = false;
        for (int[] square : ChessGame.board.getPieces().get(selectedPiece[0]).get(selectedPiece[1]).getPossibleMoves(selectedPiece[0], selectedPiece[1])) {
            if (square[0] == x && square[1] == y) {
                validMove = true;
            }
        }

        if (validMove) {
            ChessGame.board.movePiece(selectedPiece[0], selectedPiece[1], x, y);
            ChessGame.board.changeTurn();
            ChessGame.board.clearHighlighted();
            ChessGame.board.setSelectedPiece(null);
        }
    }

}
