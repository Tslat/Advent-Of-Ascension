package net.tslat.aoa3.structure.barathos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SmallBaronRock3 extends AoAStructure { //StructureSize: 4x1x2
	private static final IBlockState baronStone = BlockRegister.BARON_STONE.getDefaultState();

	public SmallBaronRock3() {
		super("SmallBaronRock3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 1, baronStone);
		addBlock(world, basePos, 1, 0, 0, baronStone);
		addBlock(world, basePos, 1, 0, 1, baronStone);
		addBlock(world, basePos, 2, 0, 0, baronStone);
		addBlock(world, basePos, 2, 0, 1, baronStone);
		addBlock(world, basePos, 3, 0, 0, baronStone);
		addBlock(world, basePos, 3, 0, 1, baronStone);
	}
}
