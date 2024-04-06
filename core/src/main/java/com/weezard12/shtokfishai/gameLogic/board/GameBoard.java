package com.weezard12.shtokfishai.gameLogic.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.weezard12.shtokfishai.gameLogic.pieces.KingPiece;
import com.weezard12.shtokfishai.gameLogic.pieces.KnightPiece;
import com.weezard12.shtokfishai.gameLogic.pieces.RookPiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameBoard {
    //Tiles
    public static Tile[][] tiles;
    Tile selectedTile;

    public BasePiece[][] board;
    public SpriteBatch batch;
    ShapeDrawer shapeDrawer;
    BoardColors boardColors;

    //Debug
    boolean isFreeMove = true;

    public GameBoard(SpriteBatch batch){
        this.batch = batch;
        boardColors = new BoardColors(Color.WHITE,Color.BLACK,Color.BLUE,Color.CYAN);

        board = new BasePiece[8][8];

        tiles = new Tile[8][8];
        createTiles();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);

        shapeDrawer = new ShapeDrawer(batch, region);
    }
    //update
    public void updateBoard(){
        checkForInput();
    }
    protected void checkForInput(){
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            for (Tile[] row: tiles) {
                for (Tile tile: row) {

                    if(tile.bounds.contains(Gdx.input.getX(),Gdx.graphics.getWidth() - Gdx.input.getY())){
                        Gdx.app.log("click on tile",tile.toString());
                        if (selectedTile == null){
                            selectedTile = tile;
                            selectedTile.highlightType=TileHighlightType.SELECTED;
                            board[selectedTile.posY][selectedTile.posX].getAllPossibleMoves();
                            //Gdx.app.log("mouse pos",Gdx.input.getX()+" "+ Gdx.input.getY());
                        }
                        else if(tile == selectedTile){
                            clearMoveHighLight();
                            selectedTile.highlightType=TileHighlightType.NONE;
                            selectedTile = null;
                        }
                        else{
                            movePiece(tile);
                            selectedTile.highlightType=TileHighlightType.NONE;
                            selectedTile = null;

                        }

                    }

                }

            }
        }

    }

    public void movePiece(Tile tile){
        clearMoveHighLight();
        if(isFreeMove){
            board[tile.posY][tile.posX] = board[selectedTile.posY][selectedTile.posX];
            board[selectedTile.posY][selectedTile.posX] = null;

        }

    }

    //region Render
    public void renderBoard(){
        batch.begin();
        drawBoard();
        drawPieces();
        batch.end();
    }
    protected void drawBoard(){
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                switch (tiles[y][x].highlightType){
                    case NONE:
                        shapeDrawer.setColor(((x+y) % 2 == 0) ? boardColors.black : boardColors.white);
                        break;
                    case SELECTED:
                        shapeDrawer.setColor(boardColors.selectedTile);
                        break;
                    case CAN_MOVE_TO:
                        shapeDrawer.setColor(boardColors.movesHighlightColor);
                        break;
                }

                shapeDrawer.filledRectangle(new Rectangle(x*128,y*128,128,128));
            }

        }
    }
    protected void drawPieces(){
        for (int x = 0; x<8;x++){
            for (int y = 0; y<8;y++){
                if(board[y][x]!=null)
                    batch.draw(board[y][x].texture,board[y][x].getPosX()*128,board[y][x].getPosY()*128 + 8);

            }

        }
    }
    //endregion
    public void setBoardByString(String s){
        int idx = 0;
        String piece = "";
        for (int y = 7; y>-1;y--){
            for (int x = 0; x<8;x++){

                while (s.charAt(idx)!=','){
                    piece+=s.charAt(idx);
                    idx++;
                }
                boolean color = piece.charAt(0)=='B';
                if(color)
                    piece = piece.substring(1);
                Gdx .app.log("p: ",piece +" "+x+" "+y +" "+color);
                switch (piece){
                    case "p":
                       board[y][x]= new BasePiece(PieceType.PAWN,color,board);
                       break;
                    case "r":
                        board[y][x]= new RookPiece(PieceType.ROOK,color,board);
                        break;
                    case "k":
                        board[y][x]= new KnightPiece(PieceType.KNIGHT,color,board);
                        break;
                    case "b":
                        board[y][x]= new BasePiece(PieceType.BISHOP,color,board);
                        break;
                    case "q":
                        board[y][x]= new BasePiece(PieceType.QUEEN,color,board);
                        break;
                    case "K":
                        board[y][x]= new KingPiece(PieceType.KING,color,board);
                        break;
                }
                piece = "";
                idx++;
            }

        }
    }
    private void createTiles(){
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                tiles[y][x] = new Tile(x,y,this);
            }

        }
    }

    public static BasePiece[][] cloneBoard(BasePiece[][] board){
        BasePiece[][] rBoard = new BasePiece[8][8];

        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if(board[y][x] != null)
                    switch (board[y][x].type){
                        case KING:
                            rBoard[y][x] = new KingPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        case KNIGHT:
                            rBoard[y][x] = new KnightPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        case ROOK:
                            rBoard[y][x] = new RookPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        default:
                            rBoard[y][x] = new BasePiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                    }

            }

        }

        return rBoard;
    }

    private void clearMoveHighLight(){
        for (Tile[] row :
            tiles) {
            for (Tile tile : row) {

                if(tile.highlightType == TileHighlightType.CAN_MOVE_TO)
                    tile.highlightType = TileHighlightType.NONE;

            }
        }
    }
    public static String toStringBoardArray(BasePiece[][] board){
        String s ="";
        for (int y = 0; y<8;y++) {
            for (int x = 0; x < 8; x++) {

                if (board[y][x] != null) {
                    s+= board[y][x].isJustMoved ? "m":"";
                    s += board[y][x].type+" ";

                }
                else s += "empty ";

            }
            s += "\n";
        }
        return s;
    }
}
