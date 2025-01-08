//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import Server.MultiServerRunner;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class MultiServerTest {
//
//    @Test
//    public void testInvalidCommand() {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream("foobar\n".getBytes());
//        System.setIn(inputStream);
//        System.setOut(new PrintStream(outputStream));
//
//        // Start the server in a separate thread
//        Thread serverThread = new Thread(() -> {
//            try {
//                MultiServerRunner.main(null);
//            } catch (Exception ignored) {
//            }
//        });
//        serverThread.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ignored) {
//        }
//
//        assertTrue(outputStream.toString().contains("Unknown command: foobar"));
//    }
//
//    @Test
//    public void testDumpCommand() {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream("dump\n".getBytes());
//        System.setIn(inputStream);
//        System.setOut(new PrintStream(outputStream));
//
//        // Start the server in a separate thread
//        Thread serverThread = new Thread(() -> {
//            try {
//                MultiServerRunner.main(null);
//            } catch (Exception ignored) {
//            }
//        });
//        serverThread.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ignored) {
//        }
//
//        assertTrue(outputStream.toString().contains("What do you want to do:"));
//    }
//
//}
