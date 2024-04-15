package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.*;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.TurnType;

import java.awt.*;
import java.util.Objects;

public class Shtokfish {

    public PositionEval whiteEval;
    public PositionEval blackEval;

    public static BoardEval getBestPosition(BasePiece[][] board, boolean forBlack){
        PositionEval bestEval = new PositionEval(0);
        PositionEval bestEvalForEnemy = new PositionEval(0);

        Gdx.app.log("shtokfish","thinking");
        return getBestPosition(board,forBlack,2,bestEval,bestEvalForEnemy);
    }
    private static BoardEval getBestPosition(BasePiece[][] board, boolean forBlack, int steps, PositionEval bestEval, PositionEval bestEvalForEnemy){
        Array<BasePiece[][]> allPositions = new Array<>();
        int movesCount = 0;

        PositionEval currentEval = new PositionEval();
        PositionEval currentEvalForEnemy = new PositionEval();

        for (int x = 0; x<8;x++){
            for (int y = 0; y<8;y++){

                if(board[y][x]!=null)
                    //getPosition only for one color
                    if(board[y][x].isEnemy == forBlack){

                        allPositions.clear();
                        board[y][x].getAllPossibleMoves(allPositions);
                        for (BasePiece[][] position : allPositions){
                            if(position!=null){
                                if(steps > 0){

                                    //moves the other color to get the real position
                                    BoardEval boardEval = getBestPosition(GameBoard.cloneBoard(position), !forBlack,steps-1, new PositionEval(), new PositionEval());

                                    //sets the current eval to the eval after other color moved
                                    currentEval = forBlack ? boardEval.blackEval : boardEval.whiteEval;

                                    //sets the position after the other color moved to the current position (overriding the other color move)
                                    currentEval.position = position;

                                    currentEvalForEnemy = forBlack ? boardEval.whiteEval : boardEval.blackEval;

                                    currentEvalForEnemy.position = position;

                                }
                                else {
                                    //gets the eval in the position (foe black and white)
                                    calculateEvalForPosition(position, currentEval,forBlack);
                                    calculateEvalForPosition(position, currentEvalForEnemy,!forBlack);
                                }


                                if (PositionEval.isLeftBiggerThanRight(currentEval,currentEvalForEnemy,bestEval,bestEvalForEnemy)){
                                    bestEval = currentEval;
                                    bestEvalForEnemy = currentEvalForEnemy;
                                    currentEval = new PositionEval();
                                    currentEvalForEnemy = new PositionEval();
                                    //Gdx.app.log("shtokfish","found pos for "+(forBlack?"black":"white"));
                                }
                                movesCount++;
                            }

                        }

                    }
            }
        }

        //is checkmate
        if(movesCount==0)
            bestEval.kingMoves = -100;
        //Gdx.app.log("shtokfish","moves possible: " + movesCount);
        return new BoardEval(forBlack ? bestEvalForEnemy : bestEval, forBlack ? bestEval : bestEvalForEnemy);
    }

    public static void calculateEvalForPosition(BasePiece[][] position, PositionEval eval,boolean forBlack){
        eval.position = position;

        eval.piecesActivity = 0;
        eval.materialValue = 0;
        eval.kingMoves = 0;
        Point p = GameBoard.finedKingInBoard(position,forBlack);
        BasePiece king = position[p.y][p.x];

        Array<BasePiece[][]> moves = new Array<>();
        king.getAllPossibleMoves(moves);
        eval.kingMoves = moves.size==0 ? -10 : moves.size * 0.5f;


        for (BasePiece[] row : position) {
            for (BasePiece piece : row) {
                if(piece != null)
                    if(forBlack==piece.isEnemy){
                        //material
                        eval.materialValue += piece.type.materialValue;

                        //piece activity
                        piece.getAllPossibleMoves(moves);
                        eval.piecesActivity += moves.size * piece.type.materialValue * 0.002f;
                    }

            }
        }

    }

}
