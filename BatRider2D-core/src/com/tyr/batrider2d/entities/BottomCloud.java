package com.tyr.batrider2d.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tyr.batrider2d.BatGame;

public class BottomCloud {

	private float dx;
	private Sprite image;

	public BottomCloud(int index) {
		int x = (int) ((Math.random() * 3948230) % 2);
		if (x == 0)
			image = BatGame.assets.getSprite("bottomcloud1");
		else
			image = BatGame.assets.getSprite("bottomcloud2");
		dx = -0.5f;
		image.setPosition(index * image.getWidth(), 20);
	}

	public void update(float delta) {
		image.setPosition(image.getX() + dx, 20);
		if(image.getX() <= -image.getWidth())
			image.setPosition(BatGame.V_WIDTH, 20);
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		image.draw(batch);
		batch.end();
	}
}
