package ru.gb.sprite.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.sprite.Sprite;

public class Logo extends Sprite {
    private Vector2 touch;
    private Vector2 speed;

    private float length = 0.01f;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        touch = new Vector2();
        speed = new Vector2();
    }


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        setHeightProportion(0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (pos.dst(touch) > length) {
            pos.add(speed);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        speed.set(touch.cpy().sub(pos)).setLength(length);
        return super.touchDown(touch, pointer, button);
    }
}
