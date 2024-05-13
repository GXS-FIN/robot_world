package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.TurtleWorld;


import java.util.List;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

public class Robot {

    private final IWorld world;
    private UpdateResponse status;
    private String name;
    private boolean turtleMode;

    public Robot(String name, IWorld world) {
        this.name = name;
        this.world = world;
        this.status = UpdateResponse.READY;
        this.turtleMode = TurtleWorld.getTurtleMode(); // Initialize within constructor
    }

    public UpdateResponse getStatus() {
        return status;
    }

    public void setStatus(UpdateResponse status) {
        this.status = status;
    }

    public void setStatus(String statusMessage) {
        this.status = UpdateResponse.SUCCESS;
        this.status.setMessage(statusMessage);
    }

    public String getName() {
        return name;
    }

    public IWorld.Direction getCurrentDirection() {
        return world.getCurrentDirection();
    }

    public Position getPosition() {
        return world.getPosition();
    }

    public List<Obstacle> getObstacles() {
        return world.getObstacles();
    }

    public boolean handleCommand(Command command) {
        return command.execute(this) && this.status != UpdateResponse.SHUTDOWN;
    }

    public boolean updatePosition(int nrSteps) {
        this.status = world.updatePosition(nrSteps);
        if (turtleMode) {
            TurtleWorld.turtleMove(nrSteps);
        }
        return status == UpdateResponse.SUCCESS;
    }

    public void updateDirection(boolean turnRight) {
        world.updateDirection(turnRight);
        if (turtleMode) {
            TurtleWorld.turnTurtleRobot(turnRight);
        }
        this.status = world.getStatus();
    }

    public void reset() {
        world.reset();
        if (turtleMode) {
            TurtleWorld.turtleReset();
        }
    }

    @Override
    public String toString() {
        Position pos = world.getPosition();
        return String.format("[%d,%d] %s> %s", pos.getX(), pos.getY(), name, status.getMessage());
    }
}
