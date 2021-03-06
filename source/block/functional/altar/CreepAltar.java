package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.scheduling.async.CreepSpawnTask;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.concurrent.TimeUnit;

public class CreepAltar extends BossAltarBlock {
	public CreepAltar() {
		super(MaterialColor.COLOR_GREEN);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		double centerX = blockPos.getX() + 0.5d;
		double centerZ = blockPos.getZ() + 0.5d;
		World world = player.level;

		if (world.getBlockState(blockPos.north()).getBlock() == this)
			centerZ -= 0.5d;

		if (world.getBlockState(blockPos.south()).getBlock() == this)
			centerZ += 0.5d;

		if (world.getBlockState(blockPos.east()).getBlock() == this)
			centerX += 0.5d;

		if (world.getBlockState(blockPos.west()).getBlock() == this)
			centerX -= 0.5d;

		new CreepSpawnTask(world, centerX, blockPos.getY() + 1, centerZ, RandomUtil.randomNumberUpTo(4)).schedule(1, TimeUnit.SECONDS);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.CREEP.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.CREEPONIA.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.EXPLOSIVE_GEMS.get();
	}
}
