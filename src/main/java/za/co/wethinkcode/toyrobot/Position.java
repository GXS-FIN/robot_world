package za.co.wethinkcode.toyrobot;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false; // More idiomatic check
        Position position = (Position) o;
        return x == position.x && y == position.y; // Streamlined condition
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y); // Properly implementing hashCode
        return result;
    }

    public boolean isIn(Position topLeft, Position bottomRight) {
        return y <= topLeft.getY() && y >= bottomRight.getY() &&
                x >= topLeft.getX() && x <= bottomRight.getX();
    }

    @Override
    public String toString() {
        return x + "," + y; // TODO removed as completed
    }
}
