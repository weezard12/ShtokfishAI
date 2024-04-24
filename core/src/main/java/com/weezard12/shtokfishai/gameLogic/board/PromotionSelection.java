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

    public static void startPromotion(BasePiece pieceToPromote, GameBoard board,Tile moveToTile){
        pieceToPromote.updatePos();

        shapeDrawer = board.shapeDrawer;
        PromotionSelection.pieceToPromote = pieceToPromote;
        PromotionSelection.board = board;

        if(!board.isBlackRotationBoard)
            outlineBounds = new Rectangle((moveToTile.bounds.x ),(moveToTile.getTileBoundsYAsPos() - (!pieceToPromote.isEnemy ? 3.4f : 0)) * 128,128,128*4.4f);
        else
            outlineBounds = new Rectangle((moveToTile.bounds.x ),(moveToTile.getTileBoundsYAsPos() -3.4f + (!pieceToPromote.isEnemy ? 3.4f : 0)) * 128,128,128*4.4f);

        boolean colorPos = (pieceToPromote.isEnemy == !board.isBlackRotationBoard);

        tiles[0] = new PromotionTile(moveToTile.posX, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?0:7,moveToTile.bounds.x ,moveToTile.bounds.y, board,PieceType.QUEEN,pieceToPromote.isEnemy);
        tiles[1] = new PromotionTile(moveToTile.posX, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?1:6,moveToTile.bounds.x ,moveToTile.bounds.y - (colorPos? -128 : 128), board,PieceType.KNIGHT,pieceToPromote.isEnemy);
        tiles[2] = new PromotionTile(moveToTile.posX, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?2:5,moveToTile.bounds.x ,moveToTile.bounds.y - (colorPos? -128*2 : 128*2), board,PieceType.ROOK,pieceToPromote.isEnemy);
        tiles[3] = new PromotionTile(moveToTile.posX, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?3:4,moveToTile.bounds.x ,moveToTile.bounds.y - (colorPos? -128*3 : 128*3), board,PieceType.BISHOP,pieceToPromote.isEnemy);



        board.board[pieceToPromote.posY][pieceToPromote.posX] = null;
        isPromoting = true;
    }

    public static void renderPromotion(){
        renderOutline();
        renderPieces();
    }
    private static void renderOutline(){
        boolean colorPos = (pieceToPromote.isEnemy == !board.isBlackRotationBoard);

        shapeDrawer.rectangle(outlineBounds, Color.CYAN,4);
        shapeDrawer.filledRectangle(outlineBounds, pieceToPromote.isEnemy ? Color.BLACK : Color.WHITE);

        shapeDrawer.line(outlineBounds.x,outlineBounds.y + (colorPos?-128:128) * 0.4f  + outlineBounds.height * (colorPos?1:0)   ,outlineBounds.x + outlineBounds.width,outlineBounds.y + (colorPos?-128:128) * 0.4f  + outlineBounds.height * (colorPos?1:0) ,Color.CYAN,2);

        MyUtils.drawX(shapeDrawer,
            outlineBounds.x + 128 * 0.4f,
            outlineBounds.y  + outlineBounds.height * (colorPos?1:0)  + (colorPos?-128:128) * 0.1f,
            outlineBounds.x + outlineBounds.width - 128 * 0.4f,
            outlineBounds.y + (colorPos?-128:128) * 0.4f  + outlineBounds.height * (colorPos?1:0) - (colorPos?-128:128) * 0.1f,
            Color.CYAN,3);


    }
    private static void renderPieces(){
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("QUEEN%s.png",pieceToPromote.isEnemy?1:0)), tiles[0].bounds.x,tiles[0].posY*128 +10);
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("KNIGHT%s.png",pieceToPromote.isEnemy?1:0)), tiles[1].bounds.x,tiles[1].posY*128 +10);
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("ROOK%s.png",pieceToPromote.isEnemy?1:0)), tiles[2].bounds.x,tiles[2].posY*128 +10);
        shapeDrawer.getBatch().draw(MyGdxGame.piecesTextures.get(String.format("BISHOP%s.png",pieceToPromote.isEnemy?1:0)), tiles[3].bounds.x,tiles[3].posY*128 +10);
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
