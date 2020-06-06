package net.tslat.aoa3.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.gun.EntityHollyArrowShot;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class HollyArrow extends ItemArrow {
	public HollyArrow(String name, String registryName) {
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.AMMUNITION);
	}

	@Override
	public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
		return new EntityHollyArrow(world, null, shooter, 9);
	}

	public EntityHollyArrow createArrow(World world, EntityHollyArrowShot archergunShot) {
		return new EntityHollyArrow(world, archergunShot);
	}

	public EntityHollyArrow createArrow(World world, BaseBow bow, ItemStack ammo, EntityLivingBase shooter, double baseDamage) {
		return new EntityHollyArrow(world, bow, shooter, baseDamage);
	}
}
