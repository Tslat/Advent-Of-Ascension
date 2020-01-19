package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
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

public class OrnamyteShovel extends BaseShovel {
	public OrnamyteShovel() {
		super("OrnamyteShovel", "ornamyte_shovel", MaterialsRegister.TOOL_ORNAMYTE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return state.getMaterial() == Material.GRASS || state.getBlock() instanceof BlockGrass ? super.getDestroySpeed(stack, state) * 10 : super.getDestroySpeed(stack, state);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.OrnamyteShovel.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
