package com.weezard12.shtokfishai.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import com.weezard12.shtokfishai.main.SolidColorImageButton;
import com.weezard12.shtokfishai.scenes.scenesBase.AllScreensBase;
import com.weezard12.shtokfishai.scenes.scenesBase.ISceneWithUI;

public class Settings extends AllScreensBase implements ISceneWithUI {
    public Settings(MyGdxGame game) {
        super(game);
        setupUI();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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

    @Override
    public void setupUI() {
        stage.clear();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",60, Color.WHITE,Color.BLACK,4);
        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.left().top();

        Label colorText = new Label("Board Color:",labelStyle);
        SolidColorImageButton black = new SolidColorImageButton(Color.BLACK,100,100);
        SolidColorImageButton white = new SolidColorImageButton(Color.WHITE,100,100);

        SolidColorImageButton cmlDefault = new SolidColorImageButton(MyUtils.rgbToFloatRgb(115,149,82),100,100);
        SolidColorImageButton cmdDefault = new SolidColorImageButton(MyUtils.rgbToFloatRgb(235,236,208),100,100);


        table.add(colorText).colspan(0).row();
        table.add(black);
        table.add(white).row();
        table.add(cmlDefault);
        table.add(cmdDefault).row();


        stage.addActor(table);
    }
}
