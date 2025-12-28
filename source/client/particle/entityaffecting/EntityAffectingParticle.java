package net.tslat.aoa3.client.particle.entityaffecting;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.tme.api.object.builder.EntityPredicateBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public abstract class EntityAffectingParticle extends TextureSheetParticle {
	protected static final Predicate<Entity> DAMAGEABLE_ENTITIES = EntityPredicateBuilder.builder().isDamageable().build();
	protected static final Predicate<Entity> CAN_COLLIDE_WITH = EntityPredicateBuilder.builder().isTargetable().build();

	protected EntityAffectingParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	@Override
	public void move(double x, double y, double z) {
		if (!this.stoppedByCollision) {
			final double xVelBefore = x;
			final double yVelBefore = y;
			final double zVelBefore = z;
			final Entity collidedEntity = getCollidedEntity(x, y, z);

			if (collidedEntity != null && handleEntityCollision(collidedEntity))
				return;

			if (this.hasPhysics && (x != 0 || y != 0 || z != 0) && x * x + y * y + z * z < MAXIMUM_COLLISION_VELOCITY_SQUARED) {
				Vec3 collidedVelocity = Entity.collideBoundingBox(null, new Vec3(x, y, z), getBoundingBox(), this.level, List.of());
				x = collidedVelocity.x;
				y = collidedVelocity.y;
				z = collidedVelocity.z;
			}

			if (x != 0 || y != 0 || z != 0) {
				setBoundingBox(getBoundingBox().move(x, y, z));
				setLocationFromBoundingbox();
			}

			if (Math.abs(yVelBefore) >= 0.00001d && Math.abs(y) < 0.00001d)
				this.stoppedByCollision = true;

			this.onGround = yVelBefore != y && yVelBefore < 0;

			if (xVelBefore != x)
				this.xd = 0;

			if (zVelBefore != z)
				this.zd = 0;
		}
	}

	protected boolean handleEntityCollision(Entity collidedEntity) {
		this.age = this.lifetime;

		return true;
	}

	@Nullable
	protected Entity getCollidedEntity(double xVelocity, double yVelocity, double zVelocity) {
		return EntityRetrievalUtil.getNearestEntity(this.level, getBoundingBox().expandTowards(xVelocity, yVelocity, zVelocity), new Vec3(this.x, this.y, this.z), CAN_COLLIDE_WITH);
	}

	@FunctionalInterface
	public interface Factory<T extends EntityAffectingParticle> {
		T create(ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity, @Nullable SpriteSet sprites, int entitySourceId);
	}

	public static class Provider<T extends EntityAffectingParticle> implements ParticleProvider<EntityTrackingParticleOptions> {
		private final SpriteSet sprites;
		private final EntityAffectingParticle.Factory<T> factory;

		public Provider(SpriteSet sprites, EntityAffectingParticle.Factory<T> factory) {
			this.sprites = sprites;
			this.factory = factory;
		}

		@Nullable
		@Override
		public Particle createParticle(EntityTrackingParticleOptions data, ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity) {
			return this.factory.create(level, x, y, z, xVelocity, yVelocity, zVelocity, this.sprites, data.entitySourceId());
		}
	}

	public static class SingleSpriteProvider<T extends EntityAffectingParticle> implements ParticleProvider.Sprite<EntityTrackingParticleOptions> {
		private final EntityAffectingParticle.Factory<T> factory;

		public SingleSpriteProvider(EntityAffectingParticle.Factory<T> factory) {
			this.factory = factory;
		}

		@Override
		public TextureSheetParticle createParticle(EntityTrackingParticleOptions data, ClientLevel level, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity) {
			return this.factory.create(level, x, y, z, xVelocity, yVelocity, zVelocity, null, data.entitySourceId());
		}
	}
}
