package ru.gb.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.screen.impl.BaseScreen;
import ru.gb.sprite.impl.Background;

public class MenuSreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Vector2 touch;
    private Vector2 speed;
    private Vector2 start;
    private float length;

    private Background background;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("textures/bg.png");

        start = new Vector2();
        speed = new Vector2();
        touch = new Vector2();
        background = new Background(bg);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, start.x , start.y, 0.5f, 0.5f);
        background.draw(batch);
        batch.end();
        length--;
        if (length > 0) {
            start.add(speed);
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
//        speed.set(touch.cpy().sub(start));
//        speed.nor();
//        length = touch.dst(start);
//        return super.touchDown(screenX, screenY, pointer, button);
//    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }
}
