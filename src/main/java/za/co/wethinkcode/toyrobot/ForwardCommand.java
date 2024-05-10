package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class ForwardCommand extends Command {
    public ForwardCommand(){super("forward");}
    public ForwardCommand(String Steps) {
        super("forward", Steps);
    }

    @Override
    public boolean execute(Robot target) {
        if (getArgument().matches("[0-9]+")){
            int Steps = Integer.parseInt(getArgument());
            target.updatePosition(Steps);
            if (target.getStatus() == IWorld.UpdateResponse.SUCCESS){
                target.setStatus("Moved forward by " + Steps +" steps.");}
        } else {
            throw new IllegalArgumentException("Please enter an integer for steps.");
        }
        return true;
    }

}

