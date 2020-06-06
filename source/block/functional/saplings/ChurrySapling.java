package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.worldgen.trees.WorldGenChurryTree;

import java.util.Random;

public class ChurrySapling extends SaplingBlock {
	public ChurrySapling() {
		super("ChurrySapling", "churry_sapling", false);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenChurryTree(this).generate(world, rand, pos);
	}
}
