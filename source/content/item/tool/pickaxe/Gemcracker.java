package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Gemcracker extends BasePickaxe implements LootModifyingItem {
	public Gemcracker() {
		super(ItemUtil.customItemTier(2100, 10.0f, 6.0f, 6, 10, null, BlockTags.MINEABLE_WITH_PICKAXE));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (BlockUtil.isAirBlock(harvestedBlock) || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1 || !harvestedBlock.is(Tags.Blocks.ORES))
			return;

		ItemStack primaryStack = existingLoot.get(0);

		if (block.asItem() != primaryStack.getItem() && !primaryStack.is(Tags.Items.RAW_MATERIALS))
			existingLoot.addAll(ItemUtil.increaseStackSize(primaryStack, 1 + RandomUtil.randomNumberUpTo(primaryStack.getCount())));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
