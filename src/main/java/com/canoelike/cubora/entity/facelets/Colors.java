package com.canoelike.cubora.entity.facelets;

/**
 * @author Konjacer
 * @create 2025-02-26 10:33
 */
public enum Colors {
    WHITE('W'),YELLOW('Y'),BLUE('B'),GREEN('G'),RED('R'),ORANGE('O');

    private char color;

    private Colors(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

}
