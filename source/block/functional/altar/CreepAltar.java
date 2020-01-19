package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.scheduling.async.CreepSpawnTask;
import net.tslat.aoa3.utils.StringUtil;

import java.util.concurrent.TimeUnit;

public class CreepAltar extends BossAltarBlock {
	public CreepAltar() {
		super("CreepAltar", "creep_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		double centerX = blockPos.getX() + 0.5d;
		double centerZ = blockPos.getZ() + 0.5d;
		World world = player.world;

		if (world.getBlockState(blockPos.north()).getBlock() == this)
			centerZ -= 0.5d;

		if (world.getBlockState(blockPos.south()).getBlock() == this)
			centerZ += 0.5d;

		if (world.getBlockState(blockPos.east()).getBlock() == this)
			centerX += 0.5d;

		if (world.getBlockState(blockPos.west()).getBlock() == this)
			centerX -= 0.5d;

		new CreepSpawnTask(world, centerX, blockPos.getY() + 1, centerZ, AdventOfAscension.rand.nextInt(4)).schedule(1, TimeUnit.SECONDS);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.creep.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.explosiveGems;
	}
}
