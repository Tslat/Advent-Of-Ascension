package net.tslat.aoa3.item.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;

public class HiveChunk extends Item {
	public HiveChunk() {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.getType() == AoAEntities.Mobs.THARAFLY.get() && attacker instanceof PlayerEntity) {
			if (stack.getCount() <= 1) {
				attacker.setHeldItem(Hand.MAIN_HAND, new ItemStack(AoAItems.HIVE_EGG.get()));
			}
			else {
				ItemUtil.givePlayerItemOrDrop((PlayerEntity)attacker, new ItemStack(AoAItems.HIVE_EGG.get()));
				stack.shrink(1);
			}

			return true;
		}

		return false;
	}
}
