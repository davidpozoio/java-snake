package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import environment.Consts;
import type.DrawComponent;

public class Node implements DrawComponent {
    private int x;
    private int y;
    private Color color;
    private final int BOX_SIZE = Consts.NODE_BOX_SIZE;

    public Node() {
        this.x = 0;
        this.y = 0;
        this.color = Color.WHITE;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D graphics) {

        Rectangle2D.Double node = new Rectangle2D.Double(this.x, this.y, this.BOX_SIZE, this.BOX_SIZE);

        graphics.setColor(this.color);
        graphics.draw(node);

    }

    public void translate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
