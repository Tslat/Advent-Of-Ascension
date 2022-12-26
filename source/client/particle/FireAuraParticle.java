package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

import javax.annotation.Nullable;

public class FireAuraParticle extends TextureSheetParticle {
	private final Entity entity;
	private double startX;
	private double startY;
	private double startZ;

	public FireAuraParticle(ClientLevel world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, SpriteSet sprite, float scale, float ageModifier, int entitySourceId) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.xd = velocityX;
		this.yd = velocityY;
		this.zd = velocityZ;
		this.quadSize = (random.nextFloat() * random.nextFloat() * 6.0f + 1.0f) * scale / 5f;
		this.lifetime = (int)Math.ceil(ageModifier * 5 / (random.nextFloat() * 0.8 + 0.2));
		this.rCol = 1;
		this.gCol = 1;
		this.bCol = 1;
		this.alpha = 1.0f;
		this.hasPhysics = false;
		this.entity = level.getEntity(entitySourceId);

		if (entity != null) {
			this.startX = entity.getX();
			this.startY = entity.getY(0.5f);
			this.startZ = entity.getZ();
		}

		pickSprite(sprite);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@Override
	public int getLightColor(float partialTick) {
		float lerpAge = Mth.clamp((this.age + partialTick) / (float)this.lifetime, 0, 1) * 240;
		int baseColour = super.getLightColor(partialTick);
		int colourMod = (baseColour & 255) + (int)lerpAge;

		if (colourMod > 240)
			colourMod = 240;

		return colourMod | (baseColour >> 16 & 255) << 16;
	}

	@Override
	public void tick() {
		if (this.entity == null || !this.entity.isAlive()) {
			remove();

			return;
		}

		this.xo = x;
		this.yo = y;
		this.zo = z;

		double slope = Math.toRadians(20);
		double angleX = Math.cos(slope);
		double angleZ = Math.sin(slope);
		double angleY = Math.cos(angleX + angleZ);
		Vec3 rotDelta = new Vec3(
				angleX * (this.x - this.startX) - angleZ * (this.z - this.startZ),
				angleY * (this.y - this.startY) * Math.tan(angleX + angleZ),
				angleZ * (this.x - this.startX) + angleX * (this.z - this.startZ)
		).normalize().scale(2f).add(this.startX, this.startY, this.startZ);

		this.xd = rotDelta.x - this.x;
		this.yd = rotDelta.y - this.y;
		this.zd = rotDelta.z - this.z;

		move(this.xd, this.yd, this.zd);

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
			return new FireAuraParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.entitySourceId);
		}
	}
}