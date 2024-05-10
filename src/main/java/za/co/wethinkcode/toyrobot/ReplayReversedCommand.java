package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.List;

public class ReplayReversedCommand extends Command {
    public ReplayReversedCommand() {
        super("Replay Reversed");
    }

    public ReplayReversedCommand(String argument){
        super("Replay Reversed", argument);
        this.argument = argument;
    }
    static ArrayList<String> movementCommands = Command.getHistory();
    private String argument = "";
    private int replayCounter = 0;

    @Override
    public boolean execute(Robot target) {
        ReplayCommand.setReplayMode(true);

        if (this.argument.isEmpty()) {
            replayReversed(target);

        } else if (this.argument.contains("-")) {
            String[] args = this.argument.trim().split("-");
            int start = Integer.parseInt(args[0]);
            int end = Integer.parseInt(args[1]);
            replayReversedRange(target,start,end);

        } else{
            int argumentInt = Integer.parseInt(argument);
            replayReversedN(target,argumentInt);

        }
        target.setStatus("replayed "+replayCounter+" commands.");
        movementCommands.clear();
        this.replayCounter = 0;
        ReplayCommand.setReplayMode(false);
        return true;
    }
    public void replayReversed(Robot target) {
        replay(target,0, movementCommands.size());
    }

    public void replayReversedRange(Robot target,int start, int end) {
        replay(target,Math.max(0, end - 2), Math.min(start-2, movementCommands.size()));
    }
    public void replayReversedN(Robot target,int n) {
        replay(target,Math.max(0, movementCommands.size() - n), movementCommands.size());
    }
    private void replay(Robot target,int fromIndex, int toIndex) {
        List<String> commandsToReplay = movementCommands.subList(fromIndex, toIndex);
        for (int i = commandsToReplay.size() - 1; i >= 0; i--) {
            Command cmd = Command.create(commandsToReplay.get(i));
            target.handleCommand(cmd);
            this.replayCounter++;
            Play.Display(target.toString());
        }
    }
}
