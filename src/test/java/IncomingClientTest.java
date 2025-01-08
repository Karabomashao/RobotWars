//import Client.IncomingClient;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.*;
//import java.net.Socket;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//class IncomingClientTest {
//
//    @Test
//    void testClientInteraction() throws IOException {
//        String testInput = "test command";
//        String testResponse = "Test Response";
//
//        Socket mockSocket = Mockito.mock(Socket.class);
//        BufferedReader mockMessageFromHandle = new BufferedReader(new StringReader(testResponse + System.lineSeparator()));
//        BufferedReader mockKeyboardInput = new BufferedReader(new StringReader(testInput + System.lineSeparator()));
//        PrintStream mockMessageToHandler = Mockito.mock(PrintStream.class);
//
//        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream(testResponse.getBytes()));
//        when(mockSocket.getOutputStream()).thenReturn(System.out);
//
//        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
//        System.setOut(new PrintStream(new ByteArrayOutputStream()));
//
//        IncomingClient.main(new String[]{});
//
//        assertEquals(testInput, mockKeyboardInput.readLine());
//        assertEquals(testResponse, mockMessageFromHandle.readLine());
//    }
//}