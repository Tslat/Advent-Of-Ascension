package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.worldgen.trees.WorldGenLunossoTree;

import java.util.Random;

public class LunossoSapling extends SaplingBlock {
	public LunossoSapling() {
		super("LunossoSapling", "lunosso_sapling", true);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenLunossoTree(this).generate(world, rand, pos);
	}
}
