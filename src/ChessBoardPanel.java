/*
 * Noah, Harjosh
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Chess game graphics
 */

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// chess board panel which shows board
public class ChessBoardPanel extends javax.swing.JPanel {

    // adds padding int
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
        // gets board width and height and stores
        int boardWidth = Board.getWidth();
        int boardHeight = Board.getHeight();
        // iterates through all of board
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                // colours in  black squraes
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(84, 150, 84));

                } else {
                    // colours in white squares
                    g.setColor(new Color(227, 213, 184));
                }
                // fill rectangle
                g.fillRect(i * width / boardWidth, (j * height / boardHeight) + PADDING, width / boardWidth, (height / boardHeight) + 1);
            }
        }
        // display the highlighted squares
        g.setColor(new Color(255, 255, 0, 128));
        // goes through board
        for (int[] square : ChessGame.board.getHighlighted()) {
            // fills rectangle
            g.fillRect(square[0] * width / 8, (square[1] * height / 8) + PADDING, width / 8, (height / 8) + 1);
        }
        // display the pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // checks if square is taken
                if (ChessGame.board.getPieces().get(i).get(j) != null) {
                    // draws image
                    g.drawImage(ChessGame.board.getPieces().get(i).get(j).getImage(), i * width / 8, (j * height / 8) + PADDING, width / 8, height / 8, null);
                }
            }
        }
        // image width
        int tankenImageWidth = (int) ((double) PADDING / 1.5);
        // draw the taken pieces on the side of the board above and below the board
        for (int i = 0; i < ChessGame.board.getTakenPieces().get(0).size(); i++) {
            g.drawImage(ChessGame.board.getTakenPieces().get(0).get(i).getImage(), i * tankenImageWidth, 0, tankenImageWidth, tankenImageWidth, null);
        }
        // draw the taken pieces on the side of the board above and below the board
        for (int i = 0; i < ChessGame.board.getTakenPieces().get(1).size(); i++) {
            g.drawImage(ChessGame.board.getTakenPieces().get(1).get(i).getImage(), i * tankenImageWidth, height + PADDING, tankenImageWidth, tankenImageWidth, null);
        }
        // display the ending message
        if (!ChessGame.board.getEnding().equals("")) {
            // displays the ending message with detail
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            // center the text
            g.drawString(ChessGame.board.getEnding(), width / 2 - g.getFontMetrics().stringWidth(ChessGame.board.getEnding()) / 2, height / 2);
            // return to the main menu button
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING, width / 4, height / 8);
            // save game button
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            // put the text in the middle of the button
            g.drawString("Main Menu", width / 2 - width / 8 + (width / 4 - g.getFontMetrics().stringWidth("Main Menu")) / 2, (height / 2) + PADDING + (height / 8) / 2 + 5);
            return;
        }

        // if promotion is available, display the promotion options
        if (ChessGame.board.getPromotion() != null) {
            // creates promotion panel
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("Promote to:", width / 2 - 100, height / 2 - 100);
            // gets the colour
            boolean color = ChessGame.board.getPieces().get(ChessGame.board.getPromotion()[0]).get(ChessGame.board.getPromotion()[1]).isWhite();
            // draw the pices in a horizontal line  in the middle of the screen
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING, width / 4, height / 4);
            // draw a box around the options
            g.drawImage(new Queen(color).getImage(), width / 2 - width / 8, (height / 2) + PADDING, width / 8, height / 8, null);
            g.drawImage(new Rook(color).getImage(), width / 2, (height / 2) + PADDING, width / 8, height / 8, null);
            g.drawImage(new Bishop(color).getImage(), width / 2 - width / 8, (height / 2 + height / 8) + PADDING, width / 8, height / 8, null);
            g.drawImage(new Knight(color).getImage(), width / 2, (height / 2 + height / 8) + PADDING, width / 8, height / 8, null);
            return;
        }

        if (ChessGame.board.getPaused()) {
            // draw a white triangle in the top right corner of the screen
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            // add the pause image in the top right corner of the screen at src/images/pauseButton.png
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width - PADDING, 0, PADDING, PADDING);
            g.drawImage(new ImageIcon("src/images/playButton.png").getImage(), width - PADDING, 0, PADDING, PADDING, null);
            // adds pause word
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("PAUSED", width / 2 - 100, height / 2);
            // save game button
            g.setColor(new Color(255, 255, 255));
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING, width / 4, height / 8);
            g.fillRect(width / 2 - width / 8, (height / 2) + PADDING + 2 * (height / 8), width / 4, height / 8);
            // sets colour to save game
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            // put the text in the middle of the button
            g.drawString("Save Game", width / 2 - width / 8 + (width / 4 - g.getFontMetrics().stringWidth("Save Game")) / 2, (height / 2) + PADDING + (height / 8) / 2 + 5);
            g.drawString("Exit", width / 2 - width / 8 + (width / 4 - g.getFontMetrics().stringWidth("Exit")) / 2, (height / 2) + PADDING + 2 * (height / 8) + (height / 8) / 2 + 5);
            return;
        } else {
            // draws the image of pause image
            g.drawImage(new ImageIcon("src/images/pauseButton.png").getImage(), width - PADDING, 0, PADDING, PADDING, null);
        }

    }
    // paints the board
    public ChessBoardPanel() {
        // listens to the mouse
        addMouseListener(new MouseAdapter() {
            //  overrides the button pressed
            @Override
            public void mousePressed(MouseEvent e) {
                // This method will be called when the canvas is clicked
                int mouseX = e.getX(); // X-coordinate of the mouse click
                int mouseY = e.getY() - PADDING; // Y-coordinate of the mouse click
                // checks if mouse is outside
                if (mouseX > width - PADDING && mouseY < 0) {
                    ChessGame.board.flipPaused(); // if the pause button is clicked, flip the paused boolean
                    repaint();
                    return;
                }
                // the boards width and height added
                int boardWidth = Board.getWidth();
                int boardHeight = Board.getHeight();
                // puts in the middle
                int x = mouseX / (width / boardWidth);
                int y = mouseY / (height / boardHeight);
                // adds to promotion array
                int[] promotion = ChessGame.board.getPromotion();
                // checks if it is valid
                if (promotion != null) {
                    // checks outside
                    if (x >= 3 && x <= 4 && y >= 4 && y <= 5) {
                        // checks if it is white
                        boolean color = ChessGame.board.getPieces().get(promotion[0]).get(promotion[1]).isWhite();
                        // checks if the clicked piece and creates new
                        if (x == 3 && y == 5) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Bishop(color));
                        } // checks if the clicked piece and creates new
                        else if (x == 4 && y == 5) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Knight(color));
                        } // checks if the clicked piece and creates new
                        else if (x == 3 && y == 4) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Queen(color));
                        } // checks if the clicked piece and creates new
                        else if (x == 4 && y == 4) {
                            ChessGame.board.getPieces().get(promotion[0]).set(promotion[1], new Rook(color));
                        }
                        // sets promtion to null after chosen
                        ChessGame.board.setPromotion(null);
                    }
                    repaint();
                    return;
                }
                // if the ending is set and the return to main menu button is clicked, return to the main menu
                if (!ChessGame.board.getEnding().equals("")) {
                    // checks if outside
                    if (x >= 3 && x <= 4 && y >= 4 && y <= 5) {
                        // start panel shows
                        ChessGame.cardLayout.show(ChessGame.cardPanel, "StartPanel");
                    }
                }
                // if the save game button is clicked, save the game
                if (ChessGame.board.getPaused()) {
                    System.out.println(x + "  " + y);
                    // checks if clicked
                    if ((x == 3 && y == 4) || (x == 4 && y == 4)) {
                        // saves game
                        ChessGame.board.saveGame();
                        ChessGame.board.setEnding("Game saved");
                        ChessGame.board.flipPaused();
                        repaint();

                    } // checks where clicked
                    else if ((x == 3 && y == 6) || (x == 4 && y == 6)) {
                        System.out.println("exit");
                        // shows start panel
                        ChessGame.cardLayout.show(ChessGame.cardPanel, "StartPanel");
                    }
                    return;

                }
                // checks if clicked outside
                if (x < 0 || x > 7 || y < 0 || y > 7) {
                    return;
                }
                // starting pieces
                int[] selectedPiece = ChessGame.board.getSelectedPiece();
                // checks if ended
                if (ChessGame.board.getEnding().equals("")) {
                    // if the square clicked is empty
                    if (ChessGame.board.getPieces().get(x).get(y) == null) {
                        // checks if piece is valid
                        if (selectedPiece != null) {
                            // trys to move peice
                            tryToMovePiece(selectedPiece, x, y);
                        }
                        ChessGame.board.clearHighlighted(); // clear the highlighted squares, if the selected piece is
                        // not null, move it to the square clicked
                    } else if (ChessGame.board.getPieces().get(x).get(y).isWhite() == ChessGame.board.isWhiteTurn()) {
                        // if the square clicked has a piece of the same color as the current turn
                        ChessGame.board.clearHighlighted(); // clear the highlighted squares, highlight the possible
                        // moves of the piece clicked, set the selected piece to the
                        // piece clicked
                        // creates new possible moves array list
                        ArrayList<int[]> possibleMoves = ChessGame.board.getPieces().get(x).get(y).getPossibleMoves(x,
                                y);
                        // creates array list for highlighted
                        ArrayList<int[]> highLighted = new ArrayList<>();
                        // goes through moves
                        for (int[] square : possibleMoves) {
                            // checks if move will check
                            if (!ChessGame.board.willThisMoveCauseCheck(x, y, square[0], square[1])) {
                                // adds highlighted to square
                                highLighted.add(square);
                            }
                        }
                        // adds list to highlighted
                        highLighted.add(new int[]{x, y});
                        // sets highlighed board
                        ChessGame.board.setHighlighted(highLighted);
                        // sets selected piece to it
                        ChessGame.board.setSelectedPiece(new int[]{x, y});
                    } else if (selectedPiece != null) { // if the square clicked has a piece of the opposite color as
                        // the current turn
                        tryToMovePiece(selectedPiece, x, y); // move the selected piece to the square clicked
                    }
                }
                // redraw the board
                repaint();

            }
        });
    }
    // tries to move the piece depending on mouse click
    public void tryToMovePiece(int[] selectedPiece, int x, int y) {
        // sets valid move to false
        boolean validMove = false;
        // removes highlighted piece
        ChessGame.board.getHighlighted().remove(new int[]{x, y});
        // goes through squares
        for (int[] square : ChessGame.board.getHighlighted()) {
            // checks if square is valid cord
            if (square[0] == x && square[1] == y) {
                // adds to valid move
                validMove = true;
                break;
            }
        }
        // checks if valid move
        if (validMove) {
            // tries to move piece
            ChessGame.board.movePiece(selectedPiece[0], selectedPiece[1], x, y);
            // if it is a castle, move the rook
            if (ChessGame.board.getPieces().get(x).get(y) instanceof King) {
                // checks castle
                if (Math.abs(x - selectedPiece[0]) == 2) {
                    // if x is 2
                    if (x == 2) {
                        // moves piece left
                        ChessGame.board.movePiece(0, y, 3, y);
                    } else if (x == 6) {
                        // moves piece right
                        ChessGame.board.movePiece(7, y, 5, y);
                    }
                }
            }
            // if the king moves, or the rook moves, the castling is no longer possible
            if (ChessGame.board.getPieces().get(x).get(y) instanceof King) {
                // checks if it is white
                if (ChessGame.board.getPieces().get(x).get(y).isWhite()) {
                    // sets white cannot castle
                    ChessGame.board.getWhiteCanCastle()[0] = false;
                    ChessGame.board.getWhiteCanCastle()[1] = false;
                } else {
                    // sets black cannot castle
                    ChessGame.board.getBlackCanCastle()[0] = false;
                    ChessGame.board.getBlackCanCastle()[1] = false;
                }
                // rook moves, the castling is no longer possible
            } else if (ChessGame.board.getPieces().get(x).get(y) instanceof Rook) {
                // cehcks if is white
                if (ChessGame.board.getPieces().get(x).get(y).isWhite()) {
                    // if left
                    if (x == 0) {
                        // cannot castle
                        ChessGame.board.getWhiteCanCastle()[0] = false;
                        // if right
                    } else if (x == 7) {
                        // cannot castle
                        ChessGame.board.getWhiteCanCastle()[1] = false;
                    }
                } else {
                    // if left
                    if (x == 0) {
                        // cannot castle
                        ChessGame.board.getBlackCanCastle()[0] = false;
                        // if right
                    } else if (x == 7) {
                        // cannot castle
                        ChessGame.board.getBlackCanCastle()[1] = false;
                    }
                }
            }
            // If a pawn reaches the end of the board, give the player the option to promote it to a queen, rook, bishop, or knight
            if (ChessGame.board.getPieces().get(x).get(y) instanceof Pawn) {
                // if white pawn
                if (ChessGame.board.getPieces().get(x).get(y).isWhite()) {
                    // if at the top
                    if (y == 0) {
                        // adds promotion
                        ChessGame.board.setPromotion(new int[]{x, y});
                    }
                } else {
                    // if at bottom
                    if (y == 7) {
                        // adds promotion
                        ChessGame.board.setPromotion(new int[]{x, y});
                    }
                }
            }
            // if the pawn captures en passant, remove the captured pawn
            if (ChessGame.board.getPieces().get(x).get(y) instanceof Pawn) {
                // if enpassant is valid
                if (ChessGame.board.getEnPassant() != null) {
                    // checks if enpassant is valid at cord
                    if (x == ChessGame.board.getEnPassant()[0] && y == ChessGame.board.getEnPassant()[1]) {
                        // adds to pieces
                        ChessGame.board.addTakenPiece(ChessGame.board.getPieces().get(x).get(selectedPiece[1]), ChessGame.board.getPieces().get(x).get(selectedPiece[1]).isWhite());
                        ChessGame.board.getPieces().get(x).set(selectedPiece[1], null);
                    }
                }
            }
            // if the pawn moves two squares forward, set the en passant square
            if (ChessGame.board.getPieces().get(x).get(y) instanceof Pawn) {
                // if cord is at 2
                if (Math.abs(y - selectedPiece[1]) == 2) {
                    // sets enpassant valid
                    ChessGame.board.setEnPassant(new int[]{x, (y + selectedPiece[1]) / 2});
                } else {
                    // removes enpassant
                    ChessGame.board.setEnPassant(null);
                }
            } else {
                // removes enpassant
                ChessGame.board.setEnPassant(null);
            }
            // creates methods for altering
            ChessGame.board.changeTurn();
            ChessGame.board.clearHighlighted();
            ChessGame.board.setSelectedPiece(null);

            // checks if move cannot move
            if (!ChessGame.board.canAMoveBeMade()) {
                // checks if can check
                if (ChessGame.board.checkForCheck()) {
                    // sets to whoever won
                    ChessGame.board.setEnding("Checkmate " + (ChessGame.board.isWhiteTurn() ? "Black" : "White") + " wins");
                } else {
                    // sets to stalemate
                    ChessGame.board.setEnding("Stalemate");
                }
                System.out.println("ENDED");
            }

        }
    }

}
