package Server.World.Obstacle;

import Server.Commands.CommandProperties;
import Server.MultiServerRunner;
import Server.World.Position;

import java.io.IOException;


public interface Obs {
    CommandProperties obstacleCheck = new CommandProperties();

    String getTypeOfObstacle();
    boolean blocksPath(Position a, Position b) throws IOException;

    boolean blocksPosition(Position position) throws IOException;

    public default int distanceOfObstacle(Position a, Position b) throws IOException {
        for (Obs obstacle : MultiServerRunner.world.getObsList()) {
            int count = 0;
            Position start = a;
            while (start.getX() != b.getX() || start.getY() != b.getY()) {
                start = obstacleCheck.moveIncrementor(start, b);
                count += 1;
                if (obstacle.blocksPosition(start)) {
                    MultiServerRunner.world.setTypeObstacle(obstacle.getTypeOfObstacle());
                    return count;
                }
            }
        }
        return 0;
    }

    public default int distanceOfEdge(Position a, Position b) throws IOException {
        for (Obs obstacle : MultiServerRunner.world.getEdgeList()) {
            int count = 0;
            Position start = a;
            while (start.getX() != b.getX() || start.getY() != b.getY()) {
                start = obstacleCheck.moveIncrementor(start, b);
                count += 1;
                if (obstacle.blocksPosition(start)) {
                    MultiServerRunner.world.setTypeObstacle(obstacle.getTypeOfObstacle());
                    return count;
                }
            }
        }
        return 0;
    }

    public int getSize() throws IOException;

    public int getBottomLeftX();

    public int getBottomLeftY();
}

