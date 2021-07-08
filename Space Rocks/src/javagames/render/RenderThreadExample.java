package javagames.render;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RenderThreadExample extends JFrame implements Runnable {
    private volatile boolean running;
    private Thread gameThread;

    public RenderThreadExample() {

    }

    protected void createAndShowGUI() {
        setSize(320, 240);
        setTitle("Render Thread");
        setVisible(true);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        running = true;
        while(running) {
            System.out.println("Game Loop");
            sleep(10);
        }
    }

    private void sleep(long sleep) {
        try{
            Thread.sleep(sleep);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    protected void onWindowClosing() {
        try {
            System.out.println("Stopping Thread...");
            running = false;
            gameThread.join();
            System.out.println("Stopped!");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        final RenderThreadExample app = new RenderThreadExample();
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);     //???????
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.createAndShowGUI();
            }
        });
    }
}
