package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.scheduling.async.CreepSpawnTask;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.concurrent.TimeUnit;

public class CreepAltar extends BossAltarBlock {
	public CreepAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		double centerX = blockPos.getX() + 0.5d;
		double centerZ = blockPos.getZ() + 0.5d;
		Level level = player.level();

		if (level.getBlockState(blockPos.north()).getBlock() == this)
			centerZ -= 0.5d;

		if (level.getBlockState(blockPos.south()).getBlock() == this)
			centerZ += 0.5d;

		if (level.getBlockState(blockPos.east()).getBlock() == this)
			centerX += 0.5d;

		if (level.getBlockState(blockPos.west()).getBlock() == this)
			centerX -= 0.5d;

		if (level instanceof ServerLevel serverLevel)
			new CreepSpawnTask(serverLevel, centerX, blockPos.getY() + 1, centerZ, RandomUtil.numberUpTo(4)).schedule(1, TimeUnit.SECONDS);
		//sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.CREEP.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.CREEPONIA);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.EXPLOSIVE_GEMS.get();
	}
}
