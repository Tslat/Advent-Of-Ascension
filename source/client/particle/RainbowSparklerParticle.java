package net.tslat.aoa3.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.world.World;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

import javax.annotation.Nullable;

public class RainbowSparklerParticle extends SpriteTexturedParticle {
	private final IAnimatedSprite sprite;

	public RainbowSparklerParticle(World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, IAnimatedSprite sprite, float scale, float ageModifier) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.motionX = velocityX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionY = velocityY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionZ = velocityZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.particleScale = (rand.nextFloat() * rand.nextFloat() * 6.0f + 1.0f) * scale / 5f;
		this.maxAge = (int)Math.ceil(ageModifier / (rand.nextFloat() * 0.8 + 0.2));
		this.particleRed = (float)rand.nextGaussian();
		this.particleGreen = (float)rand.nextGaussian();
		this.particleBlue = (float)rand.nextGaussian();
		this.particleAlpha = 1.0f;

		selectSpriteWithAge(sprite);
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;

		selectSpriteWithAge(this.sprite);

		move(motionX, motionY, motionZ);

		this.motionX *= 0.8999999761581421;
		this.motionY *= 0.8999999761581421;
		this.motionZ *= 0.8999999761581421;

		if (onGround) {
			this.motionX *= 0.699999988079071;
			this.motionZ *= 0.699999988079071;
		}

		if (age++ >= maxAge)
			setExpired();
	}

	public static class Factory implements IParticleFactory<CustomisableParticleType.Data> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle makeParticle(CustomisableParticleType.Data data, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new RainbowSparklerParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier);
		}
	}
}