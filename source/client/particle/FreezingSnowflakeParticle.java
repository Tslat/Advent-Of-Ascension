package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ParticleEffectPacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityRetrievalUtil;

import javax.annotation.Nullable;

public class FreezingSnowflakeParticle extends EntityAffectingParticle {
	private final SpriteSet sprite;
	private final int senderId;

	protected FreezingSnowflakeParticle(ClientLevel world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, SpriteSet sprite, float scale, float ageModifier, int entitySourceId) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.xd = velocityX;
		this.yd = velocityY;
		this.zd = velocityZ;
		this.quadSize = (random.nextFloat() * random.nextFloat() * 6.0f + 1.0f) * scale / 5f;
		this.lifetime = (int)Math.ceil(ageModifier / (random.nextFloat() * 0.8 + 0.2));
		this.senderId = entitySourceId;

		if (alpha == 0)
			this.alpha = 1f;

		setSprite(sprite.get(0, 1));
		setSize(0.2f, 0.2f);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	protected boolean handleCollision(Entity collidedEntity) {
		if (collidedEntity.getTicksFrozen() <= collidedEntity.getTicksRequiredToFreeze() * 2.5f && collidedEntity instanceof Player)
			AoAPackets.messageServer(new ParticleEffectPacket(ParticleEffectPacket.Type.FREEZING_SNOWFLAKE, collidedEntity.getId()));

		return true;
	}

	@Nullable
	protected Entity getCollidedEntity(double xVelocity, double yVelocity, double zVelocity) {
		if (senderId == -1)
			return null;

		return EntityRetrievalUtil.getNearestEntity(level, getBoundingBox().expandTowards(xVelocity, yVelocity, zVelocity), new Vec3(x, y, z), EntityPredicate.TARGETABLE_ENTITIES.and(entity -> entity.getId() != senderId));
	}

	public static class Factory implements ParticleProvider<CustomisableParticleType.Data> {
		private final SpriteSet sprite;

		public Factory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(CustomisableParticleType.Data data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new FreezingSnowflakeParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.entitySourceId);
		}
	}
}
