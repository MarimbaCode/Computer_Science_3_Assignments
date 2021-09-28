package net.thejrdev.assignments.graphics.basics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HSBPractice {

    public static void main(String[] args) throws InterruptedException {

        int width = 1000, height = 1000;

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                image.setRGB(i, j, Color.HSBtoRGB(i / ((float) image.getWidth()), 1f, 1f));

            }
        }

        ColorShiftCanvas canvas = new ColorShiftCanvas(image);

        JFrame frame = new JFrame();
        frame.add(canvas);
        frame.pack();

        frame.setVisible(true);

        frame.setSize(width, height);

        float a =  10000f/GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();

        long start;
        float offset = 0;
        while(true) {
            start = System.currentTimeMillis();
            canvas.repaint();

            float delay = a - (System.currentTimeMillis() - start) + offset;
            if (offset != 0) {
                offset = 0;
            }

            Thread.sleep((int) (delay < 0 ? 0 : delay));
            if (delay < 0) {
                offset = delay;
            }
        }

    }

    static class ColorShiftCanvas extends Canvas{

        BufferedImage image, buffer;
        float[][][] color;

        public ColorShiftCanvas(BufferedImage image){
            this.image = image;
            buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics g2 = buffer.getGraphics();
            g2.drawImage(image, 0, 0, null);
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {

                    int rgb = image.getRGB(i, j);
                    float[] hsb = new float[3];
                    hsb = Color.RGBtoHSB((rgb >> 16) & 0b11111111, (rgb >> 8) & 0b11111111, rgb & 0b11111111, hsb);

                    hsb[0] += 0.001f;

                    image.setRGB(i, j, Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));


                }
            }

            g.drawImage(buffer,0 ,0 , null);
        }
    }
}
