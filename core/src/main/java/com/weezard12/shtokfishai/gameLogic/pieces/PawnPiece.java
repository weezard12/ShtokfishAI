package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class PawnPiece extends BasePiece {
    public PawnPiece(PieceType type, boolean isEnemy, BasePiece[][] board) {
        super(type, isEnemy, board);
    }

    @Override
    public Array<BasePiece[][]> getAllPossibleMoves() {
        Array<BasePiece[][]> r = new Array<>();
        int isEnemyInt = isEnemy ? 1 : 0;

        //first move
        if (getPosY() == 1 + (5 * isEnemyInt)){
            BasePiece[][] twoMove = GameBoard.cloneBoard(board);
            movePiece(this,getPosX(),getPosY()+2 - (4 * isEnemyInt),twoMove,r);
        }

        BasePiece[][] oneMove = GameBoard.cloneBoard(board);
        BasePiece[][] eatLeft = GameBoard.cloneBoard(board);
        BasePiece[][] eatRight = GameBoard.cloneBoard(board);

        //one move
        if(board[getPosY()+1 - (2 * isEnemyInt)][getPosX()] == null){
            Gdx.app.log("moved up",this.toString());
            boolean moved = movePiece(this,getPosX(),getPosY()+1 - (2 * isEnemyInt),oneMove);

            //makes a queen
            if((getPosY() + 1 - (2 * isEnemyInt)) == (7 * (isEnemy? 0 : 1))){
                oneMove[getPosY()+1 - (2 * isEnemyInt)][getPosX()] = new QueenPiece(PieceType.QUEEN,this.isEnemy,oneMove);
                oneMove[getPosY()+1 - (2 * isEnemyInt)][getPosX()].isJustMoved = true;
            }
            if(moved)
                r.add(oneMove);
        }

        //eat left
        if(getPosX()>0)
            if(board[getPosY()+1 - (2 * isEnemyInt)][getPosX()-1] != null){
                movePiece(this,getPosX()-1,getPosY()+1 - (2 * isEnemyInt),eatLeft);

                //makes a queen
                if((getPosY() + 1 - (2 * isEnemyInt)) == (7 * (isEnemy? 0 : 1))){
                    eatLeft[getPosY()+1 - (2 * isEnemyInt)][getPosX()-1] = new QueenPiece(PieceType.QUEEN,this.isEnemy,eatLeft);
                    eatLeft[getPosY()+1 - (2 * isEnemyInt)][getPosX()-1].isJustMoved = true;
                }

                r.add(eatLeft);
            }

        //eat right
        if(getPosX()<7)
            if(board[getPosY()+1 - (2 * isEnemyInt)][getPosX()+1] != null){
                movePiece(this,getPosX()+1,getPosY()+1 - (2 * isEnemyInt),eatRight);

                //makes a queen
                if((getPosY() + 1 - (2 * isEnemyInt)) == (7 * (isEnemy? 0 : 1))){
                    eatRight[getPosY()+1 - (2 * isEnemyInt)][getPosX()+1] = new QueenPiece(PieceType.QUEEN,this.isEnemy,eatRight);
                    eatRight[getPosY()+1 - (2 * isEnemyInt)][getPosX()+1].isJustMoved = true;
                }

                r.add(eatRight);
            }


/*        //promote
        if(getPosY() == 7 * isEnemyInt)
            board[getPosY()][getPosX()] = new QueenPiece(PieceType.QUEEN,this.isEnemy,board);*/

        Tile.setTileHighlight(r,this, GameBoard.tiles);
        //Gdx.app.log("pawn move",GameBoard.toStringBoardArray(oneMove));
        return super.getAllPossibleMoves();
    }

    @Override
    public boolean doesCheck(int mX, int mY, int kX, int kY) {
        if( (kX==mX-1 || kX==mX+1) && kY==mY+1-2*(isEnemy?1:0))
            return true;
        return false;
    }
}
