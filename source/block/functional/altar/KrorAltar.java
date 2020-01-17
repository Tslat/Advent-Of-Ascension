package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.scheduling.async.KrorSpawnTask;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.concurrent.TimeUnit;

public class KrorAltar extends BossAltarBlock {
	public KrorAltar() {
		super("KrorAltar", "kror_altar");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem() == ItemBlock.getItemFromBlock(BlockRegister.chargingTable))
			return false;

		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		if (player instanceof EntityPlayerMP)
			new KrorSpawnTask((EntityPlayerMP)player, blockPos.up()).schedule(1, TimeUnit.SECONDS);

		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.kror.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos pos) {
		if (player.world.getBlockState(pos.up()).getBlock() != BlockRegister.chargingTable) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.krorAltar.chargingTable");

			return false;
		}

		return true;
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.boulderDash;
	}
}
