package net.thejrdev.assignments.cycle_2;

import edu.princeton.cs.algs4.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static java.lang.Math.*;

public class RepulsiveMotion {

    public static void main(String[] args) throws InterruptedException {
        AgentManager manager = new AgentManager(73, 600, 600, 1);
        while(true) {
            manager.tick();
        }
    }

    static class AgentManager{
        protected int width, height;
        protected List<Agent> agents;
        protected boolean isTiled;
        protected Picture p;
        protected int scaleFactor;
        public AgentManager(int agentCount, int width, int height){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width - width;
            int screenHeight = screenSize.height - height;
            agents = new ArrayList<>();
            for (int i = 0; i < agentCount; i++) {
                agents.add(new Agent(Math.random() * screenWidth, Math.random() * screenHeight, width, height));
            }
            this.width = width;
            this.height = height;
            this.isTiled = false;

            Agent.screenWidth = screenSize.width - width;
            Agent.screenHeight = screenSize.height - height;

        }
        public AgentManager(int agentCount, int width, int height, int scaleFactor){

            Agent.screenWidth = width;
            Agent.screenHeight = height;
            Agent.agentCount = agentCount;

            agents = new ArrayList<>();
            for (int i = 0; i < agentCount; i++) {
                agents.add(new Agent(Math.random() * width, Math.random() * height, 10, 10));
            }

            this.scaleFactor = scaleFactor;
            this.width = width;
            this.height = height;
            this.isTiled = true;



            p = new Picture(width * (scaleFactor), height * (scaleFactor));



        }
        public void tick() throws InterruptedException {

            addForces();
            applyForces();
            if(isTiled) {
                showTile();
            }else{
                  showWindow();
            }
            Thread.sleep(1);

        }
        public void addForces(){

            for (int i = 0; i < agents.size(); i++) {
                for (int j = 0; j < agents.size(); j++) {
                    if(i != j) {
                        Agent.applyForces(agents.get(i), agents.get(j));
                    }
                }
            }
        }
        public void applyForces(){
            for (Agent a :
                    agents) {
                a.calculateVelocity();
            }
        }
        public void showWindow(){
            for(Agent a: agents){
                a.showWindow();
            }
        }
        public void showTile(){
            for (int i = 0; i < p.width(); i++) {
                for (int j = 0; j < p.height(); j++) {
                    if(i%width == 0 || j%height == 0){
                        p.setRGB(i, j, 0x440000);
                    }else {
                        p.setRGB(i, j, 0x000000);
                    }
                }
            }

            for (Agent a: agents) {
                for (int i = 0; i < scaleFactor; i++) {
                    for (int j = 0; j < scaleFactor; j++) {

                        for (int k = 0; k < 5; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(a.x < 0 || a.y < 0){
                                    continue;
                                }
                                p.setRGB(((int) a.x + k)%width + width * i, ((int) a.y + l)%height + height * j, 0xFFFFFF);
                            }
                        }
                    }
                }
            }
            p.show();
        }
    }

    static class Agent{
        protected static final double drag = 0.01;
        protected static int agentCount;
        protected static Integer screenWidth, screenHeight;
        protected double x, y;
        protected double vx, vy;
        protected double k;
        protected Queue<Force> forces;
        protected JFrame frame;
        protected BufferedImage image;
        public Agent(double x, double y, int width, int height){

            this.x = x;
            this.y = y;

            this.vx = 0;
            this.vy = 0;

            frame = new JFrame();
            try {
//              image = ImageIO.read(new File("res/images/ninetails.jpg"));
                image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            frame.setContentPane(label);
            frame.setSize(width,height);

            forces = new LinkedList<>();

            k = (screenWidth * screenHeight)/(100.0 * sqrt(agentCount));
        }
        public void showWindow(){

            frame.setLocation((int)(x), (int)(y));
            frame.setVisible(true);
        }
        public void calculateVelocity(){

            double fx = 0, fy = 0;

            while(!forces.isEmpty()){
                Force f = forces.poll();
                fx += f.x;
                fy += f.y;
            }

            vx *= 1 - drag;
            vy *= 1 - drag;

            vx += fx;
            vy += fy;

            x += vx;
            y += vy;

            while(y < 0){
                y += screenHeight;
            }
            if(y > screenHeight){
                y %= screenHeight;
            }
            while (x < 0){
                x += screenWidth;
            }
            if(x > screenWidth){
                x %= screenWidth;
            }


        }
        public void addForce(Force f){
            forces.add(f);
        }
        public static void applyForces(Agent a1, Agent a2) {

            double fx = 0, fy = 0;

            for(int offsetX: new int[]{-screenWidth, 0, screenWidth}) {
                for(int offsetY: new int[]{-screenHeight, 0, screenHeight}) {
                    double x1, y1, x2, y2, dx, dy, dist, theta, force;

                    x1 = a1.x;
                    x2 = a2.x + offsetX;

                    y1 = a1.y;
                    y2 = a2.y + offsetY;

                    dist = Math.sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));

                    force = a1.k / pow(1.5 * dist, 2);
                    theta = atan2(y1 - y2, x1 - x2);

                    fx += force * cos(theta);
                    fy += force * sin(theta);

                }
            }

            a1.addForce(new Force(fx, fy));

        }
    }
    record Force(double x, double y){}

}
