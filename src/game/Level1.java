package game;

import geometry.Point;
import shapes.Circle;
import shapes.LineShape;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static utilities.Axis.*;

public class Level1 implements LevelInformation {
    private Point topLeft = new Point(0, 0);
    private Point bottomRight = new Point(800, 600);

    public Level1() {
    }

    public Level1(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(10, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        //Background background = new Background(new Block(topLeft, bottomRight, Color.BLACK));
        Background background = new Background(new Rectangle(topLeft, bottomRight, Color.BLACK, Color.BLACK));
        Point center = topLeft.shiftPoint(bottomRight.getX() / 2, X).shiftPoint(bottomRight.getY() / 4, Y);
        background.addSprite(new Circle(center, 100, Color.BLUE));
        background.addSprite(new Circle(center, 75, Color.BLUE));
        background.addSprite(new Circle(center, 50, Color.BLUE));
        background.addSprite(new LineShape(center.shiftPoint(-110,X), center.shiftPoint(110,X),Color.BLUE));
        background.addSprite(new LineShape(center.shiftPoint(-110,Y), center.shiftPoint(110,Y),Color.BLUE));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point center = topLeft.shiftPoint(bottomRight.getX() / 2, X).shiftPoint(bottomRight.getY() / 4, Y);
        blocks.add(new Block(center.shiftPoint(-25,XY),center.shiftPoint(25,XY),Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}