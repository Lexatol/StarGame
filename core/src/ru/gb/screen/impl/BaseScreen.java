package ru.gb.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.gb.math.MatrixUtils;
import ru.gb.math.Rect;

public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;
    private Rect screenBounds; // хранит размеры экрана в пикселях
    private Rect worldBounds; // мировая система координат которую мы хотим у себя использовать
    private Rect glBounds; //координатная сетка Gl

    private Matrix4 worldToGl;
    private Matrix3 screenToWorld;

    private Vector2 touch;

    //срабатывает когда экран отражается
    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        screenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0, 0, 1f, 1f);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        touch = new Vector2();

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
        // Описывает скриновскую систему координат: задает размер, устанавливает точку в 0 0
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width / (float) height;
        worldBounds.setSize(1f * aspect, 1f);

        //копируем матрицу с заданными параметрами в свою матрицу
        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        resize(worldBounds);

        //Матрица событий
        MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds, glBounds);
    }

    // получаем актуальные границы в новой системе координат
    public void resize(Rect worldBounds) {
        System.out.println("worldBounds width = " + worldBounds.getWidth() + " height = " + worldBounds.getHeight());

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
        System.out.println("touchDown screenX = " + screenX + " screenY = " + screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch, pointer, button);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        System.out.println("touchDown touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    //палец с экрана убрали
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp screenX = " + screenX + " screenY = " + screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchUp(touch, pointer, button);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUp touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }


    //прислонили палец и провели
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged screenX = " + screenX + " screenY = " + screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        System.out.println("touchDragged amountX = " + amountX + " amountY = " + amountY);
        return false;
    }
}
