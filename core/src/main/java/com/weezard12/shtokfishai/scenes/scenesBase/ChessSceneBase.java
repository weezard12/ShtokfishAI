package com.weezard12.shtokfishai.scenes.scenesBase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.ui.DebugBoardUI;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardUI;
import com.weezard12.shtokfishai.main.MyGdxGame;

public abstract class ChessSceneBase extends AllScreensBase{
    protected GameBoard gameBoard;

    SpriteBatch batch = MyGdxGame.batch;
    public BoardUI boardUI;

    public ChessSceneBase(MyGdxGame game) {
        super(game);
        gameBoard = new GameBoard(MyGdxGame.batch);
        Shtokfish.init(gameBoard);
        setupUI();

        GameBoard.setBoardByString(gameBoard.board,
            "Br,Bk,Bb,Bq,BK,Bb,Bk,Br,"+
            "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp,"+
            "e,e,e,e,e,e,e,e,"+
            "e,e,e,e,e,e,e,e,"+
            "e,e,e,e,e,e,e,e,"+
            "e,e,e,e,e,e,e,e,"+
            "p,p,p,p,p,p,p,p,"+
            "r,k,b,q,K,b,k,r,"
        );
/*        gameBoard.setBoardByString(gameBoard.board,
         "Br,Bk,e,Br,e,e,BK,e,"+
            "Bp,Bp,Bp,e,e,Bp,e,e,"+
            "e,e,e,e,e,Bb,Bp,e,"+
            "e,e,e,e,e,e,e,Bp,"+
            "p,e,p,e,p,e,Bb,p,"+
            "e,e,k,e,e,k,e,e,"+
            "e,p,e,e,e,p,p,e,"+
            "r,e,e,e,K,b,e,r,"
        );*/

    }
    protected void setupUI(){
        boardUI = new DebugBoardUI(gameBoard, game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameBoard.updateBoard();

        batch.begin();
        boardUI.renderE();
        gameBoard.renderBoard();
        batch.end();

        batch.begin();
        boardUI.renderUI();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
