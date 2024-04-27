package com.weezard12.shtokfishai.gameLogic.pieces.baseClasses;

public enum PieceType {
    EMPTY(0,0),
    PAWN(1,1),
    KNIGHT(3,3),
    BISHOP(3,5),
    ROOK(5,8),
    QUEEN(9,13),
    KING(0,3.75f);
    public final int materialValue;
    public final float movementValue;

    PieceType(int materialValue,float movementValue) {
        this.materialValue = materialValue;
        this.movementValue = movementValue;
    }
}
