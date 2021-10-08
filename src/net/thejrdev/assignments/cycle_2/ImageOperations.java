package net.thejrdev.assignments.cycle_2;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class ImageOperations {

    public static void main(String[] args) {

        Picture picture = new Picture("res/images/sailcat.jpg");


        op1();
        op2();
        op3();
        op4();
        op5();
        op6();
        op7();
        op8();

        op9();
        op10();
        op11();
        op12();
        op13();
        op14();
        op15();
        op16();

    }

    public static void op1(){

        Picture picture = new Picture("res/images/sailcat.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                picture.setRGB(i,j, 0xFFFFFF - picture.getRGB(i,j));
            }
        }
        picture.show();
    }
    public static void op2(){

        Picture picture = new Picture("res/images/sailcat.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                int red = c.getRed() / 2;
                int green = c.getGreen() / 2;
                int blue = c.getBlue() / 2;

                red = Math.max(Math.min(red, 255),0);
                green = Math.max(Math.min(green, 255),0);
                blue = Math.max(Math.min(blue, 255),0);

                Color color = new Color(red,green,blue);
                picture.set(i,j, color);
            }
        }
        picture.show();
    }
    public static void op3(){

        Picture picture = new Picture("res/images/sailcat.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                int red = c.getRed() - 50;
                int green = c.getGreen()- 50;
                int blue = c.getBlue() - 50;

                red = Math.max(Math.min(red, 255),0);
                green = Math.max(Math.min(green, 255),0);
                blue = Math.max(Math.min(blue, 255),0);

                Color color = new Color(red,green,blue);
                picture.set(i,j, color);            }
        }
        picture.show();
    }
    public static void op4(){

        Picture picture = new Picture("res/images/sailcat.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                int red = c.getRed() * 2;
                int green = c.getGreen() * 2;
                int blue = c.getBlue() * 2;

                red = Math.max(Math.min(red, 255),0);
                green = Math.max(Math.min(green, 255),0);
                blue = Math.max(Math.min(blue, 255),0);

                Color color = new Color(red,green,blue);
                picture.set(i,j, color);
            }
        }
        picture.show();
    }
    public static void op5(){

        Picture picture = new Picture("res/images/sailcat.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                int red = c.getRed() + 50;
                int green = c.getGreen() + 50;
                int blue = c.getBlue() + 50;

                red = Math.max(Math.min(red, 255),0);
                green = Math.max(Math.min(green, 255),0);
                blue = Math.max(Math.min(blue, 255),0);

                Color color = new Color(red,green,blue);
                picture.set(i,j, color);
            }
        }
        picture.show();
    }
    public static void op6(){

        Picture picture = new Picture("res/images/sailcat.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                double red = c.getRed() * 0.2126 + c.getGreen() * 0.7152 + c.getBlue() * 0.0722;

                red = Math.max(Math.min(red, 255),0);

                Color color = new Color((int) red,(int) red,(int) red);
                picture.set(i,j, color);
            }
        }
        picture.show();
    }
    public static void op7(){

        Picture picture = new Picture("res/images/ninetails.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                picture.set(i,j, new Color(c.getRed(), c.getBlue(), c.getGreen()));
            }
        }
        picture.show();
    }
    public static void op8(){

        Picture picture = new Picture("res/images/ninetails.jpg");

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                Color c = picture.get(i,j);

                picture.set(i,j, new Color(c.getBlue(), c.getGreen(), c.getRed()));
            }
        }
        picture.show();
    }

    public static void op9(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = ntred + scred;
                int newGreen = ntgreen + scgreen;
                int newBlue = ntblue + scblue;

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op10(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = ntred - scred;
                int newGreen = ntgreen - scgreen;
                int newBlue = ntblue - scblue;

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op11(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = scred - ntred;
                int newGreen = scgreen - ntgreen;
                int newBlue = scblue - ntblue;

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op12(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = Math.abs(ntred - scred);
                int newGreen = Math.abs(ntgreen - scgreen);
                int newBlue = Math.abs(ntblue - scblue);

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op13(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = (int)(255 * (ntred/255.0 * scred/255.0));
                int newGreen = (int)(255 * (ntgreen/255.0 * scgreen/255.0));
                int newBlue = (int)(255 * (ntblue/255.0 * scblue/255.0));

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op14(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = (ntred + scred) / 2;
                int newGreen = (ntgreen + scgreen) / 2;
                int newBlue = (ntblue + scblue) / 2;

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op15(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = (int) ((ntred * i/268.0) + (scred * (268-i)/268.0));
                int newGreen = (int) ((ntgreen * i/268.0) + (scgreen * (268-i)/268.0));
                int newBlue = (int) ((ntblue * i/268.0) + (scblue * (268-i)/268.0));

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
    public static void op16(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = ntred ^ scred;
                int newGreen = ntgreen ^ scgreen;
                int newBlue = ntblue ^ scblue;

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }
}

/*
    public static void op#(){

        Picture nt = new Picture("res/images/ninetails.jpg");
        Picture sc = new Picture("res/images/sailcat.jpg");
        Picture out = new Picture(286, 183);

        for (int i = 0; i < 268; i++) {
            for (int j = 0; j < 183; j++) {


                Color ntc = nt.get(i,j);

                int ntred = ntc.getRed();
                int ntgreen = ntc.getGreen();
                int ntblue = ntc.getBlue();

                ntred = Math.max(Math.min(ntred, 255),0);
                ntgreen = Math.max(Math.min(ntgreen, 255),0);
                ntblue = Math.max(Math.min(ntblue, 255),0);

                Color scc = sc.get(i,j);

                int scred = scc.getRed();
                int scgreen = scc.getGreen();
                int scblue = scc.getBlue();

                scred = Math.max(Math.min(scred, 255),0);
                scgreen = Math.max(Math.min(scgreen, 255),0);
                scblue = Math.max(Math.min(scblue, 255),0);

                int newRed = ;
                int newGreen = ;
                int newBlue = ;

                newRed = Math.max(Math.min(newRed, 255),0);
                newGreen = Math.max(Math.min(newGreen, 255),0);
                newBlue = Math.max(Math.min(newBlue, 255),0);

                out.set(i,j, new Color(newRed, newGreen, newBlue));
            }
        }

        out.show();
    }



 */