/*
public class OldBlock implements Collidable {
    //Fields
    private final Point startingPoint;
    private final Rectangle boundary;
    private boolean isCollided=false;
    //private final double height;
    //private final double width;

    //Constructor
    public OldBlock(Point startingPoint, Rectangle boundary) {
        this.startingPoint = startingPoint;
        this.boundary = boundary;
        //this.width = width;
        //this.height = height;
    }

    //get method
    public Point getStartingPoint() {
        return this.startingPoint;
    }

    //get method
    public Rectangle getBoundary() {
        return this.boundary;
    }

    //set method

    //get method
    //public double height() {
    //    return this.height;


    public Rectangle getCollisionRectangle(){
        //size and location
        Rectangle collisionRect = new Rectangle
                (this.startingPoint,this.boundary.width(),this.boundary.height());
        return collisionRect;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint != null) {
            this.isCollided = true;
        }
        Velocity newVel = currentVelocity;
        if (this.isCollided) {
            //find where the collision occurred on the block so we'll change the velocity accordingly
            double x = collisionPoint.getX();
            double y = collisionPoint.getY();

            //if collision point hits horizontal edge- change velocity up/down
            if ((y >this.boundary.topLeft().getY())
                    && (y <this.boundary.getBottomLeftP().getY())){
                newVel.setDx((currentVelocity.getDx()) * (-1));
            }
            //if collision point hits vertical edge- change velocity right/left
            else
                if ((x > this.boundary.topLeft().getX())
                        && ((x > this.boundary.getUpperRightP().getX()))){
                    newVel.setDy((currentVelocity.getDy()) * (-1));
            }
            */
/*if collision point hits rectangle's corners-
            change velocity up/down & right/left
             *//*

            else {
                newVel.setDx(currentVelocity.getDx()*(-1));
                newVel.setDy(currentVelocity.getDy()*(-1));
                }
        }
        return newVel;
    }

}*/
