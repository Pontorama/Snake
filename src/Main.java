import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){
        initUI();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new Main();
            game.setVisible(true);
        });
    }

    private void initUI(){
        add(new GameBoard());
        setTitle("Snake");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }
}
