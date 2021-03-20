package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class RosidianShotEntity extends BaseEnergyShot {
	private boolean impacted = false;

	public RosidianShotEntity(RosidianShotEntity tangleShot, double posX, double posY, double posZ) {
		super(AoAEntities.Projectiles.ROSIDIAN_SHOT.get(), tangleShot.getOwner(), tangleShot.weapon, posX, posY, posZ, -1f);

		lifespan = 2;
	}

	public RosidianShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), shooter, weapon, 180);
	}

	public RosidianShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), world, x, y, z);
	}

	public RosidianShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public RosidianShotEntity(World world) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), world);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && impacted && lifespan > 15 && tickCount % 5 == 0) {
			double posX = this.getX() + random.nextGaussian() * 3;
			double posZ = this.getZ() + random.nextGaussian() * 3;
			double posY = level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, getY(), posZ)).getY();

			level.addFreshEntity(new RosidianShotEntity(this, posX, posY + 0.5, posZ));
		}
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			super.onHit(result);
		}
		else {
			setDeltaMovement(0, 0, 0);

			impacted = true;
		}
	}
}
