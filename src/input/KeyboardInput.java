package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class KeyboardInput extends KeyAdapter {
    private final Map<Integer, Function<KeyEvent, Void>> events;

    public KeyboardInput() {
        this.events = new HashMap<>();
    }

    public void addEvent(int key, Function<KeyEvent, Void> func) {
        this.events.put(key, func);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int key = e.getKeyCode();
        Function<KeyEvent, Void> event = this.events.get(e.getKeyCode());

        if (event != null) {
            event.apply((null));
            return;
        }
        System.out.println("Event associated to " + key + " key not found");
    }
}
