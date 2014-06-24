package com.tyr.batrider2d.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {

	private BitmapFont font;
	private int score;

	public HUD() {
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		score = 0;
	}

	public void update(int score) {
		this.score = score;
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		font.draw(batch, "Score: " + score, 140, 232);
		batch.end();
	}
}
