
import java.awt.*;
import java.io.*;
import java.net.Socket;
import org.mockito.Mockito;
import Server.ClientHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@Nested
class ClientHandlerTest {

    @Test
    public void testClientHandler() {

        Socket mockSocket = createMockSocket();
        assertNotNull(mockSocket);
    }

    private Socket createMockSocket() {
        return new Socket() {
            @Override
            public InputStream getInputStream() {
                return new ByteArrayInputStream("Test Input".getBytes());
            }

            @Override
            public OutputStream getOutputStream() {
                return new ByteArrayOutputStream();
            }
        };
    }

    @Test
    void testGetRobots() {
        Robot robot = mock(Robot.class);
    }

//    @Test
//    void testSendMessage() throws IOException {
//        Socket mockSocket = mock(Socket.class);
//        InputStream inputStream = new ByteArrayInputStream("launch".getBytes());
//        when(mockSocket.getInputStream()).thenReturn(inputStream);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        when(mockSocket.getOutputStream()).thenReturn(outputStream);
//
//        ClientHandler clientHandler = new ClientHandler(mockSocket);
//
//        clientHandler.run();
//
//        assertEquals("No robots launched for this client!", outputStream.toString().trim());
//    }

    @Test
    void testGetClientInfo() {
        try {
            Socket testSocket = new Socket();
            ClientHandler clientHandler = new ClientHandler(testSocket);
            // assertNotNull(clientHandler.getClientInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}