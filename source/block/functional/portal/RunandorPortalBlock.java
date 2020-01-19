package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class RunandorPortalBlock extends PortalBlock {
	public RunandorPortalBlock() {
		super("RunandorPortal", "runandor_portal", ConfigurationUtil.MainConfig.dimensionIds.runandor, Enums.RGBIntegers.ELECTRIC_BLUE);
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (world.isRemote)
			return;

		if (!(entity instanceof EntityPlayer) || (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.runandor || ((EntityPlayer)entity).capabilities.isCreativeMode || ((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(ItemRegister.realmTravelTicket))))
			super.onEntityCollision(world, pos, state, entity);
	}
}
