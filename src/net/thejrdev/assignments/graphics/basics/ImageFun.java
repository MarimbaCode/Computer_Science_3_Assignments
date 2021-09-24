package net.thejrdev.assignments.graphics.basics;

import edu.princeton.cs.algs4.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class ImageFun {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame();
        Canvas canvas = new CustomCanvas();
        frame.add(canvas);





        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
        int maxx = size.width - 334, maxy = size.height - 131;
        float x = 0, y = 0, mx = 1, my = 1;

        long start = System.currentTimeMillis();
        float offset = 0;

        while(true){
            start = System.currentTimeMillis();
            frame.setSize(295,223);
            canvas.repaint();

            float delay = 16.8f - (System.currentTimeMillis() - start) + offset;

            Thread.sleep((int)( delay < 0 ? 0 : delay));
            if(delay < 0){
                offset = delay;
            }
            x = (mx * 5f + x);
            y = (my * 5f + y);

            if(x < 0){
                mx = 1;
                x = 0;
            }
            if (x>maxx){
                mx = -1;
                x = maxx;
            }
            if (y<0){
                my = 1;
                y = 0;
            }
            if (y>maxy){
                my = -1;
                y = maxy;
            }

            frame.setLocation((int)x,(int)y);

        }

    }

    static class CustomCanvas extends Canvas{


        BufferedImage image, buffer;
        float offset = 0;

        public void prepImage(){

        }

        @Override
        public void paint(Graphics g) {
            buffer = new BufferedImage(295, 223, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g2 = buffer.getGraphics();

            try {
                image = ImageIO.read(new File("res/images/ninetails.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < image.getHeight(); j++) {
                for (int i = 0; i < image.getWidth(); i++) {
                    int rc = (image.getRGB(i,j)>>16) & 0xFF;
                    int gc = (image.getRGB(i,j)>>8) & 0xFF;
                    int bc = (image.getRGB(i,j)) & 0xFF;

                    float[] hsb = new float[3];
                    hsb = Color.RGBtoHSB(rc, gc, bc, hsb);

                    int c = Color.HSBtoRGB((hsb[0] + (offset + (j / 1000.0f) - (i / 1000.0f))), hsb[1], hsb[2]);

                    image.setRGB(i, j, c);
                }
            }
            offset += 0.02f;
            g2.drawImage(image, 0, 0, null);
            g.drawImage(buffer, 0,0, null);
        }

    }
}
