package net.thejrdev.assignments.cycle_2;

import edu.princeton.cs.algs4.Picture;


import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SchellingModel {

    static Agent[][] model;
    static Picture p;
    static int scale;

    public void init(double populationPercent, int agentTypes, double tolerance, double happinessReq, int width, int height, int scale) {

        Agent.happinessReq = happinessReq;
        Agent.typeTotal = agentTypes;
        model = new Agent[width][height];
        SchellingModel.scale = scale;
        Agent.tolerance = tolerance;

        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[0].length; j++) {
                if (Math.random() > (1 - (populationPercent))) {
                    model[i][j] = new Agent(j, i, (int) (Math.random() * agentTypes));
                } else {
                    Agent.open.add(new int[]{j, i});
                }
            }
        }
        p = new Picture(width * scale, height * scale);


    }


    public void modelSeg() throws InterruptedException {
        draw();
        Thread.sleep(2000);
        while (true) {
            Agent.moveAll();
            draw();
        }


    }

    public void draw() {

        for (int i = 0; i < p.height(); i++) {
            for (int j = 0; j < p.width(); j++) {
                Agent agent = model[i/scale][j/scale];
                if (agent == null) {
                    p.set(j, i, Color.black);
                }else {
                    p.set(j, i, Color.getHSBColor(agent.type / (float) Agent.typeTotal, 1, agent.happy ? 1:0.5f));
                }

            }
        }
        p.show();

    }


    static class Agent {

        static double happinessReq;
        static int typeTotal;
        static double tolerance;
        static List<Agent> agents = new ArrayList<>();
        static List<int[]> open = new ArrayList<>();

        int x;
        int y;

        int type;
        boolean happy;


        public Agent(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
            agents.add(this);
        }

        public void check() {
            Agent[] others = getOthers(this);
            double h = 0;
            for (Agent other : others) {

                int diff = other.type - this.type;

                if (Math.abs(diff) <= typeTotal*tolerance || diff >= typeTotal - (typeTotal*tolerance)) {
                    h++;
                }
            }

            h /= others.length;
            happy = h >= happinessReq;

        }

        public void move() {

            int[] moves = open.get((int) (Math.random() * open.size()));
            model[this.y][this.x] = null;
            model[moves[1]][moves[0]] = this;
            open.add(new int[]{this.x, this.y});
            this.x = moves[0];
            this.y = moves[1];
            open.remove(moves);


        }

        static void checkAll() {
            for (Agent a : agents) {
                a.check();
            }
        }

        static void moveAll() {
            Collections.shuffle(open);
            for (Agent agent : agents) {
                agent.check();
                if (!agent.happy) {
                    agent.move();
                }
            }
        }

        static Agent[] getOthers(Agent agent) {

            List<Agent> others = new ArrayList<>();
            for (int[] offset : new int[][]{
                    {1, 0},
                    {0, 1},
                    {-1, 0},
                    {0, -1},
                    {1, 1},
                    {-1, 1},
                    {-1, 1},
                    {-1, -1}
            }) {
                int offsetX = (agent.x + model[0].length + offset[1]) % model[0].length;
                int offsetY = (agent.y + model.length + offset[0]) % model.length;
                if (model[offsetY][offsetX] == null) {
                    continue;
                }
                others.add(model[offsetY][offsetX]);
            }
            return others.toArray(Agent[]::new);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SchellingModel model = new SchellingModel();
        model.init(0.9, 3, 0,0.5, 200,200, 3);

        model.modelSeg();


    }
}
/*

Cool settings
Agents: 100
Tolerance: 0.1
Size: 200x200


 */