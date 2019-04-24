package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntitySunsetBullet extends BaseBullet implements HardProjectile {
	public EntitySunsetBullet(World world) {
		super(world);
	}

	public EntitySunsetBullet(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntitySunsetBullet(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
