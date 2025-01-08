//import java.io.IOException;
//import java.net.Socket;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ConnectionTest {
//    private static final String SERVER_ADDRESS = "localhost";
//    private static final int SERVER_PORT = 2222;
//
//    @Test
//    public void testServerConnection() {
//        try {
//            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
//            assertTrue(socket.isConnected());
//            socket.close();
//        } catch (IOException e) {
//            fail("Failed to connect to server: " + e.getMessage());
//        }
//    }
//}
