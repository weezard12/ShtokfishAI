package com.weezard12.shtokfishai.gameLogic.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.ai.ShtokfishThread;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardUI;
import com.weezard12.shtokfishai.gameLogic.ui.base.ColorSelectionUI;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;
import com.weezard12.shtokfishai.scenes.HomeScreen;

public class PlayVsPlayerUI extends BoardUI {
    public PlayVsPlayerUI(GameBoard gameBoard, Game game) {
        super(gameBoard, game);
    }

    @Override
    protected void setupUI() {
        super.setupUI();
        Table colorSelection = ColorSelectionUI.getColorSelectionTable(gameBoard);
        table.add(colorSelection).row();

        TextButton.TextButtonStyle style4 = new TextButton.TextButtonStyle();
        style4.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",80, Color.WHITE);
        TextButton playButton = new TextButton("Play",style4);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                gameBoard.isUpdatingInput = true;

                if(gameBoard.isBlackTurn)
                    if(gameBoard.moveTheBot){
                        gameBoard.isBlackTurn = !gameBoard.isBlackTurn;
                        Shtokfish.thread.interrupt();
                        Shtokfish.thread = new ShtokfishThread(gameBoard);
                        Shtokfish.thread.start();
                    }

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
