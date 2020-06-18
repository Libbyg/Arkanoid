package game;

import geometry.Point;
import shapes.Circle;
import shapes.LineShape;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static utilities.Axis.X;
import static utilities.Axis.Y;


public class Level2 implements LevelInformation {
    //private static final Color OUTER1 = new Color(234, 216, 141);
    private static final Color OUTER1 = new Color(251, 212, 185);
    private static final Color OUTER3 = new Color(255, 129, 0);
    private static final Color OUTER2 = new Color(255, 159, 0);
    private static final Color INNER = new Color(255, 216, 0);
    private static final Color[] COLORS = {Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.BLUE, Color.GREEN,
            Color.RED, Color.PINK, Color.ORANGE};

    private Point topLeft = new Point(0,0);
    private Point bottomRight = new Point(800, 600);

    public Level2() {
    }
    public Level2(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List <Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        velocities.add(Velocity.fromAngleAndSpeed(1, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 110;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background(new Rectangle(topLeft, bottomRight, Color.WHITE));
        Point center = topLeft.shiftPoint(bottomRight.getX() / 4, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i+=10) {
            Point endPoint = new Point (i,this.bottomRight.getY()/2.5);
            background.addSprite(new LineShape(center,endPoint,INNER));
        }
        background.addSprite(new Circle(center, 60, OUTER1, OUTER1));
        background.addSprite(new Circle(center, 50, OUTER2, OUTER2));
        background.addSprite(new Circle(center, 45, OUTER3, OUTER3));
        background.addSprite(new Circle(center, 40, INNER, INNER));


       // background.addSprite(new LineShape(center.shiftPoint(-110,X), center.shiftPoint(110,X),Color.BLUE));

        //background.addSprite(new LineShape(center.shiftPoint(-110,Y), center.shiftPoint(110,Y),Color.BLUE));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List <Block> blocks = new ArrayList<>();
        double blockHeight = 20;
        double blockWidth = 49.3;
        Block b1 = null;
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[0]);
            }
            if (i >= 2 && i < 4) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[1]);
            }
            if (i >= 4 && i < 6) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[2]);
            }
            if (i >= 6 && i < 9) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[3]);
            }
            if (i >= 9 && i < 11) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[4]);
            }
            if (i >= 11 && i < 13) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[5]);
            }
            if (i >= 13) {
                b1 = new Block(new Point(30 + (i * 49.333), 240), blockWidth, blockHeight, COLORS[6]);
            }
            blocks.add(b1);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
