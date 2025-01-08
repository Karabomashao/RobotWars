package Server.World.Obstacle;

import Server.Commands.CommandProperties;
import Server.MultiServerRunner;
import Server.World.Position;

public class Obstacles {
//    private int bottomLeftX;
//    private int bottomLeftY;
//    private final CommandProperties obstacleCheck;
//
//    public Obstacles(int a, int b){
//        bottomLeftX = a;
//        bottomLeftY = b;
//        obstacleCheck = new CommandProperties();
//    }
//
//    public Obstacles(){
//        obstacleCheck = new CommandProperties();
//    }
//
//    public int getBottomLeftX() {
//        return bottomLeftX;
//    }
//
//    public int getBottomLeftY() {
//        return bottomLeftY;
//    }
//    public boolean blocksPosition(Position position) {
//        return position.isIn(new Position(getBottomLeftX(), getBottomLeftY() + (getSize() - 1)),
//                new Position(getBottomLeftX() + (getSize() - 1), getBottomLeftY()));
//    }
//
//    public int getSize() {
//        return 5;
//    }
//
//    public int distanceOfObstacle(Position a, Position b) {
//
//        for (Obstacles obstacle : MultiServerRunner.world.getObstacles_()) {
//            int count = 0;
//            Position start = a;
//            while (start.getX() != b.getX() || start.getY() != b.getY()) {
//                start = obstacleCheck.moveIncrementor(start, b);
//                count += 1;
//                if (obstacle.blocksPosition(start)) {
//                    return count;
//                }
//            }
//        }
//        return 0;
//    }
//
//    public int distanceOfPit(Position a, Position b) {
//
//        for (Obstacles pit : MultiServerRunner.world.getPits_()) {
//            int count = 0;
//            Position start = a;
//            while (start.getX() != b.getX() || start.getY() != b.getY()) {
//                start = obstacleCheck.moveIncrementor(start, b);
//                count += 1;
//                if (pit.blocksPosition(start)) {
//                    return count;
//                }
//            }
//        }
//        return 0;
//    }
//
//    public int distanceOfTree(Position a, Position b) {
//
//        for (Obstacles tree : MultiServerRunner.world.getTrees_()) {
//            int count = 0;
//            Position start = a;
//            while (start.getX() != b.getX() || start.getY() != b.getY()) {
//                start = obstacleCheck.moveIncrementor(start, b);
//                count += 1;
//                if (tree.blocksPosition(start)) {
//                    return count;
//                }
//            }
//        }
//        return 0;
//    }
//
//    public boolean blocksPath(Position a, Position b) {
//
//        while (a.getX() != b.getX() || a.getY() != b.getY()){
//
//            a = obstacleCheck.moveIncrementor(a, b);
//            if (blocksPosition(a)){return true;}
//        }
//        return false;
//    }
}
