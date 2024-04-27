package com.weezard12.shtokfishai.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.weezard12.shtokfishai.scenes.ChessAnalysis;
import com.weezard12.shtokfishai.scenes.ChessVsBot;
import com.weezard12.shtokfishai.scenes.HomeScreen;
import com.weezard12.shtokfishai.scenes.scenesBase.ChessSceneBase;

import java.util.HashMap;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGdxGame extends Game {
    public static final HashMap<String,Texture> piecesTextures = new HashMap<>();

    public static int tileSize = 128;
    public static int boardSize = 1024;

    public static SpriteBatch batch;

    public static String assetsPath = "";


    @Override
    public void create() {

        MyGdxGame.tileSize = Gdx.graphics.getHeight() / 8;
        MyGdxGame.boardSize = Gdx.graphics.getHeight();

        //for android or desktop
        loadPiecesTextures(assetsPath+"pieces/");

        batch = new SpriteBatch();
        setScreen(new ChessVsBot(this));
        //setScreen(new ChessSceneBase(this));


    }

    private void loadPiecesTextures(String piecesFolder){
        FileHandle folder = Gdx.files.internal(piecesFolder);
        FileHandle[] files = folder.list();

        piecesTextures.clear();
        for (FileHandle file : files) {
            Gdx.app.log("loadPiecesTextures",file.name());
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
