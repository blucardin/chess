import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoardPanel extends javax.swing.JPanel {
    
    final int PADDING = 50; 

    static int width, height; // width and height of the window

    // Override the paintComponent method for custom drawing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the background
        g.drawImage(new ImageIcon("src/images/woodPattern.png").getImage(), 0, 0, getWidth(), getHeight(), null);
        
        // Get the current width and height of the window
        width = getWidth();
        height = getHeight() - (2 * PADDING);

        int boardWidth = Board.getWidth();
        int boardHeight = Board.getHeight();

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {

                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(84, 150, 84 ));
                } else {
                    g.setColor(new Color(227, 213, 184));
                }

                g.fillRect(i * width / boardWidth, (j * height / boardHeight) + PADDING, width / boardWidth,
                        (height / boardHeight) + 1);
            }
        }

        // display the highlighted squares
        g.setColor(new Color(255, 255, 0, 128));
        for (int[] square : ChessGame.board.getHighlighted()) {
            g.fillRect(square[0] * width / 8, (square[1] * height / 8) + PADDING, width / 8, (height / 8) + 1);
        }

        // display the pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessGame.board.getPieces().get(i).get(j) != null) {
                    g.drawImage(ChessGame.board.getPieces().get(i).get(j).getImage(), i * width / 8, (j * height / 8) + PADDING,
                            width / 8, height / 8, null);
                }
            }
        }
        
        int tankenImageWidth = (int) ((double) PADDING / 1.5);
        
        // draw the taken pieces on the side of the board above and below the board
        for (int i = 0; i < ChessGame.board.getTakenPieces().get(0).size(); i++) {
            g.drawImage(ChessGame.board.getTakenPieces().get(0).get(i).getImage(), i * tankenImageWidth, 0, tankenImageWidth, tankenImageWidth, null);
        }

        for (int i = 0; i < ChessGame.board.getTakenPieces().get(1).size(); i++) {
            g.drawImage(ChessGame.board.getTakenPieces().get(1).get(i).getImage(), i * tankenImageWidth, height + PADDING, tankenImageWidth, tankenImageWidth, null);
        }


        // display the ending message
        if (!ChessGame.board.getEnding().equals("")) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            // center the text
            g.drawString(ChessGame.board.getEnding(), width / 2 - g.getFontMetrics().stringWidth(ChessGame.board.getEnding()) / 2, height / 2);

            // return to the main menu button
                        // save game button
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING, width / 4 , height / 8);

            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            // put the text in the middle of the button
            g.drawString("Main Menu", width / 2 - width / 8 + (width / 4 - g.getFontMetrics().stringWidth("Main Menu")) / 2, (height / 2) + PADDING + (height / 8) / 2 + 5);            
            return; 
        }

        // if promotion is available, display the promotion options
        if (ChessGame.board.getPromotion() != null) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("Promote to:", width / 2 - 100, height / 2 - 100);

            boolean color = ChessGame.board.getPieces().get(ChessGame.board.getPromotion()[0]).get(ChessGame.board.getPromotion()[1]).isWhite();

            // draw the pices in a horizontal line  in the middle of the screen

            // draw a box around the options
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING, width / 4 , height / 4);

            g.drawImage(new Queen(color).getImage(), width / 2 - width / 8, (height / 2) + PADDING, width / 8, height / 8, null);
            g.drawImage(new Rook(color).getImage(), width / 2, (height / 2) + PADDING, width / 8, height / 8, null);
            g.drawImage(new Bishop(color).getImage(), width / 2 - width / 8, (height / 2 + height / 8) + PADDING, width / 8, height / 8, null);
            g.drawImage(new Knight(color).getImage(), width / 2, (height / 2 + height / 8) + PADDING, width / 8, height / 8, null);
            
            return;
        }

                // add the pause image in the top right corner of the screen at src/images/pauseButton.png
        if (ChessGame.board.getPaused()) {
            // draw a white triangle in the top right corner of the screen

            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(new Color(255, 255, 255));
            g.fillRect(width - PADDING, 0, PADDING, PADDING);
            g.drawImage(new ImageIcon("src/images/playButton.png").getImage(), width - PADDING, 0, PADDING, PADDING, null);


            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("PAUSED", width / 2 - 100, height / 2);

            // save game button
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING, width / 4 , height / 8);
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING + 2 * (height / 8), width / 4 , height / 8);
            

            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            // put the text in the middle of the button
            g.drawString("Save Game", width / 2 - width / 8 + (width / 4 - g.getFontMetrics().stringWidth("Save Game")) / 2, (height / 2) + PADDING + (height / 8) / 2 + 5);            
            g.drawString("Exit", width / 2 - width / 8 + (width / 4 - g.getFontMetrics().stringWidth("Exit")) / 2, (height / 2) + PADDING + 2 * (height / 8) + (height / 8) / 2 + 5);

            return; 
        } else {
            g.drawImage(new ImageIcon("src/images/pauseButton.png").getImage(), width - PADDING, 0, PADDING, PADDING, null);
        }

    }

    public ChessBoardPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // This method will be called when the canvas is clicked
                int mouseX = e.getX(); // X-coordinate of the mouse click
                int mouseY = e.getY() - PADDING; // Y-coordinate of the mouse click

                if (mouseX > width - PADDING && mouseY < PADDING) {
                    ChessGame.board.flipPaused(); // if the pause button is clicked, flip the paused boolean
                    repaint();
                    return;
                }

                int boardWidth = Board.getWidth();
                int boardHeight = Board.getHeight();

                int x = mouseX / (width / boardWidth);
                int y = mouseY / (height / boardHeight);

                int[] promotion = ChessGame.board.getPromotion();
                if (promotion != null) {
                    if (x >= 3 && x <= 4 && y >= 4 && y <= 5) {
                        boolean color = ChessGame.board.getPieces().get(promotion[0]).get(promotion[1]).isWhite();
                        if (x == 3 && y == 5) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Bishop(color));
                        } else if (x == 4 && y == 5) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Knight(color));
                        } else if (x == 3 && y == 4) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Queen(color));
                        } else if (x == 4 && y == 4) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Rook(color));
                        }
                        ChessGame.board.setPromotion(null);
                    }
                    repaint();
                    return;
                }

                // if the ending is set and the return to main menu button is clicked, return to the main menu
                if (!ChessGame.board.getEnding().equals("")){
                    if (x >= 3 && x <= 4 && y >= 4 && y <= 5) {
                        ChessGame.cardLayout.show(ChessGame.cardPanel, "StartPanel");
                    }
                }
                
                // if the save game button is clicked, save the game
                if (ChessGame.board.getPaused()) {
                    System.out.println(x + "  " + y);
                    if ((x == 3 && y == 4) || (x == 4 && y == 4)) {
                        ChessGame.board.saveGame();
                        ChessGame.board.setEnding("Game saved");
                        ChessGame.board.flipPaused();
                        repaint();
                        
                    }

                    else if ((x == 3 && y == 6) || (x == 4 && y == 6)) {
                        System.out.println("exit");
                        ChessGame.cardLayout.show(ChessGame.cardPanel, "StartPanel");
                    }
                    
                    return; 

                }

                if (x < 0 || x > 7 || y < 0 || y > 7) {
                    return;
                }
                
                int[] selectedPiece = ChessGame.board.getSelectedPiece();

                if (ChessGame.board.getEnding().equals("")) {

                    if (ChessGame.board.getPieces().get(x).get(y) == null) { // if the square clicked is empty
                        if (selectedPiece != null) {
                            tryToMovePiece(selectedPiece, x, y);
                        }
                        ChessGame.board.clearHighlighted(); // clear the highlighted squares, if the selected piece is
                                                            // not null, move it to the square clicked
                    } else if (ChessGame.board.getPieces().get(x).get(y).isWhite() == ChessGame.board.isWhiteTurn()) {
                        // if the square clicked has a piece of the same color as the current turn
                        ChessGame.board.clearHighlighted(); // clear the highlighted squares, highlight the possible
                                                            // moves of the piece clicked, set the selected piece to the
                                                            // piece clicked
                        ArrayList<int[]> possibleMoves = ChessGame.board.getPieces().get(x).get(y).getPossibleMoves(x,
                                y);
                        ArrayList<int[]> highLighted = new ArrayList<>();

                        for (int[] square : possibleMoves) {
                            if (!ChessGame.board.willThisMoveCauseCheck(x, y, square[0], square[1])) {
                                highLighted.add(square);
                            }
                        }

                        highLighted.add(new int[] { x, y });
                        ChessGame.board.setHighlighted(highLighted);

                        ChessGame.board.setSelectedPiece(new int[] { x, y });
                    } else if (selectedPiece != null) { // if the square clicked has a piece of the opposite color as
                                                        // the current turn
                        tryToMovePiece(selectedPiece, x, y); // move the selected piece to the square clicked
                    }
                }

                // redraw the board
                repaint();
                
                // if the turn is black
                if (!ChessGame.board.isWhiteTurn()) {
                    int[] bestMove = new chessBot(ChessGame.board).getBestMove();
                    ChessGame.board.movePiece(bestMove[0], bestMove[1], bestMove[2], bestMove[3]);
                    if (!ChessGame.board.canAMoveBeMade()) {
                        if (ChessGame.board.checkForCheck()) {
                            ChessGame.board.setEnding("Checkmate " + (ChessGame.board.isWhiteTurn() ? "Black" : "White") + " wins");
                        } else {
                            ChessGame.board.setEnding("Stalemate");
                        }
                    }
                                
                    ChessGame.board.changeTurn();
                }

                repaint();

            }
        });
    }

    public void tryToMovePiece(int[] selectedPiece, int x, int y) {
        boolean validMove = false;
        ChessGame.board.getHighlighted().remove(new int[] { x, y });
        for (int[] square : ChessGame.board.getHighlighted()) {
            if (square[0] == x && square[1] == y) {
                validMove = true;
                break;
            }
        }

        if (validMove) {
            ChessGame.board.movePiece(selectedPiece[0], selectedPiece[1], x, y);

            // if it is a castle, move the rook
            if (ChessGame.board.getPieces().get(x).get(y) instanceof King) {
                if (Math.abs(x - selectedPiece[0]) == 2) {
                    if (x == 2) {
                        ChessGame.board.movePiece(0, y, 3, y);
                    } else if (x == 6) {
                        ChessGame.board.movePiece(7, y, 5, y);
                    }
                }
            }

            // if the king moves, or the rook moves, the castling is no longer possible
            if (ChessGame.board.getPieces().get(x).get(y) instanceof King) {
                if (ChessGame.board.getPieces().get(x).get(y).isWhite()) {
                    ChessGame.board.getWhiteCanCastle()[0] = false;
                    ChessGame.board.getWhiteCanCastle()[1] = false;
                } else {
                    ChessGame.board.getBlackCanCastle()[0] = false;
                    ChessGame.board.getBlackCanCastle()[1] = false;
                }
            } else if (ChessGame.board.getPieces().get(x).get(y) instanceof Rook) {
                if (ChessGame.board.getPieces().get(x).get(y).isWhite()) {
                    if (x == 0) {
                        ChessGame.board.getWhiteCanCastle()[0] = false;
                    } else if (x == 7) {
                        ChessGame.board.getWhiteCanCastle()[1] = false;
                    }
                } else {
                    if (x == 0) {
                        ChessGame.board.getBlackCanCastle()[0] = false;
                    } else if (x == 7) {
                        ChessGame.board.getBlackCanCastle()[1] = false;
                    }
                }
            }

            if (!ChessGame.board.canAMoveBeMade()) {
                if (ChessGame.board.checkForCheck()) {
                    ChessGame.board.setEnding("Checkmate " + (ChessGame.board.isWhiteTurn() ? "Black" : "White") + " wins");
                } else {
                    ChessGame.board.setEnding("Stalemate");
                }
            }

            // If a pawn reaches the end of the board, give the player the option to promote it to a queen, rook, bishop, or knight

            if (ChessGame.board.getPieces().get(x).get(y) instanceof Pawn) {
                if (ChessGame.board.getPieces().get(x).get(y).isWhite()) {
                    if (y == 0) {
                        ChessGame.board.setPromotion(new int[] { x, y });
                    }
                } else {
                    if (y == 7) {
                        ChessGame.board.setPromotion(new int[] { x, y });
                    }
                }
            }

            // if the pawn captures en passant, remove the captured pawn
            if (ChessGame.board.getPieces().get(x).get(y) instanceof Pawn) {
                if (ChessGame.board.getEnPassant() != null) {
                    if (x == ChessGame.board.getEnPassant()[0] && y == ChessGame.board.getEnPassant()[1]) {
                        ChessGame.board.getPieces().get(x).set(selectedPiece[1], null);
                    }
                }
            }

            // if the pawn moves two squares forward, set the en passant square
            if (ChessGame.board.getPieces().get(x).get(y) instanceof Pawn) {
                if (Math.abs(y - selectedPiece[1]) == 2) {
                    ChessGame.board.setEnPassant(new int[] { x, (y + selectedPiece[1]) / 2 });
                } else {
                    ChessGame.board.setEnPassant(null);
                }
            } else {
                ChessGame.board.setEnPassant(null);
            }

            ChessGame.board.changeTurn();
            ChessGame.board.clearHighlighted();
            ChessGame.board.setSelectedPiece(null);

        }
    }

}
