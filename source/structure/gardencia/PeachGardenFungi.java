package net.tslat.aoa3.structure.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class PeachGardenFungi extends AoAStructure { //StructureSize: 7x13x7
	private static final IBlockState peachMushroomOutside = BlockRegister.mushroomPeachOutside.getDefaultState();
	private static final IBlockState peachMushroomInside = BlockRegister.mushroomPeachInside.getDefaultState();
	private static final IBlockState stem = BlockRegister.mushroomStemOrange.getDefaultState();

	public PeachGardenFungi() {
		super("PeachGardenFungi");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, stem);
		addBlock(world, basePos, 3, 1, 3, stem);
		addBlock(world, basePos, 3, 2, 3, stem);
		addBlock(world, basePos, 3, 3, 3, stem);
		addBlock(world, basePos, 3, 4, 3, stem);
		addBlock(world, basePos, 3, 5, 3, stem);
		addBlock(world, basePos, 3, 6, 3, stem);
		addBlock(world, basePos, 3, 7, 3, stem);
		addBlock(world, basePos, 3, 8, 3, stem);
		addBlock(world, basePos, 3, 9, 3, stem);
		addBlock(world, basePos, 3, 10, 3, stem);
		addBlock(world, basePos, 0, 11, 2, peachMushroomOutside);
		addBlock(world, basePos, 0, 11, 3, peachMushroomOutside);
		addBlock(world, basePos, 0, 11, 4, peachMushroomOutside);
		addBlock(world, basePos, 1, 11, 1, peachMushroomOutside);
		addBlock(world, basePos, 1, 11, 2, peachMushroomInside);
		addBlock(world, basePos, 1, 11, 3, peachMushroomInside);
		addBlock(world, basePos, 1, 11, 4, peachMushroomInside);
		addBlock(world, basePos, 1, 11, 5, peachMushroomOutside);
		addBlock(world, basePos, 2, 11, 0, peachMushroomOutside);
		addBlock(world, basePos, 2, 11, 1, peachMushroomInside);
		addBlock(world, basePos, 2, 11, 2, peachMushroomInside);
		addBlock(world, basePos, 2, 11, 3, peachMushroomInside);
		addBlock(world, basePos, 2, 11, 4, peachMushroomInside);
		addBlock(world, basePos, 2, 11, 5, peachMushroomInside);
		addBlock(world, basePos, 2, 11, 6, peachMushroomOutside);
		addBlock(world, basePos, 3, 11, 0, peachMushroomOutside);
		addBlock(world, basePos, 3, 11, 1, peachMushroomInside);
		addBlock(world, basePos, 3, 11, 2, peachMushroomInside);
		addBlock(world, basePos, 3, 11, 3, peachMushroomInside);
		addBlock(world, basePos, 3, 11, 4, peachMushroomInside);
		addBlock(world, basePos, 3, 11, 5, peachMushroomInside);
		addBlock(world, basePos, 3, 11, 6, peachMushroomOutside);
		addBlock(world, basePos, 4, 11, 0, peachMushroomOutside);
		addBlock(world, basePos, 4, 11, 1, peachMushroomInside);
		addBlock(world, basePos, 4, 11, 2, peachMushroomInside);
		addBlock(world, basePos, 4, 11, 3, peachMushroomInside);
		addBlock(world, basePos, 4, 11, 4, peachMushroomInside);
		addBlock(world, basePos, 4, 11, 5, peachMushroomInside);
		addBlock(world, basePos, 4, 11, 6, peachMushroomOutside);
		addBlock(world, basePos, 5, 11, 1, peachMushroomOutside);
		addBlock(world, basePos, 5, 11, 2, peachMushroomInside);
		addBlock(world, basePos, 5, 11, 3, peachMushroomInside);
		addBlock(world, basePos, 5, 11, 4, peachMushroomInside);
		addBlock(world, basePos, 5, 11, 5, peachMushroomOutside);
		addBlock(world, basePos, 6, 11, 2, peachMushroomOutside);
		addBlock(world, basePos, 6, 11, 3, peachMushroomOutside);
		addBlock(world, basePos, 6, 11, 4, peachMushroomOutside);
		addBlock(world, basePos, 1, 12, 2, peachMushroomOutside);
		addBlock(world, basePos, 1, 12, 3, peachMushroomOutside);
		addBlock(world, basePos, 1, 12, 4, peachMushroomOutside);
		addBlock(world, basePos, 2, 12, 1, peachMushroomOutside);
		addBlock(world, basePos, 2, 12, 2, peachMushroomOutside);
		addBlock(world, basePos, 2, 12, 3, peachMushroomOutside);
		addBlock(world, basePos, 2, 12, 4, peachMushroomOutside);
		addBlock(world, basePos, 2, 12, 5, peachMushroomOutside);
		addBlock(world, basePos, 3, 12, 1, peachMushroomOutside);
		addBlock(world, basePos, 3, 12, 2, peachMushroomOutside);
		addBlock(world, basePos, 3, 12, 3, peachMushroomOutside);
		addBlock(world, basePos, 3, 12, 4, peachMushroomOutside);
		addBlock(world, basePos, 3, 12, 5, peachMushroomOutside);
		addBlock(world, basePos, 4, 12, 1, peachMushroomOutside);
		addBlock(world, basePos, 4, 12, 2, peachMushroomOutside);
		addBlock(world, basePos, 4, 12, 3, peachMushroomOutside);
		addBlock(world, basePos, 4, 12, 4, peachMushroomOutside);
		addBlock(world, basePos, 4, 12, 5, peachMushroomOutside);
		addBlock(world, basePos, 5, 12, 2, peachMushroomOutside);
		addBlock(world, basePos, 5, 12, 3, peachMushroomOutside);
		addBlock(world, basePos, 5, 12, 4, peachMushroomOutside);
	}
}
