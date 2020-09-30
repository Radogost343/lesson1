package ru.geekbrains.java_two.lesson_a.online;
import javax.swing.*;
import java.awt.*;

public class Background extends Sprite{
    private float time;
    private static final float AMPLITUDE = 255f/2f;
    private Color color;


    @Override
    void update(GameCanvas canvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red,green,blue);
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
     canvas.setBackground(color);
    }

}