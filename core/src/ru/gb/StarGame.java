package ru.gb;

import com.badlogic.gdx.Game;

import ru.gb.screen.impl.MenuSreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuSreen(this));
	}
}
