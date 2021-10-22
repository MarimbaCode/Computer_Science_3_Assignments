package net.thejrdev.assignments.cycle_2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFun {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame();
        CustomCanvas canvas = new CustomCanvas();
//        frame.add(canvas);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("res/images/ninetails.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setContentPane(new JLabel(new ImageIcon(image)));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        float a =  1000f/GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        int maxx = size.width - image.getWidth(), maxy = size.height - image.getHeight();
        float x = 0, y = 0, mx = 1, my = 1;



        while(true) {
            frame.setSize(295, 223);

            offsetImage(image);
            frame.repaint();

            x = (mx * 5f + x);
            y = (my * 5f + y);

            if (x < 0) {
                mx = 1;
                x = 0;
            }
            if (x > maxx) {
                mx = -1;
                x = maxx;
            }
            if (y < 0) {
                my = 1;
                y = 0;
            }
            if (y > maxy) {
                my = -1;
                y = maxy;
            }
            frame.setLocation((int) x, (int) y);

        }

    }

    public static void prepareImage(BufferedImage image){
        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                int rc = (image.getRGB(i,j)>>16) & 0xFF;
                int gc = (image.getRGB(i,j)>>8) & 0xFF;
                int bc = (image.getRGB(i,j)) & 0xFF;

                float[] hsb = new float[3];
                hsb = Color.RGBtoHSB(rc, gc, bc, hsb);
                int c = Color.HSBtoRGB((hsb[0] + ((float)i)/image.getWidth()) - ((float)j)/image.getHeight(), hsb[1] * 1.3f, hsb[2]);

                image.setRGB(i, j, c);
            }
        }
    }

    public static void offsetImage(BufferedImage image){

        for (int j = 10; j < image.getHeight()- 10; j++) {
            for (int i = 25; i < image.getWidth() - 25; i++) {
                int rc = (image.getRGB(i,j)>>16) & 0xFF;
                int gc = (image.getRGB(i,j)>>8) & 0xFF;
                int bc = (image.getRGB(i,j)) & 0xFF;

                float[] hsb = new float[3];
                hsb = Color.RGBtoHSB(rc, gc, bc, hsb);

                int c = Color.HSBtoRGB((hsb[0] + 0.0001f), hsb[1], hsb[2]);

                image.setRGB(i, j, c);
            }
        }

    }

    static class CustomCanvas extends Canvas{


        BufferedImage image, buffer, og;
        Graphics g2;
        float offset = 0;

        public CustomCanvas(){
            try {
                image = ImageIO.read(new File("res/images/ninetails.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                og = ImageIO.read(new File("res/images/ninetails.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer = new BufferedImage(295, 223, BufferedImage.TYPE_4BYTE_ABGR);
            g2 = buffer.getGraphics();
            for (int j = 0; j < og.getHeight(); j++) {
                for (int i = 0; i < og.getWidth(); i++) {
                    int rc = (og.getRGB(i,j)>>16) & 0xFF;
                    int gc = (og.getRGB(i,j)>>8) & 0xFF;
                    int bc = (og.getRGB(i,j)) & 0xFF;

                    float[] hsb = new float[3];
                    hsb = Color.RGBtoHSB(rc, gc, bc, hsb);

                    int c = Color.HSBtoRGB((hsb[0] + ((float)i)/og.getWidth()) - ((float)j)/og.getHeight(), hsb[1] * 1.3f, hsb[2]);

                    og.setRGB(i, j, c);
                }
            }
        }

        public void prepImage(){
            for (int j = 10; j < image.getHeight()- 10; j++) {
                for (int i = 25; i < image.getWidth() - 25; i++) {
                    int rc = (og.getRGB(i,j)>>16) & 0xFF;
                    int gc = (og.getRGB(i,j)>>8) & 0xFF;
                    int bc = (og.getRGB(i,j)) & 0xFF;

                    float[] hsb = new float[3];
                    hsb = Color.RGBtoHSB(rc, gc, bc, hsb);

                    int c = Color.HSBtoRGB((hsb[0] + offset), hsb[1], hsb[2]);

                    image.setRGB(i, j, c);
                }
            }
            offset += 0.03f;
            g2.drawImage(image, 0, 0, null);
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(buffer, 0,0, null);
        }

    }
}
