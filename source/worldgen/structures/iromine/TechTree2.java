package net.tslat.aoa3.worldgen.structures.iromine;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TechTree2 extends AoAStructure { //StructureSize: 5x8x5
	private static final BlockState log = AoABlocks.IROLOG.get().getDefaultState();
	private static final BlockState glass = AoABlocks.IRO_GLASS.get().getDefaultState();
	private static final BlockState iroCrate = AoABlocks.IRO_CRATE.get().getDefaultState();

	public TechTree2() {
		super("TechTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, log);
		addBlock(world, basePos, 2, 1, 2, log);
		addBlock(world, basePos, 2, 2, 2, log);
		addBlock(world, basePos, 0, 3, 2, log);
		addBlock(world, basePos, 1, 3, 2, log);
		addBlock(world, basePos, 2, 3, 0, log);
		addBlock(world, basePos, 2, 3, 1, log);
		addBlock(world, basePos, 2, 3, 2, log);
		addBlock(world, basePos, 2, 3, 3, log);
		addBlock(world, basePos, 2, 3, 4, log);
		addBlock(world, basePos, 3, 3, 2, log);
		addBlock(world, basePos, 4, 3, 2, log);
		addBlock(world, basePos, 0, 4, 2, iroCrate);
		addBlock(world, basePos, 1, 4, 1, glass);
		addBlock(world, basePos, 1, 4, 2, glass);
		addBlock(world, basePos, 1, 4, 3, glass);
		addBlock(world, basePos, 2, 4, 0, iroCrate);
		addBlock(world, basePos, 2, 4, 1, glass);
		addBlock(world, basePos, 2, 4, 2, glass);
		addBlock(world, basePos, 2, 4, 3, glass);
		addBlock(world, basePos, 2, 4, 4, iroCrate);
		addBlock(world, basePos, 3, 4, 1, glass);
		addBlock(world, basePos, 3, 4, 2, glass);
		addBlock(world, basePos, 3, 4, 3, glass);
		addBlock(world, basePos, 4, 4, 2, iroCrate);
		addBlock(world, basePos, 0, 5, 2, iroCrate);
		addBlock(world, basePos, 1, 5, 1, glass);
		addBlock(world, basePos, 1, 5, 2, glass);
		addBlock(world, basePos, 1, 5, 3, glass);
		addBlock(world, basePos, 2, 5, 0, iroCrate);
		addBlock(world, basePos, 2, 5, 1, glass);
		addBlock(world, basePos, 2, 5, 2, glass);
		addBlock(world, basePos, 2, 5, 3, glass);
		addBlock(world, basePos, 2, 5, 4, iroCrate);
		addBlock(world, basePos, 3, 5, 1, glass);
		addBlock(world, basePos, 3, 5, 2, glass);
		addBlock(world, basePos, 3, 5, 3, glass);
		addBlock(world, basePos, 4, 5, 2, iroCrate);
		addBlock(world, basePos, 0, 6, 2, iroCrate);
		addBlock(world, basePos, 1, 6, 1, glass);
		addBlock(world, basePos, 1, 6, 2, glass);
		addBlock(world, basePos, 1, 6, 3, glass);
		addBlock(world, basePos, 2, 6, 0, iroCrate);
		addBlock(world, basePos, 2, 6, 1, glass);
		addBlock(world, basePos, 2, 6, 2, glass);
		addBlock(world, basePos, 2, 6, 3, glass);
		addBlock(world, basePos, 2, 6, 4, iroCrate);
		addBlock(world, basePos, 3, 6, 1, glass);
		addBlock(world, basePos, 3, 6, 2, glass);
		addBlock(world, basePos, 3, 6, 3, glass);
		addBlock(world, basePos, 4, 6, 2, iroCrate);
		addBlock(world, basePos, 0, 7, 2, log);
		addBlock(world, basePos, 1, 7, 2, log);
		addBlock(world, basePos, 2, 7, 0, log);
		addBlock(world, basePos, 2, 7, 1, log);
		addBlock(world, basePos, 2, 7, 2, log);
		addBlock(world, basePos, 2, 7, 3, log);
		addBlock(world, basePos, 2, 7, 4, log);
		addBlock(world, basePos, 3, 7, 2, log);
		addBlock(world, basePos, 4, 7, 2, log);
	}
}
