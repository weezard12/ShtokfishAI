package com.weezard12.shtokfishai.gameLogic.ui.base;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.weezard12.shtokfishai.main.MyGdxGame;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ColorSelectionUI {

public static Table getColorSelectionTable(){
    Table table = new Table();
    Image whiteKing = new Image(MyGdxGame.piecesTextures.get("KING0.png"));
    Image blackKing = new Image(MyGdxGame.piecesTextures.get("KING1.png"));
    //Image randomKing = new Image(MyGdxGame.piecesTextures.get("KING0"));

    table.add(whiteKing);
    table.add(blackKing);

    return table;
}

}
