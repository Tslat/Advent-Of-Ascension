package net.tslat.aoa3.library.constant;

import net.tslat.aoa3.util.RandomUtil;

public class ScreenImageEffect {
	private final Type type;

	private float rotation = 0;
	private float scale = 1;
	private int colour = 0;
	private int duration = 60;

	private int durationRemaining = 60;
	private int x = 0;
	private int y = 0;

	public ScreenImageEffect(Type type) {
		this.type = type;
	}

	public ScreenImageEffect rotated(float rotation) {
		this.rotation = rotation;

		return this;
	}

	public ScreenImageEffect randomRotation() {
		return rotated((float)RandomUtil.randomValueBetween(-180, 179));
	}

	public ScreenImageEffect scaled(float scale) {
		this.scale = scale;

		return this;
	}

	public ScreenImageEffect randomScale() {
		return scaled((float)RandomUtil.randomValueBetween(0.25, 1.25f));
	}

	public ScreenImageEffect coloured(int colour) {
		this.colour = colour;

		return this;
	}

	public ScreenImageEffect duration(int ticks) {
		this.duration = ticks;

		return this;
	}

	public void tickDown() {
		this.durationRemaining--;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public float getRotation() {
		return rotation;
	}

	public float getScale() {
		return scale;
	}

	public int getColour() {
		return colour;
	}

	public int getDuration() {
		return duration;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public Type getType() {
		return type;
	}

	public enum Type {
		SCRATCH("textures/gui/overlay/effect/scratch", 4),
		BLOOD("textures/gui/overlay/effect/splat", 4);

		public final String path;
		public final int variants;

		Type(String path, int variants) {
			this.path = path;
			this.variants = variants;
		}
	}
}
