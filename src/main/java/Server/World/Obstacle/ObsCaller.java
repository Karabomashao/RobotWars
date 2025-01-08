package Server.World.Obstacle;

import Server.World.Position;

public class ObsCaller implements Obs{
    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

    @Override
    public boolean blocksPosition(Position position) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getBottomLeftX() {
        return 0;
    }

    @Override
    public int getBottomLeftY() {
        return 0;
    }
    @Override
    public String getTypeOfObstacle() {
        return "";
    }
}
