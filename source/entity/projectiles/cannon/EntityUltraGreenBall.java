package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityUltraGreenBall extends EntityMiniGreenBall {
	public EntityUltraGreenBall(World world) {
		super(world);
	}

	public EntityUltraGreenBall(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, piercingValue);
	}

	public EntityUltraGreenBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
