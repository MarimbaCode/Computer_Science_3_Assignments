package net.thejrdev.assignments.cycle_3.mendelbrot;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.Scanner;

public class MandelbrotSet {

    protected final int width, height;
    protected final Picture picture;

    protected double x1, x2, y1, y2, maximumMagn;
    protected short maximumIters;
    protected short[][] map, set;
    protected double xStep, yStep;

    protected CalculationThreadManager manager;


    public static void main(String[] args) throws InterruptedException {
        MandelbrotSet mandelbrotSet = new MandelbrotSet(600,600, (short) 255, 2);

        mandelbrotSet.run();

    }
    public MandelbrotSet(int width, int height, short maximum, double maximumMagn) {
        this.width = width;
        this.height = height;
        this.maximumIters = maximum;
        this.maximumMagn = maximumMagn;

        picture = new Picture(width, height);

        x1 = -1.5;
        x2 = 0.5;
        y1 = -1;
        y2 = 1;

        map = new short[picture.height()][picture.width()];
        set = new short[picture.height()][picture.width()];
    }

    public void run() throws InterruptedException {

        xStep = (x2 - x1) / width;
        yStep = (y2 - y1) / height;

        manager = new CalculationThreadManager();

        InputManager inputManager = new InputManager();
        Thread inputThread = new Thread(inputManager);
        inputThread.start();
        Viewer viewer = new Viewer();
        viewer.run();
        Thread viewingThread = new Thread(new Viewer());
        viewingThread.start();

    }

    class CalculationThreadManager {
        public void run() throws InterruptedException {



            Thread[] threads = new Thread[32];

            for (int i = 0; i < threads.length; i++) {
                Thread thread = new Thread(new Calculator(i, threads.length));
                threads[i] = thread;
            }

            for (Thread th : threads) {
                th.start();
            }
            for (Thread th : threads) {
                th.join();
            }
        }
    }

    class Calculator implements Runnable{

        int bandNumber;
        int totalBands;

        public Calculator(int bandNumber, int totalBands) {
            this.bandNumber = bandNumber;
            this.totalBands = totalBands;
        }

        @Override
        public void run() {

            for (int i = 0; i < width; i++) {
                for (int j = height/totalBands * bandNumber; j < height/totalBands * (bandNumber + 1); j++) {
                    set[j][i] = calculate(new ComplexNumber(
                            x1 + (i * xStep),
                            y1 + (j * yStep)
                    ));
                }
            }
            map = set;
        }
    }

    class Viewer implements Runnable{

        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        Color color;
                        if(map[j][i] == 255){
                            color = Color.black;
                        }else {
                            color = Color.getHSBColor(map[j][i] / 255f + 0.5f, 1, 0.8f + ((float)Math.random())/5);
                        }
                        picture.set(i, j, color);
                    }
                }
                picture.show();

            }
        }
    }


    public short calculate(ComplexNumber number){

        ComplexNumber z = number;
        for (short t = 0; t < maximumIters; t++) {
            if (z.magnitude() > 2.0) return t;
            z = z.multiply(z).add(number);
        }

        return 255;
    }

    class InputManager implements Runnable{


        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                manager.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                double nx1 = scanner.nextDouble();
                double ny1 = scanner.nextDouble();
                double nx2 = scanner.nextDouble();
                double ny2 = scanner.nextDouble();
                System.out.println("-".repeat(12));

                double x1Step, x2Step, y1Step, y2Step;

                x1Step = (x1-nx1)/20.0;
                x2Step = (x2-nx2)/20.0;
                y1Step = (y1-ny1)/20.0;
                y2Step = (y2-ny2)/20.0;


                for (int i = 0; i < 20; i++) {
                    x1 -= x1Step;
                    x2 -= x2Step;
                    y1 -= y1Step;
                    y2 -= y2Step;
                    xStep = (x2 - x1) / width;
                    yStep = (y2 - y1) / height;

                    try {
                        manager.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }

}
