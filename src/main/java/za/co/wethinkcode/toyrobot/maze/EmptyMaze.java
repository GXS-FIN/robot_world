package za.co.wethinkcode.toyrobot.maze;


import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class EmptyMaze extends AbstractMaze{

    private final List<Obstacle> obstaclesList = new ArrayList<>();

    public EmptyMaze() {
        this.generateObstacles();
        setObstacleList(this.obstaclesList);
    }

    @Override
    public void generateObstacles() {
        this.obstaclesList.clear();
    }
}
