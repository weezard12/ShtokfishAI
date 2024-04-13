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
    public Array<BasePiece[][]> getAllPossibleMoves() {
        Array<BasePiece[][]> r = new Array<>();

        //Rook moves
        movePieceInRow(this,1,0, GameBoard.cloneBoard(board),r);
        movePieceInRow(this,-1,0,GameBoard.cloneBoard(board),r);
        movePieceInRow(this,0,1,GameBoard.cloneBoard(board),r);
        movePieceInRow(this,0,-1,GameBoard.cloneBoard(board),r);

        //Bishop moves
        movePieceInRow(this,1,1, GameBoard.cloneBoard(board),r);
        movePieceInRow(this,-1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(this,1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(this,-1,1,GameBoard.cloneBoard(board),r);

        Tile.setTileHighlight(r,this, GameBoard.tiles);
        return r;
    }
    @Override
    public boolean doesCheck(int mX,int mY,BasePiece king) {
        //ROOK
        //left
        if(moveInLineUntilHit(this,1,0,board)==king)
            return true;
        //right
        if(moveInLineUntilHit(this,-1,0,board)==king)
            return true;
        //up
        if(moveInLineUntilHit(this,0,1,board)==king)
            return true;
        //down
        if(moveInLineUntilHit(this,0,-1,board)==king)
            return true;

        //BISHOP
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
