package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class MetalSlugEntity extends BaseBullet implements HardProjectile {
	public MetalSlugEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public MetalSlugEntity(Level world) {
		super(AoAProjectiles.METAL_SLUG.get(), world);
	}

	public MetalSlugEntity(LivingEntity shooter, BaseGun gun, int piercingValue) {
		super(AoAProjectiles.METAL_SLUG.get(), shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public MetalSlugEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.METAL_SLUG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public MetalSlugEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.METAL_SLUG.get(), world, x, y, z);
	}
}
