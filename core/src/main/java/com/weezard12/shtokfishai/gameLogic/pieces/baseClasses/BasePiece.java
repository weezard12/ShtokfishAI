package com.weezard12.shtokfishai.gameLogic.pieces.baseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;

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

        texture = new Texture( String.format("pieces\\%s%s.png",Gdx.files.internal(String.valueOf(type)),isEnemy ? 1 : 0 ));
    }
    public Array<BasePiece[][]> getAllPossibleMoves(){
        return null;
    }

    //simple move (just changing pos ones)
    public boolean movePiece(BasePiece piece,int mX,int mY,BasePiece[][] board){
        Gdx.app.log("movePiece func",String.format("mX: %s, mY: %s, posX: %s, posY: %s",mX,mY,piece.getPosX(),piece.getPosY()));

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

        Gdx.app.log("movePiece func","moved");
        return true;
    }
    public void movePiece(BasePiece piece,int mX,int mY,BasePiece[][] board,Array<BasePiece[][]> moves){
        movePiece(piece,mX,mY,board);
        moves.add(board);

    }


    //move the piece in a line
    public void movePieceInRow(BasePiece piece,int mX,int mY,BasePiece[][] board,Array<BasePiece[][]> moves){

        BasePiece[][] cBoard = GameBoard.cloneBoard(board);
        boolean stop = false;
        int cX = piece.getPosX()+mX;
        int cY = piece.getPosY()+mY;

        while (!stop){

            if (movePiece(piece, cX, cY, cBoard)){
                //always null because the piece moved there
                if (cBoard[cY][cX] != null)
                    return;

                cX+=mX;
                cY+=mY;
                moves.add(cBoard);
                cBoard = GameBoard.cloneBoard(board);
                Gdx.app.log("Moved in row","");
            }
            else stop = true;

        }

    }

}
