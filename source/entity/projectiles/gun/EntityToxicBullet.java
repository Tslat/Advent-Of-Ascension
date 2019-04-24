package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityToxicBullet extends BaseBullet implements HardProjectile {
	public EntityToxicBullet(World world) {
		super(world);
	}

	public EntityToxicBullet(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityToxicBullet(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
