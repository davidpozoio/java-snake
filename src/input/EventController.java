package input;

import java.awt.event.KeyEvent;

@FunctionalInterface
public interface EventController {
    void event(KeyEvent event);
}