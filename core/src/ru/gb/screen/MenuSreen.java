package ru.gb.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.screen.impl.BaseScreen;

public class MenuSreen extends BaseScreen {

    private Texture img;
    private Vector2 touch;
    private Vector2 speed;
    private Vector2 start;
    private float length;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        start = new Vector2();
        speed = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, start.x, start.y);
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
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        speed.set(screenX - start.x, Gdx.graphics.getHeight() - screenY - start.y);
        speed.nor();
        length = touch.dst(start);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
