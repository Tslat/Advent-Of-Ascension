package net.tslat.aoa3.item.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Gravitator extends SimpleItem {
	public Gravitator() {
		super("Gravitator", "gravitator");
		setMaxDamage(1500);
		setMaxStackSize(1);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity holder, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, holder, itemSlot, isSelected);

		if (isSelected && holder.motionY < -0.079 && holder instanceof EntityLivingBase) {
			holder.motionY *= 0.8f;
			holder.fallDistance = -0.5f;

			if (itemRand.nextInt(8) == 0 && (!(holder instanceof EntityPlayer) || !((EntityPlayer)holder).capabilities.isCreativeMode))
				stack.damageItem(1, (EntityLivingBase)holder);
		}
	}
}
