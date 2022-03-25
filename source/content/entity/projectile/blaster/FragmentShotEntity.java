package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class FragmentShotEntity extends BaseEnergyShot {
	public FragmentShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public FragmentShotEntity(World world) {
		super(AoAEntities.Projectiles.FRAGMENT_SHOT.get(), world);
	}

	public FragmentShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.FRAGMENT_SHOT.get(), shooter, weapon, maxAge);
	}

	public FragmentShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FRAGMENT_SHOT.get(), world, x, y, z);
	}
}
