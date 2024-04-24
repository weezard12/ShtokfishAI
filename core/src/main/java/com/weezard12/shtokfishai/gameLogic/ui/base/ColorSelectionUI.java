package com.weezard12.shtokfishai.gameLogic.ui.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.main.MyGdxGame;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ColorSelectionUI {

public static Table getColorSelectionTable(GameBoard gameBoard){
    Table table = new Table();

    Image whiteKing = new Image(MyGdxGame.piecesTextures.get("KING0.png"));
    Image blackKing = new Image(MyGdxGame.piecesTextures.get("KING1.png"));

    whiteKing.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            whiteKing.setColor(Color.CYAN);
            blackKing.setColor(Color.WHITE);
            gameBoard.isBlackTurn = false;
        }
    });

    blackKing.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            whiteKing.setColor(Color.WHITE);
            blackKing.setColor(Color.CYAN);
            gameBoard.isBlackTurn = true;
        }
    });

    //Image randomKing = new Image(MyGdxGame.piecesTextures.get("KING0"));

    table.add(whiteKing);
    table.add(blackKing);

    return table;
}

}
