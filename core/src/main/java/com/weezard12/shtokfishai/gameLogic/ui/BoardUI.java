package com.weezard12.shtokfishai.gameLogic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.ai.BoardEval;
import com.weezard12.shtokfishai.gameLogic.ai.PositionEval;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;

public class BoardUI {
    GameBoard gameBoard;
    Stage stage = new Stage();
    Table table = new Table();

    private boolean checkForBlack = false;
    private TextButton askForBestMove;
    private TextButton changeColor;

    EvaluationBar evaluationBar;

    public  BoardUI(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        setupUI();
    }
    private void setupUI(){
        stage.addActor(table);
        stage.setDebugAll(true);
        table.setFillParent(true);
        table.top().left();
        table.setBounds(MyGdxGame.boardSize + GameBoard.offsetToRight,0, 0,0);

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


        evaluationBar = new EvaluationBar(gameBoard.shapeDrawer,stage);
        //stage.addActor(evaluationBar);
        evaluationBar.setup();

        Gdx.input.setInputProcessor(stage);
    }
    public void renderUI(){

        //ui
        stage.act();
        stage.draw();



    }
    public void renderE(){

        //shape renderer
        evaluationBar.draw();
    }
}
