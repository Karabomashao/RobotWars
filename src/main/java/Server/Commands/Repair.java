package Server.Commands;

import Server.World.JsonStringResponses;
import Server.World.Robot;
import Server.World.World;
import java.io.IOException;
import static Server.World.JsonStringResponses.repair;

/**
 * This class represents a repair command.
 */
public class Repair extends Command {
    /**
     * The time it takes to repair the robot's shield.
     */
    private final int repairTime;

    /**
     * Constructor for the Repair class.
     */
    public Repair() {
        super("reload");
        this.repairTime = 6000;
    }

    /**
     * Execute the repair command.
     * @param target the world where the robot is located
     * @param name the name of the robot to repair
     * @throws IOException if the robot is not found
     */
    @Override
    public void execute(World target, String name) throws IOException {
        Robot robot = getRobotByName(target, name);
        if (robot != null) {
            String JsonStringResponses = repair(robot);
        } else {
            throw new IOException("Robot not found: " + name);
        }
    }

    /**
     * Repair the robot's shield.
     * @param robot the robot to repair
     * @return the status of the repair operation
     */
    public synchronized String repair(Robot robot){
        String message = "Normal";

        // Checks if the shield is less than the maximum
        if (robot.getShield() < robot.getMaxShield()) {
            robot.setShield(robot.getMaxShield());
            message = "REPAIRING";
            robot.setStatus(JsonStringResponses.repair(message, robot));

            try {
                // Stops the robot until repair is done
                Thread.sleep(repairTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            message = "Done";
            robot.setStatus(JsonStringResponses.repair(message, robot));

        }
        // Check if the shield is already at the maximum
        else if (robot.getShield() == robot.getMaxShield()) {
            message = "Cannot Repair shields are at maximum value:";
        }
        robot.setStatus(JsonStringResponses.repair(message, robot));

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


