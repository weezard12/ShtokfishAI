package com.weezard12.shtokfishai.gameLogic.ui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.main.MyGdxGame;
import com.weezard12.shtokfishai.main.MyUtils;

public class BoardUI {
    protected GameBoard gameBoard;
    protected Stage stage = new Stage();
    protected Table table = new Table();

    EvaluationBar evaluationBar;

    public  BoardUI(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        setupUI();
    }
    protected void setupUI(){
        stage.addActor(table);
        stage.setDebugAll(true);
        table.setFillParent(true);
        table.top().left();
        table.setBounds(MyGdxGame.boardSize + GameBoard.offsetToRight,0, 0,0);

        evaluationBar = new EvaluationBar(gameBoard.shapeDrawer,stage);
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
