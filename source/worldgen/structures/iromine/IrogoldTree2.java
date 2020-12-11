package net.tslat.aoa3.worldgen.structures.iromine;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class IrogoldTree2 extends AoAStructure { //StructureSize: 7x10x7
	private static final BlockState irogoldLeaves = AoABlocks.IROGOLD_LEAVES.get().getDefaultState();
	private static final BlockState log = AoABlocks.IROLOG.get().getDefaultState();

	public IrogoldTree2() {
		super("IrogoldTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, log);
		addBlock(world, basePos, 3, 1, 3, log);
		addBlock(world, basePos, 3, 2, 3, log);
		addBlock(world, basePos, 3, 3, 3, log);
		addBlock(world, basePos, 3, 4, 3, log);
		addBlock(world, basePos, 3, 5, 3, log);
		addBlock(world, basePos, 2, 6, 3, log);
		addBlock(world, basePos, 3, 6, 2, log);
		addBlock(world, basePos, 3, 6, 3, log);
		addBlock(world, basePos, 3, 6, 4, log);
		addBlock(world, basePos, 4, 6, 3, log);
		addBlock(world, basePos, 0, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 1, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 1, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 1, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 1, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 2, 7, 5, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 0, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 1, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 5, irogoldLeaves);
		addBlock(world, basePos, 3, 7, 6, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 1, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 4, 7, 5, irogoldLeaves);
		addBlock(world, basePos, 5, 7, 2, irogoldLeaves);
		addBlock(world, basePos, 5, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 5, 7, 4, irogoldLeaves);
		addBlock(world, basePos, 6, 7, 3, irogoldLeaves);
		addBlock(world, basePos, 1, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 2, 8, 2, irogoldLeaves);
		addBlock(world, basePos, 2, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 2, 8, 4, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 1, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 2, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 4, irogoldLeaves);
		addBlock(world, basePos, 3, 8, 5, irogoldLeaves);
		addBlock(world, basePos, 4, 8, 2, irogoldLeaves);
		addBlock(world, basePos, 4, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 4, 8, 4, irogoldLeaves);
		addBlock(world, basePos, 5, 8, 3, irogoldLeaves);
		addBlock(world, basePos, 2, 9, 3, irogoldLeaves);
		addBlock(world, basePos, 3, 9, 2, irogoldLeaves);
		addBlock(world, basePos, 3, 9, 3, irogoldLeaves);
		addBlock(world, basePos, 3, 9, 4, irogoldLeaves);
		addBlock(world, basePos, 4, 9, 3, irogoldLeaves);
	}
}
