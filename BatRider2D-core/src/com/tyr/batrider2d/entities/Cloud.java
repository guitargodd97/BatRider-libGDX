package com.tyr.batrider2d.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.B2DVars;

public class Cloud extends B2DSprite {

	private float dx;

	public Cloud(Body body) {
		super(body);
		dx = -1;
		body.setLinearVelocity(dx, 0);
		body.setTransform(
				new Vector2(
						(float) (((Math.random() * 23907520) % BatGame.V_WIDTH + 30))
								/ B2DVars.PPM,
						(float) (((Math.random() * 392572344) % (BatGame.V_HEIGHT - 60)) + 25)
								/ B2DVars.PPM), body.getAngle());
	}

	public void update(float delta) {
		super.update(delta);
		if (body.getPosition().x < (-30 / B2DVars.PPM)) {
			body.setTransform(
					new Vector2(
							(float) (BatGame.V_WIDTH + ((Math.random() * 23907520) % 80 + 30))
									/ B2DVars.PPM,
							(float) (((Math.random() * 392572344) % (BatGame.V_HEIGHT - 60)) + 25)
									/ B2DVars.PPM), body.getAngle());
		}
	}
}
