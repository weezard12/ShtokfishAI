package com.weezard12.shtokfishai.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import space.earlygrey.shapedrawer.ShapeDrawer;

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

    //shape drawer
    public static void drawX(ShapeDrawer shapeDrawer, float x1, float y1, float x2, float y2, Color color, int lineWidth){
        shapeDrawer.line(x1, y1, x2, y2, color,lineWidth);
        shapeDrawer.line(x2, y1, x1, y2, color,lineWidth);
    }

    public static String floatToShortString(float num){
        return String.format(Locale.US,"%.2f",num);
    }

}
