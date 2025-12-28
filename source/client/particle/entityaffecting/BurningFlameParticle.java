package net.tslat.aoa3.client.particle.entityaffecting;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ParticleEffectPacket;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class BurningFlameParticle extends EntityAffectingParticle {
	private final Predicate<Entity> canCollideWith;
	private final SpriteSet sprites;
	private final int particleSourceId;

	public BurningFlameParticle(ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity, SpriteSet sprites, int entitySourceId) {
		super(level, x, y, z, xVelocity, yVelocity, zVelocity);

		this.sprites = sprites;
		this.xd = xVelocity;
		this.yd = yVelocity;
		this.zd = zVelocity;
		this.quadSize = (this.random.nextFloat() * this.random.nextFloat() * 6 + 1) * 0.35f / 5f;
		this.lifetime = Mth.ceil(5 / (this.random.nextFloat() * 0.8f + 0.2f));
		this.particleSourceId = entitySourceId;
		this.canCollideWith = CAN_COLLIDE_WITH.and(entity -> entity.getId() != this.particleSourceId);

		setSpriteFromAge(this.sprites);
		setSize(0.2f, 0.2f);
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
		super.tick();

		setSpriteFromAge(this.sprites);
	}

	@Override
	protected boolean handleEntityCollision(Entity collidedEntity) {
		if (DAMAGEABLE_ENTITIES.test(collidedEntity))
			AoANetworking.sendToServer(new ParticleEffectPacket(ParticleEffectPacket.Type.BURNING_FLAME, this.particleSourceId, collidedEntity.getId()));

		return super.handleEntityCollision(collidedEntity);
	}

	@Nullable
	protected Entity getCollidedEntity(double xVelocity, double yVelocity, double zVelocity) {
		if (this.particleSourceId == -1)
			return null;

		return EntityRetrievalUtil.getNearestEntity(this.level, getBoundingBox().expandTowards(xVelocity, yVelocity, zVelocity), new Vec3(this.x, this.y, this.z), this.canCollideWith);
	}

	public static class Provider implements ParticleProvider<EntityTrackingParticleOptions> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites) {
			this.sprites = sprites;
		}

		@Nullable
		@Override
		public Particle createParticle(EntityTrackingParticleOptions data, ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity) {
			return new BurningFlameParticle(level, x, y, z, xVelocity, yVelocity, zVelocity, this.sprites, data.entitySourceId());
		}
	}
}
