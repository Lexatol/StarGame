package ru.gb.sprite.impl;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.pool.impl.BulletPool;
import ru.gb.pool.impl.ExplosionPool;
import ru.gb.sprite.Ship;

public class EnemyShip extends Ship {


    public EnemyShip(ExplosionPool explosionPool, BulletPool bulletPool, Sound bulletSound, Rect worldBounds) {
        this.explosionPool = explosionPool;
        this.bulletPool = bulletPool;
        this.bulletSound = bulletSound;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }

        if (getTop() > worldBounds.getTop()) {
            v.set(0, -0.3f);
        } else {
            v.set(v0);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV = bulletV;
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.setHeightProportion(height);
        this.hp = hp;
    }

    public boolean isBulletCollision(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );

    }
}
