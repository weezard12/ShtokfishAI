package com.weezard12.shtokfishai.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Locale;

import static com.badlogic.gdx.Files.FileType.Local;

public class MyUtils {

    private static final boolean logEnabled = false;
    public static BitmapFont getBitMapFont(String fontPath, int size, Color textColor, Color borderColor,int borderWidth){
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.borderColor = borderColor;
        fontParameter.color = textColor;
        fontParameter.borderWidth =borderWidth;

        BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();

        return font;
    }
    public static BitmapFont getBitMapFont(String fontPath, int size, Color textColor){
        return getBitMapFont(fontPath,size,textColor,Color.WHITE,0);
    }

    public static void log(String tag, String message){
        if(logEnabled)
            Gdx.app.log(tag,message);
    }

    public static String floatToShortString(float num){
        return String.format(Locale.US,"%.2f",num);
    }

}
