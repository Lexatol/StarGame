package ru.gb.pool.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.gb.pool.SpritesPool;
import ru.gb.sprite.impl.Bullet;

public class BulletPool extends SpritesPool<Bullet> {



    @Override
    protected Bullet newObject() {
        return new Bullet();

    }
}
