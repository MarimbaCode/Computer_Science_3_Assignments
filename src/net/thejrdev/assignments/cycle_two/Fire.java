package net.thejrdev.assignments.cycle_two;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class Fire {

    public static void main(String[] args) throws InterruptedException {

        int width = 1200;
        int height = 400;

        Picture p = new Picture(width, height);

        float[][] color = new float[height + 2][width + 2], sparks = new float[height + 2][width + 2];;
        Color[] pal = new Color[1000];

        for (int i = 0; i < pal.length; i++) {

            float prog = ((float)i)/pal.length;

            Color ypal = Color.getHSBColor(0.4f * prog/2, 1, prog);

            Color c = new Color((ypal.getRed()), (ypal.getGreen()) ,0);
            pal[i] = c;
        }


        for (int i = 0; i < color[0].length; i++) {
            color[color.length - 2][i] = (int) ((color[0].length/2) * (-Math.cos(Math.PI * ((float)i / color[0].length) * 2) + 1));
        }

        while(true){

            for (int i = 0; i < color[0].length; i++) {
                color[color.length - 1][i] = (int) (Math.random() * pal.length/4f) + 3 * pal.length/4f;
            }

//            for (int i = 0; i < color[0].length; i++) {
//                color[color.length - 1][i] = (int) (Math.random() * pal.length);
//            }
//            for (int i = 0; i < color[0].length; i++) {
//                color[color.length - 2][i] = (int) (Math.random() * pal.length);
//            }

            for (int i = 0; i < color.length-2; i++) {
                for (int j = 1; j < color[i].length-2; j++) {

                    double avg = color[i+1][j] * (0.3 * (Math.random()/4 +0.875) * (i/(color.length * 8f) + 0.93));
                    avg += color[i+1][j-1] * (0.2 * (1.0625-(float)i/(color.length * 8f))) + color[i+1][j+1] * (0.2 * (1.0625-i/(8f*color.length))) ;
                    avg += color[i+2][j] * 0.1;
                    avg += color[i][j] * 0.2;
                    int newavg = (int) avg;
                    if(newavg >= pal.length){
                        newavg = pal.length-2;
                    }else if(newavg < 0){
                        newavg = 0;
                    }


                    color[i][j] = newavg;
                    if(Math.random() > 0.999999 && i > 20 && i < color.length - 6){
                        sparks[i][j] = 6;
                        sparks[i-1][j] = 4;
                        sparks[i+1][j] = 8;
                        sparks[i][j-1] = 6;
                        sparks[i][j+1] = 6;
                    }
                }
            }
            for (int i = 0; i < color.length-2; i++) {
                for (int j = 1; j < color[i].length-2; j++) {
                    if(sparks[i][j] > 0) {
                        color[i][j] = pal.length-1;
                        sparks[i][j]--;
                        sparks[i-1][j] = sparks[i][j];
                    }

                }
            }





            for (int i = 0; i < p.height(); i++) {
                for (int j = 0; j < p.width(); j++) {
                    p.set(j, i, pal[(int)color[i][j]]);
                }
            }
            p.show();
            Thread.sleep(0);
        }











    }


}
