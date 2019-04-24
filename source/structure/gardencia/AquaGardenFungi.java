package net.tslat.aoa3.structure.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class AquaGardenFungi extends AoAStructure { //StructureSize: 7x16x7
	private static final IBlockState aquaMushroomOutside = BlockRegister.mushroomAquaOutside.getDefaultState();
	private static final IBlockState aquaMushroomInside = BlockRegister.mushroomAquaInside.getDefaultState();
	private static final IBlockState stem = BlockRegister.plantStem.getDefaultState();

	public AquaGardenFungi() {
		super("AquaGardenFungi");
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
		addBlock(world, basePos, 3, 11, 3, stem);
		addBlock(world, basePos, 0, 12, 2, aquaMushroomOutside);
		addBlock(world, basePos, 0, 12, 3, aquaMushroomOutside);
		addBlock(world, basePos, 0, 12, 4, aquaMushroomOutside);
		addBlock(world, basePos, 1, 12, 1, aquaMushroomOutside);
		addBlock(world, basePos, 1, 12, 2, aquaMushroomInside);
		addBlock(world, basePos, 1, 12, 3, aquaMushroomInside);
		addBlock(world, basePos, 1, 12, 4, aquaMushroomInside);
		addBlock(world, basePos, 1, 12, 5, aquaMushroomOutside);
		addBlock(world, basePos, 2, 12, 0, aquaMushroomOutside);
		addBlock(world, basePos, 2, 12, 1, aquaMushroomInside);
		addBlock(world, basePos, 2, 12, 2, aquaMushroomInside);
		addBlock(world, basePos, 2, 12, 3, aquaMushroomInside);
		addBlock(world, basePos, 2, 12, 4, aquaMushroomInside);
		addBlock(world, basePos, 2, 12, 5, aquaMushroomInside);
		addBlock(world, basePos, 2, 12, 6, aquaMushroomOutside);
		addBlock(world, basePos, 3, 12, 0, aquaMushroomOutside);
		addBlock(world, basePos, 3, 12, 1, aquaMushroomInside);
		addBlock(world, basePos, 3, 12, 2, aquaMushroomInside);
		addBlock(world, basePos, 3, 12, 3, aquaMushroomInside);
		addBlock(world, basePos, 3, 12, 4, aquaMushroomInside);
		addBlock(world, basePos, 3, 12, 5, aquaMushroomInside);
		addBlock(world, basePos, 3, 12, 6, aquaMushroomOutside);
		addBlock(world, basePos, 4, 12, 0, aquaMushroomOutside);
		addBlock(world, basePos, 4, 12, 1, aquaMushroomInside);
		addBlock(world, basePos, 4, 12, 2, aquaMushroomInside);
		addBlock(world, basePos, 4, 12, 3, aquaMushroomInside);
		addBlock(world, basePos, 4, 12, 4, aquaMushroomInside);
		addBlock(world, basePos, 4, 12, 5, aquaMushroomInside);
		addBlock(world, basePos, 4, 12, 6, aquaMushroomOutside);
		addBlock(world, basePos, 5, 12, 1, aquaMushroomOutside);
		addBlock(world, basePos, 5, 12, 2, aquaMushroomInside);
		addBlock(world, basePos, 5, 12, 3, aquaMushroomInside);
		addBlock(world, basePos, 5, 12, 4, aquaMushroomInside);
		addBlock(world, basePos, 5, 12, 5, aquaMushroomOutside);
		addBlock(world, basePos, 6, 12, 2, aquaMushroomOutside);
		addBlock(world, basePos, 6, 12, 3, aquaMushroomOutside);
		addBlock(world, basePos, 6, 12, 4, aquaMushroomOutside);
		addBlock(world, basePos, 1, 13, 2, aquaMushroomOutside);
		addBlock(world, basePos, 1, 13, 3, aquaMushroomOutside);
		addBlock(world, basePos, 1, 13, 4, aquaMushroomOutside);
		addBlock(world, basePos, 2, 13, 1, aquaMushroomOutside);
		addBlock(world, basePos, 2, 13, 2, aquaMushroomInside);
		addBlock(world, basePos, 2, 13, 3, aquaMushroomInside);
		addBlock(world, basePos, 2, 13, 4, aquaMushroomInside);
		addBlock(world, basePos, 2, 13, 5, aquaMushroomOutside);
		addBlock(world, basePos, 3, 13, 1, aquaMushroomOutside);
		addBlock(world, basePos, 3, 13, 2, aquaMushroomInside);
		addBlock(world, basePos, 3, 13, 3, aquaMushroomInside);
		addBlock(world, basePos, 3, 13, 4, aquaMushroomInside);
		addBlock(world, basePos, 3, 13, 5, aquaMushroomOutside);
		addBlock(world, basePos, 4, 13, 1, aquaMushroomOutside);
		addBlock(world, basePos, 4, 13, 2, aquaMushroomInside);
		addBlock(world, basePos, 4, 13, 3, aquaMushroomInside);
		addBlock(world, basePos, 4, 13, 4, aquaMushroomInside);
		addBlock(world, basePos, 4, 13, 5, aquaMushroomOutside);
		addBlock(world, basePos, 5, 13, 2, aquaMushroomOutside);
		addBlock(world, basePos, 5, 13, 3, aquaMushroomOutside);
		addBlock(world, basePos, 5, 13, 4, aquaMushroomOutside);
		addBlock(world, basePos, 1, 14, 2, aquaMushroomOutside);
		addBlock(world, basePos, 1, 14, 3, aquaMushroomOutside);
		addBlock(world, basePos, 1, 14, 4, aquaMushroomOutside);
		addBlock(world, basePos, 2, 14, 1, aquaMushroomOutside);
		addBlock(world, basePos, 2, 14, 2, aquaMushroomInside);
		addBlock(world, basePos, 2, 14, 3, aquaMushroomInside);
		addBlock(world, basePos, 2, 14, 4, aquaMushroomInside);
		addBlock(world, basePos, 2, 14, 5, aquaMushroomOutside);
		addBlock(world, basePos, 3, 14, 1, aquaMushroomOutside);
		addBlock(world, basePos, 3, 14, 2, aquaMushroomInside);
		addBlock(world, basePos, 3, 14, 3, aquaMushroomInside);
		addBlock(world, basePos, 3, 14, 4, aquaMushroomInside);
		addBlock(world, basePos, 3, 14, 5, aquaMushroomOutside);
		addBlock(world, basePos, 4, 14, 1, aquaMushroomOutside);
		addBlock(world, basePos, 4, 14, 2, aquaMushroomInside);
		addBlock(world, basePos, 4, 14, 3, aquaMushroomInside);
		addBlock(world, basePos, 4, 14, 4, aquaMushroomInside);
		addBlock(world, basePos, 4, 14, 5, aquaMushroomOutside);
		addBlock(world, basePos, 5, 14, 2, aquaMushroomOutside);
		addBlock(world, basePos, 5, 14, 3, aquaMushroomOutside);
		addBlock(world, basePos, 5, 14, 4, aquaMushroomOutside);
		addBlock(world, basePos, 2, 15, 2, aquaMushroomOutside);
		addBlock(world, basePos, 2, 15, 3, aquaMushroomOutside);
		addBlock(world, basePos, 2, 15, 4, aquaMushroomOutside);
		addBlock(world, basePos, 3, 15, 2, aquaMushroomOutside);
		addBlock(world, basePos, 3, 15, 3, aquaMushroomOutside);
		addBlock(world, basePos, 3, 15, 4, aquaMushroomOutside);
		addBlock(world, basePos, 4, 15, 2, aquaMushroomOutside);
		addBlock(world, basePos, 4, 15, 3, aquaMushroomOutside);
		addBlock(world, basePos, 4, 15, 4, aquaMushroomOutside);
	}
}
