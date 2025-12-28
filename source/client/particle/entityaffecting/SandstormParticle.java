package net.tslat.aoa3.client.particle.entityaffecting;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ParticleEffectPacket;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class SandstormParticle extends EntityAffectingParticle {
	private final Predicate<Entity> canCollideWith;
	private final int particleSourceId;

	public SandstormParticle(ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity, SpriteSet sprites, int entitySourceId) {
		super(level, x, y, z, xVelocity, yVelocity, zVelocity);

		this.xd = xVelocity;
		this.yd = yVelocity;
		this.zd = zVelocity;
		this.quadSize = (this.random.nextFloat() * this.random.nextFloat() * 6 + 1) * 0.5f / 5f;
		this.lifetime = Mth.ceil(4 / (this.random.nextFloat() * 0.8f + 0.2f));
		this.particleSourceId = entitySourceId;
		this.rCol = RandomUtil.valueBetween(0.95f, 1f);
		this.gCol = RandomUtil.valueBetween(0.7f, 0.85f);
		this.bCol = RandomUtil.valueBetween(0.45f, 0.55f);
		this.canCollideWith = CAN_COLLIDE_WITH.and(entity -> entity.getId() != this.particleSourceId);

		pickSprite(sprites);
		setSize(0.1f, 0.1f);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	protected boolean handleEntityCollision(Entity collidedEntity) {
		if (DAMAGEABLE_ENTITIES.test(collidedEntity) || collidedEntity instanceof Projectile)
			AoANetworking.sendToServer(new ParticleEffectPacket(ParticleEffectPacket.Type.SANDSTORM, this.particleSourceId, collidedEntity.getId()));

		return true;
	}

	@Nullable
	protected Entity getCollidedEntity(double xVelocity, double yVelocity, double zVelocity) {
		if (this.particleSourceId == -1)
			return null;

		return EntityRetrievalUtil.getNearestEntity(this.level, getBoundingBox().expandTowards(xVelocity, yVelocity, zVelocity), new Vec3(this.x, this.y, this.z), this.canCollideWith);
	}
}
