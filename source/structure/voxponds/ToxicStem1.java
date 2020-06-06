package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ToxicStem1 extends AoAStructure { //StructureSize: 1x4x2
	private static final IBlockState toxicStem = BlockRegister.TOXIC_STEM.getDefaultState();

	public ToxicStem1() {
		super("ToxicStem1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 1, toxicStem);
		addBlock(world, basePos, 0, 1, 1, toxicStem);
		addBlock(world, basePos, 0, 2, 0, toxicStem);
		addBlock(world, basePos, 0, 2, 1, toxicStem);
		addBlock(world, basePos, 0, 3, 0, toxicStem);
	}
}
