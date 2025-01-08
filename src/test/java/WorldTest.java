import Server.World.*;
import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import Server.World.Obstacle.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * This JUnit test class tests the methods of the World class.
 */
public class WorldTest {

    private World world;
    private Obs obstacle1, obstacle2;
    
    /**
     * Sets up the test by creating instances of the World, Walls, and Pits classes.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Before
    public void setUp() throws IOException {
        world = new World();
        obstacle1 = new Walls(0, 0);
        obstacle2 = new Pits(5, 5);
    }

    /**
     * Tests the addRobot method of the World class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testAddRobot() throws IOException {
        Robot robot = new Robot("TestRobot");
        world.addRobot(robot);
        assertTrue(world.robotExists("TestRobot"));
    }

    @Test
    public void testIsPathBlocked_true() throws IOException {
        Obs obstacle = mock(Obs.class);
        when(obstacle.blocksPosition(any(Position.class))).thenReturn(true);
        world.getObsList().add(obstacle);
        boolean result = world.isPathBlocked(obstacle);
        assertTrue(result);
    }

    /**
     * Tests the isPathBlocked method of the World class when the path is blocked.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testIsPathBlocked_false() throws IOException {
        Obs obstacle = mock(Obs.class);
        when(obstacle.blocksPosition(any(Position.class))).thenReturn(false);
        world.getObsList().add(obstacle);
        boolean result = world.isPathBlocked(obstacle);
        assertFalse(result);
    }


    /**
     * Tests the isPathBlocked method of the World class when the path is not blocked.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testRobotExists() throws IOException {
        Robot robot = new Robot("TestRobot");
        world.addRobot(robot);
        assertTrue(world.robotExists("TestRobot"));
        assertFalse(world.robotExists("Robot3"));
    }

    /**
     * Tests the robotExists method of the World class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testRobotInPlace() throws IOException {
        Robot robot = new Robot("TestRobot");
        world.addRobot(robot);
        robot.setPosition(new Position(0, 0));
        robot.setPosition(new Position(1, 1));
        assertFalse(world.robotInPlace(new Position(0, 0), robot));
    }

    /**
     * Tests the distanceOfRobot method of the World class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testDistanceOfRobot() throws IOException {
        Robot robot = new Robot("TestRobot");
        world.addRobot(robot);
        Position target = new Position(0, 0);
        int distance = world.distanceOfRobot(robot, target);
        assertEquals(0, distance);
    }

    /**
     * Tests the robotInPlace method of the World class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testIsRobotInPlace() throws IOException {
        Robot robot1 = new Robot("Robot1");
        Robot robot2 = new Robot("Robot2");
        world.addRobot(robot1);
        world.addRobot(robot2);
        assertTrue(world.isRobotInPlace(robot1.getPosition(), robot2.getName()));
        assertFalse(world.isRobotInPlace(robot1.getPosition(), robot1.getName()));
    }

    /**
     * Tests the getObstacles method of the World class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testGetObstacles() throws IOException {
        world.getObsList().add(obstacle1);
        world.getObsList().add(obstacle2);
        String obstacles = world.getObstacles();
        assertTrue(obstacles.contains("Wall"));
        assertTrue(obstacles.contains("Pit"));
    }
}
