package net.tslat.aoa3.structure.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class TechTree3 extends AoAStructure { //StructureSize: 5x12x5
	private static final IBlockState log = BlockRegister.IRO_LOG.getDefaultState();
	private static final IBlockState glass = BlockRegister.IRO_GLASS.getDefaultState();
	private static final IBlockState iroCrate = BlockRegister.IRO_CRATE.getDefaultState();

	public TechTree3() {
		super("TechTree3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, log);
		addBlock(world, basePos, 2, 1, 2, log);
		addBlock(world, basePos, 2, 2, 2, log);
		addBlock(world, basePos, 2, 3, 2, log);
		addBlock(world, basePos, 0, 4, 2, log);
		addBlock(world, basePos, 1, 4, 2, log);
		addBlock(world, basePos, 2, 4, 0, log);
		addBlock(world, basePos, 2, 4, 1, log);
		addBlock(world, basePos, 2, 4, 2, log);
		addBlock(world, basePos, 2, 4, 3, log);
		addBlock(world, basePos, 2, 4, 4, log);
		addBlock(world, basePos, 3, 4, 2, log);
		addBlock(world, basePos, 4, 4, 2, log);
		addBlock(world, basePos, 0, 5, 2, iroCrate);
		addBlock(world, basePos, 2, 5, 0, iroCrate);
		addBlock(world, basePos, 2, 5, 2, log);
		addBlock(world, basePos, 2, 5, 4, iroCrate);
		addBlock(world, basePos, 4, 5, 2, iroCrate);
		addBlock(world, basePos, 0, 6, 2, iroCrate);
		addBlock(world, basePos, 2, 6, 0, iroCrate);
		addBlock(world, basePos, 2, 6, 2, log);
		addBlock(world, basePos, 2, 6, 4, iroCrate);
		addBlock(world, basePos, 4, 6, 2, iroCrate);
		addBlock(world, basePos, 0, 7, 2, iroCrate);
		addBlock(world, basePos, 2, 7, 0, iroCrate);
		addBlock(world, basePos, 2, 7, 2, log);
		addBlock(world, basePos, 2, 7, 4, iroCrate);
		addBlock(world, basePos, 4, 7, 2, iroCrate);
		addBlock(world, basePos, 0, 8, 2, log);
		addBlock(world, basePos, 1, 8, 2, log);
		addBlock(world, basePos, 2, 8, 0, log);
		addBlock(world, basePos, 2, 8, 1, log);
		addBlock(world, basePos, 2, 8, 2, log);
		addBlock(world, basePos, 2, 8, 3, log);
		addBlock(world, basePos, 2, 8, 4, log);
		addBlock(world, basePos, 3, 8, 2, log);
		addBlock(world, basePos, 4, 8, 2, log);
		addBlock(world, basePos, 2, 9, 2, log);
		addBlock(world, basePos, 1, 10, 1, glass);
		addBlock(world, basePos, 1, 10, 2, glass);
		addBlock(world, basePos, 1, 10, 3, glass);
		addBlock(world, basePos, 2, 10, 1, glass);
		addBlock(world, basePos, 2, 10, 2, glass);
		addBlock(world, basePos, 2, 10, 3, glass);
		addBlock(world, basePos, 3, 10, 1, glass);
		addBlock(world, basePos, 3, 10, 2, glass);
		addBlock(world, basePos, 3, 10, 3, glass);
		addBlock(world, basePos, 1, 11, 1, glass);
		addBlock(world, basePos, 1, 11, 2, glass);
		addBlock(world, basePos, 1, 11, 3, glass);
		addBlock(world, basePos, 2, 11, 1, glass);
		addBlock(world, basePos, 2, 11, 2, glass);
		addBlock(world, basePos, 2, 11, 3, glass);
		addBlock(world, basePos, 3, 11, 1, glass);
		addBlock(world, basePos, 3, 11, 2, glass);
		addBlock(world, basePos, 3, 11, 3, glass);
	}
}
