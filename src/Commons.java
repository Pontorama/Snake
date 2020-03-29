import java.awt.*;

public interface Commons {
    int WIDTH = 900;
    int HEIGHT = 600;
    int TILEWIDTH = Commons.WIDTH/Commons.CELLSIZE;
    int TILEHEIGHT = Commons.HEIGHT/Commons.CELLSIZE;
    int CELLSIZE = 20;
    int TICKTIME = 1000;
    int INITIALLENGTH = 3;
    Color SNAKECOLOR = Color.green;
    Color FRUITCOLOR = Color.red;
    Color BGCOLOR = Color.white;
    int STARTX = Commons.TILEWIDTH/2;
    int STARTY = Commons.TILEHEIGHT/2;
    String GAMEOVER = "Game over!\nPress R to restart";
}
