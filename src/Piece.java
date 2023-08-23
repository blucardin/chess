/*
 * Noah, Harjosh
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Abstract class for all pieces
 */
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
// abstract class which serves for all pieces
abstract class Piece {

    // will store if the piece is white
    protected boolean isWhite;
    // prefix for image file name
    protected String filePrefix;

    // will store all the possible poves the piece can go
    public abstract ArrayList<int[]> getPossibleMoves(int x, int y);

    // define a getImage function
    public Image getImage() {
        // returns the image of the specific piece 
        return new ImageIcon("src/images/" + getFileString() + ".png").getImage();
    }

    // to string method
    @Override
    public abstract String toString();

    // getter for file string
    public String getFileString() {
        // declares the file name
        String fileName;
        // checks if the box is white
        if (this.isWhite) {
            // changes file name to get white file image 
            fileName = "w" + filePrefix;
        } else {
            // changes file name to get black file image
            fileName = "b" + filePrefix;
        }
        // return the file name
        return fileName;
    }

    // getter for iswhite
    public boolean isWhite() {
        return isWhite;
    }

    // Helper method to check if a position is valid on the chessboard
    public boolean isValidPosition(int x, int y) {
        // returns wether a position is valid
        return x >= 0 && x < Board.getWidth() && y >= 0 && y < Board.getHeight();
    }

}
