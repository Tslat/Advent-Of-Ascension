package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.scheduling.async.KrorSpawnTask;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.concurrent.TimeUnit;

public class KrorAltar extends BossAltarBlock {
	public KrorAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == Item.byBlock(AoABlocks.CHARGING_TABLE.get()))
			return InteractionResult.FAIL;

		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		if (player instanceof ServerPlayer) {
			new KrorSpawnTask((ServerPlayer)player, blockPos.above()).schedule(1, TimeUnit.SECONDS);

			//PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable(AoAMobs.KROR.get().getDescriptionId() + ".start"));
		}
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		if (player.level().getBlockState(pos.above()).getBlock() != AoABlocks.CHARGING_TABLE.get() && player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer(player, Component.translatable(LocaleUtil.createFeedbackLocaleKey("krorAltar.chargingTable")));

			return false;
		}

		return WorldUtil.isWorld(player.level(), AoADimensions.DEEPLANDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.BOULDER_DASH.get();
	}
}
