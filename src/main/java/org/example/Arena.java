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
    private List<Monster> monsters; // Lista de monstros

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls(); // Inicializa as paredes
        this.coins = createCoins(); // Inicializa as coins
        this.random = new Random();
        this.monsters = createMonsters(); // Inicializa a lista de monstros
    }
    //Criar monstros
    private List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(5, 5));
        monsters.add(new Monster(8, 8));
        return monsters;
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
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
        hero.draw(graphics);

        // Draw monsters
        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
    }
    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position newPosition = monster.move();
            if (canMonsterMove(newPosition)) {
                monster.setPosition(newPosition);
            }
        }
    }
    public void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                System.out.println("Game Over! You were caught by a monster!");
                System.exit(0); // Terminate the game
            }
        }
    }
    private boolean canMonsterMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false; // Cannot move into the wall
            }
        }
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
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
        coins.removeIf(coin -> coin.getPosition().equals(hero.getPosition()));
    }
    public void checkCoinCollisions() {
        coins.removeIf(coin -> coin.getPosition().equals(hero.getPosition()));
    }

}