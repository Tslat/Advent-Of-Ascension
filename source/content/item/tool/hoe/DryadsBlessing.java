package net.tslat.aoa3.content.item.tool.hoe;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.ListIterator;

public class DryadsBlessing extends BaseHoe implements LootModifyingItem {
	public DryadsBlessing() {
		super(ItemUtil.customItemTier(3020, 10f, 3f, 5, 18, null, BlockTags.MINEABLE_WITH_HOE),
				-4,
				AttackSpeed.forAttacksPerSecond(4),
				new Item.Properties());
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
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
		pTooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}
}
