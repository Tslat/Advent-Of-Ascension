package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.block.generation.log.LogBlock;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OrnamyteAxe extends BaseAxe {
	public OrnamyteAxe() {
		super(AoATiers.ORNAMYTE, 4f, AttackSpeed.AXE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.getSoundType() == SoundType.WOOD && (!state.is(BlockTags.LOGS) && !(state.getBlock() instanceof LogBlock)) ? super.getDestroySpeed(stack, state) * 10 : super.getDestroySpeed(stack, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
