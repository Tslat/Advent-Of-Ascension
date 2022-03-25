package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class NoxiousShotEntity extends BaseEnergyShot {
	public NoxiousShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public NoxiousShotEntity(World world) {
		super(AoAEntities.Projectiles.NOXIOUS_SHOT.get(), world);
	}

	public NoxiousShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.NOXIOUS_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public NoxiousShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.NOXIOUS_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}
