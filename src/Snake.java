import java.awt.event.KeyEvent;
import java.util.*;

public class Snake{
    private int bodyLength;
    private int xDir;
    private int yDir;
    private int snakeXDir;
    private int snakeYDir;
    private List<Bodypart> body;

    public Snake(){
        initSnake();
    }

    private void initSnake(){
        initBody();
        this.xDir = 1;
        this.yDir = 0;
        this.snakeXDir = xDir;
        this.snakeYDir = yDir;
    }

    private void initBody(){
        this.bodyLength = Commons.INITIALLENGTH;
        body = new ArrayList<Bodypart>();
        body.add(0, new Bodypart(null));
        for(int i = 1; i < bodyLength; i++){
            body.add(i, new Bodypart(body.get(i-1)));
        }
    }

    public void keyPressed(KeyEvent e){
        int newDir = 0;
        boolean x = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT && this.xDir == 0){
            newDir = -1;
            x = true;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && this.xDir == 0){
            newDir = 1;
            x = true;
        }
        if(newDir == this.xDir && x){
            return;
        }else if(x){
            this.yDir = 0;
            this.xDir = newDir;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP && this.yDir == 0){
            newDir = -1;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN && this.yDir == 0){
            newDir = 1;
        }
        if(newDir == this.yDir && !x){
            return;
        }else if(!x){
            this.xDir = 0;
            this.yDir = newDir;
            this.snakeYDir = newDir;
            return;
        }
    }

    public int getBodyLength(){
        return this.bodyLength;
    }

    public List<Bodypart> getBody(){
        return this.body;
    }

    public void move(){
        Bodypart head = this.body.get(0);
        head.setPrevX(head.getX());
        head.setPrevY(head.getY());

        head.setX(head.getX() + this.xDir);
        head.setY(head.getY() + this.yDir);

        for (int i = 1; i<body.size(); i++){
            body.get(i).move();
        }
    }

    public void eat(){
        Bodypart next = new Bodypart(body.get(body.size() -1));
        bodyLength++;
        body.add(body.size(), next);
    }
}