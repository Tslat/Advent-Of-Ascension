package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityMetalSlug extends BaseBullet implements HardProjectile {
	public EntityMetalSlug(World world) {
		super(world);
	}

	public EntityMetalSlug(EntityLivingBase shooter, BaseGun gun, int piercingValue) {
		super(shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public EntityMetalSlug(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityMetalSlug(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
