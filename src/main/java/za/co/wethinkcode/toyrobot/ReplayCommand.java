package za.co.wethinkcode.toyrobot;

import java.util.List;
import java.util.ArrayList;

public class ReplayCommand extends Command {
    public ReplayCommand() {super("replay");}

    public ReplayCommand(String argument) {
        super("replay", argument);
        this.argument = argument;
    }

    private static boolean replayMode = false;
    private String argument = "";
    public static int replayCounter = 0;
    static ArrayList<String> movementCommands = Command.getHistory();
    public static boolean getReplayMode() {
        return replayMode;
    }

    public static void setReplayMode(boolean Mode) {
        ReplayCommand.replayMode = Mode;
    }

    @Override
    public boolean execute(Robot target) {
        setReplayMode(true);

        if (this.argument.equals("")){
            replayAll(target);

        }else if (this.argument.contains("-")){

            String[] args = this.argument.trim().split("-");
            int startNumber = Integer.parseInt(args[0]);
            int endNumber = Integer.parseInt(args[1]);
            replayRange(target,startNumber,endNumber);
        }
        else{
            int argumentInt = Integer.parseInt(argument);
            replayLastN(target,argumentInt);
        }
        target.setStatus("replayed "+replayCounter+" commands.");
        movementCommands.clear();
        replayCounter = 0;
        setReplayMode(false);
        return true;
    }

    public static void replayAll(Robot target) {
        replay(target,0, movementCommands.size());
    }

    public static void replayRange(Robot target, int start, int end) {
        replay(target,end-2,start-2);
    }
    public static void replayLastN(Robot target, int n) {
        replay(target,Math.max(0, movementCommands.size() - n), movementCommands.size());
    }

    private static void replay(Robot target, int fromIndex, int toIndex) {
            List<String> commandsToReplay = movementCommands.subList(fromIndex, toIndex);
            for (String command : commandsToReplay) {
                Command cmd = Command.create(command);
                target.handleCommand(cmd);
                replayCounter++;
                Play.Display(target.toString());
            }
        }
}



