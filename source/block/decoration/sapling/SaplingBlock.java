package net.tslat.aoa3.block.decoration.sapling;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.worldgen.trees.TreeGenerator;

import java.util.Random;

public abstract class SaplingBlock extends net.minecraft.block.SaplingBlock {
	private final boolean growsInDark;

	public SaplingBlock(MaterialColor mapColor, boolean growsInDark) {
		super(null, generateBlockProperties(mapColor));

		this.growsInDark = growsInDark;
	}

	private static Block.Properties generateBlockProperties(MaterialColor mapColor) {
		return Block.Properties.create(Material.PLANTS, mapColor)
				.doesNotBlockMovement()
				.tickRandomly()
				.hardnessAndResistance(0)
				.sound(SoundType.PLANT);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!world.isAreaLoaded(pos, 1))
			return;

		if ((growsInDark || world.getLight(pos.up()) >= 9) && rand.nextInt(7) == 0)
			placeTree(world, pos, state, rand);
	}

	@Override
	public void placeTree(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
		if (state.get(STAGE) == 0) {
			world.setBlockState(pos, state.cycle(STAGE), 4);
		}
		else if (ForgeEventFactory.saplingGrowTree(world, rand, pos)) {
			getTree(world, pos, state, rand).generate(world, rand, pos);
		}
	}

	protected abstract TreeGenerator getTree(ServerWorld world, BlockPos pos, BlockState groundBlock, Random rand);

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return super.isValidGround(state, worldIn, pos) || state.getBlock().isIn(AoATags.Blocks.GRASS) || state.getBlock().isIn(Tags.Blocks.DIRT);
	}
}
