package net.tslat.aoa3.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface LongReachItem {
	float getReach();

	boolean hitEntity(ItemStack stack, Entity target, LivingEntity attacker, float dmg);
}
