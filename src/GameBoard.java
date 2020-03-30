import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class GameBoard extends JPanel {

    private boolean inGame = false;
    private String message = "";
    private Snake snek;
    private Fruit fruit;
    private int points;

    public GameBoard(){
        InitBoard();
        gameInit();
    }

    public void InitBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));
    }

    public void gameInit(){
        snek = new Snake();
        spawnFruit();
        Timer timer = new Timer(Commons.TICKTIME, new GameCycle());
        timer.start();
        this.inGame = true;
        this.points = 0;
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            doGameCycle();
        }
    }

    private void doGameCycle(){
        this.snek.move();
        this.checkCollisions();
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D)g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if(this.inGame){
            drawObjects(g2d);
        }else{
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    public void drawObjects(Graphics2D g2d){
        List<Bodypart> body = this.snek.getBody();

        for(int i = 0; i < body.size(); i++) {
            Bodypart b = body.get(i);
            g2d.drawImage(b.getImage(), b.getX()*Commons.CELLSIZE, b.getY()*Commons.CELLSIZE, b.getImageWidth(), b.getImageHeight(), null);
        }

        g2d.drawImage(this.fruit.getImage(), this.fruit.getX()*Commons.CELLSIZE, this.fruit.getY()*Commons.CELLSIZE, this.fruit.getImageWidth(), this.fruit.getImageHeight(), null);
    }
    private void checkCollisions(){
        if(this.snek.getBody().get(0).getX() == this.fruit.getX() && this.snek.getBody().get(0).getY() == this.fruit.getY()){
            this.fruit = null;
            this.points++;
            this.snek.eat();
            spawnFruit();
        }
    }

    private void spawnFruit(){
        Random r = new Random();
        int fx = (int)(r.nextDouble() * Commons.TILEWIDTH);
        int fy = (int)(r.nextDouble() * Commons.TILEHEIGHT);

        while(true){
            boolean forbidden = false;
            for(int i = 0; i < this.snek.getBodyLength(); i++){
                if(fx == snek.getBody().get(i).getX() && fy == snek.getBody().get(i).getY()){
                    forbidden = true;
                }

            }
            if(fx > Commons.TILEWIDTH || fx < 0 || fy > Commons.TILEHEIGHT || fy < 0){
                forbidden = true;
            }
            if(!forbidden){
                break;
            }
            fx = r.nextInt();
            fy = r.nextInt();
        }

        this.fruit = new Fruit(fx,fy);
    }

    private void gameFinished(Graphics2D g2d){
        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(this.message, (Commons.WIDTH - fontMetrics.stringWidth(this.message)) / 2, Commons.HEIGHT/2);
        font = new Font("Verdana", Font.BOLD, 14);
        g2d.setFont(font);
        fontMetrics = this.getFontMetrics(font);
        String restartText =Commons.GAMEOVER;
        g2d.drawString(restartText, (Commons.WIDTH-fontMetrics.stringWidth(restartText))/ 2, (Commons.HEIGHT+30)/2);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            snek.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
        }
    }

}
