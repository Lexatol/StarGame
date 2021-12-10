package ru.gb.sprite.impl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.StarGame;
import ru.gb.math.Rect;
import ru.gb.pool.impl.BulletPool;
import ru.gb.pool.impl.EnemyPool;
import ru.gb.screen.impl.GameScreen;
import ru.gb.screen.impl.MenuSreen;
import ru.gb.sprite.BaseButton;
import ru.gb.util.EnemyEmitter;

public class ButtonNG extends BaseButton {

    private GameScreen gameScreen;

    private static final float HEIGHT = 0.04f;
    private static final float MARGIN = 0.03f;

    public ButtonNG(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(0.2f);
    }

    @Override
    protected void action() {
        gameScreen.startGame();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);

    }
}
