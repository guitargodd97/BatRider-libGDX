package com.tyr.batrider2d.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.tyr.batrider2d.BatGame;

public class BatInputProcessor extends InputAdapter {

	// Mouse is moved
	public boolean mouseMoved(int x, int y) {
		BatInput.x = x / BatGame.SCALE;
		BatInput.y = (Gdx.graphics.getHeight() - y) / BatGame.SCALE;
		return true;
	}

	// Touch is dragged
	public boolean touchDragged(int x, int y, int pointer) {
		BatInput.x = x / BatGame.SCALE;
		BatInput.y = (Gdx.graphics.getHeight() - y) / BatGame.SCALE;
		BatInput.down = true;
		return true;
	}

	// Touch is down
	public boolean touchDown(int x, int y, int pointer, int button) {
		BatInput.x = x / BatGame.SCALE;
		BatInput.y = (Gdx.graphics.getHeight() - y) / BatGame.SCALE;
		BatInput.down = true;
		return true;
	}

	// Touch is released
	public boolean touchUp(int x, int y, int pointer, int button) {
		BatInput.x = x / BatGame.SCALE;
		BatInput.y = (Gdx.graphics.getHeight() - y) / BatGame.SCALE;
		BatInput.down = false;
		return true;
	}
}
