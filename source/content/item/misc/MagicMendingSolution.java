package net.tslat.aoa3.content.item.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class MagicMendingSolution extends Item {
	public MagicMendingSolution() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1).craftRemainder(AoAItems.METAL_TUB.get()));
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		CompoundTag tag = stack.getOrCreateTag();

		if (!tag.contains("coolingTime"))
			tag.putInt("coolingTime", 600);

		int coolingTime = tag.getInt("coolingTime");

		if (coolingTime <= 0) {
			if (!world.isClientSide) {
				stack.shrink(1);

				if (entity instanceof Player)
					ItemUtil.givePlayerMultipleItems((Player)entity, new ItemStack(AoAItems.METAL_TUB.get()), new ItemStack(AoAItems.MAGIC_MENDING_COMPOUND.get()));
			}
		}
		else {
			tag.putInt("coolingTime", coolingTime - 1);
			stack.setTag(tag);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
