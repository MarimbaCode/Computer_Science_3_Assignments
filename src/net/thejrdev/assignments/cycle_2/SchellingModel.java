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


    public void init(double populationPercent, int agentTypes, double happinessReq){

        Agent.happinessReq = happinessReq;
        model = new Agent[100][100];

        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[0].length; j++) {
                if(Math.random() > (1-(populationPercent))){
                    model[i][j] = new Agent(j,i, (int)(Math.random() * agentTypes));
                }
            }
        }
        p = new Picture(400,400);


    }


    public void modelSeg() throws InterruptedException {
        while(true){
            Agent.moveAll();
            draw();
        }



    }

    public void draw(){

        for (int i = 0; i < p.height(); i++) {
            for (int j = 0; j < p.width(); j++) {
                Agent agent = model[i/4][j/4];
                if(agent == null){
                    p.set(j,i,Color.black);
                }else if(agent.type == 0){
                    p.set(j,i, Color.red);
                }else if(agent.type == 1){
                    p.set(j,i,Color.CYAN);
                }
            }
        }
        p.show();

    }


    static class Agent{

        static double happinessReq;
        static List<Agent> agents = new ArrayList<>();

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

        public void check(){
            Agent[] others = getOthers(this);
            double h = 0;
            for(Agent other: others){
                if(other.type == this.type){
                    h++;
                }
            }

            h /= 8;
            happy = h >= happinessReq;

        }

        public void move(){

            int[][] allMoves = findMoves(this);
            if(allMoves.length == 0){return;}
            int[] moves = allMoves[(int)(allMoves.length * Math.random())];
            if(moves != null){
                model[this.y][this.x] = null;
                model[moves[0]][moves[1]] = this;
                this.x = moves[1];
                this.y = moves[0];
            }

        }

        static void checkAll(){
            for (Agent a: agents){
                a.check();
            }
        }

        static void moveAll(){
            for(Agent agent: agents){
                agent.check();
                if(!agent.happy){
                    agent.move();
                }
            }
        }

        static Agent[] getOthers(Agent agent){

            List<Agent> others = new ArrayList<>();
            for(int[] offset: new int[][]{
                    {1,0},
                    {0,1},
                    {-1,0},
                    {0,-1},
                    {1,1},
                    {-1,1},
                    {-1,1},
                    {-1,-1}
            }){
                int offsetX = (agent.x + model[0].length + offset[1])%model[0].length;
                int offsetY = (agent.y + model.length + offset[0])%model.length;
                if(model[offsetY][offsetX] == null){continue;}
                others.add(model[offsetY][offsetX]);
            }
            return others.toArray(Agent[]::new);
        }

        static int[][] findMoves(Agent agent){
            List<int[]> moves = new ArrayList<>();
            for(int[] offset: new int[][]{
                    {1,0},
                    {0,1},
                    {-1,0},
                    {0,-1},
                    {1,1},
                    {-1,1},
                    {-1,1},
                    {-1,-1}
            }){
                int offsetX = (agent.x + model[0].length + offset[1])%model[0].length;
                int offsetY = (agent.y + model.length + offset[0])%model.length;

                if(model[offsetY][offsetX] == null){
                    moves.add(new int[]{offsetY,offsetX});
                }
            }
            return moves.toArray(int[][]::new);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        SchellingModel model = new SchellingModel();
        model.init(0.5, 2, 0.5);

        model.modelSeg();


    }
}
