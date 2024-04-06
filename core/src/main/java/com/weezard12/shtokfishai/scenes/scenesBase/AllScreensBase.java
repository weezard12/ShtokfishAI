package com.weezard12.shtokfishai.scenes.scenesBase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.weezard12.shtokfishai.main.MyGdxGame;

public abstract class AllScreensBase implements Screen {
    protected Game game;
    public AllScreensBase(MyGdxGame game){
        this.game = game;
    }

}
