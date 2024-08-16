package Geometry;

//Omri Cohen 318673407

/**
 * Class that represents a point.
 * it can return the x,y coordinates of the point, calculate distance
 * from another point, and check if another point is identical to it.
 */
public class Point {
    //Threshold for double comparison
    private static final double EPSILON = 0.000001;
    private final double x;
    private final double y;

    /**
     * creates a new Point.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance between a point and another point.
     *
     * @param other the other point.
     * @return the distance between them.
     */
    public double distance(Point other) {
        double x1 = other.getX();
        double y1 = other.getY();
        double x2 = this.getX(), y2 = this.getY();

        double distance = Math.sqrt((x2 - x1) * (x2 - x1)
                + (y2 - y1) * (y2 - y1));
        return distance;
    }

    /**
     * checks if our point and another point are the same.
     *
     * @param other the other point.
     * @return if points are the same returns true,else false.
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
            return ((this.getX() - other.getX()) <= EPSILON
                    && (this.getY() - other.getY()) <= EPSILON);
    }

    /**
     * Gets x coordinate.
     *
     * @return the x coordinate
     */
// Return the x and y values of this point
    public double getX() {
        return x;
    }

    /**
     * Gets y coordinate.
     *
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }
}