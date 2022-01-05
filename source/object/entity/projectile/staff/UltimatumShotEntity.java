package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class UltimatumShotEntity extends BaseEnergyShot {
	public UltimatumShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public UltimatumShotEntity(World world) {
		super(AoAEntities.Projectiles.ULTIMATUM_SHOT.get(), world);
	}

	public UltimatumShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ULTIMATUM_SHOT.get(), shooter, weapon, maxAge);
	}

	public UltimatumShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ULTIMATUM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0f;
	}
}
