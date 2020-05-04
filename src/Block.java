import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;

    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public Block(Point topLeft, Point bottomRight, Color color) {
        this(new Rectangle(topLeft, bottomRight), color);
    }

    public Block(Point topLeft, double width, double height, Color color) {
        this(new Rectangle(topLeft, width, height), color);
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public Color getColor() {
        return this.color;
    }

    public double width() {
        return this.rectangle.width();
    }

    public double height() {
        return this.rectangle.height();
    }

    public Point topLeft() {
        return this.rectangle.topLeft();
    }

    public Point bottomRight() {
        return this.rectangle.bottomRight();
    }

    public Line getEdge(Side edge) {
        return this.rectangle.getEdge(edge);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Side hitSide = Side.NONE;
        for (Side edge : this.rectangle.getEdges().keySet()) {
            if (this.getEdge(edge).pointOnLine(collisionPoint)) {
                if (hitSide != Side.NONE) {
                    hitSide = Side.CORNER;
                    break;
                }
                hitSide = edge;
            }
        }
        return currentVelocity.changeSigns(hitSide);
    }

    @Override
    public CollisionInfo getCollisionInfo(Line trajectory) {
        Point collision = trajectory.closestIntersectionToStartOfLine(this.rectangle);
        return collision != null ? new CollisionInfo(collision, this) : null;

    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(((int) this.topLeft().getX()), ((int) this.topLeft().getY()), ((int) this.width()),
                ((int) this.height()));
        surface.setColor(Color.BLACK);
        surface.drawRectangle(((int) this.topLeft().getX()), ((int) this.topLeft().getY()), ((int) this.width()),
                ((int) this.height()));
    }

    @Override
    public void timePassed(double time) {

    }

}
