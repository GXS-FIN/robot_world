package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class TurnCommand extends Command {
    public TurnCommand(String argument) {
        super("turn", argument);
    }

    @Override
    public boolean execute(Robot target) {
        String nTurn = getArgument();
        if (nTurn.equals("left")){
            target.updateDirection(false);
            target.setStatus(IWorld.UpdateResponse.TURNED_LEFT);
        }else if (nTurn.equals("right")){
            target.updateDirection(true);
            target.setStatus(IWorld.UpdateResponse.TURNED_RIGHT);
        }
        return true;
    }
}
