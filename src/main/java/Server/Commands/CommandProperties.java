package Server.Commands;

import Server.World.Position;
import Server.World.Robot;

/**
 * This class provides utility methods for handling command properties, such as
 * calculating the incremental movement of a robot and determining the target
 * destination based on the robot's direction and the command.
 */
public class CommandProperties {

    /**
     * Constructor for the CommandProperties class.
     */
    public CommandProperties(){
    }

    /**
     * Calculates the incremental movement of a robot from the start position to
     * the end position.
     *
     * @param start the starting position of the robot
     * @param end   the ending position of the robot
     * @return the new position of the robot after the incremental movement
     */
    public Position moveIncrementor(Position start, Position end) {
        if (start.getX() < end.getX()) {start = new Position(start.getX() + 1, start.getY());}
        if (start.getX() > end.getX()) {start = new Position(start.getX() - 1, start.getY());}
        if (start.getY() < end.getY()) {start = new Position(start.getX(), start.getY() + 1);}
        if (start.getY() > end.getY()) {start = new Position(start.getX(), start.getY() - 1);}
        return start;
    }

    /**
     * Calculates the target destination of a robot based on its current
     * direction and the command (forward or backward).
     *
     * @param robot   the robot whose target destination is to be calculated
     * @param target  the distance to the target destination
     * @param command the command (forward or backward)
     * @return the new position of the robot at the target destination
     */
    public Position targetDestination(Robot robot, int target, String command) {

        int newX = robot.getPosition().getX();
        int newY = robot.getPosition().getY();

        switch (robot.getDirection()) {
            case NORTH:
                if (command.equals("forward")){newY += target;}
                else {newY -= target;}
                break;
            case EAST:
                if (command.equals("forward")){newX += target;}
                else {newX -= target;}
                break;
            case WEST:
                if (command.equals("forward")){newX -= target;}
                else {newX += target;}
                break;
            case SOUTH:
                if (command.equals("forward")){newY -= target;}
                else {newY += target;}
                break;
        }
        return new Position(newX, newY);
    }
}



