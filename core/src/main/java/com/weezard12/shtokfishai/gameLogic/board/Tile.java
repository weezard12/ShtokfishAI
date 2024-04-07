package com.weezard12.shtokfishai.gameLogic.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.pieces.QueenPiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;



public class Tile {
    GameBoard gameBoard;
    public BasePiece piece;
    public TileHighlightType highlightType = TileHighlightType.NONE;

    //from 0 - 8 (tiles on the board)
    public int posX;
    public int posY;
    Rectangle bounds;


    public Tile(int posX, int posY,GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.piece = piece;
        this.posX =posX;
        this.posY=posY;
        bounds = new Rectangle(posX*128,posY*128,128,128);
    }

    @Override
    public String toString() {
        return String.format("x: %s, y: %s, piece on the tile: %s ",posX,posY, (gameBoard.board[posY][posX] == null) ? "empty" : gameBoard.board[posY][posX].type + " "+gameBoard.board[posY][posX].isEnemy);
    }

    public static void setTileHighlight(Array<BasePiece[][]> moves, BasePiece piece,Tile[][] tiles){
        int bCount = 0;
        for (BasePiece[][] boards : moves) {
            for (BasePiece[] row : boards) {
                for (BasePiece p : row) {
                    if(p != null)
                        if(p.isJustMoved){
                            tiles[p.getPosY()][p.getPosX()].highlightType = TileHighlightType.CAN_MOVE_TO;
                            Gdx.app.log("highlight",p.getPosX()+" "+p.getPosY()+" "+p.type+" "+bCount);
                        }
                }

            }
            bCount++;
        }
    }
}
