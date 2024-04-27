package com.weezard12.shtokfishai.gameLogic.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.ai.ShtokfishThread;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardUI;
import com.weezard12.shtokfishai.main.MyUtils;

public class DebugBoardUI extends BoardUI {
    public DebugBoardUI(GameBoard gameBoard, Game game) {
        super(gameBoard,game);
    }

    private boolean checkForBlack = false;
    private TextButton enableBot;
    private TextButton changeColor;

    @Override
    protected void setupUI() {
        super.setupUI();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",40, Color.WHITE);
        enableBot =  new TextButton("Enable / Disable Bot",style);
        enableBot.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameBoard.clearMoveHighLight();

                gameBoard.moveTheBot = !gameBoard.moveTheBot;

                Shtokfish.thread.interrupt();
                Shtokfish.thread = new ShtokfishThread(gameBoard);
                Shtokfish.thread.start();
                gameBoard.board = Shtokfish.currentBoardEval.whiteEval.position;

                if(gameBoard.moveTheBot)
                    enableBot.getStyle().fontColor =Color.GREEN;
                else
                    enableBot.getStyle().fontColor =Color.WHITE;

            }
        });

        changeColor = new TextButton("change color",style);
        changeColor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameBoard.isBlackTurn = !gameBoard.isBlackTurn;
                Gdx.app.log("current moving color",gameBoard.isBlackTurn?"Black":"White");
                //changeColor.getStyle().fontColor = (checkForBlack?Color.BLACK: Color.WHITE);

            }
        });

        changeColor.align(Align.left);

        enableBot.align(Align.left);
        table.add(enableBot).left().row();

        table.add(changeColor).left().row();
    }
}
