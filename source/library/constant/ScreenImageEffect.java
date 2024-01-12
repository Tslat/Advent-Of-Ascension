package net.tslat.aoa3.library.constant;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ScreenEffectPacket;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ScreenImageEffect {
	private final Type type;

	private boolean fullscreen = false;
	private float scale = 1;
	private ColourUtil.Colour colour = new ColourUtil.Colour(1, 1, 1, 1);
	private int duration = 60;

	private long expiredAt = 0;
	private float posX;
	private float posY;
	private ResourceLocation cachedTexture = null;

	public ScreenImageEffect(Type type) {
		this.type = type;
	}

	public ScreenImageEffect scaled(float scale) {
		this.scale = scale;

		return this;
	}

	public ScreenImageEffect randomScale() {
		return scaled((float)RandomUtil.randomValueBetween(0.25f, 1.25f));
	}

	public ScreenImageEffect coloured(int red, int green, int blue, int alpha) {
		this.colour = new ColourUtil.Colour(red / 255f, green / 255f, blue / 255f, alpha / 255f);

		return this;
	}

	public ScreenImageEffect coloured(int colour) {
		this.colour = new ColourUtil.Colour(colour);

		return this;
	}

	public ScreenImageEffect duration(int ticks) {
		this.duration = ticks;

		return this;
	}

	public ScreenImageEffect fullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;

		return this;
	}

	public float getScale() {
		return this.scale;
	}

	public int getColour() {
		return this.colour.packed();
	}

	public float getRed() {
		return this.colour.red();
	}

	public float getGreen() {
		return this.colour.green();
	}

	public float getBlue() {
		return this.colour.blue();
	}

	public float getAlpha() {
		return this.colour.alpha();
	}

	public int getDuration() {
		return this.duration;
	}

	public Type getType() {
		return this.type;
	}

	public ResourceLocation getTexture() {
		return this.cachedTexture;
	}

	public boolean isFullscreen() {
		return this.fullscreen;
	}

	public void sendToPlayer(ServerPlayer player) {
		AoANetworking.sendToPlayer(player, new ScreenEffectPacket(this));
	}

	public void init(Window window, long gameTime) {
		if (this.expiredAt != 0)
			return;

		setExpiry(gameTime);

		Random random = ThreadLocalRandom.current();

		if (this.type.variants == 1) {
			this.cachedTexture = this.type.texture;
		}
		else {
			ResourceLocation baseTexture = this.type.texture;
			this.cachedTexture = new ResourceLocation(baseTexture.getNamespace(), baseTexture.getPath().replace(".png", 1 + random.nextInt(this.type.variants) + ".png"));
		}

		if (!this.fullscreen) {
			this.posX = random.nextFloat(window.getGuiScaledWidth() + 256) / this.scale;
			this.posY = random.nextFloat(window.getGuiScaledHeight() + 256) / this.scale;
		}
	}

	public void setExpiry(long gameTime) {
		this.expiredAt = gameTime + this.duration;
	}

	public boolean isExpired(long gameTime) {
		return this.expiredAt <= gameTime;
	}

	public long getExpiry() {
		return this.expiredAt;
	}

	public float x() {
		return this.posX;
	}

	public float y() {
		return this.posY;
	}

	public enum Type {
		SCRATCH(AdventOfAscension.id("textures/gui/overlay/effect/scratch.png"), 4),
		BLOOD(AdventOfAscension.id("textures/gui/overlay/effect/splat.png"), 4),
		ACTION_KEY_VIGNETTE(AdventOfAscension.id("textures/gui/overlay/misc/action_key_activation_vignette.png"), 1);

		public final ResourceLocation texture;
		public final int variants;

		Type(ResourceLocation texture, int variants) {
			this.texture = texture;
			this.variants = variants;
		}
	}
}
