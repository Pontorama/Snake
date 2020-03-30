import java.awt.*;
import java.awt.image.BufferedImage;

public class Fruit extends Sprite {

    public Fruit(int x, int y){
        super(x,y);
        this.setColor();
    }

    private void setColor(){
        BufferedImage img = new BufferedImage(Commons.CELLSIZE, Commons.CELLSIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        g2d.setColor(Commons.BGCOLOR);
        g2d.fillRect(0,0,Commons.CELLSIZE, Commons.CELLSIZE);
        g2d.setColor(Commons.FRUITCOLOR);
        g2d.fillRect(1,1, Commons.CELLSIZE-1,Commons.CELLSIZE-1);

        this.image = img;
        g2d.dispose();
    }
    
}
