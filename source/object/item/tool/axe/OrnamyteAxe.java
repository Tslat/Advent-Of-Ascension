package net.tslat.aoa3.object.item.tool.axe;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.object.block.generation.log.LogBlock;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OrnamyteAxe extends BaseAxe {
	public OrnamyteAxe() {
		super(ItemUtil.customItemTier(2750, 10.0f, 11.5f, 5, 14, null));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.getMaterial() == Material.WOOD && (!state.getBlock().is(BlockTags.LOGS) && !(state.getBlock() instanceof LogBlock)) ? super.getDestroySpeed(stack, state) * 10 : super.getDestroySpeed(stack, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
