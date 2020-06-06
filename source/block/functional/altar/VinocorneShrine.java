package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.StringUtil;

public class VinocorneShrine extends BossAltarBlock {
	public VinocorneShrine() {
		super("VinocorneShrine", "vinocorne_shrine");
	}

	@Override
	protected boolean checkActivationConditions(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos pos) {
		return player.world.getBlockState(pos.up()).getBlock().isReplaceable(player.world, pos.up());
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		player.world.setBlockState(blockPos.up(), BlockRegister.LIVING_GROWTH.getDefaultState());
		player.world.scheduleUpdate(blockPos.up(), BlockRegister.LIVING_GROWTH, 40);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.vinocorne.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);

		if (world.getBlockState(pos.up()).getBlock() == BlockRegister.LIVING_GROWTH)
			world.setBlockToAir(pos.up());
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.PETALS;
	}
}