import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import Client.LaunchRequestString;
import static org.junit.Assert.assertEquals;

public class LaunchRequestStringTest {

    /**
     * This JUnit test checks if the LaunchRequestString class correctly generates a launch request string based on a given make and configuration.
     */
    @Test
    public void testLaunchRequestString() {
        String make = "sniper";
        JSONObject config = readConfigFile();

        JSONObject makeObject = config.getJSONObject("makes").getJSONObject(make);

        String maxShield = String.valueOf(makeObject.getInt("maxShield"));
        String maxShots = String.valueOf(makeObject.getInt("maxShots"));

        LaunchRequestString launchRequest = new LaunchRequestString(make, maxShield, maxShots);

        String expectedLaunchRequest = "{\"arguments\":[\"sniper\",\"4\",\"5\"],\"command\":\"launch\"}";

        assertEquals(expectedLaunchRequest, launchRequest.getLaunchRequest());
    }

    /**
     * This method reads the configuration file named "config.json" from the "src/main/resources" directory
     * and returns it as a JSON object.
     * @return The JSON object representing the configuration file.
     */
    private static JSONObject readConfigFile() {
        try {
            String configPath = "src/main/resources/config.json";
            String configJson = new String(Files.readAllBytes(Paths.get(configPath)));
            return new JSONObject(configJson);
        } catch (IOException e) {
            throw new RuntimeException("Error reading config.json file", e);
        }
    }

}

