package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private Random random;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls(); // Inicializa as paredes
        this.coins = createCoins(); // Initializa as coins
        this.random = new Random();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0)); // Cima
            walls.add(new Wall(c, height - 1)); // Baixo
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r)); // Esquerda
            walls.add(new Wall(width - 1, r)); // Direita
        }
        return walls;
    }


    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        String keyT = key.getKeyType().toString();
        switch (keyT) {
            case "ArrowUp":
                moveHero(hero.moveUp());
                break;
            case "ArrowDown":
                moveHero(hero.moveDown());
                break;
            case "ArrowLeft":
                moveHero(hero.moveLeft());
                break;
            case "ArrowRight":
                moveHero(hero.moveRight());
                break;
        }
    }

    public void draw(TextGraphics graphics) throws IOException {
        // Cor para a arena
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699")); // Cor
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }

        hero.draw(graphics);
    }

    private boolean canHeroMove(Position position) {
        // Verificar se está a esbarrar na arena
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false; // Não move para dentro da parede
            }
        }
        // Verifica se está dentro das paredes
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }
    private void retrieveCoins() {
        // Verifica se chegou na moeda
        coins.removeIf(coin -> coin.getPosition().equals(hero.getPosition()));
    }
}