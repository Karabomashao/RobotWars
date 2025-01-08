package Client;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class represents a launch request for a robot.
 * It creates a JSON-formatted string containing the robot's name, the "launch" command, and the robot's make, maximum shield strength, and maximum shots.
 */
public class LaunchRequestString {

    private final String launchRequest;

    /**
     * Constructs a `LaunchRequestString` object with the given robot information.
     *
     * @param make       The make of the robot.
     * @param maxShield  The maximum shield strength of the robot.
     * @param maxShots   The maximum number of shots the robot can fire.
     */
    public LaunchRequestString(String make, String maxShield, String maxShots){
        JSONArray arguments = new JSONArray();
        arguments.put(make);
        arguments.put(maxShield);
        arguments.put(maxShots);

        JSONObject mainObject = new JSONObject();
        mainObject.put("command", "launch");
        mainObject.put("arguments", arguments);

        this.launchRequest = mainObject.toString();
    }

    /**
     * Returns the launch request string.
     */
    public String getLaunchRequest() {
        return launchRequest;
    }
}
