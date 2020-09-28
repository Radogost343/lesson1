package ru.geekbrains.java_two.lesson_a.online;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseActions extends MouseAdapter {
    MainCircles gameController;

    public MouseActions(MainCircles gameController) {
        super();
        this.gameController = gameController;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                addBall(gameController);
                break;
            case MouseEvent.BUTTON3:
                removeBall(gameController);
                break;
        }
    }

    private void addBall(MainCircles gameController) {
        Sprite[] sprites = new Sprite[gameController.sprites.length + 1];
        for (int i = 0; i < gameController.sprites.length; i++) {
            sprites[i] = gameController.sprites[i];
        }
        gameController.sprites = sprites;
        gameController.sprites[sprites.length - 1] = new Ball();
    }

    private void removeBall(MainCircles gameController) {
        Sprite[] sprite = new Sprite[gameController.sprites.length - 1];
        for (int i = 0; i < gameController.sprites.length - 1; i++) {
            sprite[i] = gameController.sprites[i];
        }
        gameController.sprites = sprite;
    }
}

