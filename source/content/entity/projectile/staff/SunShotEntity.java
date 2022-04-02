package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;

public class SunShotEntity extends BaseEnergyShot {
	public SunShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public SunShotEntity(Level world) {
		super(AoAProjectiles.SUN_SHOT.get(), world);
	}

	public SunShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.SUN_SHOT.get(), shooter, weapon, maxAge);
	}

	public SunShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SUN_SHOT.get(), world, x, y, z);
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
			discard();
		}
	}

	@Override
	protected void onHit(HitResult result) {
		setDeltaMovement(new Vec3(0, level.getBlockState(blockPosition().below()).getBlock() != Blocks.AIR ? 1 : getDeltaMovement().y(), 0));
	}
}