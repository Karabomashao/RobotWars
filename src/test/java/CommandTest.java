import Server.Commands.Command;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandTest {

    @Test
    public void testCreateCommand() {
        Command command = Command.create("forward 5");
        assertEquals("forward", command.getName());
        assertEquals("5", command.getArgument());
    }

//    @Test
//    public void testUnsupportedCommand() {
//        try {
//            Command.create("unsupported");
//            fail("Expected an exception");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Unsupported command: unsupported", e.getMessage());
//        }
//    }

    @Test
    public void testCreateForwardCommand() {
        Command command = Command.create("forward 5");
        assertEquals("forward", command.getName());
        assertEquals("5", command.getArgument());
    }

    @Test
    public void testCreateBackCommand() {
        Command command = Command.create("back 5");
        assertEquals("back", command.getName());
        assertEquals("5", command.getArgument());
    }

    @Test
    public void testCreateLookCommand() {
        Command command = Command.create("look");
        assertEquals("look", command.getName());
        assertEquals("", command.getArgument());
    }

    @Test
    public void testCreateStateCommand() {
        Command command = Command.create("state");
        assertEquals("state", command.getName());
        assertEquals("", command.getArgument());
    }

    @Test
    public void testCreateReloadCommand() {
        Command command = Command.create("reload");
        assertEquals("reload", command.getName());
        assertEquals("", command.getArgument());
    }
}
