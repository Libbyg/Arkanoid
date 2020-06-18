package utilities;

/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
public class Utilities {
    private static final double TOLERANCE = Math.pow(10, -4);

    /**
     * doublesEqual checks if there's an epsilon difference between two doubles.
     * which means they're actually equal
     * @param first double number to compare
     * @param second double number to compare
     * @return true/false if the difference is equals to epsilon and thy're equal or not.
     */
    public static boolean doublesEqual(double first, double second) {
        return Math.abs(first - second) <= TOLERANCE;
    }

    /**
     * Calculate an angle based on x's and y's sign value.
     * @param sin the y
     * @param cos the x
     * @return the angle in radians
     */
    public static double calculateAngle(double sin, double cos) {
        double angle;
        if (cos == 0) {
            if (sin == 0) {
                angle = 0;
            } else if (sin > 0) {
                angle = Math.PI / 2;
            } else {
                angle = -Math.PI / 2;
            }
        } else {
            angle = Math.atan(sin / cos);
            if (cos < 0) {
                if (sin > 0) {
                    angle += Math.PI;
                } else if (sin < 0) {
                    angle -= Math.PI;
                } else {
                    angle = Math.PI;
                }
            }
        }
        return angle;
    }
}
