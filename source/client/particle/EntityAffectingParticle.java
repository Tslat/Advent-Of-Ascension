package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.api.util.EntityRetrievalUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class EntityAffectingParticle extends TextureSheetParticle {
	protected EntityAffectingParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z);
	}

	protected EntityAffectingParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	@Override
	public void move(double x, double y, double z) {
		if (!this.stoppedByCollision) {
			double xVelBefore = x;
			double yVelBefore = y;
			double zVelBefore = z;

			Entity collidedEntity = getCollidedEntity(x, y, z);

			if (collidedEntity != null && handleCollision(collidedEntity))
				return;

			if (this.hasPhysics && (x != 0 || y != 0 || z != 0) && x * x + y * y + z * z < 10000) {
				Vec3 vec3 = Entity.collideBoundingBox(null, new Vec3(x, y, z), this.getBoundingBox(), this.level, List.of());
				x = vec3.x;
				y = vec3.y;
				z = vec3.z;
			}

			if (x != 0 || y != 0 || z != 0) {
				setBoundingBox(getBoundingBox().move(x, y, z));
				setLocationFromBoundingbox();
			}

			if (Math.abs(yVelBefore) >= 0.00001d && Math.abs(y) < 0.00001d) {
				this.stoppedByCollision = true;
			}

			this.onGround = yVelBefore != y && yVelBefore < 0;

			if (xVelBefore != x)
				this.xd = 0;

			if (zVelBefore != z)
				this.zd = 0;
		}
	}

	protected boolean handleCollision(Entity collidedEntity) {
		this.age = this.lifetime;

		return true;
	}

	@Nullable
	protected Entity getCollidedEntity(double xVelocity, double yVelocity, double zVelocity) {
		return EntityRetrievalUtil.getNearestEntity(level, getBoundingBox().expandTowards(xVelocity, yVelocity, zVelocity), new Vec3(x, y, z), EntityPredicate.TARGETABLE_ENTITIES);
	}
}
