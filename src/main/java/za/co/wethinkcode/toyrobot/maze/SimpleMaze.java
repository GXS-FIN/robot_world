package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMaze extends AbstractMaze implements Maze{

    private final List<Obstacle> obstacleList = new ArrayList<>();

    public SimpleMaze(){
        this.generateObstacles();
        setObstacleList(this.obstacleList);
    }
    public List<Obstacle> getObstacleList(){
        return this.obstacleList;
    }

    @Override
    public void generateObstacles() {
        Obstacle obstacle = new SquareObstacle(1, 1);
        this.obstacleList.add(obstacle);

    }
}