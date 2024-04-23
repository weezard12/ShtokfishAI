package com.weezard12.shtokfishai.gameLogic.pieces.baseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.PawnPiece;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;

public abstract class BasePiece {
    protected BasePiece[][] board;
    public PieceType type;
    public boolean isEnemy;

    //forHighlights
    public boolean isJustMoved;


    //from 0 - 8 (tiles on the board)
    public int posX;
    public int getPosX() {
        if(isEnemy){
            for (int y = board.length-1; y > -1; y--) {
                for (int x = 0; x < board[y].length; x++) {
                    if (board[x][y] == this) {
                        return y;
                    }
                }
            }
        }
        else for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] == this) {
                    return y;
                }
            }
        }
        Gdx.app.error("BasePiece","cant fined piece X");
        return 0;
    }

    public int posY;
    public int getPosY() {
        if(isEnemy){
            for (int y = board.length-1; y > -1; y--) {
                for (int x = 0; x < board[y].length; x++) {
                    if (board[x][y] == this) {
                        return x;
                    }
                }
            }
        }
        else for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] == this) {
                    return x;
                }
            }
        }
        Gdx.app.error("BasePiece","cant fined piece X");
        return 0;
    }

    public void updatePos(){
        posX = getPosX();
        posY = getPosY();
    }

    public Texture texture;
    public BasePiece(boolean isEnemy,BasePiece[][] board){
        this.board = board;

        this.isEnemy=isEnemy;

    }

    public void removeFromBoard(){
        board[posY][posX] = null;
    }

    public void getAllPossibleMoves(int pX, int pY, Array<BasePiece[][]> r){
    }
    public boolean doesCheck(int mX,int mY, int kX,int kY){
        //Gdx.app.log("doesCheck func",String.format("mX: %s, mY: %s, kX: %s, kY: %s",mX,mY,kX,kY));
        return false;
    }


    //simple move (just changing pos ones)
    public boolean movePiece(int cX, int cY,boolean isEnemy, int mX,int mY,BasePiece[][] board){
        MyUtils.log("movePiece func",String.format("mX: %s, mY: %s, posX: %s, posY: %s",mX,mY,cX,cY));

        //don't move a null piece
        if(board[cY][cX]==null)
            return false;

        //outside the board
        if(mY < 0 || mY > 7 ||mX < 0 || mX > 7)
            return false;

        //don't move to the same color (if not null)
        if(board[mY][mX] != null)
            if (isEnemy == board[mY][mX].isEnemy)
                return false;

        board[mY][mX] = board[cY][cX];
        board[mY][mX].isJustMoved = true;
        board[cY][cX] = null;

        //don't move if there is check (might be a pin)
        if(GameBoard.isColorInCheck(board,isEnemy))
            return false;

        MyUtils.log("movePiece func","moved");
        return true;
    }
    public void movePiece(int cX,int cY, boolean isEnemy,int mX,int mY,BasePiece[][] board,Array<BasePiece[][]> moves){
         if(movePiece(cX,cY,isEnemy,mX,mY,board))
            moves.add(board);

    }



    //move the piece in a line
    public void movePieceInRow(int sX, int sY,boolean isEnemy, int mX,int mY,BasePiece[][] board,Array<BasePiece[][]> moves){

        BasePiece[][] cBoard = GameBoard.cloneBoard(board);
        int cX = sX;
        int cY = sY;

        BasePiece hit = moveInLineUntilHit(sX, sY, mX,mY,cBoard);
        int hitX = 0;
        int hitY = 0;
        if (hit!=null){
            hit.updatePos();
            hitX = hit.posX;
            hitY = hit.posY;
            if(movePiece(sX,sY,isEnemy, hit.posX, hit.posY, cBoard)){
                moves.add(cBoard);
            }

        }

        while (true){

            cBoard = GameBoard.cloneBoard(board);
            cX += mX;
            cY += mY;

            if(cY < 0 || cY > 7 || cX < 0 || cX > 7)
                    return;

            if (hit != null)
                if (cX==hitX && cY==hitY)
                    return;

            movePiece(sX,sY,isEnemy, cX, cY, cBoard, moves);

                //Gdx.app.log("Moved in row","");



        }





    }

    //check for piece in line without moving
    public BasePiece moveInLineUntilHit(int cX, int cY, int mX,int mY,BasePiece[][] board){
        cX += mX;
        cY += mY;
        while (true){
            if(cX > 7 || cX < 0 || cY>7 || cY<0)
                return null;

            if(board[cY][cX] != null)
                return board[cY][cX];
            cX +=mX;
            cY +=mY;

        }

    }

    @Override
    public String toString() {
        return String.format("pX: %s, pY: %s, type: %s",getPosX(),getPosY(),isEnemy? "Black " + type : type);
    }
}
