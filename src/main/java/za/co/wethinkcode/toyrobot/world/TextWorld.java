package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

public class TextWorld extends AbstractWorld{
    public TextWorld(Maze maze) {
        super(maze);
        this.showObstacles();
    }
    @Override
    public void showObstacles() {
        List<Obstacle> obstacleList = this.getObstacles();
        Play.Display("There are some obstacles:");
        for (Obstacle obs : obstacleList) {
            int x = obs.getBottomLeftX();
            int y = obs.getBottomLeftY();
            int size = obs.getSize() - 1;
            Play.Display("- At position " + x + "," + y + " (to " + (x + size) + "," + (y + size) + ")");
        }
    }
}
