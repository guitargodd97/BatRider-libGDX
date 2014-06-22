package com.tyr.batrider2d.handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	public static final String TEXTURE_LOCATION = "data/textures/texture.atlas";
	public static final String SFX_LOCATION = "data/sound/sfx/";
	public static final String MUSIC_LOCATION = "data/sound/music/";

	private AssetManager musicManager;
	private AssetManager soundManager;
	private AssetManager textureManager;

	// Constructor
	public Assets() {
		// Creates the musicManager
		musicManager = new AssetManager();
		musicManager.setLoader(Music.class, new MusicLoader(
				new InternalFileHandleResolver()));

		// Creates the soundManager
		soundManager = new AssetManager();
		soundManager.setLoader(Sound.class, new SoundLoader(
				new InternalFileHandleResolver()));

		// Creates the textureManager
		textureManager = new AssetManager();
		textureManager.setLoader(TextureAtlas.class, new TextureAtlasLoader(
				new InternalFileHandleResolver()));
	}

	// Returns if the managers are done loading everything
	public boolean doneLoading() {
		return musicManager.update() && soundManager.update()
				&& textureManager.update();
	}

	// Loads a sound
	public void loadSound(String name) {
		soundManager.load(SFX_LOCATION + name + ".mp3", Sound.class);
	}

	// Loads music
	public void loadMusic(String name) {
		musicManager.load(MUSIC_LOCATION + name + ".mp3", Music.class);
	}

	// Loads texture
	public void loadTexture() {
		textureManager.load(TEXTURE_LOCATION, TextureAtlas.class);
		textureManager.finishLoading();
	}

	// Retrieves a sound
	public Sound getSound(String name) {
		return soundManager.get(SFX_LOCATION + name + ".mp3", Sound.class);
	}

	// Retrieves a song
	public Music getMusic(String name) {
		return musicManager.get(MUSIC_LOCATION + name + ".mp3", Music.class);
	}

	// Retrieves a texture
	public TextureAtlas getTexture(String name) {
		return textureManager.get(name, TextureAtlas.class);
	}

	// Retrieves a sprite
	public Sprite getSprite(String name) {
		return textureManager.get(Assets.TEXTURE_LOCATION, TextureAtlas.class)
				.createSprite(name);
	}

	// Retrieves an animated sprite
	public Sprite[] getAnimatedSprite(String name, int numOfFrames) {
		Sprite[] array = new Sprite[numOfFrames];
		for (int i = 0; i < numOfFrames; i++)
			array[i] = textureManager.get(Assets.TEXTURE_LOCATION,
					TextureAtlas.class).createSprite(name, i);
		return array;
	}

	// Disposes of everything
	public void disposeAll() {
		textureManager.dispose();
		soundManager.dispose();
		musicManager.dispose();
	}
}
