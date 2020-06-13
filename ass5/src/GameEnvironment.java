/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class that sets the environment of the game.
 */
public class GameEnvironment {
    /**
     * Fields.
     */
    private List<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * constructor.
     * @param collidables objects passed by arguments
     */
    public GameEnvironment(List<Collidable> collidables) {
        this();
        this.collidables.addAll(collidables);
    }

    /**
     * method adds collidable object to collidables collection of the game environment.
     * @param c collidable object to be added to all collidables list
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * method adds list of collidables to main collidable collection.
     * @param collidableList that is partial and will be unified with a list of other collidables
     */
    public void addCollidable(List<Collidable> collidableList) {
        this.collidables.addAll(collidableList);
    }

    /**
     * method removes specific collidable object from collidables collection.
     * @param c collidable object to e removed from collidables collection
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.collidables.remove(c);
        }
    }

    /**
     * the method returns the collidables collection list.
     * @return List of collidable objects.
     */
    public List<Collidable> getCollidables() {
        return new ArrayList<>(this.collidables);
    }

    /**
     * the method returns the collisionInfo i.e. includes collision point and the object of collision
     * @param trajectory Line object of collision
     * @return CollisionInfo object
     */
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
                if (bestInfo.collisionPoint().distance(start) < info.collisionPoint().distance(start)) {
                    bestInfo = info;
                }
            }
            return bestInfo;
        }
    }
}
