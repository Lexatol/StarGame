package ru.gb.sprite.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.sprite.Sprite;

public class Ship extends Sprite {
    private TextureRegion img;

    private Vector2 touch;
    private Vector2 speed;

    private static final float MARGIN = 0.03f;
    private final float LENGTH = 0.01f;
    private final float STEP = 0.01f;

    private Rect worldBounds;

    public Ship(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, 195, 287));
        touch = new Vector2();
        speed = new Vector2();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeightProportion(0.2f * worldBounds.getHeight());
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (pos.dst(touch) >= LENGTH) {
            pos.add(speed);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        speed.set(touch.cpy().sub(pos)).setLength(LENGTH);
        return false;
    }


    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            checkBounds();
            speed.x -= STEP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            checkBounds();
            speed.x += STEP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            checkBounds();
            speed.y += STEP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            checkBounds();
            speed.y -= STEP;
        }
        return false;
    }

    private void checkBounds() {
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
        if (getTop() > worldBounds.getTop()) {
            setTop(worldBounds.getTop());
        }
        if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
        }
    }
}
