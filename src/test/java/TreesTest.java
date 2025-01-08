import java.io.IOException;
import org.junit.jupiter.api.Test;
import Server.World.Obstacle.Trees;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit test class tests the methods of the Trees class.
 */
public class TreesTest {

    /**
     * Tests the getTypeOfObstacle method of the Trees class.
     */
    @Test
    public void testGetTypeOfObstacle() {
        Trees trees = new Trees(0, 0);
        assertEquals("Tree", trees.getTypeOfObstacle());
    }

    /**
     * Tests the getSize method of the Trees class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testGetSize() throws IOException {
        Trees trees = new Trees(0, 0);
        assertEquals(5, trees.getSize());
    }

    /**
     * Tests the getBottomLeftX method of the Trees class.
     */
    @Test
    public void testGetBottomLeftX() {
        Trees trees = new Trees(0, 0);
        assertEquals(0, trees.getBottomLeftX());
    }

    /**
     * Tests the getBottomLeftY method of the Trees class.
     */
    @Test
    public void testGetBottomLeftY() {
        Trees trees = new Trees(0, 0);
        assertEquals(0, trees.getBottomLeftY());
    }
}
