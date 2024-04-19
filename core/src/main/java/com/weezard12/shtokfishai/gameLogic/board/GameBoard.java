package com.weezard12.shtokfishai.gameLogic.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.pieces.*;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.PieceType;
import com.weezard12.shtokfishai.gameLogic.ui.BoardUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

import com.weezard12.shtokfishai.main.Point;

public class GameBoard {
    public boolean isWhiteTurn = true;

    //region Scale and UI
    public BoardUI boardUI;
    public static final int offsetToRight = ((int)(MyGdxGame.boardSize * 0.08f));
    //endregion


    //Tiles
    public static Tile[][] tiles;
    Tile selectedTile;

    public BasePiece[][] board;
    public SpriteBatch batch;
    public ShapeDrawer shapeDrawer;
    BoardColors boardColors;

    Array<BasePiece[][]> possibleMoves = new Array<>();

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
        boardUI = new BoardUI(this);

    }
    //update
    public void updateBoard(){
        checkForInput();
    }
    protected void checkForInput(){
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            for (Tile[] row: tiles) {
                for (Tile tile: row) {

                    if(tile.bounds.contains(Gdx.input.getX(),MyGdxGame.boardSize - Gdx.input.getY())){
                        Gdx.app.log("click on tile",tile.toString());
                        if (selectedTile == null){
                            clearMoveHighLight();

                            selectedTile = tile;
                            selectedTile.highlightType=TileHighlightType.SELECTED;
                            if(board[selectedTile.posY][selectedTile.posX] != null){
                                possibleMoves.clear();
                                board[selectedTile.posY][selectedTile.posX].getAllPossibleMoves(selectedTile.posX,selectedTile.posY,possibleMoves);
                                Tile.setTileHighlight(possibleMoves,board[selectedTile.posY][selectedTile.posX],tiles);
                            }

                            //Gdx.app.log("check check",""+board[selectedTile.posY][selectedTile.posX].doesCheck(finedKingInBoard(board,board[selectedTile.posY][selectedTile.posX].isEnemy?false:true)));
                        }
                        else if(tile == selectedTile){
                            clearMoveHighLight();
                            selectedTile.highlightType=TileHighlightType.NONE;
                            selectedTile = null;
                        }
                        else{
                            if(board[selectedTile.posY][selectedTile.posX] != null)
                                movePiece(tile,board[selectedTile.posY][selectedTile.posX]);

                            selectedTile.highlightType=TileHighlightType.NONE;
                            selectedTile = null;

                        }

                    }

                }

            }
        }

    }

    public void movePiece(Tile tile,BasePiece selectedPiece){

        if(tile.highlightType!=TileHighlightType.CAN_MOVE_TO)
            return;

        clearMoveHighLight();
        if(isFreeMove){
            //check for queen spawn
            if(board[selectedTile.posY][selectedTile.posX].type==PieceType.PAWN){

                //if en passant left
                if(tile.posX > 0)
                    if(tile.posY == 5 - 3 * (selectedPiece.isEnemy ? 1 : 0))
                        if(selectedPiece.getPosY() == 4 - (selectedPiece.isEnemy ? 1 : 0))
                            if(board[selectedPiece.getPosY()][selectedPiece.getPosX()-1] != null)
                                if(board[selectedPiece.getPosY()][selectedPiece.getPosX()-1] instanceof PawnPiece)
                                    if(((PawnPiece)board[selectedPiece.getPosY()][selectedPiece.getPosX()-1]).isMovedTwo)
                                        board[selectedPiece.getPosY()][selectedPiece.getPosX()-1] = null;

                //if en passant right
                if(tile.posX < 7)
                    if(tile.posY == 5 - 3 * (selectedPiece.isEnemy ? 1 : 0))
                        if(selectedPiece.getPosY() == 4 - (selectedPiece.isEnemy ? 1 : 0))
                            if(board[selectedPiece.getPosY()][selectedPiece.getPosX()+1] != null)
                                if(board[selectedPiece.getPosY()][selectedPiece.getPosX()+1] instanceof PawnPiece)
                                    if(((PawnPiece)board[selectedPiece.getPosY()][selectedPiece.getPosX()+1]).isMovedTwo)
                                        board[selectedPiece.getPosY()][selectedPiece.getPosX()+1] = null;

                //if first move
                if(selectedPiece.getPosY() == 6 -5 * (selectedPiece.isEnemy ? 0 : 1))
                    if(tile.posY == (4 - (board[selectedTile.posY][selectedTile.posX].isEnemy ? 0 : 1))){
                        ((PawnPiece)selectedPiece).isMovedTwo = true;
                    }


                //promote
                if(tile.posY== 7 * (board[selectedTile.posY][selectedTile.posX].isEnemy? 0 : 1))
                    board[tile.posY][tile.posX] = new QueenPiece(PieceType.QUEEN,board[selectedTile.posY][selectedTile.posX].isEnemy,board);
                else
                    board[tile.posY][tile.posX] = board[selectedTile.posY][selectedTile.posX];
            }
            else if(board[selectedTile.posY][selectedTile.posX].type==PieceType.KING){
                boolean isCastled = false;
                if(selectedPiece.getPosX() == 4)
                {


                    if(!((KingPiece)selectedPiece).isEverMoved)
                        if(tile.posX==2){
                            if(board[selectedPiece.getPosY()][0] != null)
                                if(board[selectedPiece.getPosY()][0].type == PieceType.ROOK)
                                    if(!((RookPiece)board[selectedPiece.getPosY()][0]).isEverMoved){
                                        board[tile.posY][2] = selectedPiece;
                                        board[tile.posY][3] = board[tile.posY][0];
                                        board[tile.posY][0] = null;
                                        isCastled = true;
                                    }
                        }
                        else if(tile.posX==6){
                            if(board[selectedPiece.getPosY()][7] != null)
                                if(board[selectedPiece.getPosY()][7].type == PieceType.ROOK)
                                    if(!((RookPiece)board[selectedPiece.getPosY()][7]).isEverMoved){
                                        board[tile.posY][6] = selectedPiece;
                                        board[tile.posY][5] = board[tile.posY][7];
                                        board[tile.posY][7] = null;
                                        isCastled = true;
                                    }
                        }
                }
                if(!isCastled)
                    board[tile.posY][tile.posX] = board[selectedTile.posY][selectedTile.posX];

            }
            else
                board[tile.posY][tile.posX] = board[selectedTile.posY][selectedTile.posX];
            if(board[tile.posY][tile.posX] instanceof KingPiece)
                ((KingPiece)board[tile.posY][tile.posX]).isEverMoved = true;

            clearEnPassantOptionFromPawns(!selectedPiece.isEnemy);
            board[selectedTile.posY][selectedTile.posX] = null;

        }

    }

    //region Render
    public void renderBoard(){
        batch.begin();
        boardUI.renderE();
        drawBoard();
        drawPieces();
        boardUI.renderUI();
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

                //shapeDrawer.filledRectangle(new Rectangle(x*128 + offsetToRight,y*128,128,128));
                shapeDrawer.filledRectangle(tiles[y][x].bounds);
            }

        }
    }
    protected void drawPieces(){
        for (int x = 0; x<8;x++){
            for (int y = 0; y<8;y++){
                if(board[y][x]!=null){
                    if(board[y][x].texture==null)
                        board[y][x].texture = MyGdxGame.piecesTextures.get(String.format("%s%s.png",Gdx.files.internal(String.valueOf(board[y][x].type)),board[y][x].isEnemy ? 1 : 0 ));
                    batch.draw(board[y][x].texture,board[y][x].getPosX()*128+offsetToRight,board[y][x].getPosY()*128 + 8);
                }
            }

        }
    }
    //endregion
    //region Setup Board
    public void setBoardByString(String s){
        int idx = 0;
        StringBuilder piece = new StringBuilder();
        for (int y = 7; y>-1;y--){
            for (int x = 0; x<8;x++){

                while (s.charAt(idx)!=','){
                    piece.append(s.charAt(idx));
                    idx++;
                }
                boolean color = piece.charAt(0)=='B';
                if(color)
                    piece = new StringBuilder(piece.substring(1));
                Gdx .app.log("p: ",piece +" "+x+" "+y +" "+color);
                switch (piece.toString()){
                    case "p":
                       board[y][x]= new PawnPiece(PieceType.PAWN,color,board);
                       break;
                    case "r":
                        board[y][x]= new RookPiece(PieceType.ROOK,color,board);
                        break;
                    case "k":
                        board[y][x]= new KnightPiece(PieceType.KNIGHT,color,board);
                        break;
                    case "b":
                        board[y][x]= new BishopPiece(PieceType.BISHOP,color,board);
                        break;
                    case "q":
                        board[y][x]= new QueenPiece(PieceType.QUEEN,color,board);
                        break;
                    case "K":
                        board[y][x]= new KingPiece(PieceType.KING,color,board);
                        break;
                }
                piece = new StringBuilder();
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
    //endregion
    public static BasePiece[][] cloneBoard(BasePiece[][] board){
        BasePiece[][] rBoard = new BasePiece[8][8];

        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if(board[y][x] != null)
                    switch (board[y][x].type){
                        case KING:
                            rBoard[y][x] = new KingPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            ((KingPiece)rBoard[y][x]).isEverMoved = ((KingPiece)board[y][x]).isEverMoved;
                            break;
                        case KNIGHT:
                            rBoard[y][x] = new KnightPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        case ROOK:
                            rBoard[y][x] = new RookPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        case BISHOP:
                            rBoard[y][x] = new BishopPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        case QUEEN:
                            rBoard[y][x] = new QueenPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        case PAWN:
                            rBoard[y][x] = new PawnPiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                        default:
                            rBoard[y][x] = new BasePiece(board[y][x].type,board[y][x].isEnemy,rBoard);
                            break;
                    }

            }

        }

        return rBoard;
    }

    public static Point finedKingInBoard(BasePiece[][] board, boolean isEnemy){
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if(board[y][x] != null)
                    if (board[y][x].type == PieceType.KING && board[y][x].isEnemy == isEnemy)
                        return new Point(x,y);
            }
        }
        return new Point(-10,-1);
    }

    public static boolean isColorInCheck(BasePiece[][] board, boolean isEnemy){
        Point p = finedKingInBoard(board,isEnemy);
        if(p.x==-10)
            return true;
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if(board[y][x] != null)
                    if (board[y][x].isEnemy == !isEnemy){
                        MyUtils.log("check all",board[y][x].toString());
                        if(board[y][x].doesCheck(x,y, p.x, p.y)){
                            MyUtils.log("check all","True");
                            return true;
                        }

                    }

            }
        }
        return false;

    }

    public void clearMoveHighLight(){
        for (Tile[] row : tiles) {
            for (Tile tile : row) {

                if(tile.highlightType == TileHighlightType.CAN_MOVE_TO)
                    tile.highlightType = TileHighlightType.NONE;

            }
        }
    }
    private void clearEnPassantOptionFromPawns(boolean isEnemy){
        for (BasePiece[] row : board) {
            for (BasePiece piece : row) {
                if (piece != null)
                    if(piece.isEnemy==isEnemy)
                        if(piece instanceof PawnPiece)
                            ((PawnPiece)piece).isMovedTwo = false;
            }
        }
    }
    public static String toStringBoardArray(BasePiece[][] board){
        String s ="";
        for (int y = 0; y<8;y++) {
            for (int x = 0; x < 8; x++) {

                if (board[y][x] != null) {
                    s+= board[y][x].isJustMoved ? "m":"";
                    s += board[y][x].type+"-";
                    s += board[y][x].isEnemy?"black ":"white ";

                }
                else s += "empty ";

            }
            s += "\n";
        }
        return s;
    }

}
