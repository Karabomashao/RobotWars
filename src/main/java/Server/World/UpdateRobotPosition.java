package Server.World;

import Server.MultiServerRunner;
import Server.World.Obstacle.Obs;

import java.io.IOException;

public class UpdateRobotPosition {

    public static void updateRobotPosition(Robot robot, Position newPosition) throws IOException {

        if (newPosition.isIn(MultiServerRunner.world.getTOP_LEFT(), MultiServerRunner.world.getBOTTOM_RIGHT())) {

            boolean obstructed = false;

            for (Obs obstacle : MultiServerRunner.world.getObsList()) {
                if (obstacle.blocksPath(robot.getPosition(), newPosition)) {
                    obstructed = true;
                    if (obstacle.getTypeOfObstacle().equals("Pit")) {
                        robot.setStatus(JsonStringResponses.ditchStatus());
                        MultiServerRunner.connectedClients.remove(robot.getRobotHandler());
                        MultiServerRunner.world.getListOfRobots().remove(robot);

                    } else {
                        robot.setStatus(JsonStringResponses.movement(robot, obstructed));
                    }
                    break;
                }
            }

            if (MultiServerRunner.world.robotInPlace(newPosition, robot)){
                obstructed = true;
                robot.setStatus(JsonStringResponses.movement(robot, obstructed));
            }

            if (!obstructed) {
                robot.setPosition(newPosition);
                robot.setStatus(JsonStringResponses.movement(robot, obstructed));
            }
        } else {
            robot.setStatus(JsonStringResponses.invalidMove());
        }
    }
}