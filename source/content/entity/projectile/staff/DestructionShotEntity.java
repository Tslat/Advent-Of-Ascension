package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.WorldUtil;

public class DestructionShotEntity extends BaseEnergyShot {
	public DestructionShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public DestructionShotEntity(World world) {
		super(AoAEntities.Projectiles.DESTRUCTION_SHOT.get(), world);
	}

	public DestructionShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.DESTRUCTION_SHOT.get(), shooter, weapon, maxAge);
	}

	public DestructionShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DESTRUCTION_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.4d, 0.4d, 0.4d));

		if (getAge() >= 65) {
			WorldUtil.createExplosion(getOwner(), level, this, 3.2f);
			remove();
		}
	}
}
