package com.weezard12.shtokfishai.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.board.BoardColors;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardColorSelection;
import com.weezard12.shtokfishai.gameLogic.ui.base.ColorSelectionUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import com.weezard12.shtokfishai.main.SolidColorImageButton;
import com.weezard12.shtokfishai.scenes.scenesBase.AllScreensBase;
import com.weezard12.shtokfishai.scenes.scenesBase.ISceneWithUI;

public class Settings extends AllScreensBase implements ISceneWithUI {

    public static String name = "Default";

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

        Table bkg = new Table();
        bkg.setFillParent(true);
        bkg.add(new Image(new Texture(Gdx.files.internal("ui/screenBackgrounds/homeScreenBKG.png")))).expand().fill();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        labelStyle.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",60, Color.WHITE,Color.BLACK,4);
        buttonStyle.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",60, Color.WHITE,Color.BLACK,4);
        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.left().top();

        Label colorText = new Label("Board Color:",labelStyle);

        TextButton backButton = new TextButton("Back",buttonStyle);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen((MyGdxGame) game));
            }
        });

        Label themeName = new Label(name,labelStyle);

        table.add(colorText);
        table.add(themeName).colspan(0).row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(Color.WHITE,Color.BLACK,Color.BLUE,Color.CYAN),"Default", themeName));
        table.row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(MyUtils.rgbToFloatRgb(235,236,208),MyUtils.rgbToFloatRgb(115,149,82),Color.BLUE,Color.CYAN),"Chess.com",themeName));
        table.row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(MyUtils.rgbToFloatRgb(237,214,176),MyUtils.rgbToFloatRgb(184,135,98),Color.BLUE,Color.CYAN),"Brown",themeName));
        table.row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(MyUtils.rgbToFloatRgb(240,241,240),MyUtils.rgbToFloatRgb(196,216,228),Color.BLUE,Color.CYAN),"Sky",themeName));
        table.row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(MyUtils.rgbToFloatRgb(139,138,136),MyUtils.rgbToFloatRgb(105,104,102),Color.BLUE,Color.CYAN),"Clear",themeName));
        table.row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(MyUtils.rgbToFloatRgb(216,217,216),MyUtils.rgbToFloatRgb(168,169,168),Color.BLUE,Color.CYAN),"Light",themeName));
        table.row();
        table.add(BoardColorSelection.getBoardColorSelectionTable(new BoardColors(MyUtils.rgbToFloatRgb(237,203,165),MyUtils.rgbToFloatRgb(216,164,109),Color.BLUE,Color.CYAN),"Light Brown",themeName));
        table.row();
        table.add(backButton);

        stage.addActor(bkg);
        stage.addActor(table);
    }
}
