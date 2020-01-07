import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Program extends JPanel implements KeyListener {
    JFrame frame;
    static int width = 450;
    static int height = 500;
    long time = System.currentTimeMillis();
    static Bird bird;
    int frameRate = 20;
    static PipeManager manager;
    int numPipes = 3;
    int diameter = 20;
    static int score = 0;
    static int highScore = 0;

    public Program(){
        manager = new PipeManager(numPipes);
        frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(width, height));
        frame.addKeyListener(this);
        this.setBackground(new Color(187, 235, 255));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        bird = new Bird(diameter);
        loop();
    }

    public void loop(){
        while (true) {
            long currTime = System.currentTimeMillis();
            if (currTime - time > frameRate){
                repaint();
                time = System.currentTimeMillis();
            }

            if (bird.isDead()){
                if (JOptionPane.showConfirmDialog(frame, "Restart?", "Game Over", JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION){
                    if (score > highScore){
                        highScore = score;
                    }
                    score = 0;
                    bird = new Bird(diameter);
                    manager = new PipeManager(numPipes);
                } else {
                    System.exit(1);
                }
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.yellow);
        g.fillOval(bird.x, bird.height, bird.diameter, bird.diameter);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(19, 200, 43));
        for (int i = 0; i < manager.pipes.length; i++){
            g2d.fill(manager.pipes[i].bottom);
            g2d.fill(manager.pipes[i].top);
        }

        g.setColor(Color.black);
        g.drawString(("Score: " + score), 30, 30);
        g.drawString(("High Score: " + highScore), 30, 50);
        bird.update();
        manager.update();
    }

    public static void main(String[] args){
        new Program();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            bird.flap();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

