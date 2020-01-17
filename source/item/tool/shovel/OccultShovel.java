package net.tslat.aoa3.item.tool.shovel;

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

public class OccultShovel extends BaseShovel {
	public OccultShovel() {
		super("OccultShovel", "occult_shovel", MaterialsRegister.TOOL_OCCULT);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return super.getDestroySpeed(stack, state) * (1 + (stack.getItemDamage() / (float)stack.getMaxDamage()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.OccultShovel.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
