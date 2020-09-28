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
        System.arraycopy(gameController.sprites, 0, sprites, 0, gameController.sprites.length);
        gameController.sprites = sprites;
        gameController.sprites[sprites.length - 1] = new Ball();
    }

    private void removeBall(MainCircles gameController) {
        Sprite[] sprite = new Sprite[gameController.sprites.length - 1];
        if (gameController.sprites.length - 1 >= 0)
            System.arraycopy(gameController.sprites, 0, sprite, 0, gameController.sprites.length - 1);
        gameController.sprites = sprite;
    }

}

