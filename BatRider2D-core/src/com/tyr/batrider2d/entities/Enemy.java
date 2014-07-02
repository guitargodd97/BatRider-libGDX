package com.tyr.batrider2d.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Enemy extends B2DSprite {

	protected float dx;
	protected float dy;
	protected int health;
	protected int points;

	public Enemy(Body body) {
		super(body);
	}

	public void loseHealth() {
		health--;
	}

}
