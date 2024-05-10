package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {
    private final int x;
    private final int y;
    private final int size;
    public SquareObstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 5;
    }

    @Override
    public int getBottomLeftX() {
        return this.x;
    }

    @Override
    public int getBottomLeftY() {
        return this.y;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean blocksPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        return this.x <= x && x <= this.x+4 && this.y <= y && y <= this.y+4;
    }

    @Override
    public boolean blocksPath(Position start, Position end) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        int dx = Integer.compare(endX, startX);
        int dy = Integer.compare(endY, startY);

        for (int x = startX + dx, y = startY + dy; x != endX || y != endY; x += dx, y += dy) {
            Position check = new Position(x,y);
            if (blocksPosition(check)) {
                return true;
            }
        }
        return false;
    }
}
