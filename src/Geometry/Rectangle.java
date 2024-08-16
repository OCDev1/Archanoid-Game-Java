package Geometry;

//Omri Cohen 318673407

/**
 * The Rectangle class to create rectangular objects.
 */
public class Rectangle {
    /**
     * The Upper left.
     */
    private Point upperLeft;
    /**
     * The Width.
     */
    private double width;
    /**
     * The Height.
     */
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the given line
     * @return java . util . list the list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new java.util.ArrayList<>();

        // Get the sides of the rectangle
        Line[] sides = this.getSides();

        // Check for intersection with each side of the rectangle
        for (Line side : sides) {
            if (line.isIntersecting(side)) {
                intersectionPoints.add(line.intersectionWith(side));
            }
        }
        return intersectionPoints;
    }

    /**
     * Get width of the shape.
     *
     * @return the double
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height of the shape.
     *
     * @return the double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point.
     *
     * @return the point
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperLeft;
    }


    /**
     * turn the rectangle into an array of lines representing the sides of it.
     *
     * @return the line [ ] the array of lines representing the sides of the rectangle
     */
    public Line[] getSides() {
        Point upperLeft = getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + getHeight());
        Point lowerRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());

        Line[] sides = new Line[4];
        sides[0] = new Line(upperLeft, upperRight);
        sides[1] = new Line(upperLeft, lowerLeft);
        sides[2] = new Line(lowerLeft, lowerRight);
        sides[3] = new Line(upperRight, lowerRight);

        return sides;
    }

}