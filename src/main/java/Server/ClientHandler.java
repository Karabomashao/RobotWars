package Server;

import Server.Commands.Command;
import Server.World.*;
import Server.World.Obstacle.Obs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class handles client requests and communicates with the game world.
 */
public class ClientHandler implements Runnable {
    Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private Robot robot;
    private String robotName;
    private RobotCharacteristics robotChars;
    private boolean launchStatus = false;
    private boolean activeRobot = false;
    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * Initializes a new ClientHandler with a socket.
     * @param socket The socket to handle.
     * @throws IOException
     */
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintStream(socket.getOutputStream());
    }

    /**
     * Sends a message to the client.
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        out.println(message);
    }

    /**
     * Displays the robot menu to the client.
     * @param message The message to display.
     */
    public void robotMenu(String message) {
        out.println(message);
    }

    /**
     * Broadcasts a message to all clients except the specified one.
     * @param message The message to broadcast.
     * @param excludeClient The client to exclude from the broadcast.
     */
    private void broadcastMessage(String message, ClientHandler excludeClient) {
        lock.lock();
        try {
            for (ClientHandler handler : MultiServerRunner.connectedClients) {
                if (handler != excludeClient) {
                    handler.sendMessage(message);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Handles the client's request.
     * @see #run()
     */
    @Override
    public void run() {
        try {
            String request;
            while ((request = in.readLine()) != null) {
                try {
                    if (isValidJson(request)) {
                        handleJsonRequest(new JSONObject(request));
                    } else {
                        out.println(JsonStringResponses.invalidJsonRequest());
                    }
                } catch (IllegalArgumentException e) {
                    out.println(JsonStringResponses.invalidCommand());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    /**
     * Handles a JSON request from the client.
     * @param object The JSON object to handle.
     * @throws JSONException
     * @throws IOException
     */
    private void handleJsonRequest(JSONObject object) throws JSONException, IOException {
        String command = object.getString("command").toLowerCase();
        if (command.equals("launch") && !launchStatus) {
            handleLaunchCommand(object);
        } else if (launchStatus && command.equals("launch")) {
            out.println(activeRobot ? JsonStringResponses.launchedStatus() : JsonStringResponses.launchStatus());
        } else {
            handleCommandRequest(object);
        }
    }

    /**
     * Handles the launch command from the client.
     * @param object The JSON object to handle.
     */
    private void handleLaunchCommand(JSONObject object) {
        try {
            String robotName = object.getString("robot");
            JSONArray arguments = object.getJSONArray("arguments");
            String robotKind = arguments.getString(0);
            int shieldStrength = arguments.getInt(1);
            int shots = arguments.getInt(2);

            if (MultiServerRunner.world.robotExists(robotName)) {
                out.println(JsonStringResponses.nameTaken(robotName));
            } else {
                robotChars = new RobotCharacteristics(robotKind, shieldStrength, shots);
                createRobotStats(robotName);
                if (isPositionValid(robot.getPosition())) {
                    activateRobot();
                } else {
                    out.println(JsonStringResponses.invalidPosition());
                    broadcastRobotMessage(robotName, "position taken");
                }
            }
        } catch (JSONException | IOException e) {
            out.println(JsonStringResponses.invalidArgument());
        }
    }

    /**
     * Checks if a position is valid.
     * @param pos The position to check.
     * @return True if the position is valid, false otherwise.
     */
    private boolean isPositionValid(Position pos) throws IOException {
        if (!MultiServerRunner.world.robotInPlace(pos, robot)) {
            for (Obs obstacle : MultiServerRunner.world.getObsList()) {
                if (obstacle.blocksPosition(pos)) {
                    robot.setStatus(JsonStringResponses.movement(robot, true));
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Activates the robot.
     */
    private void activateRobot() {
        robot.setStatus(JsonStringResponses.launchResponse(robot, robotChars));
        out.println(robot.getStatus());
        MultiServerRunner.world.addRobot(robot);
        launchStatus = true;
        activeRobot = true;
        broadcastRobotMessage(robot.getName(), "Has joined the GAME!!!!");
    }

    /**
     * Broadcasts a robot message to all clients.
     * @param robotName The name of the robot.
     * @param message The message to broadcast.
     */
    private void broadcastRobotMessage(String robotName, String message) {
        JSONObject broadcastRobot = new JSONObject();
        broadcastRobot.put("name", robotName);
        broadcastRobot.put("message", message);
        broadcastMessage(broadcastRobot.toString(), this);
    }

    /**
     * Handles a command request from the client.
     * @param object The JSON object to handle.
     * @throws JSONException
     * @throws IOException
     */
    private void handleCommandRequest(JSONObject object) throws JSONException, IOException {
        if (robot != null) {
            String command = object.getString("command");
            JSONArray arguments = object.optJSONArray("arguments");

            if (command.equals("forward") || command.equals("back")) {
                if (arguments == null || arguments.length() != 1 || !arguments.getString(0).matches("\\d+")) {
                    out.println(JsonStringResponses.invalidCommand());
                } else {
                    Command command_;
                    command_ = Command.create(command + " " + arguments.get(0));
                    command_.execute(MultiServerRunner.world, robot.getName());

                    out.println(robot.getStatus());

                }
            } else {
                Command command_;
                if (arguments == null || arguments.length() == 0) {
                    command_ = Command.create(command);
                } else {
                    command_ = Command.create(command + " " + arguments.get(0));
                }

                command_.execute(MultiServerRunner.world, robot.getName());
                out.println(robot.getStatus());
            }
        } else {
            out.println(JsonStringResponses.launchStatus());
        }
    }

    /**
     * Creates the robot's statistics.
     * @param name The name of the robot.
     */
    private void createRobotStats(String name) throws IOException {
        robot = new Robot(name);
        robot.setShots(robotChars.getShots());
        robot.setShield(robotChars.getShield());
        robot.setRobotMake(robotChars.getRobotMake());
        robot.setBulletTravel(robotChars.getBulletTravel());
        robot.setVisibility(robotChars.getVisibility());
        robot.setMaxShot(robotChars.getMaxShots());
        robot.setMaxShield(robotChars.getMaxShield());
        robot.setRobotHandler(this);
    }

    /**
     * Cleans up the client's resources.
     */
    private void cleanup() {
        MultiServerRunner.connectedClients.remove(this);
        MultiServerRunner.world.getListOfRobots().remove(robot);
        broadcastRobotMessage(robotName, "Has left the GAME!!!!");
        out.println(new JSONObject().put("message", "You have exited the GAME!!!").toString());
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a JSON string is valid.
     * @param jsonString The JSON string to check.
     * @return True if the JSON string is valid, false otherwise.
     */
    public static boolean isValidJson(String jsonString) {
        try {
            new JSONObject(jsonString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
