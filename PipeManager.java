public class PipeManager {
    Pipe[] pipes;

    public PipeManager(int numPipes){
        pipes = new Pipe[numPipes];
        for (int i = 0; i < pipes.length; i++){
            pipes[i] = new Pipe(i);
        }
    }
    public void update(){
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].move();
        }
    }
}
