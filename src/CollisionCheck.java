import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CollisionCheck {
    public static void main(String[] args) {
        List<Collidable> collidableList = new ArrayList<>();
        Point topLeft = new Point(0,0);
        Point bottomRight = new Point(400,400);
        Point topRight = new Point(bottomRight.getX(),topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(),bottomRight.getY());
        collidableList.add(new Block(topLeft,20, ((int) bottomLeft.getY()), Color.GRAY));
        collidableList.add(new Block(topLeft, ((int) topRight.getX()), 20, Color.GRAY));
        collidableList.add(new Block(new Point(bottomLeft.getX(),bottomLeft.getY()-20),bottomRight,Color.GRAY));
        collidableList.add(new Block(new Point(topRight.getX()-20,topRight.getY()),bottomRight,Color.GRAY));
        GameEnvironment gameEnvironment = new GameEnvironment(collidableList);
        Ball ball = new Ball(200,200,10,Color.GREEN);
        ball.setVelocity(Velocity.fromAngleAndSpeed(60,12));
        ball.setEnvironment(gameEnvironment);
        GUI gui = new GUI("check",500,500);
        Sleeper sleeper = new Sleeper();
        while (true){
            DrawSurface surface = gui.getDrawSurface();
            ball.moveOneStep();
            for (Collidable c: collidableList){
                if (c instanceof Block){
                    ((Block) c).drawOn(surface);
                }
            }
            ball.drawOn(surface);
            gui.show(surface);
            sleeper.sleepFor(50);
        }

    }
}
