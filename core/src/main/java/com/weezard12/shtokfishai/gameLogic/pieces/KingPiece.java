package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class KingPiece extends BasePiece {
    public boolean isEverMoved = false;
    public KingPiece(PieceType type, boolean isEnemy, BasePiece[][] board) {
        super(type, isEnemy, board);
    }

    @Override
    public Array<BasePiece[][]> getAllPossibleMoves() {
        Gdx.app.log("king move","moved");
        Array<BasePiece[][]> r = new Array<>();


        //castle O-O
        if(!this.isEverMoved){
            if(getPosX() == 4)
                if(board[getPosY()][getPosX()+1] == null && board[getPosY()][getPosX()+2] == null)
                    if(board[getPosY()][getPosX()+3].type == PieceType.ROOK && board[getPosY()][getPosX()+3].isEnemy == this.isEnemy)
                        if(!((RookPiece) board[getPosY()][getPosX() + 3]).isEverMoved){
                            this.isEverMoved = true;
                            BasePiece[][] shortCastle = GameBoard.cloneBoard(board);
                            shortCastle[getPosY()][getPosX()+2]=shortCastle[getPosY()][getPosX()];
                            shortCastle[getPosY()][getPosX()+2].isJustMoved = true;
                            shortCastle[getPosY()][getPosX()]=new RookPiece(PieceType.ROOK,this.isEnemy,shortCastle);

                            r.add(shortCastle);
                            this.isEverMoved = false;
                            Gdx.app.log("king move","castle");
                    }

        }

        //castle O-O-O
        if(!this.isEverMoved){
            if(getPosX() == 4)
                if(board[getPosY()][getPosX()-1] == null && board[getPosY()][getPosX()-2] == null && board[getPosY()][getPosX()-3]==null)
                    if(board[getPosY()][getPosX()-4].type == PieceType.ROOK && board[getPosY()][getPosX()-4].isEnemy == this.isEnemy)
                        if(!((RookPiece) board[getPosY()][getPosX() - 4]).isEverMoved){
                            this.isEverMoved = true;
                            BasePiece[][] longCastle = GameBoard.cloneBoard(board);
                            longCastle[getPosY()][getPosX()-2]=longCastle[getPosY()][getPosX()];
                            longCastle[getPosY()][getPosX()-2].isJustMoved = true;
                            longCastle[getPosY()][getPosX()]=new RookPiece(PieceType.ROOK,this.isEnemy,longCastle);

                            r.add(longCastle);
                            this.isEverMoved = false;
                            Gdx.app.log("king move","castle");
                        }

        }

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


        Tile.setTileHighlight(r,this, GameBoard.tiles);

        return super.getAllPossibleMoves();
    }

    @Override
    public boolean doesCheck(int mX,int mY,int kX, int kY) {
        if(kX == mX+1 || kX == mX-1 || kX == mX)
            if(kY == mY+1 || kY == mY-1 || kY == mY)
                return true;


        return false;
    }
    // 5 5
    //4 4

}
