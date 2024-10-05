package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private final TerminalScreen screen;
    private Arena arena;


    public Game(int width, int height) throws IOException {
        arena = new Arena (width, height); //inicializar a arena
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        arena.draw(graphics);
        screen.refresh();
    }

    public void run() throws IOException {
        while (true) {
            draw();
            arena.moveMonsters();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                break;
            if (key.getKeyType() == KeyType.EOF)
                break;
            processKey(key);
        }
        screen.close();
    }
    private void processKey(KeyStroke key) {
        arena.processKey(key);
        arena.moveMonsters();
        arena.verifyMonsterCollisions();
        arena.checkCoinCollisions();
    }
}
