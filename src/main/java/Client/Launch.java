package Client;

import java.util.Scanner;
import static java.lang.System.out;

/**
 * This class represents a client-side launcher for robots.
 * It prompts the user to enter the robot's make, maximum shield strength, and maximum shots.
 * The entered values are then used to create a `LaunchRequestString` object.
 */
public class Launch {

    /**
     * Executes the launch process by prompting the user for input and creating a `LaunchRequestString` object.
     *
     * @return The launch request string.
     */
    public String execute() {

        Scanner scanner = new Scanner(System.in);

        out.println("Enter robot make");
        String make_ = scanner.nextLine();

        out.println("Enter maximum shield strength");
        String maxShield = scanner.nextLine();

        out.println("Enter maximum shots");
        String maxShots = scanner.nextLine();

        LaunchRequestString launchRequest = new LaunchRequestString(make_, maxShield, maxShots);
        return launchRequest.getLaunchRequest();
    }
}
