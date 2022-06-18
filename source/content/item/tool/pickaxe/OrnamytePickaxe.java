package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoATiers;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OrnamytePickaxe extends BasePickaxe {
	public OrnamytePickaxe() {
		super(AoATiers.ORNAMYTE, -2, AttackSpeed.PICKAXE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		float efficiency = super.getDestroySpeed(stack, state);

		return state.getBlock().defaultDestroyTime() >= 50 ? efficiency * 10f : efficiency;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}