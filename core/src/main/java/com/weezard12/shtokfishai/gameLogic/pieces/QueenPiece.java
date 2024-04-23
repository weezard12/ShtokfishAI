package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class QueenPiece extends BasePiece {
    public QueenPiece(boolean isEnemy, BasePiece[][] board) {
        super(isEnemy, board);
        type = PieceType.QUEEN;
    }

    @Override
    public void getAllPossibleMoves(int pX, int pY, Array<BasePiece[][]> r) {

        //Rook moves
        movePieceInRow(pX,pY,isEnemy,1,0, GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,-1,0,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,0,1,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,0,-1,GameBoard.cloneBoard(board),r);

        //Bishop moves
        movePieceInRow(pX,pY,isEnemy,1,1, GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,-1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,-1,1,GameBoard.cloneBoard(board),r);

        //Tile.setTileHighlight(r,this, GameBoard.tiles);
    }
    @Override
    public boolean doesCheck(int mX,int mY,int kX,int kY) {

        //ROOK
        //left
        if(moveInLineUntilHit(mX,mY,1,0,board)==board[kY][kX])
            return true;
        //right
        if(moveInLineUntilHit(mX,mY,-1,0,board)==board[kY][kX])
            return true;
        //up
        if(moveInLineUntilHit(mX,mY,0,1,board)==board[kY][kX])
            return true;
        //down
        if(moveInLineUntilHit(mX,mY,0,-1,board)==board[kY][kX])
            return true;

        //BISHOP
        //left
        if(moveInLineUntilHit(mX,mY,1,1,board)==board[kY][kX])
            return true;
        //right
        if(moveInLineUntilHit(mX,mY,1,-1,board)==board[kY][kX])
            return true;
        //up
        if(moveInLineUntilHit(mX,mY,-1,-1,board)==board[kY][kX])
            return true;
        //down
        if(moveInLineUntilHit(mX,mY,-1,1,board)==board[kY][kX])
            return true;
        return false;
    }
}
