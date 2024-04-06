package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class RookPiece extends BasePiece {
    public RookPiece(PieceType type, boolean isEnemy, BasePiece[][] board) {
        super(type, isEnemy, board);
    }

    @Override
    public Array<BasePiece[][]> getAllPossibleMoves() {
        Gdx.app.log("king move", "moved");
        Array<BasePiece[][]> r = new Array<>();

        movePieceInRow(this,1,0,GameBoard.cloneBoard(board),r);


        Tile.setTileHighlight(r,this, GameBoard.tiles);

        return r;
    }
}
