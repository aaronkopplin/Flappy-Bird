import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pipe {
    Rectangle bottom;
    Rectangle top;
    int distanceToNextPipe = 150;
    int width = 50;
    Random r = new Random();
    int speed = 3;
    int number;
    int gap = 100;

    public Pipe(int number){
        this.number = number;
        int middle = (Program.height/2 -50) + r.nextInt(100);
        int bottomY = middle + gap/2;
        int x = Program.width + number * distanceToNextPipe;
        bottom = new Rectangle(x,
                bottomY, width, Program.height - bottomY);
        top = new Rectangle(x, 0, width, middle-gap/2);
    }

    public void move(){
        bottom.x -= speed;
        top.x -= speed;

        if(bottom.x <= 0){
            Program.score++;
            bottom.x = Program.width;
            top.x = Program.width;

            int middle = (Program.height/2 -50) + r.nextInt(100);

            bottom.y = middle + gap/2;
            bottom.height = Program.height - bottom.y;
            top.y = 0;
            top.height = middle - gap/2;
        }
    }
}
