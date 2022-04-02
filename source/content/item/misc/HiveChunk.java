package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;

public class HiveChunk extends Item {
	public HiveChunk() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.getType() == AoAMobs.THARAFLY.get() && attacker instanceof Player) {
			if (stack.getCount() <= 1) {
				attacker.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAItems.HIVE_EGG.get()));
			}
			else {
				ItemUtil.givePlayerItemOrDrop((Player)attacker, new ItemStack(AoAItems.HIVE_EGG.get()));
				stack.shrink(1);
			}

			return true;
		}

		return false;
	}
}
