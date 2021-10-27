package net.thejrdev.assignments.cycle_3.mendelbrot;

import edu.princeton.cs.algs4.Picture;

import java.util.ArrayList;
import java.util.List;

public class MandelbrotSet {

    protected final int width, height;
    protected final List<ComplexNumber> set = new ArrayList<>();

    protected final Picture picture;

    public MandelbrotSet(int width, int height) {
        this.width = width;
        this.height = height;

        picture = new Picture(width, height);
    }



    class MandelBrotSetGenerator implements Runnable{


        @Override
        public void run() {

        }
    }

    class MandelbrotSetViewer implements Runnable{

        @Override
        public void run() {

        }
    }

    class InputHandler implements Runnable{


        @Override
        public void run() {

        }
    }

}
