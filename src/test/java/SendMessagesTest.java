//import Client.SendMessages;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class SendMessagesTest {
//
//    @Test
//    void testExecute() {
//        String message = "Test Message";
//        SendMessages sendMessages = new SendMessages(message);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//
//        sendMessages.execute(printStream);
//
//        assertEquals(message + System.lineSeparator(), outputStream.toString());
//    }
//}