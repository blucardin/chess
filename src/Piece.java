import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

abstract class Piece {

    protected boolean isWhite;
    protected String filePrefix;

    public abstract ArrayList<int[]> getPossibleMoves(int x, int y);

    // define a getImage function
    public Image getImage() {

        return new ImageIcon("images/" + getFileString() + ".png").getImage();
    } 

    public abstract String toString();

    public String getFileString(){
        String fileName = "";
        if (this.isWhite){
            fileName  =  "w" + filePrefix ;
        }
        else{
            fileName = "b" + filePrefix;
        }
        return fileName;
    }

    public boolean isWhite() {
        return isWhite;
    }
    
}
