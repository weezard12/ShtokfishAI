package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class QueenPiece extends BasePiece {
    public QueenPiece(PieceType type, boolean isEnemy, BasePiece[][] board) {
        super(type, isEnemy, board);
    }

    @Override
    public void getAllPossibleMoves(Array<BasePiece[][]> r) {
        updatePos();

        //Rook moves
        movePieceInRow(posX,posY,isEnemy,1,0, GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,-1,0,GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,0,1,GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,0,-1,GameBoard.cloneBoard(board),r);

        //Bishop moves
        movePieceInRow(posX,posY,isEnemy,1,1, GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,-1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(posX,posY,isEnemy,-1,1,GameBoard.cloneBoard(board),r);

        //Tile.setTileHighlight(r,this, GameBoard.tiles);
    }
    @Override
    public boolean doesCheck(int mX,int mY,BasePiece king) {
        updatePos();
        //ROOK
        //left
        if(moveInLineUntilHit(posX,posY,1,0,board)==king)
            return true;
        //right
        if(moveInLineUntilHit(posX,posY,-1,0,board)==king)
            return true;
        //up
        if(moveInLineUntilHit(posX,posY,0,1,board)==king)
            return true;
        //down
        if(moveInLineUntilHit(posX,posY,0,-1,board)==king)
            return true;

        //BISHOP
        //left
        if(moveInLineUntilHit(posX,posY,1,1,board)==king)
            return true;
        //right
        if(moveInLineUntilHit(posX,posY,1,-1,board)==king)
            return true;
        //up
        if(moveInLineUntilHit(posX,posY,-1,-1,board)==king)
            return true;
        //down
        if(moveInLineUntilHit(posX,posY,-1,1,board)==king)
            return true;
        return false;
    }
}
