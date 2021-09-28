package net.thejrdev.assignments.graphics.basics;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class Fire {

    public static void main(String[] args) throws InterruptedException {

        int width = 500;
        int height = 200;

        Picture p = new Picture(width, height);

        float[][] color = new float[height + 2][width + 2];
        Color[] pal = new Color[10000];

        for (int i = 0; i < pal.length; i++) {

            float prog = ((float)i)/pal.length;

            int b = (int)(255/Math.pow(i, i));
            Color ypal = Color.getHSBColor(0.333f * prog/2, 1, prog);

            Color c = new Color((ypal.getRed()), (ypal.getGreen()) ,0);
            pal[i] = c;
        }
        Picture p2 = new Picture(pal.length, 100);
        for (int i = 0; i < p2.width(); i++) {
            for (int j = 0; j < p2.height(); j++) {
                p2.set(i,j, pal[i]);
            }
        }
        p2.show();


        while(true){

//            for (int i = 0; i < color[0].length; i++) {
//                color[color.length - 1][i] = (int) (Math.random() * pal.length/4) + 3 * pal.length/4;
//            }            for (int i = 0; i < color[0].length; i++) {
//                color[color.length - 2][i] = (int) (Math.random() * pal.length/4) + 3 * pal.length/4;
//            }

            for (int i = 0; i < color[0].length; i++) {
                color[color.length - 1][i] = (int) (Math.random() * pal.length);
            }            for (int i = 0; i < color[0].length; i++) {
                color[color.length - 2][i] = (int) (Math.random() * pal.length);
            }

            for (int i = 0; i < color.length-2; i++) {
                for (int j = 1; j < color[i].length-2; j++) {

                    double avg = color[i+1][j] * 0.48;
                    avg += color[i+1][j-1]* (0.2) + color[i+1][j+1] * (0.2) ;
                    avg += color[i+2][j] * 0.1;
                    int newavg = (int) avg;
                    if(newavg >= pal.length){
                        newavg = pal.length-2;
                    }else if(newavg < 0){
                        newavg = 0;
                    }
                    color[i][j] = newavg;
                }
            }






            for (int i = 0; i < p.height(); i++) {
                for (int j = 0; j < p.width(); j++) {
                    p.set(j, i, pal[(int)color[i][j]]);
                }
            }
            p.show();
            Thread.sleep(10);
        }











    }


}
