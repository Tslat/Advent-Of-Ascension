package net.tslat.aoa3.item.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface LongReachWeapon {
	public float getReach();

	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg);
}
