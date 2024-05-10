package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.concurrent.SynchronousQueue;

public class ShutdownCommand extends Command {
    public ShutdownCommand() {
        super("off");
    }
    @Override
    public boolean execute(Robot target) {
        target.setStatus(IWorld.UpdateResponse.SHUTDOWN);
        return false;
    }
}