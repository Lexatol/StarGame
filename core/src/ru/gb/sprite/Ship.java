package ru.gb.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.pool.impl.BulletPool;
import ru.gb.sprite.impl.Bullet;

public class Ship extends Sprite {

    protected Vector2 v;
    protected Vector2 v0;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int damage;
    protected Sound bulletSound;

    protected Rect worldBounds;

    protected float reloadTimer;
    protected float reloadInterval;
    protected int hp;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer +=delta;
        if (reloadTimer > reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV , bulletHeight, worldBounds, damage);
        bulletSound.play();
    }
}