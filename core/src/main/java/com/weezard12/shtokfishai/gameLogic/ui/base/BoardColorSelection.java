package com.weezard12.shtokfishai.gameLogic.ui.base;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.weezard12.shtokfishai.gameLogic.board.BoardColors;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.main.SolidColorImageButton;
import com.weezard12.shtokfishai.scenes.Settings;

import java.awt.*;

public class BoardColorSelection {
    public static Table getBoardColorSelectionTable(BoardColors colors, String themeName, Label textToChange){
        SolidColorImageButton b = new SolidColorImageButton(colors.black,100,100);
        SolidColorImageButton w = new SolidColorImageButton(colors.white,100,100);
        SolidColorImageButton h = new SolidColorImageButton(colors.movesHighlightColor,100,100);

        ClickListener clicked = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameBoard.boardColors.black = colors.black;
                GameBoard.boardColors.white = colors.white;
                GameBoard.boardColors.movesHighlightColor = colors.movesHighlightColor;
                textToChange.setText(themeName);
                Settings.name = themeName;
            }
        };
        b.addListener(clicked);
        w.addListener(clicked);
        h.addListener(clicked);
        Table cTable = new Table();

        cTable.add(b);
        cTable.add(w);
        cTable.add(h);

        return cTable;

    }

}
