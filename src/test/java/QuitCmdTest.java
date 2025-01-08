//import Client.QuitCmd;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.PrintStream;
//
//import static org.mockito.Mockito.verify;
//
//class QuitCmdTest {
//
//    @Test
//    void testExecute() {
//        PrintStream mockOut = Mockito.mock(PrintStream.class);
//        QuitCmd quitCmd = new QuitCmd();
//
//        quitCmd.execute(mockOut);
//
//        verify(mockOut).println("quit");
//    }
//}