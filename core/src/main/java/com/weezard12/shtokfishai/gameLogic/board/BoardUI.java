package com.weezard12.shtokfishai.gameLogic.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.ai.BoardEval;
import com.weezard12.shtokfishai.gameLogic.ai.PositionEval;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;

public class BoardUI {
    GameBoard gameBoard;
    Stage stage = new Stage();
    Table table = new Table();

    private boolean checkForBlack = false;
    private TextButton askForBestMove;
    private TextButton changeColor;


    public  BoardUI(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        setupUI();
    }
    private void setupUI(){
        stage.addActor(table);
        stage.setDebugAll(true);
        table.setFillParent(true);
        table.top().left();
        table.setBounds(MyGdxGame.boardSize + gameBoard.offsetToRight,0, 0,0);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",40, Color.WHITE,Color.BLACK);
        askForBestMove =  new TextButton("Calculate Best Move",style);
        askForBestMove.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameBoard.clearMoveHighLight();

                 new Thread(() -> {
                     BoardEval boardEv = Shtokfish.getBestPosition(gameBoard.board,checkForBlack);
                     PositionEval ev = checkForBlack? boardEv.blackEval : boardEv.whiteEval;
                     if(ev.position!=null)
                         gameBoard.board = ev.position;
                     Gdx.app.log("pos",""+ev.materialValue);
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


        Gdx.input.setInputProcessor(stage);
    }
    public void render(){
        stage.act();
        stage.draw();
    }
}
