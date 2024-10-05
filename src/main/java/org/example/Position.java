package org.example;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Position move(int deltaX, int deltaY) {
        return new Position(this.x + deltaX, this.y + deltaY);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and type
        Position position = (Position) o; // Cast to Position
        return x == position.x && y == position.y; // Check for equality of coordinates
    }
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
