package com.tyr.batrider2d.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.Animation;
import com.tyr.batrider2d.handlers.B2DVars;

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
		fading = false;
	}

	public void update(float delta) {
		if (active) {
			if (!fading) {
				super.update(delta);
			} else
				fade.update(delta);
		}
		if ((!fading && fade.hasPlayedOnce())
				|| body.getPosition().x > BatGame.V_WIDTH / B2DVars.PPM)
			deactivate();
	}

	public void activate(Vector2 position) {
		active = true;
		fade.setTimesPlayed(0);
		dx = 5;
		body.setLinearVelocity(dx, 0);
		body.setTransform(position, body.getAngle());
	}

	public void deactivate() {
		dx = 0;
		body.setLinearVelocity(dx, 0);
		active = false;
		body.setTransform(new Vector2(-1000 / B2DVars.PPM, 40), body.getAngle());
		fading = false;
	}

	public boolean isActive() {
		return active;
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(animation.getFrame(),
				(body.getPosition().x * B2DVars.PPM - width / 2),
				(int) (body.getPosition().y * B2DVars.PPM - height / 2));
		batch.end();
	}

	public void setFade(boolean fading) {
		this.fading = fading;
	}
}
