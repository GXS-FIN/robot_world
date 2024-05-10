package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);
    private static final ArrayList<String> commandHistory = new ArrayList<>();
    public static ArrayList<String> getHistory() {
        return commandHistory;
    }
    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        if (!ReplayCommand.getReplayMode()){
            String[] keywords = {"forward", "back", "left", "right", "sprint"};
            for (String keyword : keywords) {
                if (instruction.contains(keyword)) {
                    commandHistory.add(instruction);
                    break;
                }
            }
        }

        switch (args[0]){
            case "shutdown":
                return new ShutdownCommand();
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                if (args.length > 1) return new ForwardCommand(args[1]);
            case "back":
                if (args.length > 1) return new BackCommand(args[1]);
            case "left":
                return new TurnCommand("left");
            case "right":
                return new TurnCommand("right");
            case "sprint":
                if (args.length > 1)return new SprintCommand(args[1]);
            case "replay":
                if (instruction.contains("reversed") && args.length == 2) {
                    return new ReplayReversedCommand();
                }
                if (args.length==1)return  new ReplayCommand();
                if (args.length==2)return  new ReplayCommand(args[1]);
                if (args.length==3)return  new ReplayReversedCommand(args[2]);
            case "mazerun":
                if (args.length==1 || args.equals("up") )return new MazeRunnerCommand();
                if (args.length==2)return new MazeRunnerCommand(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}

