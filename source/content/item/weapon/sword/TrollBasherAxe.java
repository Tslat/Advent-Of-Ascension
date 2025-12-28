package net.tslat.aoa3.content.item.weapon.sword;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class TrollBasherAxe extends AoASword {
	public TrollBasherAxe(Tier tier, Item.Properties properties) {
		super(tier, properties, createToolProperties(tier, BlockTags.MINEABLE_WITH_AXE));
	}

	public static Tool createToolProperties(Tier tier, TagKey<Block> toolMineableTag) {
		final Tool tierDefaults = tier.createToolProperties(toolMineableTag);
		final List<Tool.Rule> rules = new ObjectArrayList<>(tierDefaults.rules());

		rules.addAll(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)));

		return new Tool(List.copyOf(rules), 1, 2);
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
		Tool tool = stack.get(DataComponents.TOOL);

		if (tool == null)
			return false;

		if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0 && tool.damagePerBlock() > 0)
			stack.hurtAndBreak(stack.get(DataComponents.TOOL).isCorrectForDrops(state) ? 1 : tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
