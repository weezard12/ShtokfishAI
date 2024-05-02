package com.weezard12.shtokfishai.scenes;

import com.weezard12.shtokfishai.gameLogic.ui.PlayVsPlayerUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.scenes.scenesBase.ChessSceneBase;

public class ChessHotSit extends ChessSceneBase {
    public ChessHotSit(MyGdxGame game) {
        super(game);
        gameBoard.isFreeMove = false;
        gameBoard.isUpdatingInput = true;
    }

    @Override
    protected void setupUI() {
        boardUI = new PlayVsPlayerUI(gameBoard,game);
    }
}
