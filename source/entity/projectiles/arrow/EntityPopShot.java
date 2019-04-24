package net.tslat.aoa3.entity.projectiles.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class EntityPopShot extends EntityHollyArrow {
	public EntityPopShot(World world) {
		super(world);
	}

	public EntityPopShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityPopShot(World world, BaseBow bow, EntityLivingBase shooter, double damageBase) {
		super(world, bow, shooter, damageBase);
	}

	@Override
	public boolean hideCriticalParticles() {
		return true;
	}

	protected void arrowHit(EntityLivingBase target) {}

	protected ItemStack getArrowStack() {
		return new ItemStack(ItemRegister.popShot);
	}
}
