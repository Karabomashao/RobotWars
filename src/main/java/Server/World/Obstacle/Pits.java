package Server.World.Obstacle;

import Server.Commands.CommandProperties;
import Server.MultiServerRunner;
import Server.World.Position;

import java.io.IOException;

import static Server.MultiServerRunner.config;

public class Pits implements Obs{

    private int bottomLeftX;
    private int bottomLeftY;
    private final CommandProperties obstacleCheck;

    public Pits(int a, int b){
        this.bottomLeftX = a;
        this.bottomLeftY = b;
        obstacleCheck = new CommandProperties();

    }

    @Override
    public String getTypeOfObstacle() {
        return "Pit";
    }

    @Override
    public boolean blocksPath(Position a, Position b) throws IOException {
        while (a.getX() != b.getX() || a.getY() != b.getY()){
            a = obstacleCheck.moveIncrementor(a, b);
            if (blocksPosition(a)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blocksPosition(Position position) throws IOException {
        MultiServerRunner.world.setTypeObstacle("Pits");
        return position.isIn(new Position(getBottomLeftX(), getBottomLeftY() + (getSize() - 1)),
                new Position(getBottomLeftX() + (getSize() - 1), getBottomLeftY()));
    }

    @Override
    public int getSize() throws IOException {

        return config("pitSize");
    }

    @Override
    public int getBottomLeftX() {
        return bottomLeftX;
    }

    @Override
    public int getBottomLeftY() {
        return bottomLeftY;
    }
}
