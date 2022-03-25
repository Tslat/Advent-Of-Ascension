package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OrnamyteShovel extends BaseShovel {
	public OrnamyteShovel() {
		super(ItemUtil.customItemTier(2750, 10.0f, 6.0f, 5, 14, null));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.is(AoATags.Blocks.GRASS) || state.getBlock() instanceof GrassBlock ? super.getDestroySpeed(stack, state) * 10 : super.getDestroySpeed(stack, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
