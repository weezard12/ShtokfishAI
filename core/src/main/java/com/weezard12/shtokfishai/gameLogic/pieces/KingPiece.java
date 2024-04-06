package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class KingPiece extends BasePiece {
    public KingPiece(PieceType type, boolean isEnemy, BasePiece[][] board) {
        super(type, isEnemy, board);
    }

    @Override
    public Array<BasePiece[][]> getAllPossibleMoves() {
        Gdx.app.log("king move","moved");
        Array<BasePiece[][]> r = new Array<>();

        BasePiece[][] option1 = GameBoard.cloneBoard(board);
        BasePiece[][] option2 = GameBoard.cloneBoard(board);
        BasePiece[][] option3 = GameBoard.cloneBoard(board);
        BasePiece[][] option4 = GameBoard.cloneBoard(board);
        BasePiece[][] option5 = GameBoard.cloneBoard(board);
        BasePiece[][] option6 = GameBoard.cloneBoard(board);
        BasePiece[][] option7 = GameBoard.cloneBoard(board);
        BasePiece[][] option8 = GameBoard.cloneBoard(board);

        movePiece(this,getPosX()+1,getPosY() +1 ,option1,r);
        movePiece(this,getPosX()+1,getPosY(),option2,r);
        movePiece(this,getPosX()+1,getPosY()-1,option3,r);

        movePiece(this,getPosX(),getPosY()+1,option4,r);
        movePiece(this,getPosX(),getPosY()-1,option5,r);

        movePiece(this,getPosX()-1,getPosY()+1,option6,r);
        movePiece(this,getPosX()-1,getPosY(),option7,r);
        movePiece(this,getPosX()-1,getPosY()-1,option8,r);

        Gdx.app.log("king move",GameBoard.toStringBoardArray(option1));
        Gdx.app.log("king move",GameBoard.toStringBoardArray(option2));

        Tile.setTileHighlight(r,this, GameBoard.tiles);

        return super.getAllPossibleMoves();
    }
}
