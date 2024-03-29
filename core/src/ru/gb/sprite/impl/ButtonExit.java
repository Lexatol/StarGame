package ru.gb.sprite.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.gb.math.Rect;
import ru.gb.sprite.BaseButton;

public class ButtonExit extends BaseButton {

    private static final float HEIGHT = 0.15f;
    private static final float MARGIN = 0.03f;

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setRight(worldBounds.getRight() - MARGIN);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    protected void action() {
        Gdx.app.exit();
    }
}
