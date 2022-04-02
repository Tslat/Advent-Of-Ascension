package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

import javax.annotation.Nullable;

public class SparklerParticle extends TextureSheetParticle {
	private final SpriteSet sprite;

	public SparklerParticle(ClientLevel world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, SpriteSet sprite, float scale, float ageModifier, float red, float green, float blue, float alpha) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.xd = velocityX * (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.yd = velocityY * (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.zd = velocityZ * (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.quadSize = (random.nextFloat() * random.nextFloat() * 6.0f + 1.0f) * scale / 5f;
		this.lifetime = (int)Math.ceil(ageModifier / (random.nextFloat() * 0.8 + 0.2));
		this.rCol = red;
		this.gCol = green;
		this.bCol = blue;
		this.alpha = alpha;

		if (alpha == 0)
			this.alpha = 1f;

		setSpriteFromAge(sprite);
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

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public static class Factory implements ParticleProvider<CustomisableParticleType.Data> {
		private final SpriteSet sprite;

		public Factory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(CustomisableParticleType.Data data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new SparklerParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.red, data.green, data.blue, data.alpha);
		}
	}
}