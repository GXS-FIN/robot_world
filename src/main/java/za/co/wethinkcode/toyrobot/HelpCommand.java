package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus(IWorld.UpdateResponse.HELP);  // Directly uses the enum from IWorld to set the status
        return true;
    }
}
