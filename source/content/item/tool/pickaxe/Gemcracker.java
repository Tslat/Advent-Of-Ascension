package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class Gemcracker extends BasePickaxe implements LootModifyingItem {
	public Gemcracker(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (harvestedBlock.isAir() || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1 || !harvestedBlock.is(Tags.Blocks.ORES))
			return;

		ItemStack primaryStack = existingLoot.get(0);

		if (block.asItem() != primaryStack.getItem() && !primaryStack.is(Tags.Items.RAW_MATERIALS))
			existingLoot.addAll(ItemUtil.increaseStackSize(primaryStack, 1 + RandomUtil.numberUpTo(primaryStack.getCount())));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
