package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.TierSortingRegistry;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TrollBasherAxe extends BaseSword {
	public TrollBasherAxe() {
		super(AoATiers.TROLL_BASHER);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_AXE) ? AoATiers.TROLL_BASHER.getSpeed() : 1;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.BLOCK_EFFICIENCY || enchantment == Enchantments.BLOCK_FORTUNE || super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		if (TierSortingRegistry.isTierSorted(getTier()))
			return TierSortingRegistry.isCorrectTierForDrops(getTier(), state) && state.is(BlockTags.MINEABLE_WITH_AXE);

		int tier = this.getTier().getLevel();

		if (tier < 3 && state.is(BlockTags.NEEDS_DIAMOND_TOOL))
			return false;

		if (tier < 2 && state.is(BlockTags.NEEDS_IRON_TOOL))
			return false;

		return (tier >= 1 || !state.is(BlockTags.NEEDS_STONE_TOOL)) && state.is(BlockTags.MINEABLE_WITH_AXE);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
