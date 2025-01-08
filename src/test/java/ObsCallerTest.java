import Server.World.Obstacle.ObsCaller;
import Server.World.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This JUnit test class tests the methods of the ObsCaller class.
 */
public class ObsCallerTest {

    /**
     * Tests the blocksPath method of the ObsCaller class.
     */
    @Test
    public void testBlocksPath() {
        ObsCaller obsCaller = new ObsCaller();
        Position a = new Position(0, 0);
        Position b = new Position(1, 1);
        assertFalse(obsCaller.blocksPath(a, b));
    }

    /**
     * Tests the blocksPosition method of the ObsCaller class.
     */
    @Test
    public void testBlocksPosition() {
        ObsCaller obsCaller = new ObsCaller();
        Position position = new Position(0, 0);
        assertFalse(obsCaller.blocksPosition(position));
    }

    /**
     * Tests the getSize method of the ObsCaller class.
     */
    @Test
    public void testGetSize() {
        ObsCaller obsCaller = new ObsCaller();
        assertEquals(0, obsCaller.getSize());
    }

    /**
     * Tests the getBottomLeftX method of the ObsCaller class.
     */
    @Test
    public void testGetBottomLeftX() {
        ObsCaller obsCaller = new ObsCaller();
        assertEquals(0, obsCaller.getBottomLeftX());
    }

    /**
     * Tests the getBottomLeftY method of the ObsCaller class.
     */
    @Test
    public void testGetBottomLeftY() {
        ObsCaller obsCaller = new ObsCaller();
        assertEquals(0, obsCaller.getBottomLeftY());
    }

    /**
     * Tests the getTypeOfObstacle method of the ObsCaller class.
     */
    @Test
    public void testGetTypeOfObstacle() {
        ObsCaller obsCaller = new ObsCaller();
        assertEquals("", obsCaller.getTypeOfObstacle());
    }
}
