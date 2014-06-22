package com.tyr.batrider2d.handlers;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animation {

	private float delay;
	private float time;
	private int currentFrame;
	private int timesPlayed;
	private Sprite[] sprites;

	// Constructor
	public Animation() {

	}

	// Constructor
	public Animation(Sprite[] sprites) {
		this(sprites, 1 / 12f);
	}

	// Constructor
	public Animation(Sprite[] sprites, float delay) {
		this.sprites = sprites;
		this.delay = delay;
		time = 0;
		currentFrame = 0;
	}

	// Sets the delay
	public void setDelay(float delay) {
		this.delay = delay;
	}

	// Sets the frames
	public void setFrames(Sprite[] sprites, float delay) {
		this.sprites = sprites;
		this.delay = delay;
	}

	// Sets the current frame
	public void setCurrentFrame(int currentFrame) {
		if (currentFrame < sprites.length)
			this.currentFrame = currentFrame;
	}

	// Updates
	public void update(float delta) {
		if (delay <= 0)
			return;
		time += delta;
		while (time >= delay) {
			step();
		}
	}

	// Steps
	private void step() {
		time -= delay;
		currentFrame++;
		if (currentFrame == sprites.length) {
			currentFrame = 0;
			timesPlayed++;
		}
	}

	// Returns the current frame
	public Sprite getFrame() {
		return sprites[currentFrame];
	}

	// Returns the times played
	public int getTimesPlayed() {
		return timesPlayed;
	}

	// Returns whether animation has played once
	public boolean hasPlayedOnce() {
		return timesPlayed > 0;
	}

	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}
}
