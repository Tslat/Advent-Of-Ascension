package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;

public class SunShotEntity extends BaseEnergyShot {
	public SunShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public SunShotEntity(World world) {
		super(AoAEntities.Projectiles.SUN_SHOT.get(), world);
	}

	public SunShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SUN_SHOT.get(), shooter, weapon, maxAge);
	}

	public SunShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SUN_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.3d, 0.3d));

		for (LivingEntity e : level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(10), EntityUtil.Predicates.HOSTILE_MOB)) {
			if (!e.isOnFire() && !e.fireImmune())
				e.setSecondsOnFire(2);
		}

		if (getAge() >= 260) {
			WorldUtil.createExplosion(getOwner(), level, this, 3.5f);
			remove();
		}
	}

	@Override
	protected void onHit(RayTraceResult result) {
		setDeltaMovement(new Vector3d(0, level.getBlockState(blockPosition().below()).getBlock() != Blocks.AIR ? 1 : getDeltaMovement().y(), 0));
	}
}
