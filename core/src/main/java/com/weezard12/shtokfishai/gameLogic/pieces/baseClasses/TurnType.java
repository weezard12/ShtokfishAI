package com.weezard12.shtokfishai.gameLogic.pieces.baseClasses;

public enum TurnType {
    BLACK(-1),
    WHITE(1);

    private final int value;


    TurnType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
