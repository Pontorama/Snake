
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
    int x,y;
    int imageWidth, imageHeight;

    Image image;

    public Sprite(){

    }

    public Sprite(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    int getX(){
        return this.x;
    }


    public void setY(int y){
        this.y = y;
    }

    int getY(){
        return this.y;
    }

    int getImageWidth(){
        return this.image.getWidth(null);
    }

    int getImageHeight(){
        return this.image.getHeight(null);
    }

    Image getImage(){
        return this.image;
    }

    Rectangle getRect(){
        return new Rectangle(this.x, this.y, this.image.getWidth(null), this.image.getHeight(null));
    }


    void getImageDimensions() {

        this.imageWidth = image.getWidth(null);
        this.imageHeight = image.getHeight(null);
    }

}
