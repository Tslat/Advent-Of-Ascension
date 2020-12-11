package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.library.scheduling.async.KrorSpawnTask;
import net.tslat.aoa3.util.player.PlayerUtil;

import java.util.concurrent.TimeUnit;

public class KrorAltar extends BossAltarBlock {
	public KrorAltar() {
		super(MaterialColor.GRAY);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand).getItem() == Item.getItemFromBlock(AoABlocks.CHARGING_TABLE.get()))
			return ActionResultType.FAIL;

		return super.onBlockActivated(state, world, pos, player, hand, hit);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		if (player instanceof ServerPlayerEntity) {
			new KrorSpawnTask((ServerPlayerEntity)player, blockPos.up()).schedule(1, TimeUnit.SECONDS);

			PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).sendThrottledChatMessage("message.mob.kror.spawn.start");
		}
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		if (player.world.getBlockState(pos.up()).getBlock() != AoABlocks.CHARGING_TABLE.get() && player instanceof ServerPlayerEntity) {
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).sendThrottledChatMessage("message.feedback.krorAltar.chargingTable");

			return false;
		}

		return true;
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.BOULDER_DASH.get();
	}
}
