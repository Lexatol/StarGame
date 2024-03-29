package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.util.Regions;

public class Sprite extends Rect {
    protected float angle; // угол спрайта
    protected float scale = 1f; // скалярная вечилина
    protected TextureRegion [] regions; // набор текстур
    protected int frame; // кадр
    private boolean destroyed; //показывает учавствует в игре спрайт или нет

    public Sprite() {

    }

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        regions = Regions.split(region, rows, cols, frames);
    }

    public void draw(SpriteBatch batch) {
        batch.draw (
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public void resize(Rect worldBounds) {

    }

    public void update(float delta) {

    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        System.out.println("touchDown touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUp touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }


    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    public void destroy() {
        this.destroyed = true;
    }

    public void flushDestroy() {
        this.destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }



    public float getAngle() {
        return angle;
    }

    public float getScale() {
        return scale;
    }

    public TextureRegion[] getRegions() {
        return regions;
    }

    public int getFrame() {
        return frame;
    }
}
