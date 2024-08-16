package Geometry;

//Omri Cohen 318673407

/**
 * Velocity specifies the change in position on the `x` and the `y` axis.
 */
public class Velocity {
    private final double dX;
    private final double dY;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the change in position on the `x` axis
     * @param dy the change in position on the `y` axis
     */
    public Velocity(double dx, double dy) {
        this.dX = dx;
        this.dY = dy;
    }


    /**
     * Gets velocity dx.
     *
     * @return the velocity dx
     */
    public double getVelocityDx() {
        return this.dX;
    }

    /**
     * Get velocity dy double.
     *
     * @return the double
     */
    public double getVelocityDy() {
        return this.dY;
    }

    /**
     * Apply velocity to given point.
     *
     * @param p the point
     * @return the new point with velocity calculations applied to it
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dX;
        double newY = p.getY() + this.dY;
        return new Point(newX, newY);
    }

    /**
     * applies velocity via angle and speed.
     *
     * @param angle the wanted angle
     * @param speed the wanted speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians
        double angleInRadians = Math.toRadians(angle);

        // Calculate dx and dy using trigonometry
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);

        // Return new Velocity
        return new Velocity(dx, dy);
    }
}