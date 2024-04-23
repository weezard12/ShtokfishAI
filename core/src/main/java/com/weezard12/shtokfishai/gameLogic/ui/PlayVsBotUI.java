package com.weezard12.shtokfishai.gameLogic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardUI;
import com.weezard12.shtokfishai.gameLogic.ui.base.ColorSelectionUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;

public class PlayVsBotUI extends BoardUI {
    Label playingText;
    Label botName;
    Image botImage;

    Slider difficultySlider;

    public PlayVsBotUI(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    protected void setupUI() {
        super.setupUI();
        Label.LabelStyle style1 = new Label.LabelStyle();
        Label.LabelStyle style2 = new Label.LabelStyle();
        style1.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",100, Color.WHITE);
        style2.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",40, Color.WHITE);

        botImage = new Image(new Texture(Gdx.files.internal("windowIcon/knight64.png")));

        playingText = new Label("Playing:",style1);
        botName = new Label("Stokfish AI",style2);

        Slider.SliderStyle style3 = new Slider.SliderStyle();
        //difficultySlider = new Slider(,style3);


        table.add(playingText).align(Align.left).left().colspan(2).row();
        table.add(botImage).align(Align.left).left();
        table.add(botName).align(Align.left).left().colspan(2).row();

        table.add(ColorSelectionUI.getColorSelectionTable());

    }
}
