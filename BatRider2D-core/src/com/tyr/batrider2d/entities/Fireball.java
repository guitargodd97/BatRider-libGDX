package com.tyr.batrider2d.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.Animation;

public class Fireball extends B2DSprite {

	private Animation fade;
	private boolean active;
	private boolean fading;
	private float dx;

	public Fireball(Body body) {
		super(body);
		active = false;
		dx = 0;
		body.setLinearVelocity(dx, 0);
		fade = new Animation(
				BatGame.assets.getAnimatedSprite("fireball-hit", 3));
	}

	public void update(float delta) {
		if (active) {
			if (!fading) {
				super.update(delta);
			} else
				fade.update(delta);

		}
		if (!fading && fade.hasPlayedOnce())
			deactivate();
	}

	public void activate() {
		active = true;
		fade.setTimesPlayed(0);
		dx = 3;
	}

	public void deactivate() {
		dx = 0;
		active = false;
	}

	public boolean isActive() {
		return active;
	}
	
}
