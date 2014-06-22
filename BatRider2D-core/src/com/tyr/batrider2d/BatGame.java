package com.tyr.batrider2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tyr.batrider2d.handlers.Assets;
import com.tyr.batrider2d.handlers.BatInput;
import com.tyr.batrider2d.handlers.BatInputProcessor;
import com.tyr.batrider2d.handlers.BoundedCamera;
import com.tyr.batrider2d.handlers.GameStateManager;

public class BatGame implements ApplicationListener {
	
	public static final float STEP = 1 / 60f;
	public static final int SCALE = 2;
	public static final int V_HEIGHT = 240;
	public static final int V_WIDTH = 320;
	public static final String TITLE = "Bat Rider";

	private BoundedCamera cam;
	private GameStateManager gsm;
	private OrthographicCamera hudCam;
	private SpriteBatch batch;
	
	public static Assets assets;

	// Called on game creation
	public void create() {

		// Sets up the input
		Gdx.input.setInputProcessor(new BatInputProcessor());

		// Loads the assets
		assets = new Assets();
		assets.loadTexture();
		//
		//assets.loadSound("");
		//
		//assets.loadMusic("");
		//assets.getMusic("").setLooping(true);
		//assets.getMusic("").setVolume(0.5f);
		//assets.getMusic("").play();
		
		
		// Sets up the cameras
		cam = new BoundedCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		// Initializes the batch
		batch = new SpriteBatch();

		// Initializes the GameStateManager
		gsm = new GameStateManager(this);
	}

	// Renders the game; called every frame
	public void render() {

		// Updates the title
		Gdx.graphics.setTitle(TITLE + " -- FPS: "
				+ Gdx.graphics.getFramesPerSecond());

		// Update manager
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();

		// Update input
		BatInput.update();
	}

	//Disposes of resources
	public void dispose() {
		assets.disposeAll();
	}

	//Called on resize
	public void resize(int width, int height) {

	}

	//Called on pause
	public void pause() {

	}

	//Called on resume
	public void resume() {

	}

	//Returns the SpriteBatch
	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	//Returns the BoundedCamera
	public BoundedCamera getCamera() {
		return cam;
	}

	//Returns the OrthographicCamera
	public OrthographicCamera getHUDCamera() {
		return hudCam;
	}
}
