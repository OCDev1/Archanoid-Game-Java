package Geometry;

//Omri Cohen 318673407

/**
 * Class that represents a line.
 */
public class Line {
    //Threshold for double comparison
    private static final double EPSILON = 0.000001;

    private final Point start;
    private final Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x coordinate of start
     * @param y1 the y coordinate of start
     * @param x2 the x coordinate of end
     * @param y2 the y coordinate of end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculates the length of a line.
     *
     * @return length of a line.
     */
    public double length() {
        double dx = this.end().getX() - this.start().getX();
        double dy = this.end().getY() - this.start().getY();
        return Math.sqrt(dy * dy + dx * dx);
    }

    /**
     * calculates the middle point of a line.
     *
     * @return the middle point of a line
     */
    public Point middle() {
        double x1 = this.start().getX(), y1 = this.start().getY();
        double x2 = this.end().getX(), y2 = this.end().getY();
        double middleX = Math.abs(x2 + x1) / 2;
        double middleY = Math.abs(y2 + y1) / 2;

        return new Point(middleX, middleY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * calculates the slope of the line.
     *
     * @return the slope of the line
     */
    public double lineSlope() {
        //calculated using the formula for slope of a line
        return (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
    }

    /**
     * calculates the y intercept of the line.
     *
     * @return the y intercept of the line
     */
    public double yIntercept() {
        //calculated using the formula for y intercept of a line
        return (this.start.getY() - this.lineSlope() * this.start.getX());
    }

    /**
     * checks if our line and another line are parallel.
     *
     * @param other the other line
     * @return true if the lines are parallel, false otherwise
     */
    public boolean isParallel(Line other) {
        //check if they have the same slope
        if (Math.abs(this.lineSlope() - other.lineSlope()) <= EPSILON) {
            //check if they have the same y intercept
            //if they do then they are not parallel
                return (Math.abs(this.yIntercept() - other.yIntercept()) <= EPSILON);
        }
        //if they don't have the same slope they aren't parallel
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the given rectangle
     * @return return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStart(Rectangle rect) {
        java.util.List<Point> intersections = new java.util.ArrayList<>();

        // Get the four sides of the rectangle
        Line[] sides = rect.getSides();

        // Check for intersections with each side of the rectangle
        for (Line side : sides) {
            Point intersection = intersectionWith(side);

            // Add the intersection point to the list if it exists
            if (intersection != null) {
                intersections.add(intersection);
            }
        }

        // Find the closest intersection point to the start of the line
        double minDistance = Double.POSITIVE_INFINITY;
        Point closestIntersection = null;

        for (Point intersection : intersections) {
            double distance = distanceToPoint(intersection);

            if (distance < minDistance) {
                minDistance = distance;
                closestIntersection = intersection;
            }
        }

        return closestIntersection;
    }

    /**
     * checks if our line and another line intersect.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //if they are parallel they never intersect
        if (this.isParallel(other)) {
            return false;
        }
        Point p1 = this.start;
        Point p2 = this.end;
        Point p3 = other.start;
        Point p4 = other.end;

        double thisStartX = Math.min(p1.getX(), p2.getX());
        double thisEndX = Math.max(p1.getY(), p2.getY());

        //if the lines are coinciding partially or fully return true
        //if the lines have the same slope and same y intercept
        if ((Math.abs(this.lineSlope() - other.lineSlope()) <= EPSILON)
                && (Math.abs(this.yIntercept()
                - other.yIntercept()) <= EPSILON)) {
            //and if the line contains the start or end of the other line
            //then they coincide
            if (((other.start.getX() >= thisStartX)
                    && (other.start.getX() <= thisEndX))
                    || ((other.end.getX() >= thisStartX)
                    && (other.end.getX() <= thisEndX))) {
                return true;
            }
        }

        //the mathematical calculations to check if the 2 lines intersect
        double denominator = ((p4.getY() - p3.getY()) * (p2.getX() - p1.getX()))
                - ((p4.getX() - p3.getX()) * (p2.getY() - p1.getY()));
        if (Math.abs(denominator - 0) <= EPSILON) {
            return false;
        }

        double positionInFirstLine = (((p4.getX() - p3.getX()) * (p1.getY()
                - p3.getY()))
                - ((p4.getY() - p3.getY())
                * (p1.getX() - p3.getX()))) / denominator;

        double positionInSecondLine = (((p2.getX() - p1.getX()) * (p1.getY()
                - p3.getY()))
                - ((p2.getY() - p1.getY())
                * (p1.getX() - p3.getX()))) / denominator;

        //if both are between 0 and 1, then the intersection point is in
        //both line segments, which means the lines intersect.
        if (positionInFirstLine >= 0 && positionInFirstLine <= 1
                && positionInSecondLine >= 0 && positionInSecondLine <= 1) {
            return true;
        }
        return false;
    }

    /**
     * calculates the intersection point if the lines intersect.
     *
     * @param other the other line
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.isParallel(other)) {
            return null;
        }

        //checks if lines intersect at all and returns intersection point
        if (this.isIntersecting(other)) {
            double m1 = this.lineSlope(), m2 = other.lineSlope();
            double b1 = this.yIntercept(), b2 = other.yIntercept();
            double x;
            double y;

            Point p1 = this.start;
            Point p2 = this.end;

            double thisStartX = Math.min(p1.getX(), p2.getX());
            double thisEndX = Math.max(p1.getY(), p2.getY());

            //if the lines are coinciding partially or fully return null
            //if the lines have the same slope and same y intercept
            if ((Math.abs(this.lineSlope() - other.lineSlope()) <= EPSILON)
                    && (Math.abs(this.yIntercept()
                    - other.yIntercept()) <= EPSILON)) {
                //and if the line contains the start or end of the other line
                //then they coincide
                if (((other.start.getX() > thisStartX)
                        && (other.start.getX() < thisEndX))
                        || ((other.end.getX() > thisStartX)
                        && (other.end.getX() < thisEndX))) {
                    return null;
                }
            }

            if (Double.isInfinite(m1)) {
                // line 1 is parallel to y-axis
                x = this.start.getX();
                y = m2 * x + b2;
            } else if (Double.isInfinite(m2)) {
                // line 2 is parallel to y-axis
                x = other.start.getX();
                y = m1 * x + b1;
            } else if (Math.abs(m1 - 0) <= EPSILON) {
                // line 1 is parallel to x-axis
                y = b1;
                x = (y - b2) / m2;
            } else if (Math.abs(m2 - 0) <= EPSILON) {
                // line 2 is parallel to x-axis
                y = b2;
                x = (y - b1) / m1;
            } else {
                // neither line is parallel to x-axis or y-axis
                x = (b2 - b1) / (m1 - m2);
                y = m1 * x + b1;
            }
            return new Point(x, y);
        }
        //lines do not intersect
        return null;

    }

    /**
     * checks if the lines are equal.
     *
     * @param other the other line
     * @return return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //if they are not parallel they aren't the same.
        if (!this.isParallel(other)) {
            return false;
        }
        //if they're parallel we check if they start and end in the same points
        if ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end)
                && this.end.equals(other.start))) {
            return true;
        }
        //if they don't start and end in the same points they aren't the same
        return false;
    }

    /**
     * calculates the distance between the start point of the line
     * and a specified point.
     *
     * @param point the wanted point
     * @return distance from start of a line to a specific point
     */
    private double distanceToPoint(Point point) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = point.getX();
        double y2 = point.getY();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}