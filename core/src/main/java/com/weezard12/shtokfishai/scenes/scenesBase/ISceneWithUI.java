package com.weezard12.shtokfishai.scenes.scenesBase;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public interface ISceneWithUI {
    Stage stage = new Stage(new ScreenViewport());

    void setupUI();
}
