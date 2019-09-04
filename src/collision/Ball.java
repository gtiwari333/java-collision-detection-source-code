package collision;

import java.awt.*;

class Ball {
    double x, y; // collision.Ball's center x and y
    double speedX, speedY; // collision.Ball's speed per step in x and y
    final double radius; // collision.Ball's radius
    private final Color color; // collision.Ball's color

    public Ball(double x, double y, double radius, double speed, double angleInDegree, Color color) {
        this.x = x;
        this.y = y;
        this.speedX = speed * Math.cos(Math.toRadians(angleInDegree));
        this.speedY = -speed * Math.sin(Math.toRadians(angleInDegree));
        this.radius = radius;
        this.color = color;
    }

    public void update(double incr) {
        this.x += this.speedX * incr;
        this.y += this.speedY * incr;
    }

    /**
     * Draw itself using the given graphics context.
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }

    /**
     * Return mass
     */
    public double getMass() {
        return 2 * radius * radius * radius / 1000f;
    }

}
