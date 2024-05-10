package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

public abstract class AbstractWorld implements IWorld{
    private final Maze maze;
    protected Position currentPosition;
    protected Direction currentDirection;
    protected List<Obstacle> obstacles;
    protected UpdateResponse status = getStatus();

    private final Position TOP_LEFT = new Position(-200,200);
    private final Position BOTTOM_RIGHT = new Position(100,-200);

    public AbstractWorld(Maze maze) {

        this.maze = maze;
        this.currentPosition = IWorld.CENTRE;
        this.currentDirection = Direction.UP;
        this.obstacles = maze.getObstacles();
        reset();
    }
    public UpdateResponse getStatus() {
        return status;
    }

    @Override
    public Position getPosition() {
        return currentPosition;
    }

    @Override
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public abstract void showObstacles();

    @Override
    public void reset() {
        this.currentPosition = IWorld.CENTRE;
        this.currentDirection = Direction.UP;
        Command.getHistory().clear();
    }

    @Override
    public boolean isAtEdge() {
        if (this.currentPosition.getX() == 100 || this.currentPosition.getX() == -100) {
            return true;
        }

        if (this.currentPosition.getY() == 200 || this.currentPosition.getY() == -200) {
            return true;
        }
        return false;
    }
    @Override
    public UpdateResponse updatePosition(int nrSteps) {

        int newX = this.currentPosition.getX();
        int newY = this.currentPosition.getY();

        if (Direction.UP.equals(this.currentDirection)) {newY = newY + nrSteps;}
        if (Direction.RIGHT.equals(this.currentDirection)) {newX = newX + nrSteps;}
        if (Direction.LEFT.equals(this.currentDirection)) {newX = newX - nrSteps;}
        if (Direction.BOTTOM.equals(this.currentDirection)) {newY = newY - nrSteps;}

        Position newPosition = new Position(newX, newY);

        if (!newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            return UpdateResponse.FAILED_OUTSIDE_WORLD;
        }

        for (Obstacle obs: obstacles) {
            if (obs.blocksPosition(newPosition) || (obs.blocksPath(this.currentPosition, newPosition))) {
                return UpdateResponse.FAILED_OBSTRUCTED;
            }
        }

        this.currentPosition = newPosition;
        return UpdateResponse.SUCCESS;
    }
    @Override
    public void updateDirection(boolean turnRight) {
        Direction currentDirect = this.currentDirection;
        Direction newDirection = null;

        if (turnRight) {
            if (currentDirect.equals(Direction.UP)) {
                newDirection = Direction.RIGHT;
            } else if (currentDirect.equals(Direction.RIGHT)) {
                newDirection = Direction.BOTTOM;
            } else if (currentDirect.equals(Direction.BOTTOM)) {
                newDirection = Direction.LEFT;
            } else {
                newDirection = Direction.UP;
            }

        } else {
            if (currentDirect.equals(Direction.UP)) {
                newDirection = Direction.LEFT;
            } else if (currentDirect.equals(Direction.LEFT)) {
                newDirection = Direction.BOTTOM;
            } else if (currentDirect.equals(Direction.BOTTOM)) {
                newDirection = Direction.RIGHT;
            } else {
                newDirection = Direction.UP;
            }
        }
        this.currentDirection = newDirection;
    }

    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT, BOTTOM_RIGHT);
    }
}

