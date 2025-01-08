import java.io.IOException;
import org.junit.jupiter.api.Test;
import Server.World.Obstacle.Walls;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This JUnit test class tests the methods of the Walls class.
 */
public class WallsTest {

    /**
     * Tests the getTypeOfObstacle method of the Walls class.
     */
    @Test
    public void testGetTypeOfObstacle() {
        Walls walls = new Walls(0, 0);
        assertEquals("Wall", walls.getTypeOfObstacle());
    }

    /**
     * Tests the getSize method of the Walls class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testGetSize() throws IOException {
        Walls walls = new Walls(0, 0);
        assertEquals(6, walls.getSize());
    }

    /**
     * Tests the getBottomLeftX method of the Walls class.
     */
    @Test
    public void testGetBottomLeftX() {
        Walls walls = new Walls(0, 0);
        assertEquals(0, walls.getBottomLeftX());
    }

    /**
     * Tests the getBottomLeftY method of the Walls class.
     */
    @Test
    public void testGetBottomLeftY() {
        Walls walls = new Walls(0, 0);
        assertEquals(0, walls.getBottomLeftY());
    }
}
