
package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;

public abstract class Command {
    private final String name;
    private String argument;
    private static final ArrayList<String> commandHistory = new ArrayList<>();

    public abstract boolean execute(Robot target);

    public Command(String name) {
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public static ArrayList<String> getHistory() {
        return commandHistory;
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        if (!ReplayCommand.getReplayMode()) {
            addCommandToHistory(args[0], instruction);
        }

        switch (args[0]) {
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
            case "back":
                if (args.length > 1) return args[0].equals("forward") ? new ForwardCommand(args[1]) : new BackCommand(args[1]);
            case "left":
            case "right":
                return new TurnCommand(args[0]);
            case "sprint":
                if (args.length > 1) return new SprintCommand(args[1]);
            case "replay":
                return handleReplay(args);
            case "mazerun":
                return handleMazeRun(args);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

    private static void addCommandToHistory(String command, String instruction) {
        String[] keywords = {"forward", "back", "left", "right", "sprint"};
        for (String keyword : keywords) {
            if (instruction.contains(keyword)) {
                commandHistory.add(instruction);
                break;
            }
        }
    }

    private static Command handleReplay(String[] args) {
        if (args.length == 1) return new ReplayCommand();
        if (args.length == 2) return args[1].equals("reversed") ? new ReplayReversedCommand() : new ReplayCommand(args[1]);
        if (args.length == 3) return new ReplayReversedCommand(args[2]);
        return null;
    }

    private static Command handleMazeRun(String[] args) {
        if (args.length == 1 || args[0].equals("up")) return new MazeRunnerCommand();
        if (args.length == 2) return new MazeRunnerCommand(args[1]);
        return null;
    }
}
