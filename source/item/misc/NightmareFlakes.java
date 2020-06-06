package net.tslat.aoa3.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.elusive.EntityElusive;
import net.tslat.aoa3.utils.ItemUtil;

public class NightmareFlakes extends SimpleItem {
	public NightmareFlakes() {
		super("NightmareFlakes", "nightmare_flakes");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (target instanceof EntityElusive && ItemUtil.consumeItem(playerIn, new ItemStack(ItemRegister.BLANK_REALMSTONE))) {
			ItemUtil.givePlayerItemOrDrop(playerIn, new ItemStack(ItemRegister.GRECKON_REALMSTONE));
			stack.shrink(1);

			return true;
		}

		return false;
	}
}
