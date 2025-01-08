import Server.Commands.CommandProperties;
import Server.Commands.Fire;
import Server.MultiServerRunner;
import Server.World.*;
import Server.World.Obstacle.Obs;
import Server.World.Obstacle.ObsCaller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FireTest {

    private World testWorld;
    private Robot testRobot;
    private Robot enemyRobot;
    private Fire fireCommand;

    @BeforeEach
    public void setUp() throws IOException {
        // Setup the test world and robots
        testWorld = new World();
        testRobot = new Robot("testRobot");
        enemyRobot = new Robot("enemyRobot");

        // Configure the robots
        testRobot.setBulletTravel(5);
        testRobot.setShots(1);
        enemyRobot.setShield(1);

        // Add robots to the world
        List<Robot> robots = new ArrayList<>();
        robots.add(testRobot);
        robots.add(enemyRobot);
        testWorld.getListOfRobots();

        // Set up MultiServerRunner to use the test world
        MultiServerRunner.world = testWorld;
        fireCommand = new Fire();
    }


    @Test
    public void testExecute_NoShotsLeft() throws IOException {
        // Set up the position and robot with no shots left
        testRobot.setShots(0);

        // Execute the command
        fireCommand.execute(testWorld, "testRobot");

        // Verify no actions are performed
        assertEquals(0, testRobot.getShots());
        assertEquals(1, enemyRobot.getShield());
        assertNull(testRobot.getStatus());
        assertNull(enemyRobot.getStatus());
    }


    @Test
    public void testExecute_RobotNotFound() throws IOException {
        // Execute the command with a non-existent robot
        fireCommand.execute(testWorld, "nonExistentRobot");

        // Verify no actions are performed
        assertEquals(1, testRobot.getShots());
        assertEquals(1, enemyRobot.getShield());
        assertNull(testRobot.getStatus());
        assertNull(enemyRobot.getStatus());
    }
}
