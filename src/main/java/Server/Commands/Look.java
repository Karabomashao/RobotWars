package Server.Commands;

import Server.MultiServerRunner;
import Server.World.*;
import Server.World.Obstacle.Obs;
import Server.World.Obstacle.ObsCaller;
import Server.World.Obstacle.Obstacles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Server.World.JsonStringResponses.look;

/**
 * This class represents a command to look around the robot's current position.
 * It checks for obstacles in the robot's line of sight and returns a status message indicating the distance and type of obstacles.
 */
public class Look extends Command {
    private List<Integer> distanceOfObstruction = new ArrayList<>();
    private List<String> typeObstruction = new ArrayList<>();
    private final Obs obstacle;

    /**
     * Executes the look command for a robot in the given world.
     * @param target The world where the robot is located.
     * @param name   The name of the robot.
     * @throws IOException If an I/O error occurs during execution.
     */
    @Override
    public void execute(World target, String name) throws IOException {

        for (Robot robot : MultiServerRunner.world.getListOfRobots()) {
            if (robot.getName().equals(name)) {

                int x = robot.getPosition().getX();
                int y = robot.getPosition().getY();
                int visibility = robot.getVisibility();

                Position[] newPosition =
                        {new Position(x, y + visibility),
                                new Position(x + visibility, y),
                                new Position(x, y + (-visibility)),
                                new Position(x + (-visibility), y)};

                for (int i = 0; i < 4; i++) {

                    int obstacleDistance = obstacle.distanceOfObstacle(robot.getPosition(), newPosition[i]);
                    int robotDistance = MultiServerRunner.world.distanceOfRobot(robot, newPosition[i]);

                    if (obstacleDistance > 0 && obstacleDistance < robotDistance || obstacleDistance > 0 &&
                            robotDistance == 0) {
                        distanceOfObstruction.add(obstacleDistance);
                        typeObstruction.add(MultiServerRunner.world.getTypeObstacle());
                    }

                    else if (robotDistance > 0 && robotDistance < obstacleDistance || robotDistance > 0 &&
                            obstacleDistance == 0) {
                        distanceOfObstruction.add(robotDistance);
                        typeObstruction.add("Robot");
                    }else {
                        distanceOfObstruction.add(0);
                        typeObstruction.add("Clear");
                    }
                }
                robot.setStatus(look(distanceOfObstruction, typeObstruction, robot));

            }
        }
    }

    /**
     * Constructor for the Look class.
     */
    public Look(){
        super("look");
        obstacle = new ObsCaller();
    }
}
