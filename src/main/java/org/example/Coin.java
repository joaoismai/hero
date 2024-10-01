package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{

    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFD700")); // Cor da moeda
        graphics.putString(position.getX(), position.getY(), "O"); // Começo - Posição 0
    }
}

