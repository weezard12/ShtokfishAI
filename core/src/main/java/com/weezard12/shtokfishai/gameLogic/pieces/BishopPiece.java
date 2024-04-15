package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class BishopPiece extends BasePiece {
    public BishopPiece(PieceType type, boolean isEnemy, BasePiece[][] board) {
        super(type, isEnemy, board);
    }

    @Override
    public void getAllPossibleMoves(Array<BasePiece[][]> r) {
        updatePos();

        movePieceInRow(posX,posY,isEnemy,1,1, GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,-1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,-1,1,GameBoard.cloneBoard(board),r);


        //Tile.setTileHighlight(r,this, GameBoard.tiles);

    }

    @Override
    public boolean doesCheck(int mX,int mY,int kX,int kY) {
        updatePos();

        //left
        if(moveInLineUntilHit(posX,posY,1,1,board)==board[kY][kX])
            return true;
        //right
        if(moveInLineUntilHit(posX,posY,1,-1,board)==board[kY][kX])
            return true;
        //up
        if(moveInLineUntilHit(posX,posY,-1,-1,board)==board[kY][kX])
            return true;
        //down
        if(moveInLineUntilHit(posX,posY,-1,1,board)==board[kY][kX])
            return true;
        return false;
    }
}
