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
    public Array<BasePiece[][]> getAllPossibleMoves() {
        Array<BasePiece[][]> r = new Array<>();

        movePieceInRow(this,1,1, GameBoard.cloneBoard(board),r);
        movePieceInRow(this,-1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(this,1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(this,-1,1,GameBoard.cloneBoard(board),r);


        //Tile.setTileHighlight(r,this, GameBoard.tiles);

        return r;
    }

    @Override
    public boolean doesCheck(int mX,int mY,BasePiece king) {
        //left
        if(moveInLineUntilHit(this,1,1,board)==king)
            return true;
        //right
        if(moveInLineUntilHit(this,1,-1,board)==king)
            return true;
        //up
        if(moveInLineUntilHit(this,-1,-1,board)==king)
            return true;
        //down
        if(moveInLineUntilHit(this,-1,1,board)==king)
            return true;
        return false;
    }
}
