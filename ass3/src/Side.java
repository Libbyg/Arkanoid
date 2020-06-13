/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
public enum Side {
    LEFT, RIGHT, TOP, BOTTOM, CORNER, NONE;

    /**
     * the method checks if the line parallel to rectangles' edges
     * @param other
     * @return boolean true/false
     */
    public boolean isParallel(Side other){
        if (this == CORNER || other == CORNER || this==NONE || other == NONE){
            return false;
        }

        if (this == RIGHT || this==LEFT){
            if (other == RIGHT || other==LEFT){
                return true;
            }
        }
        if (this == TOP || this==BOTTOM){
            if (other == TOP || other==BOTTOM){
                return true;
            }
        }
        return false;
    }
}
