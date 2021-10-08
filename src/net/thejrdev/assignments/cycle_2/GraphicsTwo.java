package net.thejrdev.assignments.cycle_2;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class GraphicsTwo {

    public static void main(String[] args) {

        Picture p = new Picture("res/images/sailcat.jpg");
        for (int l = 0; l < 10000000; l++) {
            for (int i = 0; i < p.width(); i++) {
                for (int j = 0; j < p.height(); j++) {
                    p.set(i, j, new Color(0xFFFFFF - p.getRGB(i, j)));
                }
            }
            p.show();
        }


    }
}
