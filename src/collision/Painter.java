package collision;

import java.awt.*;

class Painter {
    int minX, maxX, minY, maxY;
    private final Color colorFilled;
    private final Color colorBorder;
    private static final Color DEFAULT_COLOR_FILLED = Color.BLACK;
    private static final Color DEFAULT_COLOR_BORDER = Color.YELLOW;

    public Painter(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
        this.colorFilled = colorFilled;
        this.colorBorder = colorBorder;
    }

    public Painter(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_COLOR_FILLED, DEFAULT_COLOR_BORDER);
    }

    public void resize(int x, int y, int width, int height) {
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
