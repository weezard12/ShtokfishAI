package com.weezard12.shtokfishai.gameLogic.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.board.Tile;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;

public class KingPiece extends BasePiece {
    public boolean isEverMoved = false;
    public KingPiece(boolean isEnemy, BasePiece[][] board) {
        super(isEnemy, board);
        type = PieceType.KING;
    }

    @Override
    public void getAllPossibleMoves(int pX, int pY, Array<BasePiece[][]> r) {
        posX = pX;
        posY = pY;


        if(!this.isEverMoved)
            if(posX == 4){

                //castle O-O
                if (board[posY][posX + 3] != null)
                    if (board[posY][posX + 3].type == PieceType.ROOK && board[posY][posX + 3].isEnemy == this.isEnemy)
                        if (!((RookPiece) board[posY][posX + 3]).isEverMoved) {

                            //check if there is a check when moved
                            if (canKingMove(posX, posY, isEnemy, posX + 1, posY, board)) {
                                if (canKingMove(posX, posY, isEnemy, posX + 2, posY, board)) {
                                    BasePiece[][] shortCastle = GameBoard.cloneBoard(board);
                                    shortCastle[posY][posX + 2] = shortCastle[posY][posX];
                                    ((KingPiece) shortCastle[posY][posX + 2]).isEverMoved = true;

                                    shortCastle[posY][posX] = null;
                                    shortCastle[posY][posX + 2].isJustMoved = true;
                                    shortCastle[posY][posX + 1] = shortCastle[posY][posX + 3];
                                    shortCastle[posY][posX + 3] = null;

                                    r.add(shortCastle);
                                }
                            }
                        }


                //castle O-O-O
                if (board[posY][posX - 4] != null)
                    if (board[posY][posX - 4].type == PieceType.ROOK && board[posY][posX - 4].isEnemy == this.isEnemy)
                        if (!((RookPiece) board[posY][posX - 4]).isEverMoved) {

                            //check if there is a check when moved
                            if (canKingMove(posX, posY, isEnemy, posX - 1, posY, board)) {
                                if (canKingMove(posX, posY, isEnemy, posX - 2, posY, board)) {

                                    if (canKingMove(posX, posY, isEnemy, posX - 3, posY, board)) {
                                        BasePiece[][] longCastle = GameBoard.cloneBoard(board);
                                        longCastle[posY][posX - 2] = longCastle[posY][posX];
                                        ((KingPiece) longCastle[posY][posX - 2]).isEverMoved = true;

                                        longCastle[posY][posX] = null;
                                        longCastle[posY][posX - 2].isJustMoved = true;
                                        longCastle[posY][posX - 1] = longCastle[posY][posX - 4];
                                        longCastle[posY][posX - 4] = null;

                                        r.add(longCastle);
                                    }
                                }
                            }
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

        movePiece(posX,posY,isEnemy,posX+1,posY +1 ,option1,r);
        movePiece(posX,posY,isEnemy,posX+1,posY,option2,r);
        movePiece(posX,posY,isEnemy,posX+1,posY-1,option3,r);

        movePiece(posX,posY,isEnemy,posX,posY+1,option4,r);
        movePiece(posX,posY,isEnemy,posX,posY-1,option5,r);

        movePiece(posX,posY,isEnemy,posX-1,posY+1,option6,r);
        movePiece(posX,posY,isEnemy,posX-1,posY,option7,r);
        movePiece(posX,posY,isEnemy,posX-1,posY-1,option8,r);


        //Tile.setTileHighlight(r,this, GameBoard.tiles);

    }

    @Override
    public boolean doesCheck(int mX,int mY,int kX, int kY) {
        if(kX == mX+1 || kX == mX-1 || kX == mX)
            if(kY == mY+1 || kY == mY-1 || kY == mY)
                return true;


        return false;
    }

}
