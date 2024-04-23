package com.weezard12.shtokfishai.gameLogic.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.weezard12.shtokfishai.gameLogic.pieces.QueenPiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class PromotionSelection {

    private static ShapeDrawer shapeDrawer;
    private static BasePiece pieceToPromote;
    private static GameBoard board;
    private static Rectangle outlineBounds;

    private static final PromotionTile[] tiles = new PromotionTile[4];


    public static boolean isPromoting = false;

    public static void startPromotion(BasePiece pieceToPromote, GameBoard board,int moveToX){
        pieceToPromote.updatePos();

        shapeDrawer = board.shapeDrawer;
        PromotionSelection.pieceToPromote = pieceToPromote;
        PromotionSelection.board = board;
        outlineBounds = new Rectangle((moveToX *128)+GameBoard.offsetToRight,(pieceToPromote.getPosY() -2.4f + (pieceToPromote.isEnemy ? 1.4f : 0)) * 128,128,128*4.4f);

        tiles[0] = new PromotionTile(moveToX, pieceToPromote.isEnemy?0:7,board,PieceType.QUEEN,pieceToPromote.isEnemy);
        tiles[1] = new PromotionTile(moveToX, pieceToPromote.isEnemy?1:6,board,PieceType.KNIGHT,pieceToPromote.isEnemy);
        tiles[2] = new PromotionTile(moveToX, pieceToPromote.isEnemy?2:5,board,PieceType.ROOK,pieceToPromote.isEnemy);
        tiles[3] = new PromotionTile(moveToX, pieceToPromote.isEnemy?3:4,board,PieceType.BISHOP,pieceToPromote.isEnemy);

        board.board[pieceToPromote.posY][pieceToPromote.posX] = null;
        isPromoting = true;
    }

    public static void renderPromotion(){
        renderOutline();
        renderPieces();
    }
    private static void renderOutline(){
        shapeDrawer.rectangle(outlineBounds, Color.CYAN,4);
        shapeDrawer.filledRectangle(outlineBounds, pieceToPromote.isEnemy ? Color.BLACK : Color.WHITE);

        shapeDrawer.line(outlineBounds.x,outlineBounds.y + (pieceToPromote.isEnemy?-128:128) * 0.4f  + outlineBounds.height * (pieceToPromote.isEnemy?1:0)   ,outlineBounds.x + outlineBounds.width,outlineBounds.y + (pieceToPromote.isEnemy?-128:128) * 0.4f  + outlineBounds.height * (pieceToPromote.isEnemy?1:0) ,Color.CYAN,2);

        MyUtils.drawX(shapeDrawer,
            outlineBounds.x + 128 * 0.4f,
            outlineBounds.y  + outlineBounds.height * (pieceToPromote.isEnemy?1:0)  + (pieceToPromote.isEnemy?-128:128) * 0.1f,
            outlineBounds.x + outlineBounds.width - 128 * 0.4f,
            outlineBounds.y + (pieceToPromote.isEnemy?-128:128) * 0.4f  + outlineBounds.height * (pieceToPromote.isEnemy?1:0) - (pieceToPromote.isEnemy?-128:128) * 0.1f,
            Color.CYAN,3);


    }
    private static void renderPieces(){
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("QUEEN%s.png",pieceToPromote.isEnemy?1:0)), GameBoard.offsetToRight+ tiles[0].posX*128,tiles[0].posY*128 +10);
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("KNIGHT%s.png",pieceToPromote.isEnemy?1:0)), GameBoard.offsetToRight+ tiles[1].posX*128,tiles[1].posY*128 +10);
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("ROOK%s.png",pieceToPromote.isEnemy?1:0)), GameBoard.offsetToRight+ tiles[2].posX*128,tiles[2].posY*128 +10);
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("BISHOP%s.png",pieceToPromote.isEnemy?1:0)), GameBoard.offsetToRight+ tiles[3].posX*128,tiles[3].posY*128 +10);
    }

    public static void checkForInput(){
        for (PromotionTile tile: tiles) {
            if(tile.bounds.contains(Gdx.input.getX(),MyGdxGame.boardSize - Gdx.input.getY())) {
                tile.setNewPieceAt(tile.posX, pieceToPromote.posY +1 -2 * (pieceToPromote.isEnemy?1:0));


                pieceToPromote.removeFromBoard();
                pieceToPromote = null;

                isPromoting = false;
                break;
            }
            else if(outlineBounds.contains(Gdx.input.getX(),MyGdxGame.boardSize - Gdx.input.getY())){
                board.board[pieceToPromote.posY][pieceToPromote.posX] = pieceToPromote;
                isPromoting = false;
            }
        }

    }

}
