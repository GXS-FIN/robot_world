package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class SprintCommand extends Command {
    public SprintCommand(){super("sprint");}

    public boolean execute(Robot target) {
        if (getArgument().matches("[0-9]+")){
            int nrSteps = Integer.parseInt(getArgument());
            for (int i = nrSteps ;i > 0;i--) {
                target.handleCommand(new ForwardCommand(Integer.toString(i)));
                if (i > 1) Play.Display(target.toString());
            }
        }else{throw new IllegalArgumentException("Please enter an integer for steps.");}
        return true;
    }

    public SprintCommand(String argument) {
        super("replay", argument);
    }
}

