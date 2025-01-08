package Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import static java.lang.System.out;

/**
 * This class represents a client that connects to a server and handles incoming messages.
 */
public class IncomingClient {
    private static final int PORT = 2222;
    public static String messageFromServer = "";
    public static String robotName;

    /**
     * The main method establishes a connection to the server.
     * The main method handles incoming messages from the sever with a thread.
     * The main method also takes in input from the user via keyboard.
     * @throws IOException If there is an error connecting to the server
     */
    public static void main(String[] args) throws IOException {



        if (args.length == 2) {
            try (
                    Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                    BufferedReader messageFromHandle = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
                    PrintStream messageToHandler = new PrintStream(socket.getOutputStream())
            ) {
                // Handle incoming messages from the server
                Thread clientServerMessages = new Thread(() -> {
                    try {
                        // Handle incoming messages from the server
                        String message;

                        while ((message = messageFromHandle.readLine()) != null) {
                            messageFromServer = message;
                            JSONObject object = new JSONObject(message);
                            // If the user enters the command quit, the program must stop running
                            if (message.contains("quit") ||  message.contains("shut")) {
                                System.out.println("Server has been closed!!!");
                                System.out.println("Client is shutting down!!!");
                                System.exit(0);

                            } else if (!object.toString().contains("equals")) {
                                System.out.println("Response: " + object.toString(2));
                                System.out.println("What do you want to do next:");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Server has shutdown");
                        System.exit(0);

                    } catch (JSONException i){
                        System.out.println("Message from server may not be a json string request");
                        System.out.println("Actual message: " + messageFromServer);
                        System.out.println("Client is shutting down...");
                        System.exit(0);

                    }finally {
                        out.println("Server may have been closed");
                        out.println("Client is shutting down");
                        System.exit(0);
                    }
                });
                clientServerMessages.start();

                // Handle user input and send commands to the server
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter robot name:");
                robotName = scanner.nextLine();
                out.println("Launch the robot by using command 'launch': ");


                while (true) {
                    // Handle user input and send commands to the server
                    String command = keyboardInput.readLine();
                    String[] commands = command.split(" ");

                    JSONObject object = new JSONObject();
                    JSONArray arguments = new JSONArray();

                    if (commands.length > 1) {
                        arguments.put(commands[1]);
                    }

                    object.put("command", commands[0]);
                    object.put("arguments", arguments);
                    object.put("robot", robotName);

                    switch (object.getString("command").toLowerCase()) {
                        case ("quit"):
                            System.out.println("Client is shutting down!!!");
                            System.exit(0);

                        case ("launch"):
                            Launch startRobot = new Launch();
                            String request = startRobot.execute();
                            JSONObject finalRequest = new JSONObject(request);
                            finalRequest.put("robot", robotName.toLowerCase().strip());
                            messageToHandler.println(finalRequest);
                            break;

                        default:
                            messageToHandler.println(object);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error connecting to server: " + e.getMessage());
            }
            catch (NumberFormatException o){
                System.out.println("A value consisting of only integers must be given");
            }
        }
        else {
            System.out.println("Invalid number of command line arguments");
        }
    }
}
