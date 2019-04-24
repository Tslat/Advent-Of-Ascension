package net.tslat.aoa3.structure.barathos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SmallBaronRock4 extends AoAStructure { //StructureSize: 2x1x3
	private static final IBlockState baronStone = BlockRegister.stoneBaron.getDefaultState();

	public SmallBaronRock4() {
		super("SmallBaronRock4");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, baronStone);
		addBlock(world, basePos, 0, 0, 1, baronStone);
		addBlock(world, basePos, 0, 0, 2, baronStone);
		addBlock(world, basePos, 1, 0, 0, baronStone);
		addBlock(world, basePos, 1, 0, 1, baronStone);
		addBlock(world, basePos, 1, 0, 2, baronStone);
	}
}
