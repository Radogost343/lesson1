package ru.geekbrains.java_two.lesson_a.online;

import javax.swing.*;
import java.awt.*;

public class Background extends Sprite{
    MainCircles gameController;


    Background(MainCircles gameController) {
        this.gameController = gameController;
    }

    private final Color color = new Color(
            (int) (Math.random() * 255),
            (int) (Math.random() * 255),
            (int) (Math.random() * 255)
    );

    @Override
    void render(GameCanvas canvas, Graphics g) {
        super.render(canvas, g);
        canvas.setBackground(color);
    }

}