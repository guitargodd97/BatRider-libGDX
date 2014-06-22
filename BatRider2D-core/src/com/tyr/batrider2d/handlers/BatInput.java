package com.tyr.batrider2d.handlers;

public class BatInput {

	public static boolean down;
	public static boolean pdown;
	public static int x;
	public static int y;

	// Updates
	public static void update() {
		pdown = down;
	}

	// Is input down
	public static boolean isDown() {
		return down;
	}

	// Is input pressed
	public static boolean isPressed() {
		return down && !pdown;
	}

	// Is input released
	public static boolean isReleased() {
		return !down && pdown;
	}
}
