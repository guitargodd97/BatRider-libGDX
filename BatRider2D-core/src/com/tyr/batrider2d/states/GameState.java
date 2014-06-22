package com.tyr.batrider2d.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.BoundedCamera;
import com.tyr.batrider2d.handlers.GameStateManager;

public abstract class GameState {

	protected BatGame game;
	protected BoundedCamera cam;
	protected GameStateManager gsm;
	protected OrthographicCamera hudCam;
	protected SpriteBatch batch;

	// Constructor
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.getBatGame();
		batch = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHUDCamera();
	}

	// Handles input
	public abstract void handleInput();

	// Updates
	public abstract void update(float delta);

	// Renders
	public abstract void render();

	// Disposes
	public void dispose() {
		//All disposal handled by assets
	}
}
