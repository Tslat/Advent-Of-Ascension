package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class ImmortallisPortalBlock extends PortalBlock {
	public ImmortallisPortalBlock() {
		super("ImmortallisPortal", "immortallis_portal", ConfigurationUtil.MainConfig.dimensionIds.immortallis, Enums.RGBIntegers.GOLDEN_POPPY);
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer || (entity instanceof EntityTameable && ((EntityTameable)entity).isTamed()))
			super.onEntityCollision(world, pos, state, entity);
	}
}
