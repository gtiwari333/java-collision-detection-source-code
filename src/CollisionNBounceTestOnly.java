import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CollisionNBounceTestOnly extends JPanel {
	List<Ball> ballsL = new ArrayList<Ball>();
	private GameField gameField;
	private DrawCanvas canvas;
	private int canvasWidth;
	private int canvasHeight;

	public CollisionNBounceTestOnly(int width, int height) {
		canvasWidth = width;
		canvasHeight = height;
		// init balls
		ballsL.add(new Ball(100, 410, 25, 3, 34, Color.YELLOW));
		ballsL.add(new Ball(80, 350, 25, 2, -114, Color.YELLOW));
		ballsL.add(new Ball(530, 400, 30, 3, 14, Color.GREEN));
		ballsL.add(new Ball(400, 400, 30, 3, 14, Color.GREEN));
		ballsL.add(new Ball(400, 50, 35, 1, -47, Color.PINK));
		ballsL.add(new Ball(480, 320, 35, 4, 47, Color.PINK));
		ballsL.add(new Ball(80, 150, 40, 1, -114, Color.GRAY));
		ballsL.add(new Ball(100, 240, 40, 2, 60, Color.ORANGE));
		ballsL.add(new Ball(250, 380, 50, 3, -42, Color.BLUE));
		ballsL.add(new Ball(200, 80, 70, 6, -84, Color.CYAN));
		ballsL.add(new Ball(500, 170, 90, 6, -42, Color.BLUE));

		gameField = new GameField(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);

		canvas = new DrawCanvas();

		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);
		// Handling window resize. Adjust container box to fill the screen.
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();
				Dimension dim = c.getSize();
				gameField.set(0, 0, dim.width, dim.height);
			}
		});
		// Start the ball bouncing
		gameStart();
	}

	/** Start the ball bouncing. */
	public void gameStart() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					gameUpdate(false);
					repaint();
					// Delay and give other thread a chance
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		gameThread.start(); // Invoke GaemThread.run()
	}

	/**
	 * detects collision, bounces, calculate final velocities
	 * 
	 * @param isStriker
	 */
	public void gameUpdate(boolean isStriker) {
		// Check collision between the balls and the box
		for (int i = 0; i < ballsL.size(); i++) {
			PhysicsUtils.collisionWithWall(new Rectangle(gameField.minX, gameField.minY, gameField.maxX, gameField.maxY), ballsL.get(i));
		}
		// Check collision between two balls
		for (int i = 0; i < ballsL.size(); i++) {
			for (int j = 0; j < ballsL.size(); j++) {
				if (i < j) {
					PhysicsUtils.intersect(ballsL.get(i), ballsL.get(j));
				}
			}
		}
		// update positions increments
		for (int i = 0; i < ballsL.size(); i++) {
			ballsL.get(i).update(1);
		}
	}

	/** The custom drawing panel for the bouncing ball (inner class). */
	class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			// Draw the balls and field
			gameField.draw(g);
			for (Ball ball : ballsL) {
				ball.draw(g);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return (new Dimension(canvasWidth, canvasHeight));
		}
	}
}
