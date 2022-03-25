package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class LunaShotEntity extends BaseEnergyShot {
	public LunaShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public LunaShotEntity(World world) {
		super(AoAEntities.Projectiles.LUNA_SHOT.get(), world);
	}

	public LunaShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.LUNA_SHOT.get(), shooter, weapon, maxAge);
	}

	public LunaShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LUNA_SHOT.get(), world, x, y, z);
	}
}
