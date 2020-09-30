package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.tslat.aoa3.worldgen.trees.WorldGenLuniciaTree;

import java.util.Random;

public class LuniciaSapling extends SaplingBlock {
	public LuniciaSapling() {
		super("LuniciaSapling", "lunicia_sapling", true);
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (TerrainGen.saplingGrowTree(world, rand, pos))
			new WorldGenLuniciaTree(this).generate(world, rand, pos);
	}
}
