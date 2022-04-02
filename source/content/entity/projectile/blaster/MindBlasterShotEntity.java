package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class MindBlasterShotEntity extends BaseEnergyShot {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public MindBlasterShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public MindBlasterShotEntity(Level world) {
		super(AoAProjectiles.MIND_BLASTER_SHOT.get(), world);
	}

	public MindBlasterShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.MIND_BLASTER_SHOT.get(), shooter, weapon, maxAge);
	}

	public MindBlasterShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.MIND_BLASTER_SHOT.get(), world, x, y, z);
	}
}
