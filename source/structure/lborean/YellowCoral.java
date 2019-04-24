package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class YellowCoral extends AoAStructure { //StructureSize: 11x11x11
	private static final IBlockState yellowCoral = BlockRegister.coralYellow.getDefaultState();

	public YellowCoral() {
		super("YellowCoral");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 4, 0, 5, yellowCoral);
		addBlock(world, basePos, 4, 1, 5, yellowCoral);
		addBlock(world, basePos, 4, 2, 5, yellowCoral);
		addBlock(world, basePos, 2, 3, 5, yellowCoral);
		addBlock(world, basePos, 3, 3, 5, yellowCoral);
		addBlock(world, basePos, 4, 3, 3, yellowCoral);
		addBlock(world, basePos, 4, 3, 4, yellowCoral);
		addBlock(world, basePos, 4, 3, 5, yellowCoral);
		addBlock(world, basePos, 4, 3, 6, yellowCoral);
		addBlock(world, basePos, 4, 3, 7, yellowCoral);
		addBlock(world, basePos, 5, 3, 5, yellowCoral);
		addBlock(world, basePos, 6, 3, 5, yellowCoral);
		addBlock(world, basePos, 2, 4, 3, yellowCoral);
		addBlock(world, basePos, 2, 4, 5, yellowCoral);
		addBlock(world, basePos, 2, 4, 7, yellowCoral);
		addBlock(world, basePos, 3, 4, 4, yellowCoral);
		addBlock(world, basePos, 3, 4, 6, yellowCoral);
		addBlock(world, basePos, 4, 4, 3, yellowCoral);
		addBlock(world, basePos, 4, 4, 7, yellowCoral);
		addBlock(world, basePos, 5, 4, 4, yellowCoral);
		addBlock(world, basePos, 5, 4, 6, yellowCoral);
		addBlock(world, basePos, 6, 4, 3, yellowCoral);
		addBlock(world, basePos, 6, 4, 5, yellowCoral);
		addBlock(world, basePos, 6, 4, 7, yellowCoral);
		addBlock(world, basePos, 7, 4, 5, yellowCoral);
		addBlock(world, basePos, 1, 5, 5, yellowCoral);
		addBlock(world, basePos, 2, 5, 3, yellowCoral);
		addBlock(world, basePos, 2, 5, 5, yellowCoral);
		addBlock(world, basePos, 2, 5, 7, yellowCoral);
		addBlock(world, basePos, 4, 5, 2, yellowCoral);
		addBlock(world, basePos, 4, 5, 3, yellowCoral);
		addBlock(world, basePos, 4, 5, 7, yellowCoral);
		addBlock(world, basePos, 4, 5, 8, yellowCoral);
		addBlock(world, basePos, 6, 5, 3, yellowCoral);
		addBlock(world, basePos, 6, 5, 7, yellowCoral);
		addBlock(world, basePos, 7, 5, 5, yellowCoral);
		addBlock(world, basePos, 7, 5, 8, yellowCoral);
		addBlock(world, basePos, 8, 5, 5, yellowCoral);
		addBlock(world, basePos, 1, 6, 2, yellowCoral);
		addBlock(world, basePos, 1, 6, 5, yellowCoral);
		addBlock(world, basePos, 1, 6, 8, yellowCoral);
		addBlock(world, basePos, 2, 6, 3, yellowCoral);
		addBlock(world, basePos, 2, 6, 7, yellowCoral);
		addBlock(world, basePos, 4, 6, 2, yellowCoral);
		addBlock(world, basePos, 4, 6, 8, yellowCoral);
		addBlock(world, basePos, 6, 6, 3, yellowCoral);
		addBlock(world, basePos, 7, 6, 2, yellowCoral);
		addBlock(world, basePos, 7, 6, 8, yellowCoral);
		addBlock(world, basePos, 8, 6, 5, yellowCoral);
		addBlock(world, basePos, 0, 7, 1, yellowCoral);
		addBlock(world, basePos, 1, 7, 2, yellowCoral);
		addBlock(world, basePos, 1, 7, 5, yellowCoral);
		addBlock(world, basePos, 1, 7, 8, yellowCoral);
		addBlock(world, basePos, 4, 7, 1, yellowCoral);
		addBlock(world, basePos, 4, 7, 2, yellowCoral);
		addBlock(world, basePos, 4, 7, 8, yellowCoral);
		addBlock(world, basePos, 4, 7, 9, yellowCoral);
		addBlock(world, basePos, 7, 7, 2, yellowCoral);
		addBlock(world, basePos, 7, 7, 8, yellowCoral);
		addBlock(world, basePos, 8, 7, 5, yellowCoral);
		addBlock(world, basePos, 8, 7, 9, yellowCoral);
		addBlock(world, basePos, 9, 7, 5, yellowCoral);
		addBlock(world, basePos, 0, 8, 1, yellowCoral);
		addBlock(world, basePos, 0, 8, 5, yellowCoral);
		addBlock(world, basePos, 1, 8, 5, yellowCoral);
		addBlock(world, basePos, 1, 8, 8, yellowCoral);
		addBlock(world, basePos, 4, 8, 1, yellowCoral);
		addBlock(world, basePos, 4, 8, 9, yellowCoral);
		addBlock(world, basePos, 7, 8, 2, yellowCoral);
		addBlock(world, basePos, 8, 8, 1, yellowCoral);
		addBlock(world, basePos, 8, 8, 9, yellowCoral);
		addBlock(world, basePos, 9, 8, 5, yellowCoral);
		addBlock(world, basePos, 0, 9, 1, yellowCoral);
		addBlock(world, basePos, 0, 9, 5, yellowCoral);
		addBlock(world, basePos, 0, 9, 9, yellowCoral);
		addBlock(world, basePos, 1, 9, 8, yellowCoral);
		addBlock(world, basePos, 4, 9, 0, yellowCoral);
		addBlock(world, basePos, 4, 9, 1, yellowCoral);
		addBlock(world, basePos, 4, 9, 9, yellowCoral);
		addBlock(world, basePos, 4, 9, 10, yellowCoral);
		addBlock(world, basePos, 8, 9, 1, yellowCoral);
		addBlock(world, basePos, 8, 9, 9, yellowCoral);
		addBlock(world, basePos, 9, 9, 5, yellowCoral);
		addBlock(world, basePos, 10, 9, 5, yellowCoral);
		addBlock(world, basePos, 0, 10, 1, yellowCoral);
		addBlock(world, basePos, 0, 10, 5, yellowCoral);
		addBlock(world, basePos, 0, 10, 9, yellowCoral);
		addBlock(world, basePos, 4, 10, 0, yellowCoral);
		addBlock(world, basePos, 4, 10, 10, yellowCoral);
		addBlock(world, basePos, 8, 10, 1, yellowCoral);
		addBlock(world, basePos, 8, 10, 9, yellowCoral);
		addBlock(world, basePos, 10, 10, 5, yellowCoral);
	}
}
