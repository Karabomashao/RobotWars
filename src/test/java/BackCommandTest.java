import org.junit.jupiter.api.Test;
import Server.Commands.Back;
import Server.MultiServerRunner;
import Server.World.Position;
import Server.World.Robot;
import Server.World.UpdateRobotPosition;
import Server.World.World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackCommandTest {

//    @Test
//    public void testBackCommand() throws IOException {
//        // Set up the world
//        World world = new World();
//        MultiServerRunner.world = world;
//
//        // Create a robot
//        Robot robot = new Robot("robot1"); // Corrected constructor call
//        world.getListOfRobots().add(robot);
//
//        // Create a Back command
//        Back backCommand = new Back("5");
//
//        // Execute the command
//        try {
//            backCommand.execute(world, "robot1");
//        } catch (IOException e) {
//            // Handle the exception
//            fail("IOException was not expected");
//        }
//
//        // Check the robot's position
//        assertEquals(new Position(0, -5), robot.getPosition());
//    }
//
//    @Test
//    public void testBackCommandInvalidArgument() throws IOException {
//        // Set up the world
//        World world = new World();
//        MultiServerRunner.world = world;
//
//        // Create a robot
//        Robot robot = new Robot("robot1"); // Corrected constructor call
//        world.getListOfRobots().add(robot);
//
//        // Create a Back command with invalid argument
//        Back backCommand = new Back("abc");
//
//        // Check if an exception is thrown
//        try {
//            backCommand.execute(world, "robot1");
//            fail("IOException was not expected");
//        } catch (IOException e) {
//            // Handle the exception
//        }
//    }
//
//    @Test
//    public void testBackCommandNonExistingRobot() throws IOException {
//        // Set up the world
//        World world = new World();
//        MultiServerRunner.world = world;
//
//        // Create a Back command
//        Back backCommand = new Back("5");
//
//        // Check if an exception is thrown
//        try {
//            backCommand.execute(world, "nonExistingRobot");
//            fail("IOException was not expected");
//        } catch (IOException e) {
//            // Handle the exception
//        }
//    }
}