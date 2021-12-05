package ru.gb.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.pool.impl.BulletPool;
import ru.gb.pool.impl.EnemyPool;
import ru.gb.screen.BaseScreen;
import ru.gb.sprite.impl.Background;
import ru.gb.sprite.impl.MainShip;
import ru.gb.sprite.impl.Star;
import ru.gb.util.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;

    private TextureAtlas atlas;
    private Star[] stars;

    private MainShip mainShip;

    private Music music;
    private Sound laserSound;
    private Sound bulletSound;

    private EnemyEmitter enemyEmitter;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, bulletSound, worldBounds);

        enemyEmitter = new EnemyEmitter(worldBounds, atlas, enemyPool);


        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();

        stars = new Star [256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas, 0.5f);
        }


        mainShip = new MainShip(atlas, bulletPool, laserSound);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        music.dispose();
        laserSound.dispose();
        bulletSound.dispose();
        enemyPool.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
        enemyPool.updateActiveSprites(delta);
        bulletPool.updateActiveSprites(delta);
        enemyEmitter.generate(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        batch.end();
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
    }
}
