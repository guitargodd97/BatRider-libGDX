package com.tyr.batrider2d.states;

import static com.tyr.batrider2d.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tyr.batrider2d.BatGame;
import com.tyr.batrider2d.entities.Player;
import com.tyr.batrider2d.handlers.Background;
import com.tyr.batrider2d.handlers.BatInput;
import com.tyr.batrider2d.handlers.GameStateManager;

public class Game extends GameState {

	private boolean debug = true;
	
	private Background bg;
	private Box2DDebugRenderer b2dRenderer;
	private Player player;
	private World world;

	public Game(GameStateManager gsm) {
		super(gsm);
		world = new World(new Vector2(0, -9.8f * 5), true);
		bg = new Background(BatGame.assets.getSprite("gamebackground"), cam,
				true, world);
		
		b2dRenderer = new Box2DDebugRenderer();
		
		cam.setToOrtho(false, BatGame.V_WIDTH, BatGame.V_HEIGHT);
		
		BodyDef playerdef = new BodyDef();
		playerdef.type = BodyType.DynamicBody;
		playerdef.position.set(30 / PPM, 200 / PPM);
		Body playerbody = world.createBody(playerdef);
		PolygonShape playershape = new PolygonShape();
		playershape.setAsBox(2f / PPM, 2f / PPM);
		FixtureDef playerfdef = new FixtureDef();
		playerfdef.shape = playershape;
		playerbody.createFixture(playerfdef);
		player = new Player(playerbody);
		Sprite[] s = new Sprite[1];
		s[0] = BatGame.assets.getSprite("batrider-hover");
		player.setAnimation(s, 1 / 12f);
	}

	public void handleInput() {
		if(BatInput.isPressed())
			player.flap();
	}

	public void update(float delta) {
		handleInput();
		world.step(delta / 5, 8, 3);
		bg.update(delta);
		player.update(delta);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(cam.combined);
		
		bg.render(batch);
		
		player.render(batch);
		
		bg.renderClouds(batch);
		
		if(debug) {
			b2dRenderer.render(world, cam.combined);
		}
	}

}
