package com.weezard12.shtokfishai.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.weezard12.shtokfishai.scenes.scenesBase.ChessSceneBase;

import java.util.HashMap;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGdxGame extends Game {
    public static final HashMap<String,Texture> piecesTextures = new HashMap<>();

    public static final int boardSize = 1024;
    public SpriteBatch batch;

    public static String assetsPath = "";

    @Override
    public void create() {

        //for android or desktop
        loadPiecesTextures(assetsPath+"pieces/");

        batch = new SpriteBatch();
        setScreen(new ChessSceneBase(this));

    }

    private void loadPiecesTextures(String piecesFolder){
        FileHandle folder = Gdx.files.internal(piecesFolder);
        FileHandle[] files = folder.list();

        piecesTextures.clear();
        for (FileHandle file : files) {
            Gdx.app.log("a",file.name());
            piecesTextures.put(file.name(),new Texture(file));
        }

    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
