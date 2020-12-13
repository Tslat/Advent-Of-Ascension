package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
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

		setMotion(getMotion().mul(0.3d, 0.3d, 0.3d));

		for (LivingEntity e : world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(10), EntityUtil.Predicates.HOSTILE_MOB)) {
			if (!e.isBurning() && !e.isImmuneToFire())
				e.setFire(1);
		}

		if (getAge() >= 260) {
			WorldUtil.createExplosion(owner, world, this, 3.0f);
			remove();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		setMotion(new Vec3d(0, world.getBlockState(getPosition().down()).getBlock() != Blocks.AIR ? 1 : getMotion().getY(), 0));
	}
}
