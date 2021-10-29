package net.thejrdev.assignments.cycle_3.mendelbrot;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.Scanner;

public class MandelbrotSet {

    protected final int width, height;
    protected final Picture picture;

    protected double x1, x2, y1, y2, maximumMagn;
    protected byte maximumIters;
    protected byte[][] map;


    public static void main(String[] args) throws InterruptedException {
        MandelbrotSet mandelbrotSet = new MandelbrotSet(600,600, Byte.MAX_VALUE, 10);

        mandelbrotSet.run();

    }
    public MandelbrotSet(int width, int height, byte maximum, double maximumMagn) {
        this.width = width;
        this.height = height;
        this.maximumIters = maximum;
        this.maximumMagn = maximumMagn;

        picture = new Picture(width, height);

        x1 = -3;
        x2 = 1;
        y1 = -2;
        y2 = 2;

        map = new byte[picture.height()][picture.width()];
    }

    public void run() throws InterruptedException {

        MandelbrotSetCalculator calculator = new MandelbrotSetCalculator();
        MandelbrotSetViewer viewer = new MandelbrotSetViewer();
        InputHandler inputHandler = new InputHandler();

        Thread inputThread = new Thread(inputHandler);
        inputThread.start();

        Thread viewingThread = new Thread(viewer);
        viewingThread.start();

        while(true){
            calculator.calculate(x1, y1, x2, y2);
        }



    }

    class MandelbrotSetCalculator {

        byte[][] set;

        public void calculate(double x1, double y1, double x2, double y2) throws InterruptedException {

            set = new byte[height][width];

            Thread[] threads = new Thread[1];

            double diffY = y2 - y1;

            for (int i = 0; i < threads.length; i++) {

                threads[i] = new Thread(
                        new MandelbrotSetGenerator(
                                x1,
                                y1 + (diffY/threads.length) * i,
                                x2,
                                y1 + (diffY/threads.length) * (i+1),
                                i,
                                threads.length
                        )
                );
            }

            for (Thread t :
                    threads) {
                t.start();
            }
            for (Thread t: threads){
                t.join();
            }
            map = set;
        }

        class MandelbrotSetGenerator implements Runnable{

            double x1, y1, x2, y2, totalIter;
            int pos;

            public MandelbrotSetGenerator(double x1, double y1, double x2, double y2, int pos, int totalIter) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;


                this.pos = pos;
                this.totalIter = totalIter;
            }

            @Override
            public void run() {
                double xStep = (x2 - x1)/width;
                double yStep = (y2 - y1)/(height * totalIter);

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height/totalIter; j++) {
                        set[j + (int)((pos*height) / totalIter )][i] = calculate(new ComplexNumber(
                                x1 + (xStep * i),
                                y1 + (yStep * j)
                        ));
                    }
                }

            }

            private byte calculate(ComplexNumber number){
                ComplexNumber og = number;
                byte iterations = 0;
                while(number.magnitude() < maximumMagn && iterations < maximumIters){
                    number = number.square().add(og);
                    iterations++;
                }
                return iterations;
            }
        }
    }



    class MandelbrotSetViewer implements Runnable{


        @Override
        public void run() {

            while (true) {
                for (int i = 0; i < picture.width(); i++) {
                    for (int j = 0; j < picture.height(); j++) {
                        picture.set(i, j, Color.getHSBColor(1, 0, 1-map[j][i]/127f));
                    }
                }
                picture.show();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class InputHandler implements Runnable{


        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while(true){
                x1 = scanner.nextDouble();
                y1 = scanner.nextDouble();
                x2 = scanner.nextDouble();
                y2 = scanner.nextDouble();
                System.out.println("--------");
            }
        }
    }

}
