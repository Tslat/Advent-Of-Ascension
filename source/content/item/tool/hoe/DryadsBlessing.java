package net.tslat.aoa3.content.item.tool.hoe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.tslat.aoa3.common.registration.item.AoAItemStats;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;
import java.util.ListIterator;

public class DryadsBlessing extends BaseHoe implements LootModifyingItem {
	public DryadsBlessing() {
		super(AoAItemStats.DRYADS_BLESSING, 0, AttackSpeed.forAttacksPerSecond(4));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (existingLoot.isEmpty() || !(block instanceof CropBlock))
			return;

		if (!((CropBlock)block).isMaxAge(harvestedBlock))
			return;

		for (ListIterator<ItemStack> iterator = existingLoot.listIterator(); iterator.hasNext();) {
			ItemStack itStack = iterator.next();

			ItemUtil.increaseStackSize(itStack, itStack.getCount()).forEach(iterator::add);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
