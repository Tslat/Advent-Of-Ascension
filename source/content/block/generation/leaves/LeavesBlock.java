package net.tslat.aoa3.content.block.generation.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;

public class LeavesBlock extends net.minecraft.world.level.block.LeavesBlock {
	private final float saplingDropChance;

	public LeavesBlock(MaterialColor mapColour, float saplingChance) {
		super(new BlockUtil.CompactProperties(Material.LEAVES, mapColour).stats(0.2f).sound(SoundType.GRASS).noOcclusion().specialSpawns((state, world, pos, entity) -> entity == EntityType.OCELOT || entity == EntityType.PARROT).breathable().noScreenCover().get());
		// TODO Tick Randomly & decay

		this.saplingDropChance = saplingChance;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {}

	public float getSaplingDropChance() {
		return saplingDropChance;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 30;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}
}
