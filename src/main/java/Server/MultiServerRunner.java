package Server;

import Server.World.JsonStringResponses;
import Server.World.World;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Properties;

import static java.lang.System.out;

/**
 * This class represents a command to look around the robot's current position.
 * It checks for obstacles in the robot's line of sight and returns a status message indicating the distance and type of obstacles.
 */
public class MultiServerRunner {

    private static final int PORT = 2222;
    public static List<ClientHandler> connectedClients = Collections.synchronizedList(new ArrayList<>());
    public static World world;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
        System.out.println("Waiting for connection from client.");
        world = new World();
        world.setObstacles();


        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        // Loop for taking in keyboard commands
        Thread serverInputKeyboard = new Thread(() -> {
            try {
                while (true) {
                    String userInput = keyboardInput.readLine();

                    switch (userInput.toLowerCase()) {
                        case ("quit"):
                            JSONObject object = new JSONObject();
                            object.put("command", "quit");
                            System.out.println("Server is shutting down!!!");
                            sendResponseToAllClients(object.toString());
                            System.exit(0);
                            break;

                        case ("robots"):
                            String result = world.getRobotNames();
                            System.out.println(result);

                            break;

                        case ("dump"):
                            dumpServerState();
                            System.out.println("Obstacles:");
                            System.out.println(world.getObstacles());
                            break;

                        default:
                            System.out.println("Unknown command: " + userInput);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverInputKeyboard.start();

        // Loop for accepting clients and creating thread

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                    System.out.println("A new client has connected successfully to the server");

                // Create a ClientHandler instance for the new client connection
                ClientHandler robot = new ClientHandler(socket);
                Thread clientThread = new Thread(robot);
                clientThread.start();
                connectedClients.add(robot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void dumpServerState() {
        StringBuilder serverState = new StringBuilder();
        serverState.append("Connected Clients:\n");
        int numClients = connectedClients.size();
        for (int i = 0; i < numClients; i++) {
            serverState.append(getClientInfo(connectedClients.get(i)));
            if (i < numClients - 1) {
                serverState.append("\n");
            }
        }
    }

    static String getClientInfo(ClientHandler handler) {
        InetAddress clientAddress = handler.socket.getInetAddress();
        String clientHostname = clientAddress.getHostName();
        String clientIPAddress = clientAddress.getHostAddress();
        String status = "Client: " + clientHostname + " (" + clientIPAddress + ")";
        System.out.println(status);
        return status;
    }

    public static void sendResponseToAllClients(String message) {
        synchronized (connectedClients) {
            for (ClientHandler handler : connectedClients) {
                handler.sendMessage(message);
            }
        }
    }
    public static int config(String toConfig) throws IOException {

        Properties loadFile = new Properties();
        // Load the properties file from the classpath
        try (InputStream input = MultiServerRunner.class.getResourceAsStream("/config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Config file not found.");
            }
            loadFile.load(input);
        }
        return Integer.parseInt(loadFile.getProperty(toConfig));
    }

    public static int robotConfig(String make, String value){

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(MultiServerRunner.class.getResourceAsStream("/config.json"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            JSONObject config = new JSONObject(jsonContent.toString());
            JSONObject makeConfig = config.getJSONObject("makes").getJSONObject(make.toLowerCase());

            return makeConfig.getInt(value);

        } catch (Exception e) {
            JSONObject config = new JSONObject(jsonContent.toString());
            JSONObject makeConfig = config.getJSONObject("makes").getJSONObject("mabena");
            return makeConfig.getInt(value);
        }
    }
}
