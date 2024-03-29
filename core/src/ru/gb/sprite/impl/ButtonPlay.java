package ru.gb.sprite.impl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.gb.math.Rect;
import ru.gb.screen.impl.GameScreen;
import ru.gb.sprite.BaseButton;

public class ButtonPlay extends BaseButton {

    private static final float HEIGHT = 0.20f;
    private static final float MARGIN = 0.03f;

    protected Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setLeft(worldBounds.getLeft() + MARGIN);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    protected void action() {
        game.setScreen(new GameScreen());
    }
}
