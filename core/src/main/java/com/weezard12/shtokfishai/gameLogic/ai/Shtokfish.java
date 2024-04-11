package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.pieces.*;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.TurnType;

public class Shtokfish {

    public PositionEval whiteEval;
    public PositionEval blackEval;

    public static PositionEval getBestPosition(BasePiece[][] board, boolean forBlack){
        Array<BasePiece[][]> allPositions;
        int movesCount = 0;

        PositionEval bestEval = new PositionEval(0);
        PositionEval bestEvalForEnemy = new PositionEval(100);

        PositionEval currentEval = new PositionEval();
        PositionEval currentEvalForEnemy = new PositionEval();

        for (BasePiece[] row : board) {
            for (BasePiece piece : row) {

                if(piece!=null)
                    //getPosition only for one color
                    if(piece.isEnemy == forBlack){

                        allPositions = piece.getAllPossibleMoves();

                        if(allPositions!=null)
                            for (BasePiece[][] position : allPositions){
                                if(position!=null){
                                    //gets the eval in the position (foe black and white)
                                    calculateEvalForPosition(position, currentEval,forBlack);
                                    calculateEvalForPosition(position, currentEvalForEnemy,!forBlack);

                                    if (PositionEval.isLeftBiggerThanRight(currentEval,currentEvalForEnemy,bestEval,bestEvalForEnemy)){
                                        bestEval = currentEval;
                                        bestEvalForEnemy = currentEvalForEnemy;
                                        currentEval = new PositionEval();
                                        currentEvalForEnemy = new PositionEval();
                                        Gdx.app.log("shtokfish","found pos");
                                    }
                                    movesCount++;
                                }

                            }

                    }
            }
        }
        Gdx.app.log("shtokfish","moves possible: " + movesCount);
        return bestEval;
    }

    public static void calculateEvalForPosition(BasePiece[][] position, PositionEval eval,boolean forBlack){
        eval.materialValue = 0;
        eval.position = position;
        for (BasePiece[] row : position) {
            for (BasePiece piece : row) {
                if(piece!=null)
                    if(forBlack==piece.isEnemy)
                        eval.materialValue+=piece.type.materialValue;
            }
        }
    }

}
