package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.worldgen.trees.WorldGenAchonyTree;

import java.util.Random;

public class AchonySapling extends SaplingBlock {
	public AchonySapling() {
		super("AchonySapling", "achony_sapling", false);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenAchonyTree(this).generate(world, rand, pos);
	}
}
