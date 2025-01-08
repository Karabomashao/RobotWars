package Server.Commands;

import Server.MultiServerRunner;
import Server.World.*;

import java.io.IOException;

import static Server.World.UpdateRobotPosition.updateRobotPosition;

/**
 * This class represents a command to move a robot forward by a specified number of steps.
 */
public class Forward extends Command {
    private final CommandProperties helperFunction;

    /**
     * Execute the Forward command.
     *
     * @param target - The world where the robot is located.
     * @param name - The name of the robot to move.
     */
    @Override
    public void execute(World target, String name) throws IOException {
        // Get the number of steps to move the robot forward
        int nrSteps = Integer.parseInt(getArgument());

        // Loop through all robots in the world
        for (Robot robotInfo : MultiServerRunner.world.getListOfRobots()) {
            // Check if the robot's name matches the given name
            if (robotInfo.getName().equals(name)) {
                // Calculate the new position for the robot
                Position newPosition = helperFunction.targetDestination(robotInfo, nrSteps, getName());
                // Update the robot's position
                updateRobotPosition(robotInfo, newPosition);
                // Break out of the loop
                break;
            }
        }
    }

    /**
     * Constructor for the Forward command.
     *
     * @param argument The number of steps to move the robot forward.
     */
    public Forward(String argument) {
        super("forward", argument);
        helperFunction = new CommandProperties();
    }
}