package com.tyr.batrider2d.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.Animation;
import com.tyr.batrider2d.handlers.B2DVars;

public class Player extends B2DSprite {

	private Animation dead;
	private Animation flap;
	private boolean flashing;
	private boolean flapping;
	private float dy;
	private int lives;

	public Player(Body body) {
		super(body);
		dy = 0;
		body.setLinearVelocity(0f, dy);
		flap = new Animation(BatGame.assets.getAnimatedSprite("batrider-flap",
				3), 1 / 6f);
		flapping = false;
	}

	public void flap() {
		if (!flapping) {
			dy = 5;
			body.setLinearVelocity(0f, dy);
			flapping = true;
		}
	}

	public void loseLife() {
		if (!flashing) {
			lives--;
			flashing = true;
		}
	}

	public void update(float delta) {
		super.update(delta);
		if(flapping)
			flap.update(delta);
		if (dy < 0)
			dy = 0;
		dy--;
		body.setTransform(new Vector2(30 / B2DVars.PPM, body.getPosition().y),
				body.getAngle());
	}

	public void render(SpriteBatch batch) {
		if (!flapping)
			super.render(batch);
		else {
			batch.begin();
			batch.draw(flap.getFrame(),
					(body.getPosition().x * B2DVars.PPM - width / 2),
					(int) (body.getPosition().y * B2DVars.PPM - height / 2));
			batch.end();
			if (flap.hasPlayedOnce()) {
				flapping = false;
				flap.setTimesPlayed(0);
			}
		}
	}
}
