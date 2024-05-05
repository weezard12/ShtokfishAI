package com.weezard12.shtokfishai.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SolidColorImageButton extends ImageButton {

    public SolidColorImageButton(Color color, float width, float height) {
        super(new TextureRegionDrawable(createSolidColorTextureRegion(color, width, height)));
        this.setSize(width, height);
    }

    private static TextureRegion createSolidColorTextureRegion(Color color, float width, float height) {
        Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        TextureRegion textureRegion = new TextureRegion(new Texture(pixmap));
        pixmap.dispose();
        return textureRegion;
    }

}
