package com.weezard12.shtokfishai.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import com.weezard12.shtokfishai.scenes.scenesBase.AllScreensBase;
import com.weezard12.shtokfishai.scenes.scenesBase.ChessSceneBase;
import com.weezard12.shtokfishai.scenes.scenesBase.ISceneWithUI;

public class HomeScreen extends AllScreensBase implements ISceneWithUI {

    private TextButton playBot;
    private TextButton playPlayer;
    private TextButton settings;
    private Label logo;
    Table playTable = new Table();

    public HomeScreen(MyGdxGame game) {
        super(game);

        setupUI();
    }

    @Override
    public void setupUI() {
        stage.setDebugAll(true);

        Table bkg = new Table();
        bkg.setFillParent(true);
        bkg.add(new Image(new Texture(Gdx.files.internal("ui/screenBackgrounds/homeScreenBKG.png")))).expand().fill();
        stage.addActor(bkg);

        //table
        playTable.setFillParent(true);
        playTable.align(Align.top);
        stage.addActor(playTable);



        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",180, Color.WHITE,Color.BLACK,4);

        logo = new Label("Chess",labelStyle);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",60, Color.WHITE,Color.BLACK,4);

        playBot = new TextButton("Play VS Bot",buttonStyle);
        playBot.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                playBot.addAction(Actions.scaleTo(2,2,1));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                playBot.setScale(4f);
            }
        });
        playBot.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ChessVsBot((MyGdxGame) game));
            }
        });

        playPlayer = new TextButton("Play VS Player",buttonStyle);
        playPlayer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ChessHotSit((MyGdxGame) game));
            }
        });

        settings = new TextButton("Settings",buttonStyle);

        playTable.add(logo).top().pad(20).padBottom(200).row();
        playTable.add(playBot).pad(20).row();
        playTable.add(playPlayer).pad(20).row();
        playTable.add(settings).pad(20);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
