package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private Position position;

    public Wall(int x, int y) {
        this.position = new Position(x, y);
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Red color for the wall
        graphics.putString(position.getX(), position.getY(), "#"); // Use '#' or another character for the wall
    }

    public Position getPosition() {
        return position;
    }
}
