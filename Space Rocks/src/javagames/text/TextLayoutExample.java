package javagames.text;

import javagames.util.SimpleFramework;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextLayoutExample extends SimpleFramework {
    private Font font;
    private int maxWidth;

    public TextLayoutExample() {
        appWidth = 640;
        appHeight = 640;
        appSleep = 10L;
        appTitle = "Text Layout Example";
        appBackground = Color.WHITE;
        appFPSColor = Color.BLACK;
    }

    @Override
    protected void initialize() {
        super.initialize();

        font = new Font("Arial", Font.BOLD, 40);
        FontMetrics fm = getFontMetrics(font);
        maxWidth = Integer.MIN_VALUE;
        for (int i = (int) '!'; i < (int) 'z'; ++i) {
            String letter = " " + (char) i;
            maxWidth = Math.max(maxWidth, fm.stringWidth(letter));
        }
        // another way
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        FontMetrics fontMetrics = g.getFontMetrics(font);
        g.dispose();
    }

    @Override
    protected void render(Graphics g) {
        super.render(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(Color.GREEN);
        g2D.setFont(font);

        FontMetrics fm = g2D.getFontMetrics();
        int height = fm.getAscent() + fm.getDescent() + fm.getLeading();
        int x = 20;
        int y = 50;

        y += fm.getAscent();
        int count = 0;
        for (int i = (int) '!'; i <= (int) 'z'; ++i) {
            String letter = " " + (char) i;
            g2D.drawString(letter, x, y);
            x += maxWidth;
            count++;
            if (count % 10 == 0) {
                y += height;
                x = 20;
            }
        }
    }

    public static void main(String[] args) {
        launchApp(new TextLayoutExample());
    }
}
