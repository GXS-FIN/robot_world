package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class ForwardCommand extends Command {
    public ForwardCommand() {
        super("forward");
    }

    public ForwardCommand(String steps) {
        super("forward", steps);
    }

    @Override
    public boolean execute(Robot target) {
        String stepsString = getArgument();
        if (stepsString.matches("\\d+")) {  // Simplifies the regex and follows Java naming conventions
            int steps = Integer.parseInt(stepsString);  // Proper camelCase naming for variable

            target.updatePosition(steps);  // Moves forward by the number of steps
            if (target.getStatus() == IWorld.UpdateResponse.SUCCESS) {
                target.setStatus("Moved forward by " + steps + " steps.");
            }
        } else {
            throw new IllegalArgumentException("Please enter a valid integer for steps.");
        }
        return true;
    }
}
