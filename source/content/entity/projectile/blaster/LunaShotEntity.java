package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class LunaShotEntity extends BaseEnergyShot {
	public LunaShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LunaShotEntity(Level world) {
		super(AoAProjectiles.LUNA_SHOT.get(), world);
	}

	public LunaShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.LUNA_SHOT.get(), shooter, weapon, maxAge);
	}

	public LunaShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.LUNA_SHOT.get(), world, x, y, z);
	}
}
