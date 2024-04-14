package com.weezard12.shtokfishai.gameLogic.ai;

import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.TurnType;

import java.util.Arrays;

public class PositionEval {
    public BasePiece[][] position;
    public TurnType evalForColor;

    public float materialValue;
    public float piecesActivity;

    public float kingMoves;

    public float getSumEval() {
        return materialValue + piecesActivity + kingMoves;
    }

    public PositionEval(){

    }

    public PositionEval(float materialValue){
        this.materialValue = materialValue;
    }

    public boolean isBiggerThan(PositionEval otherEval){
        return (materialValue > otherEval.materialValue);
    }
    public static boolean isLeftBiggerThanRight(PositionEval firstEval,PositionEval firstEnemyEval,PositionEval secondEval,PositionEval secondEnemyEval){
        if(firstEval.position == null || firstEnemyEval.position==null)
            return false;
        if(secondEval.position == null || secondEnemyEval.position==null)
            return true;
        return ((firstEval.getSumEval() - firstEnemyEval.getSumEval()) > (secondEval.getSumEval() - secondEnemyEval.getSumEval()));
    }

    @Override
    public String toString() {
        return String.format("position: %s",position==null?"empty position": GameBoard.toStringBoardArray(position));
    }
}