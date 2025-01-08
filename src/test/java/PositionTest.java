import org.junit.Test;
import Server.World.Position;
import static org.junit.Assert.*;

/**
 * This JUnit test class tests the methods of the Position class.
 */
public class PositionTest {

    /**
     * Tests the equals method of the Position class.
     */
    @Test
    public void testEquals() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(0, 0);
        Position position3 = new Position(1, 1);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
    }

    /**
     * Tests the hashCode method of the Position class.
     */
    @Test
    public void testHashCode() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(0, 0);
        Position position3 = new Position(1, 1);

        assertEquals(position1.hashCode(), position2.hashCode());
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }

    /**
     * Tests the robotPresent method of the Position class.
     */
    @Test
    public void testRobotPresent() {
        Position position = new Position(0, 0);
        Position robotPosition = new Position(0, 0);

        assertTrue(position.robotPresent(robotPosition));
    }
}
