package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;
public abstract class AbstractMaze implements Maze{
    private List<Obstacle> obstacleList = new ArrayList<>();
    private List<Position> spaceList = new ArrayList<>();

    public void setSpaceList(List<Position> spaceList2) {this.spaceList = spaceList2;}

    public List<Position> getSpace() {return spaceList;}

    public void setObstacleList(List<Obstacle> list) {
        this.obstacleList = list;
    }

    @Override
    public List<Obstacle> getObstacles() {return obstacleList;}

    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

    public abstract void generateObstacles();
}
