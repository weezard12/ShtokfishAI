package com.weezard12.shtokfishai.gameLogic.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardUI;
import com.weezard12.shtokfishai.gameLogic.ui.base.ColorSelectionUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import com.weezard12.shtokfishai.scenes.HomeScreen;

public class PlayVsBotUI extends BoardUI {
    Label playingText;
    Label botName;
    Image botImage;

    Slider difficultySlider;

    public PlayVsBotUI(GameBoard gameBoard, Game game) {
        super(gameBoard, game);
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

        Table botTable = new Table();

        table.add(playingText).align(Align.left).left().colspan(2).row();
        botTable.add(botImage).align(Align.left).left();
        botTable.add(botName).align(Align.left).left().bottom().colspan(2).row();
        table.add(botTable).left().row();

        Table colorSelection = ColorSelectionUI.getColorSelectionTable(gameBoard);
        table.add(colorSelection).row();

        TextButton.TextButtonStyle style4 = new TextButton.TextButtonStyle();
        style4.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",80, Color.WHITE);
        TextButton playButton = new TextButton("Play",style4);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                colorSelection.remove();
                playButton.remove();
            }
        });
        TextButton backButton = new TextButton("Back",style4);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen((MyGdxGame) game));
            }
        });
        TextButton rotateBoard = new TextButton("Rotate",style4);
        rotateBoard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameBoard.isBlackRotationBoard = !gameBoard.isBlackRotationBoard;
                gameBoard.createTiles(gameBoard.isBlackRotationBoard);
            }
        });

        table.add(rotateBoard).row();
        table.add(playButton).row();
        table.add(backButton).row();
    }
}
