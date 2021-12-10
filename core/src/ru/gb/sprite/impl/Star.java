package ru.gb.sprite.impl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.sprite.Sprite;

public class Star extends Sprite {
    protected final Vector2 v;
    protected Rect worldBounds;

    public Star(TextureAtlas atlas, float speed) {
        super(atlas.findRegion("star"));
        float x = MathUtils.random(-0.005f, 0.005f);
        float y = MathUtils.random(-0.2f * speed, -0.1f);
        this.v = new Vector2(x, y);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.05f * v.y);
        float x = MathUtils.random(worldBounds.getLeft(), worldBounds.getRight());
        float y = MathUtils.random(worldBounds.getBottom(), worldBounds.getTop());
        this.pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        checkBounds();
    }

    protected void checkBounds() {
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
    }
}
