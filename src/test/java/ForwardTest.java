import Server.Commands.Forward;
import Server.World.Robot;
import Server.World.World;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests the Forward class.
 */
public class ForwardTest {
    /**
     * Tests if the constructor sets the correct values for the name and argument properties.
     */
    @Test
    public void testConstructor() {
        // Arrange
        String argument = "5";

        // Act
        Forward forward = new Forward(argument);

        // Assert
        assertEquals("forward", forward.getName());
        assertEquals(argument, forward.getArgument());

    }
}