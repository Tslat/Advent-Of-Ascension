package net.tslat.aoa3.object.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class MagicMendingSolution extends Item {
	public MagicMendingSolution() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1).craftRemainder(AoAItems.METAL_TUB.get()));
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!stack.hasTag())
			stack.setTag(new CompoundNBT());

		CompoundNBT tag = stack.getTag();

		if (!tag.contains("coolingTime"))
			tag.putInt("coolingTime", 600);

		int coolingTime = tag.getInt("coolingTime");

		if (coolingTime <= 0) {
			if (!world.isClientSide) {
				stack.shrink(1);

				if (entity instanceof PlayerEntity)
					ItemUtil.givePlayerMultipleItems((PlayerEntity)entity, new ItemStack(AoAItems.METAL_TUB.get()), new ItemStack(AoAItems.MAGIC_MENDING_COMPOUND.get()));
			}
		}
		else {
			tag.putInt("coolingTime", coolingTime - 1);
			stack.setTag(tag);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
