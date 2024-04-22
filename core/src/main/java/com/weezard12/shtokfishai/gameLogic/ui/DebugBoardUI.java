package com.weezard12.shtokfishai.gameLogic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.ui.base.BoardUI;
import com.weezard12.shtokfishai.main.MyUtils;

public class DebugBoardUI extends BoardUI {
    public DebugBoardUI(GameBoard gameBoard) {
        super(gameBoard);
    }

    private boolean checkForBlack = false;
    private TextButton askForBestMove;
    private TextButton changeColor;

    @Override
    protected void setupUI() {
        super.setupUI();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",40, Color.WHITE);
        askForBestMove =  new TextButton("Calculate Best Move",style);
        askForBestMove.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameBoard.clearMoveHighLight();

                new Thread(() -> {
                    while (true){
                        Shtokfish.getBestPosition(gameBoard.board,checkForBlack);
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

/*                     BoardEval boardEv = Shtokfish.currentBoardEval;
                     PositionEval ev = checkForBlack? boardEv.blackEval : boardEv.whiteEval;
                     if(ev.position!=null)
                         gameBoard.board = ev.position;
                     Gdx.app.log("white pos",""+boardEv.whiteEval.getSumEval());
                     Gdx.app.log("black pos",""+boardEv.blackEval.getSumEval());*/
                }).start();
            }
        });

        changeColor = new TextButton("change color",style);
        changeColor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkForBlack = !checkForBlack;
                changeColor.getStyle().fontColor = (checkForBlack?Color.BLACK: Color.WHITE);

            }
        });

        changeColor.align(Align.left);

        askForBestMove.align(Align.left);
        table.add(askForBestMove).left().row();

        table.add(changeColor).left().row();
    }
}
