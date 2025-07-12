package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardInput extends KeyAdapter {
    private final Map<Integer, EventController> events;

    public KeyboardInput() {
        this.events = new HashMap<>();
    }

    public void addEvent(int key, EventController func) {
        this.events.put(key, func);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int key = e.getKeyCode();
        EventController event = this.events.get(e.getKeyCode());

        if (event != null) {
            event.event(e);
            return;
        }
        System.out.println("Event associated to " + key + " key not found");
    }
}
