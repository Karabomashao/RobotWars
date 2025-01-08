package Server.Commands;

import Server.MultiServerRunner;
import Server.World.Direction;
import Server.World.JsonStringResponses;
import Server.World.Robot;
import Server.World.World;

/**
 * This class represents a command to turn the robot to the left.
 */
public class Left extends Command{

    /**
     * Execute the left command.
     *
     * @param target the world where the robot is located
     * @param name the name of the robot to turn
     */
    @Override
    public void execute(World target, String name) {
        // Get the list of robots
        for (Robot robotInfo : MultiServerRunner.world.getListOfRobots()) {
            // Checks if the robot's name matches the given name
            if (robotInfo.getName().equals(name)) {
                // Determine the new direction based on the current direction
                switch (robotInfo.getDirection()) {
                    case NORTH:
                        // Turn the robot to the west
                        robotInfo.setDirection(Direction.WEST);
                        robotInfo.setStatus(JsonStringResponses.turnStateResponse(robotInfo));
                        break;
                    case WEST:
                        // Turn the robot to the south
                        robotInfo.setDirection(Direction.SOUTH);
                        robotInfo.setStatus(JsonStringResponses.turnStateResponse(robotInfo));
                        break;
                    case SOUTH:
                        // Turn the robot to the east
                        robotInfo.setDirection(Direction.EAST);
                        robotInfo.setStatus(JsonStringResponses.turnStateResponse(robotInfo));
                        break;
                    case EAST:
                        // Turn the robot to the north
                        robotInfo.setDirection(Direction.NORTH);
                        robotInfo.setStatus(JsonStringResponses.turnStateResponse(robotInfo));
                        break;
                }
            }
        }
    }

    /**
     * Constructor for the Left class.
     * @param argument the argument for the command
     */
    public Left(String argument){
        super("turn", argument);
    }
}
