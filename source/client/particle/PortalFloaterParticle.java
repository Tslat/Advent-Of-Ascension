package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;

import javax.annotation.Nullable;

public class PortalFloaterParticle extends TextureSheetParticle {
	private final SpriteSet sprite;
	private final double originPosX;
	private final double originPosY;
	private final double originPosZ;

	public PortalFloaterParticle(ClientLevel world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, SpriteSet sprite, float red, float green, float blue, float alpha) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.originPosX = posX;
		this.originPosY = posY;
		this.originPosZ = posZ;
		this.xd = velocityX;
		this.yd = velocityY;
		this.zd = velocityZ;
		this.rCol = red;
		this.gCol = green;
		this.bCol = blue;
		this.alpha = alpha;
		this.quadSize = random.nextFloat() * 0.07F;
		this.lifetime = (int)((Math.random() * 10.0D) + 40);

		pickSprite(this.sprite);
	}

	@Override
	public float getQuadSize(float scaleFactor) {
		float scale = 1 - ((float)age + scaleFactor) / (float)lifetime;
		scale = 1 - (scale * scale);

		return this.quadSize * scale;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z) {
		setBoundingBox(getBoundingBox().move(x, y, z));
		setLocationFromBoundingbox();
	}

	@Override
	public void tick() {
		float percentAged = (float)age / (float)lifetime;
		float ageModifier = 1f - (-percentAged + percentAged * percentAged * 2.0F);

		xo = x;
		yo = y;
		zo = z;
		x = originPosX + xd * (double)ageModifier;
		y = originPosY + yd * (double)ageModifier + (double)(1.0F - percentAged);
		z = originPosZ + zd * (double)ageModifier;

		setSpriteFromAge(sprite);

		if (age++ >= lifetime)
			remove();
	}

	@Override
	public int getLightColor(float partialTick) {
		int initialBrightness = super.getLightColor(partialTick);
		double percentAged = Math.pow((float)this.age / (float)this.lifetime, 3);
		int brightnessLowerBits = initialBrightness & 255;
		int brightnessUpperBits = initialBrightness >> 16 & 255;
		brightnessUpperBits = Math.min(brightnessUpperBits + (int)(percentAged * 240f), 240);

		return brightnessLowerBits | brightnessUpperBits << 16;
	}

	public static class Factory implements ParticleProvider<PortalFloaterParticleType.Data> {
		private final SpriteSet sprite;

		public Factory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(PortalFloaterParticleType.Data data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new PortalFloaterParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.red, data.green, data.blue, data.alpha);
		}
	}
}
