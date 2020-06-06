package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.worldgen.trees.WorldGenHavenTree;

import java.util.Random;

public class YellowHavenSapling extends SaplingBlock {
	public YellowHavenSapling() {
		super("YellowHavenSapling", "yellow_haven_sapling", false);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenHavenTree(this, BlockRegister.YELLOW_HAVEN_LEAVES, rand).generate(world, rand, pos);
	}
}
