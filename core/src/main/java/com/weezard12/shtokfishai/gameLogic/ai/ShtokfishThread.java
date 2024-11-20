package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.main.Point;

public class ShtokfishThread extends Thread{

    public boolean isCalculating = false;
    GameBoard gameBoard;
    public ShtokfishThread(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void run() {
        isCalculating = true;
        Shtokfish.getBestPosition(gameBoard.board,gameBoard.isBlackTurn);

        if(gameBoard.moveTheBot){
            Point[] points = finedMovedPiece(gameBoard.board, Shtokfish.currentBoardEval.whiteEval.position,gameBoard.isBlackTurn);
            gameBoard.setMovedTiles(points[0],points[1]);
            gameBoard.board = Shtokfish.currentBoardEval.whiteEval.position;
            gameBoard.isBlackTurn = !gameBoard.isBlackTurn;
        }
        isCalculating = false;
    }

    @Override
    public void interrupt() {
        super.interrupt();

    }
    private Point[] finedMovedPiece(BasePiece[][] board, BasePiece[][] cBoard, boolean forBlack){

        //0 - moved from
        //1 - moved to
        Point[] points = new Point[2];

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                if(board[y][x] == null){
                    if(cBoard[y][x] != null){
                        points[1] = new Point(x,y);
                        if(points[0]!=null)
                            return points;
                    }

                }
                else if(board[y][x].isEnemy == forBlack){
                    if(cBoard[y][x] == null){
                        points[0] = new Point(x,y);
                    }
                }
            }
        }

        return points;
    }
}
