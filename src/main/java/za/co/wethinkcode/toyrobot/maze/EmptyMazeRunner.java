package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class EmptyMazeRunner implements MazeRunner{
    private int stepCost = 0;

    public EmptyMazeRunner() {
        super();
    }

    public boolean mazeRun (Robot target, IWorld.Direction edgeDirection) {
        target.handleCommand(new ForwardCommand("100"));
        target.handleCommand(new ForwardCommand("100"));
        stepCost = 2;
        return true;
    }

    public int getMazeRunCost() {
        return stepCost;
    }
}
