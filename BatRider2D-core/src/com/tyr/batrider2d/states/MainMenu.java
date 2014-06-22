package com.tyr.batrider2d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.Animation;
import com.tyr.batrider2d.handlers.Background;
import com.tyr.batrider2d.handlers.GUIButton;
import com.tyr.batrider2d.handlers.GameStateManager;

public class MainMenu extends GameState {

	private Animation batAnimation;
	private Background bg;
	private GUIButton highscores;
	private GUIButton newGame;
	private GUIButton options;
	private GUIButton quit;

	// Constructor
	public MainMenu(GameStateManager gsm) {
		super(gsm);
		bg = new Background(BatGame.assets.getSprite("menubackground"), cam, false, null);

		Sprite[] temp = BatGame.assets.getAnimatedSprite("batrider-menu", 3);
		for (Sprite s : temp)
			s.setPosition((BatGame.V_WIDTH - s.getWidth()) / 2 - 15,
					BatGame.V_HEIGHT - s.getHeight() - 15);
		batAnimation = new Animation(temp);

		cam.setToOrtho(false, BatGame.V_WIDTH, BatGame.V_HEIGHT);

		newGame = new GUIButton(BatGame.assets.getAnimatedSprite(
				"newgame-button", 2), 160, 120);
		highscores = new GUIButton(BatGame.assets.getAnimatedSprite(
				"highscores-button", 2), 160, 90);
		options = new GUIButton(BatGame.assets.getAnimatedSprite(
				"options-button", 2), 160, 60);
		quit = new GUIButton(
				BatGame.assets.getAnimatedSprite("quit-button", 2), 160, 30);

	}

	public void handleInput() {
		if (newGame.isClicked())
			gsm.setState(GameStateManager.GAME);
		if (highscores.isClicked())
			gsm.setState(GameStateManager.HIGHSCORE);
		if (options.isClicked())
			gsm.setState(GameStateManager.OPTIONS);
		if (quit.isClicked())
			Gdx.app.exit();
	}

	public void update(float delta) {
		handleInput();

		bg.update(delta);
		batAnimation.update(delta);
		newGame.update(delta);
		highscores.update(delta);
		options.update(delta);
		quit.update(delta);
	}

	public void render() {
		batch.setProjectionMatrix(cam.combined);

		bg.render(batch);

		batch.begin();
		batAnimation.getFrame().draw(batch);
		batch.end();

		newGame.render(batch);
		highscores.render(batch);
		options.render(batch);
		quit.render(batch);
	}
}
