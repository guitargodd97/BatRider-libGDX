package com.tyr.batrider2d.handlers;

import static com.tyr.batrider2d.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.entities.BottomCloud;
import com.tyr.batrider2d.entities.Cloud;

public class Background {

	private Array<Cloud> clouds;
	private boolean level;
	private BottomCloud[] bottom;
	private Sprite image;

	// Constructor
	public Background(Sprite image, OrthographicCamera gameCam, boolean level,
			World world) {
		this.image = image;
		this.image.setPosition((gameCam.viewportWidth - image.getWidth()) / 2,
				(gameCam.viewportHeight - image.getHeight()) / 2);
		this.level = level;

		if (level) {
			bottom = new BottomCloud[25];
			for (int i = 0; i < bottom.length; i++)
				bottom[i] = new BottomCloud();

			clouds = new Array<Cloud>();
			BodyDef clouddef = new BodyDef();
			clouddef.type = BodyType.KinematicBody;
			clouddef.position.set(160 / PPM, 180 / PPM);
			Body cloudbody = world.createBody(clouddef);
			PolygonShape cloudshape = new PolygonShape();
			cloudshape.setAsBox(2f / PPM, 2f / PPM);
			FixtureDef cloudfdef = new FixtureDef();
			cloudfdef.shape = cloudshape;
			cloudbody.createFixture(cloudfdef);
			Cloud c = new Cloud(cloudbody);
			Sprite[] s = new Sprite[1];
			s[0] = BatGame.assets.getSprite("cloud1");
			c.setAnimation(s, 1 / 12f);
			clouds.add(c);

			clouddef = new BodyDef();
			clouddef.type = BodyType.KinematicBody;
			clouddef.position.set(260 / PPM, 100 / PPM);
			cloudbody = world.createBody(clouddef);
			cloudshape = new PolygonShape();
			cloudshape.setAsBox(2f / PPM, 2f / PPM);
			cloudfdef = new FixtureDef();
			cloudfdef.shape = cloudshape;
			cloudbody.createFixture(cloudfdef);
			c = new Cloud(cloudbody);
			s[0] = BatGame.assets.getSprite("cloud1");
			c.setAnimation(s, 1 / 12f);
			clouds.add(c);

			clouddef = new BodyDef();
			clouddef.type = BodyType.KinematicBody;
			clouddef.position.set(300 / PPM, 200 / PPM);
			cloudbody = world.createBody(clouddef);
			cloudshape = new PolygonShape();
			cloudshape.setAsBox(2f / PPM, 2f / PPM);
			cloudfdef = new FixtureDef();
			cloudfdef.shape = cloudshape;
			cloudbody.createFixture(cloudfdef);
			c = new Cloud(cloudbody);
			s[0] = BatGame.assets.getSprite("cloud1");
			c.setAnimation(s, 1 / 12f);
			clouds.add(c);

			clouddef = new BodyDef();
			clouddef.type = BodyType.KinematicBody;
			clouddef.position.set(120 / PPM, 40 / PPM);
			cloudbody = world.createBody(clouddef);
			cloudshape = new PolygonShape();
			cloudshape.setAsBox(2f / PPM, 2f / PPM);
			cloudfdef = new FixtureDef();
			cloudfdef.shape = cloudshape;
			cloudbody.createFixture(cloudfdef);
			c = new Cloud(cloudbody);
			s[0] = BatGame.assets.getSprite("cloud2");
			c.setAnimation(s, 1 / 12f);
			clouds.add(c);

			clouddef = new BodyDef();
			clouddef.type = BodyType.KinematicBody;
			clouddef.position.set(320 / PPM, 75 / PPM);
			cloudbody = world.createBody(clouddef);
			cloudshape = new PolygonShape();
			cloudshape.setAsBox(2f / PPM, 2f / PPM);
			cloudfdef = new FixtureDef();
			cloudfdef.shape = cloudshape;
			cloudbody.createFixture(cloudfdef);
			c = new Cloud(cloudbody);
			s[0] = BatGame.assets.getSprite("cloud2");
			c.setAnimation(s, 1 / 12f);
			clouds.add(c);
		}
	}

	// Updates
	public void update(float delta) {
		if (level) {
			for (Cloud c : clouds)
				c.update(delta);
			// for (BottomCloud b : bottom)
			// b.update(delta);
		}
	}

	// Renders
	public void render(SpriteBatch batch) {

		// Draws background
		batch.begin();
		image.draw(batch);
		batch.end();

		// Draws clouds
		if (level) {
			for (Cloud c : clouds)
				c.render(batch);
			// for (BottomCloud b : bottom)
			// b.render(batch);
		}
	}
}
