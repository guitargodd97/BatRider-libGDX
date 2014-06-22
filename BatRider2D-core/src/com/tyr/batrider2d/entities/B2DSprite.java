package com.tyr.batrider2d.entities;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tyr.batrider2d.handlers.Animation;
import com.tyr.batrider2d.handlers.B2DVars;

public class B2DSprite {

	protected Animation animation;
	protected Body body;
	protected float height;
	protected float width;

	// Constructor
	public B2DSprite(Body body) {
		this.body = body;
		animation = new Animation();
	}

	// Sets the animation
	public void setAnimation(Sprite[] sprites, float delay) {
		animation.setFrames(sprites, delay);
		width = sprites[0].getWidth();
		height = sprites[0].getHeight();
	}

	// Updates
	public void update(float delta) {
		animation.update(delta);
	}

	// Renders
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(animation.getFrame(),
				(body.getPosition().x * B2DVars.PPM - width / 2),
				(int) (body.getPosition().y * B2DVars.PPM - height / 2));
		batch.end();
	}

	// Return the body
	public Body getBody() {
		return body;
	}

	// Returns the position
	public Vector2 getPosition() {
		return body.getPosition();
	}

	// Returns the width
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
