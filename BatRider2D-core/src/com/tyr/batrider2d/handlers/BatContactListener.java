package com.tyr.batrider2d.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class BatContactListener implements ContactListener {

	// Constructor
	public BatContactListener() {
		super();
	}

	// Triggered by beginning contact
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa == null || fb == null)
			return;

		// Player shot X enemy
		if (fa.getUserData() != null && fa.getUserData().equals("playershot")) {
			if (fb.getUserData() != null && fb.getUserData().equals("enemy")) {

			}
		}
		if (fb.getUserData() != null && fb.getUserData().equals("playershot")) {
			if (fa.getUserData() != null && fa.getUserData().equals("enemy")) {

			}
		}

		// Enemy shot X player
		if (fa.getUserData() != null && fa.getUserData().equals("enemyshot")) {
			if (fb.getUserData() != null && fb.getUserData().equals("player")) {

			}
		}
		if (fb.getUserData() != null && fb.getUserData().equals("enemyshot")) {
			if (fa.getUserData() != null && fa.getUserData().equals("player")) {

			}
		}

		// Enemy X player
		if (fa.getUserData() != null && fa.getUserData().equals("enemy")) {
			if (fb.getUserData() != null && fb.getUserData().equals("player")) {

			}
		}
		if (fb.getUserData() != null && fb.getUserData().equals("enemy")) {
			if (fa.getUserData() != null && fa.getUserData().equals("player")) {

			}
		}
	}

	// Triggered by ending contact
	public void endContact(Contact contact) {
	}

	// Pre solve
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	// Post solve
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}