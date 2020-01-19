package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class OccultAxe extends BaseAxe {
	public OccultAxe() {
		super("OccultAxe", "occult_axe", MaterialsRegister.TOOL_OCCULT);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
		if (entity instanceof EntityPlayer) {
			BlockPos breakPos = pos;

			while (world.getBlockState(breakPos = breakPos.up()).getBlock().isWood(world, breakPos)) {
				WorldUtil.harvestAdditionalBlock(world, (EntityPlayer)entity, stack, pos, breakPos);
			}
		}

		return super.onBlockDestroyed(stack, world, state, pos, entity);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.OccultAxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
