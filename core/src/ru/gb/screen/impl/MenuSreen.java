package ru.gb.screen.impl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.screen.BaseScreen;
import ru.gb.sprite.impl.Background;
import ru.gb.sprite.impl.ButtonExit;
import ru.gb.sprite.impl.ButtonPlay;
import ru.gb.sprite.impl.Star;

public class MenuSreen extends BaseScreen {

    private final Game game;


    private static final int STAR_COUNT = 256;

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;
    private Star [] stars;
    private ButtonExit btnExit;
    private ButtonPlay btnPlay;

    public MenuSreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        stars = new Star [256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas, 1f);
        }
        btnExit = new ButtonExit(atlas);
        btnPlay = new ButtonPlay(atlas, game);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        btnExit.resize(worldBounds);
        btnPlay.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        btnExit.draw(batch);
        btnPlay.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        btnExit.touchDown(touch, pointer, button);
        btnPlay.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        btnExit.touchUp(touch, pointer, button);
        btnPlay.touchUp(touch, pointer, button);
        return false;
    }
}
