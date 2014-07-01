package com.tyr.batrider2d.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.Animation;
import com.tyr.batrider2d.handlers.B2DVars;

public class Player extends B2DSprite {

	private Animation dead;
	private Animation flap;
	private boolean flashing;
	private boolean flapping;
	private Fireball[] fireballs;
	private float dy;
	private int lives;
	private Sprite[] lifeSprites;

	public Player(Body body) {
		super(body);
		dy = 0;
		body.setLinearVelocity(0f, dy);
		flap = new Animation(BatGame.assets.getAnimatedSprite("batrider-flap",
				3), 1 / 6f);
		flapping = false;
		lifeSprites = new Sprite[2];
		lifeSprites[0] = BatGame.assets.getSprite("life");
		lifeSprites[0].setPosition(2, 222);
		lifeSprites[1] = BatGame.assets.getSprite("life");
		lifeSprites[1].setPosition(4 + lifeSprites[1].getWidth(), 222);
		lives = 3;

		fireballs = new Fireball[5];
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.KinematicBody;
		bdef.position.set(-1000 / B2DVars.PPM, body.getPosition().y);
		bdef.fixedRotation = true;

		Body fbody = body.getWorld().createBody(bdef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(2f / B2DVars.PPM, 2f / B2DVars.PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.density = 1;
		fdef.friction = 0;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER_SHOT;
		fdef.filter.maskBits = B2DVars.BIT_ENEMY;

		fbody.createFixture(fdef);
		shape.dispose();

		for (int i = 0; i < fireballs.length; i++) {
			fireballs[i] = new Fireball(fbody);
			fireballs[i].setAnimation(
					BatGame.assets.getAnimatedSprite("fireball-move", 2),
					1 / 12f);
		}
	}

	public void flap() {
		if (!flapping) {
			dy += 6;
			body.setLinearVelocity(0f, dy);
			flapping = true;
		}
	}

	public void shoot() {
		if (!flapping) {

		} else {

			switch (flap.getCurrentFrameNum()) {
			case (1):

				break;
			case (2):

				break;
			default:

				break;
			}
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
		if (flapping)
			flap.update(delta);
		if (dy < 0)
			dy = 0;
		dy--;
		body.setTransform(new Vector2(20 / B2DVars.PPM, body.getPosition().y),
				body.getAngle());
		if (body.getPosition().y * B2DVars.PPM > 198)
			body.setTransform(new Vector2(20 / B2DVars.PPM, 198 / B2DVars.PPM),
					body.getAngle());
		else if (body.getPosition().y * B2DVars.PPM < -30)
			body.setTransform(new Vector2(20 / B2DVars.PPM, -30 / B2DVars.PPM),
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
		batch.begin();
		for (int i = 0; i < lifeSprites.length; i++) {
			if (lives - 1 >= i)
				lifeSprites[i].draw(batch);
		}
		batch.end();
	}

	public boolean isDead() {
		return lives > 0;
	}
}
