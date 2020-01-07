import java.awt.*;

public class Bird {
    int diameter;
    int height = 200;
    int x = 100;
    double gravity = -.5;
    int flapPower = 10;
    double acceleration = 0;
    boolean dead = false;

    public Bird(int diameter){
        this.diameter = diameter;
    }

    public void flap(){
        acceleration -= flapPower;
    }

    public void update(){
        acceleration -= gravity;
        height += acceleration;
        if (height + diameter >= Program.height){
            dead = true;
        }

        if (height < 0){
            height = 0;
            acceleration = 0;
        }

        Rectangle b = new Rectangle(x, height, diameter, diameter);
        for (int i = 0; i < Program.manager.pipes.length; i++){
            if (b.intersects(Program.manager.pipes[i].bottom)
            || b.intersects(Program.manager.pipes[i].top)){
                dead = true;
                break;
            }
        }
    }

    public boolean isDead(){
        return dead;
    }
}
