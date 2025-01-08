package Server.Commands;

import Server.MultiServerRunner;
import Server.World.*;
import Server.World.Obstacle.Obs;
import Server.World.Obstacle.ObsCaller;
import java.io.IOException;
/**
 * This class represents a command to fire a bullet from a robot.
 */
public class Fire extends Command{
    private final CommandProperties props;
    private final Obs obstacle;
    private String typeObstacle = MultiServerRunner.world.getTypeObstacle();
    /**
     * Execute the Fire command.
     *
     * @param target- is the world where the robot is located.
     * @param name- is the name of the robot to fire.
     * @throws IOException- If there is an error firing the bullet.
     */
    @Override
    public void execute(World target, String name) throws IOException {

        String message;
        for (Robot robot : MultiServerRunner.world.getListOfRobots()) {

            if (robot.getName().equals(name)) {

                int bulletTravel = robot.getBulletTravel();
                Position newPosition = props.targetDestination(robot, bulletTravel, "forward");
                int obstacleDistance = obstacle.distanceOfObstacle(robot.getPosition(), newPosition);
                int robotDistance = MultiServerRunner.world.distanceOfRobot(robot, newPosition);
                Robot enemyRobot = MultiServerRunner.world.getRobotAttacked();
                // Check if the robot has any shots left
                if (robot.getShots() > 0) {
                    // Decrement the number of shots left
                    robot.setShots(robot.getShots() - 1);

                    // Check if the robot is close enough to the obstacle or if the obstacle is not a pit
                    if ((robotDistance > 0 && robotDistance < obstacleDistance ||
                            robotDistance > 0 && obstacleDistance == 0) && !typeObstacle.equals("Pit")) {
                        // Set the message to "HIT"
                        message = "HIT";
                        // Decrement the shield of the enemy robot
                        enemyRobot.setShield(enemyRobot.getShield() - 1);
                        // Check if the enemy robot's shield is now zero
                        if (enemyRobot.getShield() == 0) {
                            // Set the status of the enemy robot to "kill"
                            enemyRobot.setStatus(JsonStringResponses.killStatus());
                            // Remove the enemy robot from the list of connected clients
                            MultiServerRunner.connectedClients.remove(enemyRobot.getRobotHandler());
                            // Remove the enemy robot from the list of robots
                            MultiServerRunner.world.getListOfRobots().remove(enemyRobot);
                        }

                        } else {
                        //set the message to "miss"
                        message = "MISS";
                        }

                    robot.setStatus(JsonStringResponses.fire(message, robotDistance,
                            enemyRobot, robot));
                    break;
                    }
                }
            }
        }
    /**
     * Constructor for the Fire command.
     */
    public Fire(){
        super("fire");
        props = new CommandProperties();
        obstacle = new ObsCaller();

    }
}
