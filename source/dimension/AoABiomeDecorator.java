package net.tslat.aoa3.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import java.util.Random;

public abstract class AoABiomeDecorator extends BiomeDecorator {
	@Override
	public void decorate(World world, Random rand, Biome biome, BlockPos pos) {
		doOreGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
		doPlantGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
		doTreeGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
		doMiscGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
	}

	protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {}

	protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {}

	protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {}

	protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {}
}
