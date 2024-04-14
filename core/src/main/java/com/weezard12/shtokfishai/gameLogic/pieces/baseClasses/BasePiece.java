package com.weezard12.shtokfishai.gameLogic.pieces.baseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.PawnPiece;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;

public class BasePiece {
    protected BasePiece[][] board;
    public PieceType type;
    public boolean isEnemy;

    //forHighlights
    public boolean isJustMoved;


    //from 0 - 8 (tiles on the board)
    private int posX;
    public int getPosX() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] == this) {
                    return y;
                }
            }
        }
        Gdx.app.error("BasePiece","cant fined piece X");
        return 0;
    }

    private int posY;
    public int getPosY() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] == this) {
                    return x;
                }
            }
        }
        Gdx.app.error("BasePiece","cant fined piece X");
        return 0;
    }

    public Texture texture;
    public BasePiece( PieceType type,boolean isEnemy,BasePiece[][] board){
        this.board = board;

        this.type=type;
        this.isEnemy=isEnemy;

    }
    public Array<BasePiece[][]> getAllPossibleMoves(){
        return null;
    }
    public boolean doesCheck(int mX,int mY, int kX,int kY){
        //Gdx.app.log("doesCheck func",String.format("mX: %s, mY: %s, kX: %s, kY: %s",mX,mY,kX,kY));
        return false;
    }
    public boolean doesCheck(BasePiece king){
        return doesCheck(this.getPosX(),this.getPosY(),king.getPosX(),king.getPosY());
    }
    public boolean doesCheck(int mX, int mY,BasePiece king){
        return doesCheck(mX,mY,king.getPosX(),king.getPosY());
    }

    //simple move (just changing pos ones)
    public boolean movePiece(BasePiece piece,int mX,int mY,BasePiece[][] board){
        MyUtils.log("movePiece func",String.format("mX: %s, mY: %s, posX: %s, posY: %s",mX,mY,piece.getPosX(),piece.getPosY()));

        //don't move a null piece
        if(board[piece.getPosY()][piece.getPosX()]==null)
            return false;

        //outside the board
        if(mY < 0 || mY > 7 ||mX < 0 || mX > 7)
            return false;

        //don't move to the same color (if not null)
        if(board[mY][mX] != null)
            if (piece.isEnemy == board[mY][mX].isEnemy)
                return false;


        board[mY][mX] = board[piece.getPosY()][piece.getPosX()];
        board[mY][mX].isJustMoved = true;
        board[piece.getPosY()][piece.getPosX()] = null;

        //don't move if there is check (might be a pin)
        if(GameBoard.isColorInCheck(board,piece.isEnemy))
            return false;

        MyUtils.log("movePiece func","moved");
        return true;
    }
    public void movePiece(BasePiece piece,int mX,int mY,BasePiece[][] board,Array<BasePiece[][]> moves){
         if(movePiece(piece,mX,mY,board))
            moves.add(board);

    }


    //move the piece in a line
    public void movePieceInRow(BasePiece piece,int mX,int mY,BasePiece[][] board,Array<BasePiece[][]> moves){

        BasePiece[][] cBoard = GameBoard.cloneBoard(board);
        boolean stop = false;
        int cX = piece.getPosX();
        int cY = piece.getPosY();

        BasePiece hit = moveInLineUntilHit(piece,mX,mY,cBoard);
        int hitX =0;
        int hitY =0;
        if (hit!=null){
            hitX = hit.getPosX();
            hitY = hit.getPosY();
            if(movePiece(piece, hit.getPosX(), hit.getPosY(), cBoard)){
                moves.add(cBoard);
            }

        }

        while (!stop){

            cBoard = GameBoard.cloneBoard(board);
            cX+=mX;
            cY+=mY;

            if(cY < 0 || cY > 7 || cX < 0 || cX > 7)
                    return;

            if (hit != null)
                if (cX==hitX && cY==hitY)
                    return;

            movePiece(piece, cX, cY, cBoard, moves);

                //Gdx.app.log("Moved in row","");



        }





    }

    //check for piece in line without moving
    public BasePiece moveInLineUntilHit(BasePiece piece, int mX,int mY,BasePiece[][] board){
        boolean stop = false;
        int cX = piece.getPosX()+mX;
        int cY = piece.getPosY()+mY;
        while (!stop){
            if(cX > 7 || cX < 0 || cY>7 || cY<0)
                return null;

            if(board[cY][cX] != null)
                return board[cY][cX];
            cX +=mX;
            cY +=mY;

        }
        return null;

    }

    @Override
    public String toString() {
        return String.format("pX: %s, pY: %s, type: %s",getPosX(),getPosY(),isEnemy? "Black " + type : type);
    }
}
