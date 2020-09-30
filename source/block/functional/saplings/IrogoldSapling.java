package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.worldgen.trees.WorldGenIrogoldTree;

import java.util.Random;

public class IrogoldSapling extends SaplingBlock {
	public IrogoldSapling() {
		super("IrogoldSapling", "irogold_sapling", false);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenIrogoldTree(this).generate(world, rand, pos);
	}
}
