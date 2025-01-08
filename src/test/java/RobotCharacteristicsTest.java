import org.junit.Test;
import static org.junit.Assert.*;
import Server.World.RobotCharacteristics;

/**
 * This JUnit test class tests the methods of the RobotCharacteristics class.
 */
public class RobotCharacteristicsTest {

    /**
     * Tests the getters method of the RobotCharacteristics class.
     */
    @Test
    public void testGetters() {
        RobotCharacteristics robotCharacteristics = new RobotCharacteristics("mabena", 100, 50);

        assertEquals("mabena", robotCharacteristics.getRobotMake());
        assertEquals(2, robotCharacteristics.getShield());
        assertEquals(3, robotCharacteristics.getShots());
        assertEquals(2, robotCharacteristics.getMaxShield());
        assertEquals(3, robotCharacteristics.getMaxShots());
    }

    /**
     * Tests the setChars method of the RobotCharacteristics class.
     */
//    @Test
//    public void testSetChars() {
//        RobotCharacteristics robotCharacteristics = new RobotCharacteristics("mabena", 100, 50);
//
//        assertEquals(2, robotCharacteristics.getShield());
//        assertEquals(3, robotCharacteristics.getShots());
//
//        robotCharacteristics.setChars("sniper");
//
//        assertEquals(2, robotCharacteristics.getShield());
//        assertEquals(3, robotCharacteristics.getShots());
//    }

    /**
     * Tests the default values of the RobotCharacteristics class.
     */
    @Test
    public void testDefaultValues() {
        RobotCharacteristics robotCharacteristics = new RobotCharacteristics("mabena", 100, 50);

        assertEquals(2, robotCharacteristics.getShield());
        assertEquals(3, robotCharacteristics.getShots());
    }
}

