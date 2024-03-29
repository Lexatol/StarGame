package ru.gb.sprite.impl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.gb.math.Rect;
import ru.gb.screen.impl.GameScreen;
import ru.gb.sprite.BaseButton;

public class ButtonNewGame extends BaseButton {

    private final GameScreen gameScreen;

    private static final float HEIGHT = 0.05f;
    private static final float MARGIN = - 0.1f;

    public ButtonNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(MARGIN);
    }

    @Override
    protected void action() {
        gameScreen.startNewGame();
    }
}
