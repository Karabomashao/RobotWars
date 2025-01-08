import java.io.IOException;
import Server.World.Obstacle.Pits;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit test class tests the methods of the Pits class.
 */
public class PitsTest {

    /**
     * Tests the getTypeOfObstacle method of the Pits class.
     */
    @Test
    public void testGetTypeOfObstacle() {
        Pits pits = new Pits(0, 0);
        assertEquals("Pit", pits.getTypeOfObstacle());
    }

    /**
     * Tests the getSize method of the Pits class.
     *
     * @throws IOException - An exception is expected if there is an issue reading the file.
     */
    @Test
    public void testGetSize() throws IOException {
        Pits pits = new Pits(0, 0);
        assertEquals(3, pits.getSize());
    }

    /**
     * Tests the getBottomLeftX method of the Pits class.
     */
    @Test
    public void testGetBottomLeftX() {
        Pits pits = new Pits(0, 0);
        assertEquals(0, pits.getBottomLeftX());
    }

    /**
     * Tests the getBottomLeftY method of the Pits class.
     */
    @Test
    public void testGetBottomLeftY() {
        Pits pits = new Pits(0, 0);
        assertEquals(0, pits.getBottomLeftY());
    }
}
