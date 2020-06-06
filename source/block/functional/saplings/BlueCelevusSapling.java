package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.worldgen.trees.WorldGenCelevusTree;

import java.util.Random;

public class BlueCelevusSapling extends SaplingBlock {
	public BlueCelevusSapling() {
		super("BlueCelevusSapling", "blue_celevus_sapling", false);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenCelevusTree(this, BlockRegister.BLUE_CELEVUS_LEAVES, rand).generate(world, rand, pos);
	}
}
