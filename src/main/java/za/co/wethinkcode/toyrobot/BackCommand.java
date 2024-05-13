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
        String argument = getArgument();
        if (argument.matches("\\d+")) {  // Ensures the argument contains only digits
            int steps = Integer.parseInt(argument); // Use a more conventional variable naming

            target.updatePosition(-steps); // Negative to indicate moving back
            target.setStatus("Moved back by " + steps + " steps.");
        } else {
            throw new IllegalArgumentException("Please enter a valid integer for steps.");
        }
        return true;
    }
}
