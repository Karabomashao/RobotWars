package Server.World;

import Server.Commands.CommandProperties;
import Server.World.Obstacle.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static Server.MultiServerRunner.config;
import static Server.World.JsonStringResponses.state;

public class World {
    private final Position TOP_LEFT;
    private final Position BOTTOM_RIGHT;
    private List<Obstacles> obstacles_;
    private List<Obs> obsList;
    private List<Obs> edgeList;
    private List<Obstacles> pits_;
    private List<Obstacles> trees_;
    private List<Robot> robots;
    private Robot robotAttacked = new Robot("Not null");
    private String typeObstacle = "test";
    private final CommandProperties props;

    public World() throws IOException {
        TOP_LEFT = new Position(-config("Width"), config("Height"));
        BOTTOM_RIGHT = new Position(config("Width"), -config("Height"));
        obsList = new ArrayList<>();
        edgeList = new ArrayList<>();
        pits_ = new ArrayList<>();
        trees_ = new ArrayList<>();
        robots = new ArrayList<>();
        props = new CommandProperties();

    }

    public String getTypeObstacle() {
        return typeObstacle;
    }

    public void setTypeObstacle(String type) {
        typeObstacle = type;
    }

    public synchronized boolean robotExists(String name) {
        for (Robot robot : robots) {
            if (robot.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Obs> getObsList() {
        return obsList;
    }

    public List<Obs> getEdgeList() {
        return edgeList;
    }

    public Robot getRobotAttacked() {
        return robotAttacked;
    }

    public synchronized void addRobot(Robot robot) {
        robots.add(robot);
    }

    public Position getTOP_LEFT() {
        return TOP_LEFT;
    }

    public Position getBOTTOM_RIGHT() {
        return BOTTOM_RIGHT;
    }

    public List<Robot> getListOfRobots() {
        return robots;
    }

    public synchronized String getRobotNames() {
        StringBuilder result = new StringBuilder();
        if (robots.isEmpty()) {
            return "No robots present in the world!!!";
        }
        for (Robot robot : robots) {
            result.append(state(robot)).append("\n");
        }
        return result.toString();
    }


    public boolean robotInPlace(Position b, Robot robot) {

        Position a = robot.getPosition();
        while (a.getX() != b.getX() || a.getY() != b.getY()) {
            a = props.moveIncrementor(a, b);
            if (isRobotInPlace(a, robot.getName())) {
                return true;
            }
        }
        return false;
    }

    public int distanceOfRobot(Robot robot, Position b) {

        int count = 0;

        Position a = robot.getPosition();
        while (a.getX() != b.getX() || a.getY() != b.getY()) {
            a = props.moveIncrementor(a, b);
            count += 1;
            if (isRobotInPlace(a, robot.getName())) {
                return count;
            }
        }
        count = 0;
        return count;
    }

    public boolean isRobotInPlace(Position position, String name) {
        for (Robot robot : robots) {
            if (!name.equals(robot.getName())
                    && robot.getPosition().equals(position)) {
                robotAttacked = robot;
                return true;
            }
        }
        return false;
    }

    public void setObstacles() throws IOException {
        int minX = -96, maxX = 96;
        int minY = -196, maxY = 196;

        Random ranVal = new Random();
        int numPits = config("numPits");
        int numTrees = config("numTrees");
        int numWalls = config("numWalls");

        int pitsCount = 0;
        int treesCount = 0;
        int wallsCount = 0;

        Obs pitObstacle;
        Obs treeObstacle;
        Obs wallObstacle;

        while (pitsCount < numPits || treesCount < numTrees || wallsCount < numWalls) {
            if (pitsCount < config("numPits")){
                pitObstacle = new Pits(ranVal.nextInt((maxX - minX + 1)) + minX,ranVal.nextInt((maxY - minY + 1)) + minY);
                if (!isPathBlocked(pitObstacle)){
                    obsList.add(pitObstacle);
                    pitsCount+=1;
                }
            }

            if (treesCount < config("numTrees")){
                treeObstacle = new Trees(ranVal.nextInt((maxX - minX + 1)) + minX,
                        ranVal.nextInt((maxY - minY + 1)) + minY);
                if (!isPathBlocked(treeObstacle)){
                    obsList.add(treeObstacle);
                    treesCount+=1;
                }
            }

            if (wallsCount < config("numWalls")){
                wallObstacle = new Walls(ranVal.nextInt((maxX - minX + 1)) + minX,
                        ranVal.nextInt((maxY - minY + 1)) + minY);
                if (!isPathBlocked(wallObstacle)){
                    obsList.add(wallObstacle);
                    wallsCount+=1;
                }
            }

        }

        setEdges();
    }

    public boolean isPathBlocked(Obs newObstacle) throws IOException {
        Position newPosition = new Position(newObstacle.getBottomLeftX(), newObstacle.getBottomLeftY());
        for (Obs existingObstacle : obsList) {
            if (existingObstacle.blocksPosition(newPosition)) {
                return true;
            }
        }
        return false;
    }

    public String getObstacles() {
        StringBuilder trees = new StringBuilder();
        StringBuilder pits = new StringBuilder();
        StringBuilder walls = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (Obs pos : obsList) {
            if (pos.getTypeOfObstacle().equals("Tree")){
                trees.append("[").append(pos.getBottomLeftX()).append(",").append(pos.getBottomLeftY()).append("]").append("\n");
            }
            else if (pos.getTypeOfObstacle().equals("Wall")){
                walls.append("[").append(pos.getBottomLeftX()).append(",").append(pos.getBottomLeftY()).append("]").append("\n");
            }
            else if (pos.getTypeOfObstacle().equals("Pit")){
                pits.append("[").append(pos.getBottomLeftX()).append(",").append(pos.getBottomLeftY()).append("]").append("\n");
            }
        }
        result.append("Trees\n").append(trees).append("\n").append("Pits\n").append(pits).append("\n").append("Walls\n").append(walls);
        return result.toString();
    }


    private void setEdges() throws IOException {
        int edgeSize = config("edgeSize");

        // North Edges
        for (int x = -100; x <= 100; x += edgeSize) {
            obsList.add(new Edge(x, 200, "North Edge"));
        }

        // South Edges
        for (int x = -100; x <= 100; x += edgeSize) {
            obsList.add(new Edge(x, -200, "South Edge"));
        }

        // East Edges
        for (int y = -200; y <= 200; y += edgeSize) {
            obsList.add(new Edge(100, y, "East Edge"));
        }

        // West Edges
        for (int y = -200; y <= 200; y += edgeSize) {
            obsList.add(new Edge(-100, y, "West Edge"));
        }
    }


}
