package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import environment.Consts;
import type.DrawComponent;

public class Snake implements DrawComponent {
    private List<Node> nodes;
    private int speedX = 0;
    private int speedY = 0;
    private final int SPEED = Consts.SNAKE_SPEED;

    public Snake() {
        this.nodes = new ArrayList<>();
        this.nodes.add(new Node(0, 0));
        this.nodes.get(0).setColor(Color.BLUE);
    }

    public int getX() {
        return this.nodes.get(0).getX();
    }

    public int getY() {
        return this.nodes.get(0).getY();
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (Node node : this.nodes) {
            node.draw(graphics);
        }

        this.nodes.get(0).draw(graphics);
    }

    public void addNode() {
        Node finalNode = this.nodes.get(this.nodes.size() - 1);

        this.nodes.add(new Node(finalNode.getX(), finalNode.getY()));
    }

    public void translate(int x, int y) {
        this.nodes.get(0).translate(x, y);
    }

    public void calculatePosition() {
        Node headNode = this.nodes.get(0);

        int lastX = headNode.getX();
        int lastY = headNode.getY();

        headNode.translate(headNode.getX() + speedX, headNode.getY() + speedY);

        final int size = this.nodes.size();

        for (int i = 1; i < size; i++) {
            Node currentNode = this.nodes.get(i);
            int nextX = currentNode.getX();
            int nextY = currentNode.getY();

            currentNode.translate(lastX, lastY);

            lastX = nextX;
            lastY = nextY;
        }
    }

    public void goTo(Directions direction) {
        if (direction == Directions.UP && speedY != SPEED) {
            speedX = 0;
            speedY = -SPEED;

        } else if (direction == Directions.DOWN && speedY != -SPEED) {
            speedX = 0;
            speedY = SPEED;

        } else if (direction == Directions.LEFT && speedX != SPEED) {
            this.speedX = -SPEED;
            this.speedY = 0;

        } else if (direction == Directions.RIGHT && speedX != -SPEED) {
            this.speedX = SPEED;
            this.speedY = 0;
        }

    }

    public boolean isColliding() {
        return this.isExceedingLimits() || this.isCollidingWithBody();
    }

    private boolean isCollidingWithBody() {
        Node headNode = this.nodes.get(0);

        for (Node node : this.nodes) {
            if (node != headNode && headNode.getX() == node.getX() && headNode.getY() == node.getY()) {
                return true;
            }
        }

        return false;
    }

    private boolean isExceedingLimits() {
        Node heaNode = this.nodes.get(0);

        if (heaNode.getX() > Consts.WIDTH || heaNode.getX() < 0) {
            return true;
        }

        if (heaNode.getY() > Consts.HEIGHT || heaNode.getY() < 0) {
            return true;
        }

        return false;
    }

    /**
     * Check if something is over the body of the snake
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean isOnTop(int x, int y) {
        for (Node node : this.nodes) {
            if (node.getX() == x && node.getY() == y) {
                return true;
            }
        }

        return false;
    }
}
