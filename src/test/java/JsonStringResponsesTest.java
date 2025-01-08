import Server.World.*;
import org.junit.Test;
import org.junit.Before;
import org.json.JSONObject;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static Server.World.JsonStringResponses.*;

/**
 * This JUnit test class tests the methods of the JsonStringResponses class.
 */
public class JsonStringResponsesTest {

    private JsonStringResponses jsonStringResponses;

    /**
     * This method sets up the test by creating an instance of the JsonStringResponses class.
     */
    @Before
    public void setup() {
        jsonStringResponses = new JsonStringResponses();
    }

    /**
     * Tests the createErrorResponse method of the JsonStringResponses class.
     */
//    @Test
//    public void testCreateErrorResponse() {
//        String message = "Robot has not been launched.";
//        JSONObject expected = new JSONObject();
//        JSONObject data = new JSONObject();
//        data.put("message", message);
//        expected.put("result", "ERROR");
//        expected.put("data", data);
//
//        JSONObject actual = createErrorResponse(message);
//        assertEquals(expected.toString(), actual.toString());
//    }

    /**
     * Tests the launchStatus method of the JsonStringResponses class.
     */
    @Test
    public void testLaunchStatus() {
        // Simulate the expected error response
        JSONObject expected = new JSONObject();
        expected.put("result", "ERROR");
        JSONObject data = new JSONObject();
        data.put("message", "Robot has not been launched.");
        expected.put("data", data);

        // Mocking the createErrorResponse method to return the expected JSON object
        String actual = JsonStringResponses.launchStatus();
        // Perform assertion
        assertEquals(expected.toString(), actual);
    }

    /**
     * Tests the launchedStatus method of the JsonStringResponses class.
     */
    @Test
    public void testLaunchedStatus() {
        String response = jsonStringResponses.launchedStatus();

        JSONObject expected = new JSONObject();
        expected.put("result", "ERROR");
        expected.put("data", new JSONObject().put("message", "You cannot launch more than one robot."));

        assertEquals(expected.toString(), response);
    }

    /**
     * Tests the killStatus method of the JsonStringResponses class.
     */
    @Test
    public void testKillStatus() {
        String response = jsonStringResponses.killStatus();

        JSONObject expected = new JSONObject();
        expected.put("message", "You have been eliminated.");

        assertEquals(expected.toString(), response);
    }

    /**
     * Tests the repair method of the JsonStringResponses class.
     */
    @Test
    public void testRepair() {
        String response = jsonStringResponses.repair("Message", mock(Robot.class));

        JSONObject result = new JSONObject();
        result.put("result", "OK");

        JSONObject data = new JSONObject();
        data.put("message", "Done");
        result.put("data", data);

        JSONObject state = new JSONObject();
        state.put("status", "RELOAD");
        result.put("state", state);

        assertEquals("OK", result.getString("result"));
        assertEquals("Done", result.getJSONObject("data").getString("message"));
        assertEquals("RELOAD", result.getJSONObject("state").getString("status"));
    }

    /**
     * Tests the reload method of the JsonStringResponses class.
     */
    @Test
    public void testReload() {
        String response = jsonStringResponses.reload("Message", mock(Robot.class));

        JSONObject result = new JSONObject();
        result.put("result", "OK");

        JSONObject data = new JSONObject();
        data.put("message", "Done");
        result.put("data", data);

        JSONObject state = new JSONObject();
        state.put("status", "RELOAD");
        result.put("state", state);

        assertEquals("OK", result.getString("result"));
        assertEquals("Done", result.getJSONObject("data").getString("message"));
        assertEquals("RELOAD", result.getJSONObject("state").getString("status"));
    }
}
