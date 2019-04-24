package net.tslat.aoa3.structure.mysterium;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PurpleMushroomTree extends AoAStructure { //StructureSize: 8x9x8
	private static final IBlockState purpleMushroomOutside = BlockRegister.mushroomPurpleOutside.getDefaultState();
	private static final IBlockState purpleMushroomInside = BlockRegister.mushroomPurpleInside.getDefaultState();
	private static final IBlockState purpleMushroomStem = BlockRegister.mushroomStemPurple.getDefaultState();
	private static final IBlockState mysteriumGrass = BlockRegister.grassMysterium.getDefaultState();

	public PurpleMushroomTree() {
		super("PurpleMushroomTree");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		for (int x = 3; x < 5; x++) {
			for (int z = 3; z < 5; z++) {
				if (world.getBlockState(basePos.add(x, -1, z)).getBlock() == Blocks.WATER)
					world.setBlockState(basePos	.add(x, -1, z), mysteriumGrass);
			}
		}

		addBlock(world, basePos, 3, 0, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 0, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 0, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 0, 4, purpleMushroomStem);
		addBlock(world, basePos, 3, 1, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 1, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 1, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 1, 4, purpleMushroomStem);
		addBlock(world, basePos, 3, 2, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 2, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 2, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 2, 4, purpleMushroomStem);
		addBlock(world, basePos, 3, 3, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 3, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 3, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 3, 4, purpleMushroomStem);
		addBlock(world, basePos, 3, 4, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 4, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 4, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 4, 4, purpleMushroomStem);
		addBlock(world, basePos, 3, 5, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 5, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 5, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 5, 4, purpleMushroomStem);
		addBlock(world, basePos, 0, 6, 1, purpleMushroomOutside);
		addBlock(world, basePos, 0, 6, 2, purpleMushroomOutside);
		addBlock(world, basePos, 0, 6, 3, purpleMushroomOutside);
		addBlock(world, basePos, 0, 6, 4, purpleMushroomOutside);
		addBlock(world, basePos, 0, 6, 5, purpleMushroomOutside);
		addBlock(world, basePos, 0, 6, 6, purpleMushroomOutside);
		addBlock(world, basePos, 1, 6, 0, purpleMushroomOutside);
		addBlock(world, basePos, 1, 6, 7, purpleMushroomOutside);
		addBlock(world, basePos, 2, 6, 0, purpleMushroomOutside);
		addBlock(world, basePos, 2, 6, 7, purpleMushroomOutside);
		addBlock(world, basePos, 3, 6, 0, purpleMushroomOutside);
		addBlock(world, basePos, 3, 6, 3, purpleMushroomStem);
		addBlock(world, basePos, 3, 6, 4, purpleMushroomStem);
		addBlock(world, basePos, 3, 6, 7, purpleMushroomOutside);
		addBlock(world, basePos, 4, 6, 0, purpleMushroomOutside);
		addBlock(world, basePos, 4, 6, 3, purpleMushroomStem);
		addBlock(world, basePos, 4, 6, 4, purpleMushroomStem);
		addBlock(world, basePos, 4, 6, 7, purpleMushroomOutside);
		addBlock(world, basePos, 5, 6, 0, purpleMushroomOutside);
		addBlock(world, basePos, 5, 6, 7, purpleMushroomOutside);
		addBlock(world, basePos, 6, 6, 0, purpleMushroomOutside);
		addBlock(world, basePos, 6, 6, 7, purpleMushroomOutside);
		addBlock(world, basePos, 7, 6, 1, purpleMushroomOutside);
		addBlock(world, basePos, 7, 6, 2, purpleMushroomOutside);
		addBlock(world, basePos, 7, 6, 3, purpleMushroomOutside);
		addBlock(world, basePos, 7, 6, 4, purpleMushroomOutside);
		addBlock(world, basePos, 7, 6, 5, purpleMushroomOutside);
		addBlock(world, basePos, 7, 6, 6, purpleMushroomOutside);
		addBlock(world, basePos, 0, 7, 1, purpleMushroomOutside);
		addBlock(world, basePos, 0, 7, 2, purpleMushroomOutside);
		addBlock(world, basePos, 0, 7, 3, purpleMushroomOutside);
		addBlock(world, basePos, 0, 7, 4, purpleMushroomOutside);
		addBlock(world, basePos, 0, 7, 5, purpleMushroomOutside);
		addBlock(world, basePos, 0, 7, 6, purpleMushroomOutside);
		addBlock(world, basePos, 1, 7, 0, purpleMushroomOutside);
		addBlock(world, basePos, 1, 7, 1, purpleMushroomInside);
		addBlock(world, basePos, 1, 7, 2, purpleMushroomInside);
		addBlock(world, basePos, 1, 7, 3, purpleMushroomInside);
		addBlock(world, basePos, 1, 7, 4, purpleMushroomInside);
		addBlock(world, basePos, 1, 7, 5, purpleMushroomInside);
		addBlock(world, basePos, 1, 7, 6, purpleMushroomInside);
		addBlock(world, basePos, 1, 7, 7, purpleMushroomOutside);
		addBlock(world, basePos, 2, 7, 0, purpleMushroomOutside);
		addBlock(world, basePos, 2, 7, 1, purpleMushroomInside);
		addBlock(world, basePos, 2, 7, 2, purpleMushroomInside);
		addBlock(world, basePos, 2, 7, 3, purpleMushroomInside);
		addBlock(world, basePos, 2, 7, 4, purpleMushroomInside);
		addBlock(world, basePos, 2, 7, 5, purpleMushroomInside);
		addBlock(world, basePos, 2, 7, 6, purpleMushroomInside);
		addBlock(world, basePos, 2, 7, 7, purpleMushroomOutside);
		addBlock(world, basePos, 3, 7, 0, purpleMushroomOutside);
		addBlock(world, basePos, 3, 7, 1, purpleMushroomInside);
		addBlock(world, basePos, 3, 7, 2, purpleMushroomInside);
		addBlock(world, basePos, 3, 7, 3, purpleMushroomInside);
		addBlock(world, basePos, 3, 7, 4, purpleMushroomInside);
		addBlock(world, basePos, 3, 7, 5, purpleMushroomInside);
		addBlock(world, basePos, 3, 7, 6, purpleMushroomInside);
		addBlock(world, basePos, 3, 7, 7, purpleMushroomOutside);
		addBlock(world, basePos, 4, 7, 0, purpleMushroomOutside);
		addBlock(world, basePos, 4, 7, 1, purpleMushroomInside);
		addBlock(world, basePos, 4, 7, 2, purpleMushroomInside);
		addBlock(world, basePos, 4, 7, 3, purpleMushroomInside);
		addBlock(world, basePos, 4, 7, 4, purpleMushroomInside);
		addBlock(world, basePos, 4, 7, 5, purpleMushroomInside);
		addBlock(world, basePos, 4, 7, 6, purpleMushroomInside);
		addBlock(world, basePos, 4, 7, 7, purpleMushroomOutside);
		addBlock(world, basePos, 5, 7, 0, purpleMushroomOutside);
		addBlock(world, basePos, 5, 7, 1, purpleMushroomInside);
		addBlock(world, basePos, 5, 7, 2, purpleMushroomInside);
		addBlock(world, basePos, 5, 7, 3, purpleMushroomInside);
		addBlock(world, basePos, 5, 7, 4, purpleMushroomInside);
		addBlock(world, basePos, 5, 7, 5, purpleMushroomInside);
		addBlock(world, basePos, 5, 7, 6, purpleMushroomInside);
		addBlock(world, basePos, 5, 7, 7, purpleMushroomOutside);
		addBlock(world, basePos, 6, 7, 0, purpleMushroomOutside);
		addBlock(world, basePos, 6, 7, 1, purpleMushroomInside);
		addBlock(world, basePos, 6, 7, 2, purpleMushroomInside);
		addBlock(world, basePos, 6, 7, 3, purpleMushroomInside);
		addBlock(world, basePos, 6, 7, 4, purpleMushroomInside);
		addBlock(world, basePos, 6, 7, 5, purpleMushroomInside);
		addBlock(world, basePos, 6, 7, 6, purpleMushroomInside);
		addBlock(world, basePos, 6, 7, 7, purpleMushroomOutside);
		addBlock(world, basePos, 7, 7, 1, purpleMushroomOutside);
		addBlock(world, basePos, 7, 7, 2, purpleMushroomOutside);
		addBlock(world, basePos, 7, 7, 3, purpleMushroomOutside);
		addBlock(world, basePos, 7, 7, 4, purpleMushroomOutside);
		addBlock(world, basePos, 7, 7, 5, purpleMushroomOutside);
		addBlock(world, basePos, 7, 7, 6, purpleMushroomOutside);
		addBlock(world, basePos, 1, 8, 1, purpleMushroomOutside);
		addBlock(world, basePos, 1, 8, 2, purpleMushroomOutside);
		addBlock(world, basePos, 1, 8, 3, purpleMushroomOutside);
		addBlock(world, basePos, 1, 8, 4, purpleMushroomOutside);
		addBlock(world, basePos, 1, 8, 5, purpleMushroomOutside);
		addBlock(world, basePos, 1, 8, 6, purpleMushroomOutside);
		addBlock(world, basePos, 2, 8, 1, purpleMushroomOutside);
		addBlock(world, basePos, 2, 8, 2, purpleMushroomOutside);
		addBlock(world, basePos, 2, 8, 3, purpleMushroomOutside);
		addBlock(world, basePos, 2, 8, 4, purpleMushroomOutside);
		addBlock(world, basePos, 2, 8, 5, purpleMushroomOutside);
		addBlock(world, basePos, 2, 8, 6, purpleMushroomOutside);
		addBlock(world, basePos, 3, 8, 1, purpleMushroomOutside);
		addBlock(world, basePos, 3, 8, 2, purpleMushroomOutside);
		addBlock(world, basePos, 3, 8, 3, purpleMushroomOutside);
		addBlock(world, basePos, 3, 8, 4, purpleMushroomOutside);
		addBlock(world, basePos, 3, 8, 5, purpleMushroomOutside);
		addBlock(world, basePos, 3, 8, 6, purpleMushroomOutside);
		addBlock(world, basePos, 4, 8, 1, purpleMushroomOutside);
		addBlock(world, basePos, 4, 8, 2, purpleMushroomOutside);
		addBlock(world, basePos, 4, 8, 3, purpleMushroomOutside);
		addBlock(world, basePos, 4, 8, 4, purpleMushroomOutside);
		addBlock(world, basePos, 4, 8, 5, purpleMushroomOutside);
		addBlock(world, basePos, 4, 8, 6, purpleMushroomOutside);
		addBlock(world, basePos, 5, 8, 1, purpleMushroomOutside);
		addBlock(world, basePos, 5, 8, 2, purpleMushroomOutside);
		addBlock(world, basePos, 5, 8, 3, purpleMushroomOutside);
		addBlock(world, basePos, 5, 8, 4, purpleMushroomOutside);
		addBlock(world, basePos, 5, 8, 5, purpleMushroomOutside);
		addBlock(world, basePos, 5, 8, 6, purpleMushroomOutside);
		addBlock(world, basePos, 6, 8, 1, purpleMushroomOutside);
		addBlock(world, basePos, 6, 8, 2, purpleMushroomOutside);
		addBlock(world, basePos, 6, 8, 3, purpleMushroomOutside);
		addBlock(world, basePos, 6, 8, 4, purpleMushroomOutside);
		addBlock(world, basePos, 6, 8, 5, purpleMushroomOutside);
		addBlock(world, basePos, 6, 8, 6, purpleMushroomOutside);
	}
}
