import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

public class GameField {
	int minX, maxX, minY, maxY; 
	private Color colorFilled;
	private Color colorBorder;
	private static final Color DEFAULT_COLOR_FILLED = Color.BLACK;
	private static final Color DEFAULT_COLOR_BORDER = Color.YELLOW;
	public static final int goalPostRadius = 100;

	public GameField(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
		this.colorFilled = colorFilled;
		this.colorBorder = colorBorder;
	}

	public GameField(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_COLOR_FILLED, DEFAULT_COLOR_BORDER);
	}

	public void set(int x, int y, int width, int height) {
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
	}

	public void draw(Graphics g) {
		g.setColor(colorFilled);
		g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
	}

}
