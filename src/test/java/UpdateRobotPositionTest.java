import java.util.List;
import Server.World.*;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import Server.MultiServerRunner;
import Server.World.Obstacle.Obs;
import static org.mockito.Mockito.*;

/**
 * This JUnit test class tests the methods of the UpdateRobotPosition class.
 */
public class UpdateRobotPositionTest {

    private Robot robot;
    private Position newPosition;
    private World world;

    /**
     * Sets up the test by creating instances of the Robot, Position, and World classes.
     */
    @Before
    public void setup() {
        robot = Mockito.mock(Robot.class);
        newPosition = Mockito.mock(Position.class);
        world = Mockito.mock(World.class);
        MultiServerRunner.world = world;
    }

    /**
     * Tests the updateRobotPosition method of the UpdateRobotPosition class when the new position is outside the bounds.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testUpdateRobotPositionOutsideBounds() throws IOException {
        when(newPosition.isIn(any(Position.class),
                any(Position.class))).thenReturn(false);
        UpdateRobotPosition.updateRobotPosition(robot, newPosition);
        verify(robot).setStatus(JsonStringResponses.invalidMove());
    }

    /**
     * Tests the updateRobotPosition method of the UpdateRobotPosition class when an obstacle blocks the path.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testUpdateRobotPosition_obstacleBlocksPath() throws IOException {
        Position newPosition = new Position(1, 1);
        List<Obs> obstacles = new ArrayList<>();
        Obs obstacle = mock(Obs.class);
        when(obstacle.blocksPath(any(Position.class), any(Position.class))).thenReturn(true);
        when(obstacle.getTypeOfObstacle()).thenReturn("RegularObstacle");
        obstacles.add(obstacle);

        when(world.getTOP_LEFT()).thenReturn(new Position(0, 0));
        when(world.getBOTTOM_RIGHT()).thenReturn(new Position(10, 10));
        when(world.getObsList()).thenReturn(obstacles);
        when(robot.getPosition()).thenReturn(new Position(0, 0));

        try {
            UpdateRobotPosition.updateRobotPosition(robot, newPosition);
        } catch (IOException e) {
            e.printStackTrace();
        }

        verify(robot).setStatus(anyString());
    }

    /**
     * Tests the updateRobotPosition method of the UpdateRobotPosition class when the move is invalid.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testUpdateRobotPosition_invalidMove() throws IOException {
        Position newPosition = new Position(11, 11);
        when(world.getTOP_LEFT()).thenReturn(new Position(0, 0));
        when(world.getBOTTOM_RIGHT()).thenReturn(new Position(10, 10));
        when(robot.getPosition()).thenReturn(new Position(0, 0));

        try {
            UpdateRobotPosition.updateRobotPosition(robot, newPosition);
        } catch (IOException e) {
            e.printStackTrace();
        }

        verify(robot).setStatus(anyString());
    }
}