package net.tslat.aoa3.structure.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class RoseTree extends AoAStructure { //StructureSize: 7x12x7
	private static final IBlockState stem = BlockRegister.plantStem.getDefaultState();
	private static final IBlockState redPetals = BlockRegister.petalsRed.getDefaultState();
	private static final IBlockState rosePetals = BlockRegister.petalsRose.getDefaultState();

	public RoseTree() {
		super("RoseTree");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, stem);
		addBlock(world, basePos, 3, 1, 3, stem);
		addBlock(world, basePos, 6, 1, 3, rosePetals);
		addBlock(world, basePos, 3, 2, 3, stem);
		addBlock(world, basePos, 3, 2, 6, rosePetals);
		addBlock(world, basePos, 4, 2, 3, stem);
		addBlock(world, basePos, 5, 2, 3, stem);
		addBlock(world, basePos, 6, 2, 2, rosePetals);
		addBlock(world, basePos, 6, 2, 3, redPetals);
		addBlock(world, basePos, 6, 2, 4, rosePetals);
		addBlock(world, basePos, 2, 3, 6, rosePetals);
		addBlock(world, basePos, 3, 3, 0, rosePetals);
		addBlock(world, basePos, 3, 3, 3, stem);
		addBlock(world, basePos, 3, 3, 4, stem);
		addBlock(world, basePos, 3, 3, 5, stem);
		addBlock(world, basePos, 3, 3, 6, redPetals);
		addBlock(world, basePos, 4, 3, 6, rosePetals);
		addBlock(world, basePos, 6, 3, 3, rosePetals);
		addBlock(world, basePos, 0, 4, 3, rosePetals);
		addBlock(world, basePos, 2, 4, 0, rosePetals);
		addBlock(world, basePos, 3, 4, 0, redPetals);
		addBlock(world, basePos, 3, 4, 1, stem);
		addBlock(world, basePos, 3, 4, 2, stem);
		addBlock(world, basePos, 3, 4, 3, stem);
		addBlock(world, basePos, 3, 4, 6, rosePetals);
		addBlock(world, basePos, 4, 4, 0, rosePetals);
		addBlock(world, basePos, 0, 5, 2, rosePetals);
		addBlock(world, basePos, 0, 5, 3, redPetals);
		addBlock(world, basePos, 0, 5, 4, rosePetals);
		addBlock(world, basePos, 1, 5, 3, stem);
		addBlock(world, basePos, 2, 5, 3, stem);
		addBlock(world, basePos, 3, 5, 0, rosePetals);
		addBlock(world, basePos, 3, 5, 3, stem);
		addBlock(world, basePos, 6, 5, 3, rosePetals);
		addBlock(world, basePos, 0, 6, 3, rosePetals);
		addBlock(world, basePos, 3, 6, 3, stem);
		addBlock(world, basePos, 4, 6, 3, stem);
		addBlock(world, basePos, 5, 6, 3, stem);
		addBlock(world, basePos, 6, 6, 2, rosePetals);
		addBlock(world, basePos, 6, 6, 3, redPetals);
		addBlock(world, basePos, 6, 6, 4, rosePetals);
		addBlock(world, basePos, 0, 7, 3, rosePetals);
		addBlock(world, basePos, 3, 7, 0, rosePetals);
		addBlock(world, basePos, 3, 7, 3, stem);
		addBlock(world, basePos, 3, 7, 6, rosePetals);
		addBlock(world, basePos, 6, 7, 3, rosePetals);
		addBlock(world, basePos, 0, 8, 2, rosePetals);
		addBlock(world, basePos, 0, 8, 3, redPetals);
		addBlock(world, basePos, 0, 8, 4, rosePetals);
		addBlock(world, basePos, 1, 8, 3, stem);
		addBlock(world, basePos, 2, 8, 0, rosePetals);
		addBlock(world, basePos, 2, 8, 3, stem);
		addBlock(world, basePos, 2, 8, 6, rosePetals);
		addBlock(world, basePos, 3, 8, 0, redPetals);
		addBlock(world, basePos, 3, 8, 1, stem);
		addBlock(world, basePos, 3, 8, 2, stem);
		addBlock(world, basePos, 3, 8, 3, stem);
		addBlock(world, basePos, 3, 8, 4, stem);
		addBlock(world, basePos, 3, 8, 5, stem);
		addBlock(world, basePos, 3, 8, 6, redPetals);
		addBlock(world, basePos, 4, 8, 0, rosePetals);
		addBlock(world, basePos, 4, 8, 6, rosePetals);
		addBlock(world, basePos, 0, 9, 3, rosePetals);
		addBlock(world, basePos, 3, 9, 0, rosePetals);
		addBlock(world, basePos, 3, 9, 3, stem);
		addBlock(world, basePos, 3, 9, 6, rosePetals);
		addBlock(world, basePos, 3, 10, 3, stem);
		addBlock(world, basePos, 2, 11, 3, rosePetals);
		addBlock(world, basePos, 3, 11, 2, rosePetals);
		addBlock(world, basePos, 3, 11, 3, redPetals);
		addBlock(world, basePos, 3, 11, 4, rosePetals);
		addBlock(world, basePos, 4, 11, 3, rosePetals);
	}
}
