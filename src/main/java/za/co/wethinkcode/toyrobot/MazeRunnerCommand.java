package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.EmptyMazeRunner;
import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;

public class MazeRunnerCommand extends Command {
    public MazeRunnerCommand() {
        super("mazerun");
    }

    public MazeRunnerCommand(String edge) {
        super("mazerun", edge);
    }

    @Override
    public boolean execute(Robot target) {
        Direction edge = determineEdge(getArgument());

        if (target.getPosition() != IWorld.CENTRE) {
            target.reset();
        }

        MazeRunner runner = new EmptyMazeRunner();
        if (runner.mazeRun(target, edge)) {
            UpdateResponse status = UpdateResponse.SOLVED;
            status.setMessage("I am at the " + edge + " edge. (Cost: " + runner.getMazeRunCost() + " steps)");
            target.setStatus(status);
        } else {
            target.setStatus(UpdateResponse.FAILED_NO_SOLUTION);
        }
        return true;
    }

    private Direction determineEdge(String argument) {
        if (argument == null || argument.isEmpty()) {
            return Direction.UP;  // Defaults to UP if no argument or empty
        }
        switch (argument.toLowerCase()) {
            case "right":
                return Direction.RIGHT;
            case "bottom":
                return Direction.BOTTOM;
            case "left":
                return Direction.LEFT;
            case "top":
                return Direction.UP;
            default:
                throw new IllegalArgumentException("Unsupported direction: " + argument);
        }
    }
}
