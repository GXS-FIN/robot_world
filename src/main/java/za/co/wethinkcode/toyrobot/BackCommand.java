package za.co.wethinkcode.toyrobot;

public class BackCommand extends Command {
    public BackCommand() {
        super("back");
    }
    public BackCommand(String argument) {
        super("back", argument);
    }
    @Override
    public boolean execute(Robot target) {
        if (getArgument().matches("[0-9]+")){
            int Steps = Integer.parseInt(getArgument());

            target.updatePosition(-Steps);
            target.setStatus("Moved back by " + Steps+" steps.");
        } else {throw new IllegalArgumentException("Please enter an integer for steps.");}
        return true;
    }
}
