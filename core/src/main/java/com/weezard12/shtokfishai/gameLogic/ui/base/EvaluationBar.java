package com.weezard12.shtokfishai.gameLogic.ui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.weezard12.shtokfishai.gameLogic.ai.Shtokfish;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.main.MyUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class EvaluationBar {
    private final ShapeDrawer shapeDrawer;

    Stage stage;
    Table table = new Table();;
    private Label blackEval;
    public Label whiteEval;

    private float startValue = 0;
    private float targetValue = 0;
    private float elapsedTime = 0;

    public EvaluationBar(ShapeDrawer shapeDrawer, Stage stage){
        this.shapeDrawer = shapeDrawer;
        this.stage = stage;

    }
    public void setup(){
        stage.addActor(table);
        table.setBounds(0,0,GameBoard.offsetToRight,Gdx.graphics.getHeight());
        table.top();
        table.padTop(Gdx.graphics.getHeight()*0.1f);
        table.padBottom(Gdx.graphics.getHeight()*0.1f);
        table.setDebug(true);

        Label.LabelStyle style = new Label.LabelStyle(MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",25,Color.WHITE,Color.BLACK,2),Color.WHITE);
        blackEval = new Label("?",style);
        blackEval.setAlignment(Align.top);
        table.add(blackEval).top().expandX().expandY().row();

        style = new Label.LabelStyle(MyUtils.getBitMapFont("ui/fonts/Roboto-Bold.ttf",30,Color.BLACK,Color.WHITE,2),Color.WHITE);
        whiteEval = new Label("?",style);
        whiteEval.setAlignment(Align.bottom);
        table.add(whiteEval);


    }

    public void draw() {
        float height = Shtokfish.currentBoardEval.getWhiteEvalAsPresent() * Gdx.graphics.getHeight();
        if(targetValue != height){
            startValue = targetValue;
            targetValue = height;
            elapsedTime = 0;
        }

        shapeDrawer.filledRectangle(0,0,GameBoard.offsetToRight, Gdx.graphics.getHeight(),Color.BLACK);

        shapeDrawer.filledRectangle(0,0,GameBoard.offsetToRight, Interpolation.circleOut.apply(startValue,targetValue,elapsedTime), Color.WHITE);


        shapeDrawer.rectangle(0,0,GameBoard.offsetToRight,Gdx.graphics.getHeight(),Color.BLUE,4);

        if(Shtokfish.currentBoardEval.getWhiteEvalSubtractByBlack() >= 100){
            whiteEval.setText("M");
            blackEval.setText("M");
        }
        else {
            whiteEval.setText(MyUtils.floatToShortString((Shtokfish.currentBoardEval.whiteEval.getSumEval() - Shtokfish.currentBoardEval.blackEval.getSumEval())));
            if(whiteEval.textEquals("0.00"))
                blackEval.setText("0.00");
            else
                blackEval.setText(""+(Float.parseFloat(whiteEval.getText().toString())*-1));
        }

        if(elapsedTime < 1)
            elapsedTime+=Gdx.graphics.getDeltaTime()*3;

    }



}
