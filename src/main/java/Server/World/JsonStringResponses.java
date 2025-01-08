package Server.World;

import org.json.JSONArray;
import org.json.JSONObject;

//import javax.crypto.KEMSpi;
import java.util.List;

public class JsonStringResponses {

    private static JSONObject createRobotState(Robot robot) {
        int[] coord = {robot.getPosition().getX(), robot.getPosition().getY()};
        JSONObject state = new JSONObject();
        state.put("position", coord);
        state.put("direction", robot.getDirection());
        state.put("shields", robot.getShield());
        state.put("shots", robot.getShots());
        state.put("status", robot.getStatus1());
        return state;
    }

    private static JSONObject createErrorResponse(String message) {
        JSONObject data = new JSONObject();
        data.put("message", message);

        JSONObject error = new JSONObject();
        error.put("result", "ERROR");
        error.put("data", data);

        return error;
    }

    public static String turnStateResponse(Robot robot) {
        JSONObject response = new JSONObject();
        response.put("message", "done");

        JSONObject root = new JSONObject();
        root.put("result", "OK");
        root.put("data", response);
        root.put("state", createRobotState(robot));

        return root.toString();
    }

    public static String state(Robot robotState) {
        JSONObject response = createRobotState(robotState);
        response.put("position", new int[]{robotState.getPosition().getX(), robotState.getPosition().getY()});

        JSONObject testing = new JSONObject();
        testing.put("name", robotState.getName());
        testing.put("result", "OK");
        testing.put("state", response);

        return testing.toString();
    }

    public static String movement(Robot robotState, boolean obstructed) {
        JSONObject data = new JSONObject();
        data.put("message", obstructed ? "Obstructed" : "Done");

        JSONObject result = new JSONObject();
        result.put("result", "OK");
        result.put("data", data);
        result.put("state", createRobotState(robotState));

        return result.toString();
    }

    public static String fire(String message, int distance, Robot attackedRobot, Robot robot) {
        JSONObject data = new JSONObject();
        data.put("message", message);
        data.put("distance", distance);
        data.put("robot", attackedRobot.getName());
        data.put("state", createRobotState(attackedRobot));

        JSONObject result = new JSONObject();
        result.put("result", "OK");
        result.put("data", data);
        result.put("state", new JSONObject().put("shots", robot.getShots()));

        JSONObject missData = new JSONObject();
        missData.put("message", "MISS");

        JSONObject missResult = new JSONObject();
        missResult.put("result", "OK");
        missResult.put("data", missData);
        missResult.put("state", new JSONObject().put("shots", robot.getShots()));

        if (message.equals("MISS")){
            return missResult.toString();
        }

        else {
            return result.toString();
        }
    }

    public static String invalidPosition() {
        return createErrorResponse("Position is taken ").toString();
    }

    public static String look(List<Integer> distanceOfObstruction, List<String> typeOfObstruction, Robot robot) {
        JSONArray objects = new JSONArray();

        String[] directions = {"North", "East", "South", "West"};
        for (int i = 0; i < 4; i++) {
            JSONObject obj = new JSONObject();
            obj.put("direction", directions[i]);
            obj.put("type", typeOfObstruction.get(i));
            obj.put("distance", distanceOfObstruction.get(i));
            objects.put(obj);
        }

        JSONObject data = new JSONObject();
        data.put("objects", objects);

        JSONObject result = new JSONObject();
        result.put("result", "OK");
        result.put("data", data);
        result.put("state", createRobotState(robot));

        return result.toString();
    }

    public static String Launch(String make, String maxShield, String maxShots, String name) {
        JSONArray arguments = new JSONArray();
        arguments.put(make);
        arguments.put(maxShield);
        arguments.put(maxShots);

        JSONObject mainObject = new JSONObject();
        mainObject.put("robot", name.toLowerCase());
        mainObject.put("command", "launch");
        mainObject.put("arguments", arguments);

        return mainObject.toString();
    }

    public static String launchResponse(Robot robot, RobotCharacteristics robotChars) {
        JSONArray position = new JSONArray();
        position.put(robot.getPosition().getX());
        position.put(robot.getPosition().getY());

        JSONObject data = new JSONObject();
        data.put("robotMake", robotChars.getRobotMake());
        data.put("position", position);
        data.put("visibility", robot.getVisibility());
        data.put("reload", 0);
        data.put("repair", 0);
        data.put("shields", robotChars.getMaxShield());

        JSONObject state = createRobotState(robot);
        state.put("direction", "NORTH");
        state.put("shields", robot.getShield());
        state.put("shots", robot.getShots());
        state.put("status", "NORMAL");

        JSONObject mainObject = new JSONObject();
        mainObject.put("result", "OK");
        mainObject.put("data", data);
        mainObject.put("state", state);

        return mainObject.toString();
    }

    public static String invalidCommand() {
        return createErrorResponse("Unsupported command").toString();
    }

    public static String invalidMove() {
        return createErrorResponse("Sorry, I can't go outside boundaries").toString();
    }

    public static String nameTaken(String name) {
        return createErrorResponse("Name " + name + " is taken").toString();
    }

    public static String invalidArgument() {
        return createErrorResponse("Could not parse arguments").toString();
    }

    public static String helpMenu() {
        JSONArray robots = new JSONArray();
        robots.put("Sniper (maxShield = 4) (maxShots = 5)");
        robots.put("Tank (maxShield = 8) (maxShots =  6)");
        robots.put("Assassin (maxShield = 3) (maxShots =  15)");
        robots.put("Spy (maxShield = 2) (maxShots =  10)");
        robots.put("Striker (maxShield = 5) (maxShots =  12)");
        robots.put("Commander (maxShield = 6) (maxShots =  10)");
        robots.put("Mabena (maxShield = 2) (maxShots =  3)");

        JSONObject result = new JSONObject();
        result.put("robots", robots);
        result.put("default robot", "mabena");
        result.put("exceeded limit", "Any value exceeding the limit of a robot will be set to the maximum of that robot");

        return result.toString();
    }

    public static String launchStatus() {
        return createErrorResponse("Robot has not been launched.").toString();
    }

    public static String launchedStatus() {
        return createErrorResponse("You cannot launch more than one robot.").toString();
    }

    public static String killStatus() {
        JSONObject message = new JSONObject();
        message.put("message", "You have been eliminated.");
        return message.toString();
    }

    public static String ditchStatus() {
        JSONObject message = new JSONObject();
        message.put("message", "You fell into a ditch.");
        return message.toString();
    }

    public static String invalidJsonRequest() {
        return createErrorResponse("Invalid json request").toString();
    }

    public static String repair(String message, Robot robot) {
        JSONObject result = new JSONObject();
        result.put("result", "OK");

        JSONObject data = new JSONObject();
        data.put("message", "Done");
        result.put("data", data);

        JSONObject state = new JSONObject();
        state.put("status", message);
        result.put("state", state);

        return result.toString();

    }

    public static String reload(String message, Robot robot) {
        JSONObject result = new JSONObject();
        result.put("result", "OK");

        JSONObject data = new JSONObject();
        data.put("message", "Done");
        result.put("data", data);

        JSONObject state = new JSONObject();
        state.put("status", message);
        result.put("state", state);

        return result.toString();

    }
}