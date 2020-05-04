public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    public void addCollidable(Collidable c){

    }
    public void addSprite(Sprite s){

    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize(){
        //creating ball
        Ball ball = new Ball (center, radius, color);
        //creating blocks
        for (int i = 1; i < numOfBlocks; i++) {
            Block block = new Block (rectangle, color);
        }
        //create paddle
    }

    // Run the game -- start the animation loop.
    public void run(){

    }
}