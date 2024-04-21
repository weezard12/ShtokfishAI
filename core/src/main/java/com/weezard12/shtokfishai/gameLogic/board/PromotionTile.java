package com.weezard12.shtokfishai.gameLogic.board;

import com.weezard12.shtokfishai.gameLogic.pieces.*;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class PromotionTile extends Tile{

    private final PieceType type;
    private final boolean isEnemy;

    public PromotionTile(int posX, int posY, GameBoard gameBoard, PieceType type,boolean isEnemy) {
        super(posX, posY, gameBoard);
        this.type = type;
        this.isEnemy = isEnemy;
    }

    public void setNewPieceAt(int x, int y){
        switch (type){
            case KING:
            gameBoard.board[y][x] = new KingPiece(type,isEnemy,gameBoard.board);
            break;
            case KNIGHT:
                gameBoard.board[y][x] = new KnightPiece(type,isEnemy,gameBoard.board);
            break;
            case ROOK:
                gameBoard.board[y][x] = new RookPiece(type,isEnemy,gameBoard.board);
            break;
            case BISHOP:
                gameBoard.board[y][x] = new BishopPiece(type,isEnemy,gameBoard.board);
            break;
            case QUEEN:
                gameBoard.board[y][x] = new QueenPiece(type,isEnemy,gameBoard.board);
            break;
            case PAWN:
                gameBoard.board[y][x] = new PawnPiece(type,isEnemy,gameBoard.board);
            break;
            default:
                gameBoard.board[y][x] = new BasePiece(type,isEnemy,gameBoard.board);
            break;
        }

    }
}
