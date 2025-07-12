package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import environment.Consts;
import type.DrawComponent;

public class Apple implements DrawComponent {
    private final Node node;
    private boolean disabled = false;

    public Apple() {
        node = new Node();
        node.setColor(Color.RED);
    }

    public int getX() {
        return this.node.getX();
    }

    public int getY() {
        return this.node.getY();
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean state) {
        this.disabled = state;
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (!disabled)
            this.node.draw(graphics);
    }

    public void generatePosition() {
        Random random = new Random();

        int maxXFactor = (Consts.WIDTH - Consts.NODE_BOX_SIZE - 20) / Consts.NODE_BOX_SIZE;
        int maxYFactor = (Consts.HEIGHT - Consts.NODE_BOX_SIZE - 20) / Consts.NODE_BOX_SIZE;

        // choose random factor between 0 and maxXFactor
        int x = random.nextInt(maxXFactor + 1) * Consts.NODE_BOX_SIZE;
        int y = random.nextInt(maxYFactor + 1) * Consts.NODE_BOX_SIZE;
        System.out.println(x + " " + y);
        this.node.translate(x, y);
    }
}
