package com.tyr.batrider2d.handlers;

import java.util.Stack;

import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.states.Game;
import com.tyr.batrider2d.states.GameState;
import com.tyr.batrider2d.states.Gameover;
import com.tyr.batrider2d.states.Highscore;
import com.tyr.batrider2d.states.MainMenu;
import com.tyr.batrider2d.states.Options;

public class GameStateManager {

	private BatGame game;
	private Stack<GameState> gameStates;

	public static final int GAME = 32523623;
	public static final int GAMEOVER = 3211111;
	public static final int HIGHSCORE = -325235;
	public static final int MAINMENU = 432405235;
	public static final int OPTIONS = 99999;

	// Constructor
	public GameStateManager(BatGame game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(MAINMENU);
	}

	// Updates
	public void update(float delta) {
		gameStates.peek().update(delta);
	}

	// Renders
	public void render() {
		gameStates.peek().render();
	}

	// Returns the game
	public BatGame getBatGame() {
		return game;
	}

	// Gets the GameState
	public GameState getState(int state) {
		if (state == GAME)
			return new Game(this);
		if (state == GAMEOVER)
			return new Gameover(this);
		if (state == HIGHSCORE)
			return new Highscore(this);
		if (state == MAINMENU)
			return new MainMenu(this);
		if (state == OPTIONS)
			return new Options(this);
		return null;
	}

	// Sets the state
	public void setState(int state) {
		popState();
		pushState(state);
	}

	// Pushes the new GameState
	public void pushState(int state) {
		gameStates.push(getState(state));
	}

	// Pops the current state off the stack
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}
}
