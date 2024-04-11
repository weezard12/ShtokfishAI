package com.weezard12.shtokfishai.gameLogic.pieces.baseClasses;

public enum PieceType {
    EMPTY(0),
    PAWN(1),
    KNIGHT(3),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0);
    public final int materialValue;

    PieceType(int materialValue) {
        this.materialValue = materialValue;
    }
}
