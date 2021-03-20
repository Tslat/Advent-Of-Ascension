package net.tslat.aoa3.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

import javax.annotation.Nullable;

public class RainbowSparklerParticle extends SpriteTexturedParticle {
	private final IAnimatedSprite sprite;

	public RainbowSparklerParticle(ClientWorld world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, IAnimatedSprite sprite, float scale, float ageModifier) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.xd = velocityX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.yd = velocityY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.zd = velocityZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.quadSize = (random.nextFloat() * random.nextFloat() * 6.0f + 1.0f) * scale / 5f;
		this.lifetime = (int)Math.ceil(ageModifier / (random.nextFloat() * 0.8 + 0.2));
		this.rCol = (float)random.nextGaussian();
		this.gCol = (float)random.nextGaussian();
		this.bCol = (float)random.nextGaussian();
		this.alpha = 1.0f;

		setSpriteFromAge(sprite);
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		this.xo = x;
		this.yo = y;
		this.zo = z;

		setSpriteFromAge(this.sprite);

		move(xd, yd, zd);

		this.xd *= 0.8999999761581421;
		this.yd *= 0.8999999761581421;
		this.zd *= 0.8999999761581421;

		if (onGround) {
			this.xd *= 0.699999988079071;
			this.zd *= 0.699999988079071;
		}

		if (age++ >= lifetime)
			remove();
	}

	public static class Factory implements IParticleFactory<CustomisableParticleType.Data> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(CustomisableParticleType.Data data, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new RainbowSparklerParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier);
		}
	}
}