public enum Side {
    LEFT, RIGHT, TOP, BOTTOM, CORNER, NONE;

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
