package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityYellowBullet extends BaseBullet implements HardProjectile {
	public EntityYellowBullet(World world) {
		super(world);
	}

	public EntityYellowBullet(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityYellowBullet(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
