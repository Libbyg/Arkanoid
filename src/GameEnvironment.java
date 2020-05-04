import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    //Fields
    private List<Collidable> collidables;

    //Constructor
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    public GameEnvironment(List<Collidable> collidables) {
        this();
        this.collidables.addAll(collidables);
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public void addCollidable(List<Collidable> collidableList) {
        this.collidables.addAll(collidableList);
    }

    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.collidables.remove(c);
        }
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> infoList = new ArrayList<>();
        for (Collidable c : this.collidables) {
            CollisionInfo info = c.getCollisionInfo(trajectory);
            if (info != null) {
                infoList.add(info);
            }
        }
        if (infoList.isEmpty()) {
            return null;
        } else {
            CollisionInfo bestInfo = infoList.get(0);
            infoList.remove(bestInfo);
            for (CollisionInfo info : infoList) {
                Point start = trajectory.start();
                if (bestInfo.collisionPoint().distance(start) < info.collisionPoint().distance(start)){
                    bestInfo = info;
                }
            }
            return bestInfo;
        }
    }
}
