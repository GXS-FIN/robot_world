package za.co.wethinkcode.toyrobot;


import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

public class Robot {

    private final IWorld world;
    private UpdateResponse status;
    private String name;
    public boolean turtleMode = TurtleWorld.getTurtleMode();

    public Robot(String name,IWorld world) {
        this.world = world;
        this.name = name;
        this.status = UpdateResponse.READY;
    }
    public UpdateResponse getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = UpdateResponse.SUCCESS;
        this.status.setMessage(status);
    }

    public void setStatus(IWorld.UpdateResponse status) {
        this.status = status;
    }
    
    public String getName() {
        return name;
    }


    public IWorld.Direction getCurrentDirection() {return this.world.getCurrentDirection();}

    public Position getPosition() {
        return this.world.getPosition();
    }

    public List<Obstacle> getObstacles(){
        return this.world.getObstacles();
    }

    public boolean handleCommand(Command command) {
        command.execute(this);
        return !(this.getStatus() == UpdateResponse.SHUTDOWN);
    }
    

    public boolean updatePosition(int nrSteps){
        status = world.updatePosition(nrSteps);
        if (turtleMode){TurtleWorld.turtleMove(nrSteps);}
        return status == UpdateResponse.SUCCESS;
    }

    public void updateDirection(boolean turnRight) {
        world.updateDirection(turnRight);
        if (turtleMode){TurtleWorld.turnTurtleRobot(turnRight);}
       status = world.getStatus();
    }

    public void reset(){
        if (turtleMode){TurtleWorld.turtleReset();}
        this.world.reset();
    }

    @Override
    public String toString() {
       return "[" + world.getPosition().getX() + "," + world.getPosition().getY() + "] "
               + this.name + "> " + this.status.getMessage();
    }
}