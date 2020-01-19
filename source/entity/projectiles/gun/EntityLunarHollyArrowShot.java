package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityLunarHollyArrowShot extends EntityHollyArrowShot {
	public EntityLunarHollyArrowShot(World world) {
		super(world);
	}

	public EntityLunarHollyArrowShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, piercingValue);
	}

	public EntityLunarHollyArrowShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0f;
	}
}
