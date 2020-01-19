package net.tslat.aoa3.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.mobs.barathos.EntityTharafly;

public class HiveChunk extends SimpleItem {
	public HiveChunk() {
		super("HiveChunk", "hive_chunk");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (target instanceof EntityTharafly) {
			attacker.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemRegister.hiveEgg, stack.getCount()));

			return true;
		}

		return false;
	}
}
