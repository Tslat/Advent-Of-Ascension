package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityGigaGreenBall extends EntityMiniGreenBall {
	public EntityGigaGreenBall(World world) {
		super(world);
	}

	public EntityGigaGreenBall(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, piercingValue);
	}

	public EntityGigaGreenBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
