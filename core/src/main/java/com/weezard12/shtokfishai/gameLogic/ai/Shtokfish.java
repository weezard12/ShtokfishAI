package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.async.ThreadUtils;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.*;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.TurnType;

import com.weezard12.shtokfishai.main.Point;
import java.util.Objects;

public class Shtokfish {

    public static ShtokfishThread thread;
    public static BoardEval currentBoardEval = new BoardEval(new PositionEval(),new PositionEval());

    public static boolean finishedCalculating = false;

    public static void init(GameBoard gameBoard){
        thread = new ShtokfishThread(gameBoard);
    }

    public static void getBestPosition(BasePiece[][] board, boolean forBlack){
        finishedCalculating = false;
        PositionEval bestEval = new PositionEval(0);
        PositionEval bestEvalForEnemy = new PositionEval(0);

        Gdx.app.log("shtokfish","thinking");


        currentBoardEval = getBestPosition(board,forBlack,1,bestEval,bestEvalForEnemy);
        finishedCalculating = true;

    }
    private static BoardEval getBestPosition(BasePiece[][] board, boolean forBlack, int steps, PositionEval bestEval, PositionEval bestEvalForEnemy){

        if(Thread.interrupted())
            return currentBoardEval;

        Array<BasePiece[][]> allPositions = new Array<>();
        int movesCount = 0;

        PositionEval currentEval = new PositionEval();
        PositionEval currentEvalForEnemy = new PositionEval();

        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){

                if(board[y][x]!=null)
                    //getPosition only for one color
                    if(board[y][x].isEnemy == forBlack){

                        allPositions.clear();
                        board[y][x].getAllPossibleMoves(x,y,allPositions);
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
        BasePiece king;
        if(p.x != -10)
            king = position[p.y][p.x];
        else{
            eval.kingMoves = -100;
            return;
        }

        Array<BasePiece[][]> moves = new Array<>();
        king.getAllPossibleMoves(p.x, p.y, moves);

        if(moves.size > 0)
            eval.kingMoves = moves.size * 0.5f;

        else{
            eval.kingMoves = -100;
            return;
        }

        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if(position[y][x] != null)
                    if(forBlack==position[y][x].isEnemy){
                        //material
                        eval.materialValue += position[y][x].type.materialValue;

                        //piece activity
                        position[y][x].getAllPossibleMoves(x,y,moves);
                        eval.piecesActivity += moves.size * position[y][x].type.materialValue * 0.002f;
                    }

            }
        }

    }

}
