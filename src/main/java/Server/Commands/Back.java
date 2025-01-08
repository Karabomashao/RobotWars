package Server.Commands;

import Server.MultiServerRunner;
import Server.World.*;

import java.io.IOException;

import static Server.World.UpdateRobotPosition.updateRobotPosition;

/**
 * This class represents a command to move a robot backward by a specified number of steps.
 */
public class Back extends Command {
    private final CommandProperties helperFunction;

    /**
     * Constructor for the Back command.
     *
     * @param argument - The number of steps to move the robot backward.
     */
    public Back(String argument) {
        super("back", argument);
        helperFunction = new CommandProperties();
    }

    /**
     * Execute the Back command.
     *
     * @param target - The world where the robot is located.
     * @param name - The name of the robot to move.
     */
    @Override
    public void execute(World target, String name) throws IOException {
        int nrSteps = Integer.parseInt(getArgument());

        for (Robot robotInfo : MultiServerRunner.world.getListOfRobots()) {
            if (robotInfo.getName().equals(name)) {
                Position newPosition = helperFunction.targetDestination(robotInfo, nrSteps, getName());
                updateRobotPosition(robotInfo, newPosition);
                break;
            }
        }
    }
}