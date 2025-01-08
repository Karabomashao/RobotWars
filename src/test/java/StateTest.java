import java.util.List;
import org.junit.Test;
import org.junit.Before;
import Server.World.Robot;
import Server.World.World;
import org.mockito.Mockito;
import Server.Commands.State;
import Server.MultiServerRunner;
import static org.mockito.Mockito.*;

/**
 * This JUnit test class tests the methods of the State class.
 */
public class StateTest {

    private State stateCommand;
    private World world;
    private Robot robot;

    /**
     * Sets up the test by creating instances of the State class, World, and Robot.
     */
    @Before
    public void setup() {
        world = Mockito.mock(World.class);
        robot = Mockito.mock(Robot.class);
        stateCommand = new State();
        MultiServerRunner.world = world;
    }

    /**
     * Tests the execute method of the State class when there is no matching robot.
     */
    @Test
    public void testExecuteWithNoMatchingRobot() {
        when(world.getListOfRobots()).thenReturn(List.of(new Robot[]{robot}));
        when(robot.getName()).thenReturn("OtherRobot");
        stateCommand.execute(world, "TestRobot");

        verify(robot, never()).setStatus(any());
    }
}
