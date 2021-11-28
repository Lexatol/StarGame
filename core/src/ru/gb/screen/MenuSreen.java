package ru.gb.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.screen.impl.BaseScreen;
import ru.gb.sprite.impl.Background;
import ru.gb.sprite.impl.Logo;

public class MenuSreen extends BaseScreen {

    private Texture img;
    private Texture bg;

    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("textures/bg.png");

        background = new Background(bg);
        logo = new Logo(img);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        logo.update(delta);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();


    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }
}
