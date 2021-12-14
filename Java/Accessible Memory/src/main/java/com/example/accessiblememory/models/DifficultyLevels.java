package com.example.accessiblememory.models;

public enum DifficultyLevels {
    VERY_EASY(2, 2),
    EASY(2, 3),
    MEDIUM(2, 4),
    HARD(3, 4),
    PROFESSIONAL(4, 5);

    public final int sizeX;
    public final int sizeY;

    DifficultyLevels(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
