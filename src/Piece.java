import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

abstract class Piece {

    protected boolean isWhite;
    protected String filePrefix;

    public abstract ArrayList<int[]> getPossibleMoves(int x, int y);

    // define a getImage function
    public Image getImage() {
        String fileName = "";
        if (this.isWhite){
            fileName  =  "w" + filePrefix + ".png";
        }
        else{
            fileName = "b" + filePrefix + ".png";
        }
        return new ImageIcon("images/" + fileName).getImage();
    } 

    public abstract String toString();

    public boolean isWhite() {
        return isWhite;
    }
    
}
