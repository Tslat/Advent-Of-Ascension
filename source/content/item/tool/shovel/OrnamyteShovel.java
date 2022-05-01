package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.AoATiers;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OrnamyteShovel extends BaseShovel {
	public OrnamyteShovel() {
		super(AoATiers.ORNAMYTE, -1.5f, AttackSpeed.SHOVEL);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.is(AoATags.Blocks.GRASS) || state.getBlock() instanceof GrassBlock ? super.getDestroySpeed(stack, state) * 10 : super.getDestroySpeed(stack, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
