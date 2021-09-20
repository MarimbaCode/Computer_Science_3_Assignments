package net.thejrdev.assignments.graphics.basics;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Picture;

import javax.swing.*;
import java.awt.*;

public class GraphicsOne {

    public static void main(String[] args) {

        Picture p = new Picture(700, 444);



//        JFrame frame = new JFrame();
//        frame.setSize(512, 384);
//        frame.setVisible(true);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int k = 0; k < 1e9; k++) {

            for (int i = 0; i < p.width(); i++) {
                for (int j = 0; j < p.height(); j++) {
                    p.set(i, j, new Color(0, 0, (i * 256)/p.width()));
                }
            }
            p.show();
        }

    }

}
