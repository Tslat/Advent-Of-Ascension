package net.tslat.aoa3.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.world.World;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

import javax.annotation.Nullable;

public class PortalFloaterParticle extends SpriteTexturedParticle {
	private final IAnimatedSprite sprite;
	private final double originPosX;
	private final double originPosY;
	private final double originPosZ;

	public PortalFloaterParticle(World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, IAnimatedSprite sprite, float scale, float ageModifier, float red, float green, float blue, float alpha) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.originPosX = posX;
		this.originPosY = posY;
		this.originPosZ = posZ;
		this.motionX = velocityX;
		this.motionY = velocityY;
		this.motionZ = velocityZ;
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		this.particleAlpha = alpha;
		this.particleScale = scale * rand.nextFloat() * 0.07F;
		this.maxAge = (int)(((Math.random() * 10.0D) + 40) * ageModifier);

		selectSpriteRandomly(this.sprite);
	}

	@Override
	public float getScale(float scaleFactor) {
		float scale = 1 - ((float)age + scaleFactor) / (float)maxAge;
		scale = 1 - (scale * scale);

		return this.particleScale * scale;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z) {
		setBoundingBox(getBoundingBox().offset(x, y, z));
		resetPositionToBB();
	}

	@Override
	public void tick() {
		float percentAged = (float)age / (float)maxAge;
		float ageModifier = 1f - (-percentAged + percentAged * percentAged * 2.0F);

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		posX = originPosX + motionX * (double)ageModifier;
		posY = originPosY + motionY * (double)ageModifier + (double)(1.0F - percentAged);
		posZ = originPosZ + motionZ * (double)ageModifier;

		selectSpriteWithAge(sprite);

		if (age++ >= maxAge)
			setExpired();
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		int initialBrightness = super.getBrightnessForRender(p_189214_1_);
		double percentAged = Math.pow((float)this.age / (float)this.maxAge, 3);
		int brightnessLowerBits = initialBrightness & 255;
		int brightnessUpperBits = initialBrightness >> 16 & 255;
		brightnessUpperBits = Math.min(brightnessUpperBits + (int)(percentAged * 240f), 240);

		return brightnessLowerBits | brightnessUpperBits << 16;
	}

	public static class Factory implements IParticleFactory<CustomisableParticleType.Data> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle makeParticle(CustomisableParticleType.Data data, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new PortalFloaterParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.red, data.green, data.blue, data.alpha);
		}
	}
}
