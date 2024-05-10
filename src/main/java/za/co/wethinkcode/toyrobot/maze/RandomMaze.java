package za.co.wethinkcode.toyrobot.maze;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RandomMaze extends AbstractMaze implements Maze {
    private static final List<Obstacle> obstacleList = new ArrayList<>();
    Random rand = new Random();
    public RandomMaze(){
        this.generateObstacles();
        setObstacleList(obstacleList);
    }
    public static List<Obstacle> getObstacleList() {
        return obstacleList;
    }
    @Override
    public void generateObstacles() {
        for (int i=1; i<= 10; i++) {
            int randomX = (int) ((Math.random() * 200) - 100);
            int randomY = (int) ((Math.random() * 200) - 100);
            Obstacle obstacle = new SquareObstacle(randomX, randomY);
            if (!this.obstacleList.contains(obstacle)) {
                this.obstacleList.add(obstacle);
            }
        }
    }
}
