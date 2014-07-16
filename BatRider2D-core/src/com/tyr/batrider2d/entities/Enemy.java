package com.tyr.batrider2d.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.handlers.Animation;
import com.tyr.batrider2d.handlers.B2DVars;

public abstract class Enemy extends B2DSprite {

	protected Animation death;
	protected boolean active;
	protected boolean canShoot;
	protected Fireball[] fireballs;
	protected float dx;
	protected float dy;
	protected int health;
	protected int points;

	public Enemy(Body body, String name, int frames) {
		super(body);
		this.setAnimation(
				BatGame.assets.getAnimatedSprite(name + "-move", frames),
				1 / 12f);
		dy = 0;
		dx = -4;
		updateVelocity();
		active = false;
		
	}

	protected void setDeathAnimation(String name, int frames) {
		death = new Animation(BatGame.assets.getAnimatedSprite(name + "-dead",
				frames), 1 / 12f);
	}

	protected void setupFireballs() {
		fireballs = new Fireball[10];
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.KinematicBody;
		bdef.position.set(-1000 / B2DVars.PPM, body.getPosition().y);
		bdef.fixedRotation = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(2f / B2DVars.PPM, 2f / B2DVars.PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.density = 1;
		fdef.friction = 0;
		fdef.filter.categoryBits = B2DVars.BIT_ENEMY_SHOT;
		fdef.filter.maskBits = B2DVars.BIT_PLAYER;

		for (int i = 0; i < fireballs.length; i++) {
			Body fbody = body.getWorld().createBody(bdef);
			fbody.createFixture(fdef);
			fireballs[i] = new Fireball(fbody);
			fireballs[i].setAnimation(
					BatGame.assets.getAnimatedSprite("fireball-move", 2),
					1 / 12f);
		}
		shape.dispose();
	}

	protected void shoot() {
		for (int i = 0; i < fireballs.length; i++) {
			if (!fireballs[i].isActive()) {
				fireballs[i].activate(body.getPosition(), true);
				i = fireballs.length;
			}
		}
	}

	public void loseHealth() {
		health--;
	}

	protected void updateVelocity() {
		body.setLinearVelocity(dx, dy);
	}

	public void update(float delta) {
		super.update(delta);
		
		for (Fireball ball : fireballs)
			ball.update(delta);
	}

	public void render(SpriteBatch batch) {
		if (health > 0)
			super.render(batch);
		else {
			batch.begin();
			batch.draw(death.getFrame(),
					(body.getPosition().x * B2DVars.PPM - width / 2),
					(int) (body.getPosition().y * B2DVars.PPM - height / 2));
			batch.end();
		}
		
		for (Fireball ball : fireballs)
			ball.render(batch);
	}

	public int getPoint() {
		return points;
	}

	public void activate(float x, float y) {
		active = true;
		body.setTransform(new Vector2(x / B2DVars.PPM, y / B2DVars.PPM),
				body.getAngle());
	}

	public void deactivate() {
		dx = 0;
		dy = 0;
		updateVelocity();
		active = false;
		body.setTransform(new Vector2(-100, -100), body.getAngle());
	}

}
