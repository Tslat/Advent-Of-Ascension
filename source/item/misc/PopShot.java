package net.tslat.aoa3.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityPopShot;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class PopShot extends HollyArrow {
	public PopShot(String name, String registryName) {
		super(name, registryName);
	}

	@Override
	public EntityHollyArrow createArrow(World world, BaseBow bow, ItemStack ammo, EntityLivingBase shooter, double baseDamage) {
		return new EntityPopShot(world, bow, shooter, baseDamage, ammo.getItem() == ItemRegister.popShot);
	}
}
