package net.tslat.aoa3.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntitySpectralHollyArrow;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class SpectralHollyArrow extends HollyArrow {
	public SpectralHollyArrow(String name, String registryName) {
		super(name, registryName);
	}

	@Override
	public EntityHollyArrow createArrow(World world, BaseBow bow, ItemStack ammo, EntityLivingBase shooter, double baseDamage) {
		return new EntitySpectralHollyArrow(world, bow, shooter, baseDamage);
	}
}
