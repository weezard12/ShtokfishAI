package com.weezard12.shtokfishai.scenes;

import com.weezard12.shtokfishai.gameLogic.ui.DebugBoardUI;
import com.weezard12.shtokfishai.gameLogic.ui.PlayVsBotUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.scenes.scenesBase.ChessSceneBase;

public class ChessAnalysis extends ChessSceneBase {

    public ChessAnalysis(MyGdxGame game) {
        super(game);
        gameBoard.isUpdatingInput = true;
    }

    @Override
    protected void setupUI() {
        boardUI = new DebugBoardUI(gameBoard, game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
