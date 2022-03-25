package net.tslat.aoa3.content.block.generation.leaves;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;

public class LeavesBlock extends net.minecraft.block.LeavesBlock {
	private final float saplingDropChance;

	public LeavesBlock(MaterialColor mapColour, float saplingChance) {
		super(new BlockUtil.CompactProperties(Material.LEAVES, mapColour).stats(0.2f).sound(SoundType.GRASS).noOcclusion().get());
		// TODO Tick Randomly & decay

		this.saplingDropChance = saplingChance;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {}

	public float getSaplingDropChance() {
		return saplingDropChance;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 30;
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 60;
	}
}
