package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.worldgen.trees.WorldGenShyreTree;

import java.util.Random;

public class BrightShyreSapling extends SaplingBlock {
	public BrightShyreSapling() {
		super("BrightShyreSapling", "bright_shyre_sapling", false);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenShyreTree(this, BlockRegister.BRIGHT_SHYRE_LEAVES, rand).generate(world, rand, pos);
	}
}
