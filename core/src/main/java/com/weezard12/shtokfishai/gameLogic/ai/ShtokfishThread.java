package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;

public class ShtokfishThread extends Thread{

    GameBoard gameBoard;
    public ShtokfishThread(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void run() {
        Shtokfish.getBestPosition(gameBoard.board,gameBoard.isBlackTurn);

        if(gameBoard.moveTheBot){
            gameBoard.board = Shtokfish.currentBoardEval.whiteEval.position;
            gameBoard.isBlackTurn = !gameBoard.isBlackTurn;
        }

    }

    @Override
    public void interrupt() {
        super.interrupt();

    }
}
