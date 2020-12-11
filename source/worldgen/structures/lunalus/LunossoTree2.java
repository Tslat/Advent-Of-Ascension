package net.tslat.aoa3.worldgen.structures.lunalus;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class LunossoTree2 extends AoAStructure { //StructureSize: 5x9x5
	private static final BlockState lunossoLeaves = AoABlocks.LUNOSSO_LEAVES.get().getDefaultState();
	private static final BlockState lunideLog = AoABlocks.LUNIDE_LOG.get().getDefaultState();

	public LunossoTree2() {
		super("LunossoTree2");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, lunideLog);
		addBlock(world, basePos, 2, 1, 2, lunideLog);
		addBlock(world, basePos, 2, 2, 2, lunideLog);
		addBlock(world, basePos, 2, 3, 2, lunideLog);
		addBlock(world, basePos, 0, 5, 2, lunossoLeaves);
		addBlock(world, basePos, 1, 5, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 5, 0, lunossoLeaves);
		addBlock(world, basePos, 2, 5, 1, lunossoLeaves);
		addBlock(world, basePos, 2, 5, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 5, 3, lunossoLeaves);
		addBlock(world, basePos, 2, 5, 4, lunossoLeaves);
		addBlock(world, basePos, 3, 5, 2, lunossoLeaves);
		addBlock(world, basePos, 4, 5, 2, lunossoLeaves);
		addBlock(world, basePos, 0, 6, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 6, 0, lunossoLeaves);
		addBlock(world, basePos, 2, 6, 4, lunossoLeaves);
		addBlock(world, basePos, 4, 6, 2, lunossoLeaves);
		addBlock(world, basePos, 0, 7, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 7, 0, lunossoLeaves);
		addBlock(world, basePos, 2, 7, 4, lunossoLeaves);
		addBlock(world, basePos, 4, 7, 2, lunossoLeaves);
		addBlock(world, basePos, 0, 8, 2, lunossoLeaves);
		addBlock(world, basePos, 1, 8, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 8, 0, lunossoLeaves);
		addBlock(world, basePos, 2, 8, 1, lunossoLeaves);
		addBlock(world, basePos, 2, 8, 2, lunossoLeaves);
		addBlock(world, basePos, 2, 8, 3, lunossoLeaves);
		addBlock(world, basePos, 2, 8, 4, lunossoLeaves);
		addBlock(world, basePos, 3, 8, 2, lunossoLeaves);
		addBlock(world, basePos, 4, 8, 2, lunossoLeaves);
	}
}
