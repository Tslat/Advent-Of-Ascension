package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class MetalSlugEntity extends BaseBullet implements HardProjectile {
	public MetalSlugEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public MetalSlugEntity(World world) {
		super(AoAEntities.Projectiles.METAL_SLUG.get(), world);
	}

	public MetalSlugEntity(LivingEntity shooter, BaseGun gun, int piercingValue) {
		super(AoAEntities.Projectiles.METAL_SLUG.get(), shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public MetalSlugEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.METAL_SLUG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public MetalSlugEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.METAL_SLUG.get(), world, x, y, z);
	}
}
