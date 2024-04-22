package com.weezard12.shtokfishai.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.weezard12.shtokfishai.gameLogic.ui.DebugBoardUI;
import com.weezard12.shtokfishai.gameLogic.ui.PlayVsBotUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.scenes.scenesBase.ChessSceneBase;

public class ChessVsBot extends ChessSceneBase {

    public ChessVsBot(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void setupUI() {
        boardUI = new PlayVsBotUI(gameBoard);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

}
