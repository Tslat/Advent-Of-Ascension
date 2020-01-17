package net.tslat.aoa3.entity.projectiles.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class EntityPopShot extends EntityHollyArrow {
	public final boolean isExplosive;

	public EntityPopShot(World world) {
		super(world);

		this.isExplosive = true;
	}

	public EntityPopShot(World world, double x, double y, double z) {
		super(world, x, y, z);

		this.isExplosive = true;
	}

	public EntityPopShot(World world, BaseBow bow, EntityLivingBase shooter, double damageBase) {
		super(world, bow, shooter, damageBase);

		this.isExplosive = true;
	}

	public EntityPopShot(World world, BaseBow bow, EntityLivingBase shooter, double damageBase, boolean isExplosive) {
		super(world, bow, shooter, damageBase);

		this.isExplosive = isExplosive;
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
