package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

import javax.annotation.Nullable;

public class LingeringSparklerParticle extends TextureSheetParticle {
	private final SpriteSet sprite;

	public LingeringSparklerParticle(ClientLevel world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, SpriteSet sprite, float scale, float ageModifier, float red, float green, float blue, float alpha) {
		super(world, posX, posY, posZ, speedX, speedY, speedZ);

		this.sprite = sprite;
		this.xd = speedX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.yd = speedY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.zd = speedZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
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
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		xo = x;
		yo = y;
		zo = z;

		setSpriteFromAge(this.sprite);

		yd += 0.004;
		xd *= 0.8999999761581421;
		yd *= 0.8999999761581421;
		zd *= 0.8999999761581421;

		if (onGround) {
			xd *= 0.699999988079071;
			zd *= 0.699999988079071;
		}

		if (age++ >= lifetime)
			remove();
	}

	public static class Factory implements ParticleProvider<CustomisableParticleType.Data> {
		private final SpriteSet sprite;

		public Factory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(CustomisableParticleType.Data data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new LingeringSparklerParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.red, data.green, data.blue, data.alpha);
		}
	}
}