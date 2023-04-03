package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class RosidianShotEntity extends BaseEnergyShot {
	private boolean impacted = false;

	public RosidianShotEntity(RosidianShotEntity tangleShot, double posX, double posY, double posZ) {
		super(AoAProjectiles.ROSIDIAN_SHOT.get(), tangleShot.getOwner(), tangleShot.weapon, posX, posY, posZ, -1f);

		lifespan = 2;
	}

	public RosidianShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon) {
		super(AoAProjectiles.PRIMORDIAL_SHOT.get(), shooter, weapon, 180);
	}

	public RosidianShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.PRIMORDIAL_SHOT.get(), world, x, y, z);
	}

	public RosidianShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public RosidianShotEntity(Level world) {
		super(AoAProjectiles.PRIMORDIAL_SHOT.get(), world);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && impacted && lifespan > 15 && tickCount % 5 == 0) {
			double posX = this.getX() + random.nextGaussian() * 3;
			double posZ = this.getZ() + random.nextGaussian() * 3;
			double posY = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(posX, getY(), posZ)).getY();

			level.addFreshEntity(new RosidianShotEntity(this, posX, posY + 0.5, posZ));
		}
	}

	@Override
	protected void onHit(HitResult result) {
		if (result.getType() == HitResult.Type.ENTITY) {
			super.onHit(result);
		}
		else {
			setDeltaMovement(0, 0, 0);

			impacted = true;
		}
	}
}
