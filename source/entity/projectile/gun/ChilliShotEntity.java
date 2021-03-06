package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class ChilliShotEntity extends BaseBullet implements HardProjectile {
	public ChilliShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ChilliShotEntity(World world) {
		super(AoAEntities.Projectiles.CHILLI_SHOT.get(), world);
	}

	public ChilliShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.CHILLI_SHOT.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public ChilliShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CHILLI_SHOT.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.1f;
	}
}
