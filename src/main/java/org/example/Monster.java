package org.example;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

public class Monster extends Element {
    private static final Random random = new Random();

    public Monster(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        graphics.putString(position.getX(), position.getY(), "M");
    }
    public Position move() {
        int direction = random.nextInt(4); // 0 = up, 1 = down, 2 = left, 3 = right
        switch (direction) {
            case 0: // Up
                return position.move(0, -1);
            case 1: // Down
                return position.move(0, 1);
            case 2: // Left
                return position.move(-1, 0);
            case 3: // Right
                return position.move(1, 0);
            default:
                return position;
        }
    }
}