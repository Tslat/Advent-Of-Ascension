package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntitySniperSlug extends BaseBullet implements HardProjectile {
	public EntitySniperSlug(World world) {
		super(world);
	}

	public EntitySniperSlug(EntityLivingBase shooter, BaseGun gun, int piercingValue) {
		super(shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public EntitySniperSlug(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntitySniperSlug(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
