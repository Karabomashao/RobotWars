package Server.Commands;

import Server.MultiServerRunner;
import Server.World.*;

/**
 * This class represents a command to get the state of a robot.
 * It retrieves the current state of a robot and updates its status with the state information.
 */
public class State extends Command {

    /**
     * Constructor for the State class.
     */

    public State() {
        super("state");
    }

    /**
     * Executes the state command for a robot in the given world.
     * @param target The world where the robot is located.
     * @param name   The name of the robot.
     */
    @Override
    public void execute(World target, String name) {

        for (Robot robotInfo : MultiServerRunner.world.getListOfRobots()) {
            if (robotInfo.getName().equals(name)) {
                robotInfo.setStatus(JsonStringResponses.state(robotInfo));
            }
        }
    }
}




