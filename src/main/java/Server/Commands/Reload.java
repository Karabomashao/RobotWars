package Server.Commands;

import Server.MultiServerRunner;
import Server.World.JsonStringResponses;
import Server.World.Robot;
import Server.World.World;

import java.io.IOException;


/**
 * This class represents a reload command for a robot.
 */
public class Reload extends Command {

    /**
     * The time it takes to reload the robot's shots.
     */
    private final int reloadTime;

    /**
     * Constructor for the Reload class.
     */
    public Reload() {
        super("reload");
        this.reloadTime = 6000;
    }

    /**
     * Execute the reload command.
     * @param target the world where the robot is located
     * @param name the name of the robot to reload
     * @throws IOException if the robot is not found
     */
    @Override
    public void execute(World target, String name) throws IOException {
        Robot robot = getRobotByName(target, name);
        if (robot != null) {
            String JsonStringResponses = reload(robot);
        } else {
            throw new IOException("Robot not found: " + name);
        }
    }

    /**
     * Reload the robot's shots.
     * @param robot the robot to reload
     * @return the status of the reload operation
     */
    public synchronized String reload(Robot robot){
        String message = "Normal";

        // Checks if the shots are less than the maximum
        if (robot.getShots() < robot.getMaxShots()) {
            robot.setShots(robot.getMaxShots());
            message = "RELOADING";
            robot.setStatus(JsonStringResponses.reload(message, robot));

            try {
                // Stops the robot until is done
                Thread.sleep(reloadTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            message = "Done";
            robot.setStatus(JsonStringResponses.reload(message, robot));

        }
        // Check if the shots are already at the maximum
        else if (robot.getShots() == robot.getMaxShots()) {
            message = "Cannot Reload shots are at maximum value:";
        }
        robot.setStatus(JsonStringResponses.reload(message, robot));

        return robot.getStatus();
    }

    /**
     * Get a robot by name.
     * @param world the world where the robot is located
     * @param name the name of the robot
     * @return the robot with the given name
     */
    private Robot getRobotByName(World world, String name) {
        for (Robot robot : world.getListOfRobots()) {
            if (robot.getName().equals(name)) {
                return robot;
            }
        }
        return null;
    }
}

