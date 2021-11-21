package ru.gb.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    //срабатывает когда экран отражается
    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
    }
    //перерисовка + отрезок времени в который срабатывает
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLUE);
    }
    //срабатывает когда происходит изменение экрана
    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + ", height = " + height);
    }
    //когда мы свернули экран
    @Override
    public void pause() {
        System.out.println("pause");
    }
    //когда мы экран развернули
    @Override
    public void resume() {
        System.out.println("resume");

    }
    //когда мы закрываем экран
    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }
    //закрытие всех ресурсов
    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.end();
    }

//кнопка на клавиатуре нажата
    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }
    //кнопка на клавиатуре отжата
    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }
    //факт нажатия кнопки, обычно идет ввод символов через этот метод
    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }
//кликаем мышкой по экрану или нажимаем пальцем на экран
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown screenX = " + screenX + " screenY = "+ screenY);
        return false;
    }
//палец с экрана убрали
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp screenX = " + screenX + " screenY = "+ screenY);

        return false;
    }
//прислонили палец и провели
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged screenX = " + screenX + " screenY = "+ screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        System.out.println("touchDragged amountX = " + amountX + " amountY = "+ amountY);
        return false;
    }
}
