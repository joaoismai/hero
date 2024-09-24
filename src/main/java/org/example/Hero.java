package org.example;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;

        public Hero (int x, int y){
            this.x = x;
            this.y = y;

        }
        public void draw(Screen screen){
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);

        }
        public void moveUp(){
            this.y = y - 1;
        }
        public void moveRight(){
            this.x = x + 1;
        }
        public void moveDown(){
            this.y = y + 1;
        }
        public void moveLeft(){
            this.x = x - 1;
        }
}
