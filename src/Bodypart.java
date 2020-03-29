import java.awt.*;
import java.awt.image.BufferedImage;

public class Bodypart extends Sprite{
    private Bodypart ahead;
    private int prevX;
    private int prevY;

    public Bodypart(Bodypart ahead){
        this();
        if(ahead==null){
            this.setX(Commons.STARTX);
            this.setY(Commons.STARTY);
            return;
        }
        if(ahead.getAhead() == null){
            this.setX(ahead.getX() -1);
            this.setY(ahead.getY());
        }else{
            this.setX(ahead.getX() -(ahead.getAhead().getX() - ahead.getX()));
            this.setY(ahead.getY() -(ahead.getAhead().getY() - ahead.getY()));
        }
        this.ahead = ahead;
    }

    public Bodypart(){
        setColor();
    }

    public Bodypart getAhead() {
        return this.ahead;
    }

    private void setColor(){
        BufferedImage img = new BufferedImage(Commons.CELLSIZE, Commons.CELLSIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        g2d.setColor(Commons.BGCOLOR);
        g2d.fillRect(0,0,Commons.CELLSIZE, Commons.CELLSIZE);
        g2d.setColor(Commons.SNAKECOLOR);
        g2d.fillRect(1,1, Commons.CELLSIZE-1,Commons.CELLSIZE-1);

        this.image = img;
        g2d.dispose();
    }

    public void move(){
        this.setPrevX(this.getX());
        this.setPrevY(this.getY());

        if(this.ahead == null){
            return;
        }
        this.setX(this.ahead.getPrevX());
        this.setY(this.ahead.getPrevY());
    }

    public int getPrevX() {
        return this.prevX;
    }

    public void setPrevX(int x){
        this.prevX = x;
    }

    public void setPrevY(int y) {
        this.prevY = y;
    }

    public int getPrevY() {
        return this.prevY;
    }
}
