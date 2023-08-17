import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
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


        // create the dark squares
        for (int i = 0; i < 8; i += 2) {
            for (int j = 1; j < 9; j += 2) {
                g.fillRect(i * width / 8, j * height / 8, width / 8, height / 8);
            }
        }

        for (int i = 1; i < 9; i += 2) {
            for (int j = 0; j < 8; j += 2) {
                g.fillRect(i * width / 8, j * height / 8, width / 8, height / 8);
            }
        }

        // display the pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.getPieces().get(i).get(j) != null) {
                    g.drawImage(Board.getPieces().get(i).get(j).getImage(), i * width / 8, j * height / 8, width / 8, height / 8, null);
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
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
