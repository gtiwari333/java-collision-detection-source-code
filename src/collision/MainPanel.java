package collision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

class MainPanel extends JPanel {
    private final List<Ball> ballList = new ArrayList<>();
    private final Painter painter;
    private final int canvasWidth;
    private final int canvasHeight;
    private final int DRAWING_DELAY = 20;

    public MainPanel(int width, int height) {
        canvasWidth = width;
        canvasHeight = height;
        // init balls
        ballList.add(new Ball(100, 410, 25, 3, 34, Color.YELLOW));
        ballList.add(new Ball(80, 350, 25, 2, -114, Color.YELLOW));
        ballList.add(new Ball(530, 400, 30, 3, 14, Color.GREEN));
        ballList.add(new Ball(400, 400, 30, 3, 14, Color.GREEN));
        ballList.add(new Ball(400, 50, 35, 1, -47, Color.PINK));
        ballList.add(new Ball(480, 320, 35, 4, 47, Color.PINK));
        ballList.add(new Ball(80, 150, 40, 1, -114, Color.GRAY));
        ballList.add(new Ball(100, 240, 40, 2, 60, Color.ORANGE));
        ballList.add(new Ball(250, 380, 50, 3, -42, Color.BLUE));
        ballList.add(new Ball(200, 80, 70, 6, -84, Color.CYAN));
        ballList.add(new Ball(500, 170, 90, 6, -42, Color.BLUE));

        painter = new Painter(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);

        DrawCanvas canvas = new DrawCanvas();

        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        // Handling window resize. Adjust container box to fill the screen.
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                Dimension dim = c.getSize();
                painter.resize(0, 0, dim.width, dim.height);
            }
        });


    }

    /**
     * Start the ball bouncing.
     */
    public void startDrawing() {
        new Thread(() -> {

            while (true) {
                updatePositionAndDirection();
                repaint();

                try {
                    Thread.sleep(DRAWING_DELAY);
                } catch (InterruptedException ex) {
                }
            }

        }).start();

    }

    /**
     * detects collision, bounces, calculate final velocities
     */
    private void updatePositionAndDirection() {
        // Check collision between the balls and the box
        for (Ball aBallsL1 : ballList) {
            PhysicsUtils.collisionWithWall(new Rectangle(painter.minX, painter.minY, painter.maxX, painter.maxY), aBallsL1);
        }

        // Check collision between two balls
        for (int i = 0; i < ballList.size(); i++) {
            for (int j = 0; j < ballList.size(); j++) {
                if (i < j) {
                    PhysicsUtils.intersect(ballList.get(i), ballList.get(j));
                }
            }
        }

        // update positions increments
        for (Ball aBallsL : ballList) {
            aBallsL.update(1);
        }
    }

    /**
     * The custom drawing panel for the bouncing ball (inner class).
     */
    class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            // Draw the balls and field
            painter.draw(g);
            for (Ball ball : ballList) {
                ball.draw(g);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(canvasWidth, canvasHeight));
        }
    }
}
