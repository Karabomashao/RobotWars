package Server.Commands;

import Server.World.World;
import java.io.IOException;

/**
 * This abstract class represents a command that can be executed in a game.
 * It provides a common interface for all commands, including methods for executing the command and getting its name and argument.
 */
public abstract class Command {
    private final String name;
    private String argument;

    /**
     * Execute the command.
     *
     * @param target the world where the command is executed
     * @param name the name of the robot to execute the command on
     * @throws IOException if the command fails
     */
    public abstract void execute(World target, String name) throws IOException;

    /**
     * Constructor for the Command class with an argument.
     * @param name the name of the command
     */
    public Command(String name) {
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    /**
     * Get the name of the command.
     * @return the name of the command
     */
    public String getName() {                                                                           //<2>
        return name;
    }

    /**
     * Get the argument for the command.
     * @return the argument for the command
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Create a command based on the instruction.
     * @param instruction the instruction to create a command from
     * @return the created command
     */
    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "forward":
                if (args.length == 2) {
                    return new Forward(args[1]);
                }else{
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }

            case "back":
                if(args.length == 2) {
                    return new Back(args[1]);
                }else{
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }

            case "turn":
                if (args.length == 2) {
                    if (args[1].equals("right")) {
                        return new Right(args[1]);
                    } else if (args[1].equals("left")) {
                        return new Left(args[1]);
                    }
                }
                else{
                    throw new IllegalArgumentException();
                }

            case "look":
                if (args.length == 1) {
                    return new Look();
                }else{
                    throw new IllegalArgumentException();
                }

            case "fire":
                if (args.length == 1) {
                    return new Fire();
                }else{
                    throw new IllegalArgumentException();
                }

            case "state":
                if (args.length == 1) {
                    return new State();
                }else{
                    throw new IllegalArgumentException();
                }

            case "reload":
                if (args.length == 1) {
                    return new Reload();
                }else {
                    throw new IllegalArgumentException();
                }

            case "repair":
                if(args.length == 1) {
                    return new Repair();
                }else {
                    throw new IllegalArgumentException();
                }

            default:
                throw new IllegalArgumentException();
        }
    }
}

