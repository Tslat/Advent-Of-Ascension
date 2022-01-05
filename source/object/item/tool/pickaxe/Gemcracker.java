package net.tslat.aoa3.object.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.object.item.LootModifyingItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Gemcracker extends BasePickaxe implements LootModifyingItem {
	public Gemcracker() {
		super(ItemUtil.customItemTier(2100, 10.0f, 6.0f, 6, 10, null));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (BlockUtil.isAirBlock(harvestedBlock) || existingLoot.isEmpty() || !harvestedBlock.isToolEffective(ToolType.PICKAXE) || !harvestedBlock.is(Tags.Blocks.ORES))
			return;

		ItemStack primaryStack = existingLoot.get(0);

		if (block.asItem() != primaryStack.getItem())
			existingLoot.addAll(ItemUtil.increaseStackSize(primaryStack, RandomUtil.randomNumberUpTo(primaryStack.getCount())));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
