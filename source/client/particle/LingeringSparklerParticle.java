package net.tslat.aoa3.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.world.World;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

import javax.annotation.Nullable;

public class LingeringSparklerParticle extends SpriteTexturedParticle {
	private final IAnimatedSprite sprite;

	public LingeringSparklerParticle(World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, IAnimatedSprite sprite, float scale, float ageModifier, float red, float green, float blue, float alpha) {
		super(world, posX, posY, posZ, speedX, speedY, speedZ);

		this.sprite = sprite;
		this.motionX = speedX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionY = speedY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionZ = speedZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.particleScale = (rand.nextFloat() * rand.nextFloat() * 6.0f + 1.0f) * scale / 5f;
		this.maxAge = (int)Math.ceil(ageModifier / (rand.nextFloat() * 0.8 + 0.2));
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		this.particleAlpha = alpha;

		if (particleAlpha == 0)
			particleAlpha = 1f;

		selectSpriteWithAge(sprite);
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		selectSpriteWithAge(this.sprite);

		motionY += 0.004;
		motionX *= 0.8999999761581421;
		motionY *= 0.8999999761581421;
		motionZ *= 0.8999999761581421;

		if (onGround) {
			motionX *= 0.699999988079071;
			motionZ *= 0.699999988079071;
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
			return new LingeringSparklerParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.red, data.green, data.blue, data.alpha);
		}
	}
}