package net.thejrdev.assignments.cycle_2;

import edu.princeton.cs.algs4.Picture;


import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SchellingModel {

    private static final int[][] offsets = new int[][]{
            {1,0},
            {-1,0},
            {0,-1},
            {0,1}
    };

    int width;
    int height;

    double population;
    int populationTypeCount;
    double tolerance;
    double happinessTolerance;

    int[][] model;
    Color[] pal;

    Picture picture;

    public SchellingModel(int width, int height, double population, int populationTypeCount, int tolerance, double happinessTolerance) {
        this.width = width;
        this.height = height;
        this.population = population;
        this.populationTypeCount = ++populationTypeCount;
        this.tolerance = tolerance;
        this.happinessTolerance = happinessTolerance;

        model = new int[height][width];

        pal = new Color[populationTypeCount];

        float step = 1f/(populationTypeCount-1);
        pal[0] = Color.black;
        for (int i = 1; i < populationTypeCount; i++) {
            pal[i] = Color.getHSBColor((i-1) * step, 1, 1);
        }
    }

    public void model() throws InterruptedException {


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((Math.random() * 100) <= population) {
                    int person = (int) (Math.random() * (populationTypeCount-1))+1;
                    model[j][i] = person;
                }
            }
        }


        picture = new Picture(width, height);

        draw();
        picture.show();

        while (true) {

            tick();
            draw();
            picture.show();
//            Thread.sleep(200);
        }


    }

    protected void tick(){

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int happiness = 0;

                int positionX = i + width;
                int positionY = j + height;

                List<int[]> emptySpot = new ArrayList<>();

                for(int k = 0; k < offsets.length; k++){
                    int[] offset = offsets[k];

                    int other = model[(positionY + offset[0])%height][(positionX + offset[1])%width];
                    if(other < 1){
                        emptySpot.add(offset);
                    }else if(Math.abs(other - model[j][i]) <= tolerance){
                        happiness++;
                    }
                }

                if(happiness < happinessTolerance && !emptySpot.isEmpty()){
                    Collections.shuffle(emptySpot);
                    int[] offset = emptySpot.get(0);
                    model[(positionY + offset[0])%height][(positionX + offset[1])%width] = model[j][i];
                    model[j][i] = 0;

                }
            }
        }


    }

    protected void draw(){

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                picture.set(i, j, pal[model[j][i]]);
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {

        SchellingModel model = new SchellingModel(300, 300, 50, 2, 0, 2);

        model.model();




    }
}
