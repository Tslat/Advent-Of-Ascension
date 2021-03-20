package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.WorldUtil;

public class HaunterShotEntity extends BaseEnergyShot {
	public HaunterShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HaunterShotEntity(World world) {
		super(AoAEntities.Projectiles.HAUNTER_SHOT.get(), world);
	}

	public HaunterShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.HAUNTER_SHOT.get(), shooter, weapon, maxAge);
	}

	public HaunterShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HAUNTER_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && getAge() % 4 == 0)
			WorldUtil.createExplosion(getOwner(), level, this, 2.0f);
	}
}
