package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class OrnamytePickaxe extends BasePickaxe {
	public OrnamytePickaxe() {
		super("OrnamytePickaxe", "ornamyte_pickaxe", MaterialsRegister.TOOL_ORNAMYTE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		float efficiency = super.getDestroySpeed(stack, state);

		return state.getBlock() instanceof BlockObsidian ? efficiency * 10f : efficiency;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.OrnamytePickaxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}