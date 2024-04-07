package com.weezard12.shtokfishai.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MyUtils {
    public BitmapFont getBitMapFont(String fontPath, int size, Color textColor, Color borderColor){
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.borderColor = borderColor;
        fontParameter.color = textColor;

        BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();

        return font;
    }

}
