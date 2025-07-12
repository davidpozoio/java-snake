package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.function.Function;

import javax.swing.JComponent;

import input.KeyboardInput;
import snake.Apple;
import snake.Directions;
import snake.Snake;

public class Canvas extends JComponent {
    private int width;
    private int height;
    private Snake snake;
    private KeyboardInput input;
    private Apple apple;

    private Function<Void, Void> error;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        input = new KeyboardInput();
        snake = new Snake();
        apple = new Apple();

        apple.generatePosition();

        snake.addNode();
        snake.addNode();

        input.addEvent(KeyEvent.VK_UP, (e) -> {
            snake.goTo(Directions.UP);
            return null;
        });

        input.addEvent(KeyEvent.VK_DOWN, (e) -> {
            snake.goTo(Directions.DOWN);
            return null;
        });

        input.addEvent(KeyEvent.VK_LEFT, (e) -> {
            snake.goTo(Directions.LEFT);
            return null;
        });

        input.addEvent(KeyEvent.VK_RIGHT, (e) -> {
            snake.goTo(Directions.RIGHT);
            return null;
        });
    }

    public KeyboardInput getInput() {
        return this.input;
    }

    public void onError(Function<Void, Void> error) {
        this.error = error;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHints(hints);

        Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, this.width, this.height);

        graphics.setColor(Color.BLACK);
        graphics.fill(rectangle);

        this.snake.calculatePosition();

        if (this.snake.isColliding() && this.snake.getX() != 0 && this.snake.getY() != 0) {
            this.error.apply(null);
            return;
        }

        if (this.snake.getX() == this.apple.getX() && this.snake.getY() == this.apple.getY()
                && !this.apple.isDisabled()) {
            snake.addNode();
            apple.generatePosition();
        }

        if (this.snake.isOnTop(apple.getX(), apple.getY())) {
            apple.generatePosition();
            apple.setDisabled(true);
        } else {
            apple.setDisabled(false);
        }

        this.snake.draw(graphics);
        apple.draw(graphics);

    }

}
