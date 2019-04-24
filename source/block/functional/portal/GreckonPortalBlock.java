package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class GreckonPortalBlock extends PortalBlock {
	public GreckonPortalBlock() {
		super("GreckonPortal", "greckon_portal", ConfigurationUtil.dimGreckon, Enums.RGBIntegers.DARK_VIOLET);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!(entity instanceof EntityPlayerMP) || (world.provider.getDimension() == ConfigurationUtil.dimGreckon || ((EntityPlayerMP)entity).capabilities.isCreativeMode || ((EntityPlayerMP)entity).inventory.hasItemStack(new ItemStack(ItemRegister.realmTravelTicket))))
			super.onEntityCollidedWithBlock(world, pos, state, entity);
	}
}
