package za.co.wethinkcode.toyrobot.world;

import org.turtle.StdDraw;
import org.turtle.Turtle;
import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.awt.*;
import java.util.List;

public class TurtleWorld extends AbstractWorld {
    private static boolean turtleMode = false;
    private static Turtle turtleRobot;
    private final Turtle obstacle;
    private final Turtle border;
    public TurtleWorld(Maze maze) {
        super(maze);
        turtleMode=true;
        turtleRobot = new Turtle(0.0,0.0,90.0);
        Play.Display("Setting up turtle");
        this.turtleSetup();
        this.border = new Turtle(-100.0,200.0,90.0);
        Play.Display("Drawing borders");
        this.drawBorder();
        this.obstacle = new Turtle(0.0,0.0,90.0);
        Play.Display("Drawing obstacles");
        this.showObstacles();
        turtleRobot.forward(0);
    }
    public static boolean getTurtleMode(){
        return turtleMode;
    }

    public void getTurtleMode(Boolean on){
        turtleMode = on;
    }
    private void turtleSetup() {
        StdDraw.setXscale(-110, 110);
        StdDraw.setYscale(-210,210);
        turtleRobot.setSize(1);
        turtleRobot.show();
        turtleRobot.setColor(Color.blue);
    }
    public static void turtleMove(int steps){
        turtleRobot.setColor(Color.blue);
        turtleRobot.forward(steps);
    }
    public static void turtleReset() {
        turtleRobot.setColor(Color.white);
        turtleRobot.forward(0.0);
    }
    public static void turnTurtleRobot(boolean turnRight) {
        if (turnRight) {
            turtleRobot.right(90.0);
        } else {
            turtleRobot.left(90.0);
        }
    }
    public void showObstacles() {
        this.obstacle.show();
        this.obstacle.setSize(0.010);
        this.obstacle.setColor(Color.black);
        List<Obstacle> obstacleList = this.getObstacles();
        for (Obstacle obstacle : obstacleList) {
            int x = obstacle.getBottomLeftX();
            int y = obstacle.getBottomLeftY();
            this.obstacle.setPosition(x, y);
            for (int z = 0; z < 4; z++) {
                this.obstacle.forward(2.0);
                this.obstacle.right(90.0);
            }
        }
    }
    public void drawBorder() {
        this.border.show();
        this.border.setSize(0.010);
        this.border.setColor(Color.red);
        this.border.right(90.0);
        for (int i = 0; i < 2;i++) {
            this.border.forward(200.0);
            this.border.right(90.0);
            this.border.forward(400.0);
            this.border.right(90.0);

        }
    }
}
