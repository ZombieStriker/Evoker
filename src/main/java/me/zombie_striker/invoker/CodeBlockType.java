package me.zombie_striker.invoker;

import java.awt.*;

public enum CodeBlockType {

    EVENT(new Color(45, 45, 232),150,100),
    VARIABLE(new Color(100,100,200),150,50),
    IF(new Color(82, 12, 192),150,100),
    METHOD(new Color(202, 25, 229),100,150),
    RELAY(new Color(138, 32, 137),150,50);

    private Color color;
    private int width;
    private int height;

    CodeBlockType(Color baseColor, int w, int h){
        this.color = baseColor;
        this.width = w;
        this.height = h;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
