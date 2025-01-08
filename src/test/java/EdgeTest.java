import java.io.IOException;
import Server.World.Obstacle.Edge;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This JUnit test class tests the methods of the Edge class.
 */
public class EdgeTest {

    /**
     * Tests the getTypeOfObstacle method of the Edge class.
     **/
    @Test
    public void testGetTypeOfObstacle() {
        Edge edge = new Edge(0, 0, "wall");
        assertEquals("wall", edge.getTypeOfObstacle());
    }

    /**
     * Tests the getSize method of the Edge class.
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testGetSize() throws IOException {
        Edge edge = new Edge(0, 0, "wall");
        assertEquals(1, edge.getSize());
    }

    /**
     * Tests the getBottomLeftX method of the Edge class.
     */
    @Test
    public void testGetBottomLeftX() {
        Edge edge = new Edge(0, 0, "wall");
        assertEquals(0, edge.getBottomLeftX());
    }

    /**
     * Tests the getBottomLeftY method of the Edge class.
     */
    @Test
    public void testGetBottomLeftY() {
        Edge edge = new Edge(0, 0, "wall");
        assertEquals(0, edge.getBottomLeftY());
    }
}
