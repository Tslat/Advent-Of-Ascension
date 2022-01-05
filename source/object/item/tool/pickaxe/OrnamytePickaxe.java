package net.tslat.aoa3.object.item.tool.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OrnamytePickaxe extends BasePickaxe {
	public OrnamytePickaxe() {
		super(ItemUtil.customItemTier(2750, 10.0f, 6.0f, 5, 14, null));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		float efficiency = super.getDestroySpeed(stack, state);

		return state.getBlock() == Blocks.OBSIDIAN || state.getBlock() == Blocks.CRYING_OBSIDIAN ? efficiency * 10f : efficiency;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}