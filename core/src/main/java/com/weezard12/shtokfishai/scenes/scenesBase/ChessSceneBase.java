package com.weezard12.shtokfishai.scenes.scenesBase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.main.MyGdxGame;

public class ChessSceneBase extends AllScreensBase{
    GameBoard gameBoard;
    public ChessSceneBase(MyGdxGame game) {
        super(game);
        gameBoard = new GameBoard(game.batch);
/*        gameBoard.setBoardByString(
            "Br,Bk,Bb,Bq,BK,Bb,Bk,Br,"+
            "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp,"+
            "e,e,e,e,e,e,e,e,"+
            "e,e,e,e,e,e,e,e,"+
            "e,e,e,e,e,e,e,e,"+
            "e,e,e,e,e,e,e,e,"+
            "p,p,p,p,p,p,p,p," +
            "r,k,b,q,K,b,k,r,"
        );*/
        gameBoard.setBoardByString(
            "e,e,e,e,BK,e,e,e,"+
                "e,Br,e,e,e,e,e,e,"+
                "e,e,e,e,e,e,e,e,"+
                "e,e,e,e,e,e,e,e,"+
                "e,e,Bb,e,e,e,e,e,"+
                "p,p,e,e,Bq,e,e,e,"+
                "p,p,p,e,e,e,p,e,"+
                "r,e,e,e,K,e,e,r,"
        );

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameBoard.updateBoard();
        gameBoard.renderBoard();
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
