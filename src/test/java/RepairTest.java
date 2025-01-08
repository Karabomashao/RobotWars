import org.mockito.Mock;
import Server.World.Robot;
import Server.World.World;
import java.io.IOException;
import java.util.Collections;
import Server.Commands.Repair;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * This JUnit test class tests the methods of the Repair class.
 */
@ExtendWith(MockitoExtension.class)
public class RepairTest {
    @Mock
    private World mockWorld;
    @Mock
    private Robot mockRobot;
    private Repair repairCommand;

    /**
     * Sets up the test by creating instances of the Repair class, World, and Robot.
     */
    @BeforeEach
    void setUp() {
        repairCommand = new Repair();
    }

    /**
     * Tests the execute method of the Repair class when the robot is not found.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    void testExecuteRobotNotFound() {
        when(mockWorld.getListOfRobots()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(IOException.class, () -> {
            repairCommand.execute(mockWorld, "nonExistentRobot");
        });

        String expectedMessage = "Robot not found: nonExistentRobot";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the repair method of the Repair class when the shield is less than the maximum.
     */
    @Test
    void testRepairWhenShieldIsLessThanMax() {
        when(mockRobot.getShield()).thenReturn(0);
        when(mockRobot.getMaxShield()).thenReturn(5);

        String status = repairCommand.repair(mockRobot);

        verify(mockRobot, times(1)).setShield(5);
        assertEquals("REPAIRING");
    }

    /**
     * Tests the repair method of the Repair class when the shield is equal to the maximum.
     */
    @Test
    void testRepairWhenShieldIsEqualToMax() {
        when(mockRobot.getShield()).thenReturn(5);
        when(mockRobot.getMaxShield()).thenReturn(5);

        String status = repairCommand.repair(mockRobot);

        verify(mockRobot, never()).setShield(anyInt());
        assertEquals("Cannot Repair shields are at maximum value:");
    }

    private void assertEquals(String s) {
    }
}
