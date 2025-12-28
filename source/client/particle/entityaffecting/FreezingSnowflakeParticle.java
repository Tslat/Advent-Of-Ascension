package net.tslat.aoa3.client.particle.entityaffecting;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ParticleEffectPacket;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class FreezingSnowflakeParticle extends EntityAffectingParticle {
	private final Predicate<Entity> canCollideWith;
	private final int particleSourceId;

	public FreezingSnowflakeParticle(ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity, @Nullable SpriteSet sprites, int entitySourceId) {
		super(level, x, y, z, xVelocity, yVelocity, zVelocity);

		this.xd = xVelocity;
		this.yd = yVelocity;
		this.zd = zVelocity;
		this.quadSize = (this.random.nextFloat() * this.random.nextFloat() * 6 + 1) * 0.35f / 5f;
		this.lifetime = Mth.ceil(5 / (this.random.nextFloat() * 0.8f + 0.2f));
		this.particleSourceId = entitySourceId;
		this.canCollideWith = CAN_COLLIDE_WITH.and(entity -> entity.getId() != this.particleSourceId);

		setSize(0.2f, 0.2f);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	protected boolean handleEntityCollision(Entity collidedEntity) {
		if (collidedEntity.getTicksFrozen() <= collidedEntity.getTicksRequiredToFreeze() * 2.5f && collidedEntity instanceof Player)
			AoANetworking.sendToServer(new ParticleEffectPacket(ParticleEffectPacket.Type.FREEZING_SNOWFLAKE, this.particleSourceId, collidedEntity.getId()));

		return true;
	}

	@Nullable
	protected Entity getCollidedEntity(double xVelocity, double yVelocity, double zVelocity) {
		if (this.particleSourceId == -1)
			return null;

		return EntityRetrievalUtil.getNearestEntity(this.level, getBoundingBox().expandTowards(xVelocity, yVelocity, zVelocity), new Vec3(this.x, this.y, this.z), this.canCollideWith);
	}
}
