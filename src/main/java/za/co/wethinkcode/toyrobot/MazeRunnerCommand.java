package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.EmptyMazeRunner;
import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;

public class MazeRunnerCommand extends Command {
    public MazeRunnerCommand() {
        super("mazerun");
    }

    public MazeRunnerCommand(String edge) {
        super("mazerun");
    }
    @Override
    public boolean execute(Robot target) {
        Direction edge;

        switch (this.getArgument().toLowerCase()){
            case "right" :
                edge = Direction.RIGHT;
                break;
            case "bottom" :
                edge = Direction.BOTTOM;
                break;
            case "left" :
                edge = Direction.LEFT;
                break;
            case "top" :
                edge = Direction.UP;
                break;
            case "" :
                edge = Direction.UP;
                break;
            default:
                throw new IllegalArgumentException("Unsupported command.");
        }
//
        if (target.getPosition() != IWorld.CENTRE){
            target.reset();
        }
        MazeRunner runner = new EmptyMazeRunner();

        if (runner.mazeRun(target, edge)){
            UpdateResponse status = UpdateResponse.SOLVED;
            status.setMessage("I am at the "+ edge +" edge. (Cost: " + runner.getMazeRunCost() + " steps)");
            target.setStatus(status);
        } else {
            target.setStatus(UpdateResponse.FAILED_NO_SOLUTION);
        }
        return true;
    }
}
