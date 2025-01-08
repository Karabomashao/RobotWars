package Client;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;


//public class LaunchTest {
//
//    private Launch launch;
//
//    @BeforeEach
//    public void setUp() {
//        launch = new Launch();
//    }
//
//        /**
//     * Test the execute method with valid inputs.
//     * It should return a correctly formatted launch request string.
//     */
//    @Test
//    public void testExecuteWithValidInputs() {
//        String input = "TestMake\n100\n10\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        String expectedOutput = "LaunchRequest: TestMake, 100, 10";
//        String actualOutput = launch.execute();
//
//        Assertions.assertEquals(expectedOutput, actualOutput);
//    }
//
//        /**
//     * Test the execute method with empty inputs.
//     * It should handle empty inputs gracefully.
//     */
//    @Test
//    public void testExecuteWithEmptyInputs() {
//        String input = "\n\n\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        String expectedOutput = "LaunchRequest: , , ";
//        String actualOutput = launch.execute();
//
//        Assertions.assertEquals(expectedOutput, actualOutput);
//    }
//
//        /**
//     * Test the execute method with edge case numeric inputs.
//     * It should handle large numeric inputs correctly.
//     */
//    @Test
//    public void testExecuteWithEdgeCaseNumericInputs() {
//        String input = "EdgeMake\n999999999\n0\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        String expectedOutput = "LaunchRequest: EdgeMake, 999999999, 0";
//        String actualOutput = launch.execute();
//
//        Assertions.assertEquals(expectedOutput, actualOutput);
//    }
//}

//    @AfterEach
//    public void restoreSystemInputStream() {
//        System.setIn(System.in);
//    }
//}
//
//    @AfterEach
//    public void restoreSystemInputStream() {
//        System.setIn(System.in);
//    }
//}

