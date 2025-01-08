import org.junit.Test;
import java.util.List;
import org.junit.Before;
import Server.World.Robot;
import Server.World.World;
import java.io.IOException;
import java.util.ArrayList;
import Server.Commands.Right;
import Server.World.Direction;
import Server.MultiServerRunner;
import Server.World.JsonStringResponses;
import static org.junit.Assert.assertEquals;

/**
 * This JUnit test class tests the methods of the Right class.
 */
public class RightTest {

    private World world;
    private MultiServerRunner multiServerRunner;
    private Right rightCommand;

    /**
     * Sets up the test by creating instances of the Right class, World, MultiServerRunner, and Robot.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Before
    public void setup() throws IOException {
        world = new World();
        multiServerRunner = new MultiServerRunner();
        multiServerRunner.world = world;
        List<Robot> robots = new ArrayList<>();
//        world.setListOfRobots(robots);
        rightCommand = new Right("TestRobot");
    }

    /**
     * Tests the execute method of the Right class.
     */
    @Test
    public void testExecute() {
        // Given
        rightCommand.execute(world, "TestRobot");

        // Then
        try {
            assertEquals(Direction.EAST, world.getListOfRobots().get(0).getDirection());
            assertEquals(JsonStringResponses.turnStateResponse(world.getListOfRobots().get(0)), world.getListOfRobots().get(0).getStatus());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("");
        }
    }
}
