package javagames.util;

import java.awt.*;

public class SimpleFrameWorkTemplate extends SimpleFramework {
    public SimpleFrameWorkTemplate() {
        appBackground = Color.WHITE;
        appBorder = Color.LIGHT_GRAY;
        appFont = new Font("Courier New", Font.PLAIN, 14);
        appBorderScale = 0.9f;
        appFPSColor = Color.BLACK;
        appWidth = 640;
        appHeight = 480;
        appMaintainRatio = true;
        appSleep = 10L;
        appTitle = "Framework Template";
        appWorldWidth = 2.0f;
        appWorldHeight = 2.0f;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void processInput(float delta) {
        super.processInput(delta);
    }

    @Override
    protected void updateObjects(float delta) {
        super.updateObjects(delta);
    }

    @Override
    protected void render(Graphics g) {
        super.render(g);
    }

    @Override
    protected void terminate() {
        super.terminate();
    }

    public static void main(String[] args) {
        launchApp(new SimpleFrameWorkTemplate());
    }
}
