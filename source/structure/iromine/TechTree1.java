package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class TechTree1 extends AoAStructure { //StructureSize: 3x9x3
	private static final IBlockState log = BlockRegister.IRO_LOG.getDefaultState();
	private static final IBlockState glass = BlockRegister.IRO_GLASS.getDefaultState();
	private static final IBlockState iroCrate = BlockRegister.IRO_CRATE.getDefaultState();

	public TechTree1() {
		super("TechTree1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, log);
		addBlock(world, basePos, 1, 1, 1, log);
		addBlock(world, basePos, 1, 2, 1, iroCrate);
		addBlock(world, basePos, 1, 3, 1, iroCrate);
		addBlock(world, basePos, 1, 4, 1, iroCrate);
		addBlock(world, basePos, 1, 5, 1, iroCrate);
		addBlock(world, basePos, 0, 6, 1, log);
		addBlock(world, basePos, 1, 6, 0, log);
		addBlock(world, basePos, 1, 6, 1, log);
		addBlock(world, basePos, 1, 6, 2, log);
		addBlock(world, basePos, 2, 6, 1, log);
		addBlock(world, basePos, 0, 7, 0, glass);
		addBlock(world, basePos, 0, 7, 1, glass);
		addBlock(world, basePos, 0, 7, 2, glass);
		addBlock(world, basePos, 1, 7, 0, glass);
		addBlock(world, basePos, 1, 7, 1, glass);
		addBlock(world, basePos, 1, 7, 2, glass);
		addBlock(world, basePos, 2, 7, 0, glass);
		addBlock(world, basePos, 2, 7, 1, glass);
		addBlock(world, basePos, 2, 7, 2, glass);
		addBlock(world, basePos, 0, 8, 0, glass);
		addBlock(world, basePos, 0, 8, 1, glass);
		addBlock(world, basePos, 0, 8, 2, glass);
		addBlock(world, basePos, 1, 8, 0, glass);
		addBlock(world, basePos, 1, 8, 1, glass);
		addBlock(world, basePos, 1, 8, 2, glass);
		addBlock(world, basePos, 2, 8, 0, glass);
		addBlock(world, basePos, 2, 8, 1, glass);
		addBlock(world, basePos, 2, 8, 2, glass);
	}
}
