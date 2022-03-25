package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class SoulStormEntity extends BaseEnergyShot {
	public SoulStormEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SoulStormEntity(World world) {
		super(AoAEntities.Projectiles.SOUL_STORM_SHOT.get(), world);
	}

	public SoulStormEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SOUL_STORM_SHOT.get(), shooter, weapon, maxAge);
	}

	public SoulStormEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SOUL_STORM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.6f;
	}
}
