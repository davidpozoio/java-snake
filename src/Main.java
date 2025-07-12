
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import canvas.Canvas;
import environment.Consts;

public class Main {
    public static void main(String[] args) {

        Canvas canvas = new Canvas(Consts.WIDTH, Consts.HEIGHT);

        JFrame frame = new JFrame();

        frame.setSize(Consts.WIDTH, Consts.HEIGHT);
        frame.setTitle("Snake");

        frame.add(canvas);
        frame.addKeyListener(canvas.getInput());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(Consts.DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.repaint();
            }
        });

        canvas.onError((e) -> {
            timer.stop();
            return null;
        });

        timer.start();
    }
}