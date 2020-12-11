package net.tslat.aoa3.worldgen.structures.lunalus;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class LunarRuneShrine extends AoAStructure { //StructureSize: 7x4x7
	private static final BlockState darkBricks = AoABlocks.DARK_BRICKS.get().getDefaultState();
	private static final BlockState darkBrickSlab = AoABlocks.DARK_BRICKS_SLAB.get().getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM);
	private static final BlockState post = AoABlocks.LUNAR_RUNE_POST.get().getDefaultState();

	public LunarRuneShrine() {
		super("LunarRuneShrine");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, darkBricks);
		addBlock(world, basePos, 0, 0, 1, darkBricks);
		addBlock(world, basePos, 0, 0, 2, darkBricks);
		addBlock(world, basePos, 0, 0, 3, darkBricks);
		addBlock(world, basePos, 0, 0, 4, darkBricks);
		addBlock(world, basePos, 0, 0, 5, darkBricks);
		addBlock(world, basePos, 0, 0, 6, darkBricks);
		addBlock(world, basePos, 1, 0, 0, darkBricks);
		addBlock(world, basePos, 1, 0, 1, darkBricks);
		addBlock(world, basePos, 1, 0, 2, darkBricks);
		addBlock(world, basePos, 1, 0, 3, darkBricks);
		addBlock(world, basePos, 1, 0, 4, darkBricks);
		addBlock(world, basePos, 1, 0, 5, darkBricks);
		addBlock(world, basePos, 1, 0, 6, darkBricks);
		addBlock(world, basePos, 2, 0, 0, darkBricks);
		addBlock(world, basePos, 2, 0, 1, darkBricks);
		addBlock(world, basePos, 2, 0, 2, darkBricks);
		addBlock(world, basePos, 2, 0, 3, darkBricks);
		addBlock(world, basePos, 2, 0, 4, darkBricks);
		addBlock(world, basePos, 2, 0, 5, darkBricks);
		addBlock(world, basePos, 2, 0, 6, darkBricks);
		addBlock(world, basePos, 3, 0, 0, darkBricks);
		addBlock(world, basePos, 3, 0, 1, darkBricks);
		addBlock(world, basePos, 3, 0, 2, darkBricks);
		addBlock(world, basePos, 3, 0, 3, darkBrickSlab);
		addBlock(world, basePos, 3, 0, 4, darkBricks);
		addBlock(world, basePos, 3, 0, 5, darkBricks);
		addBlock(world, basePos, 3, 0, 6, darkBricks);
		addBlock(world, basePos, 4, 0, 0, darkBricks);
		addBlock(world, basePos, 4, 0, 1, darkBricks);
		addBlock(world, basePos, 4, 0, 2, darkBricks);
		addBlock(world, basePos, 4, 0, 3, darkBricks);
		addBlock(world, basePos, 4, 0, 4, darkBricks);
		addBlock(world, basePos, 4, 0, 5, darkBricks);
		addBlock(world, basePos, 4, 0, 6, darkBricks);
		addBlock(world, basePos, 5, 0, 0, darkBricks);
		addBlock(world, basePos, 5, 0, 1, darkBricks);
		addBlock(world, basePos, 5, 0, 2, darkBricks);
		addBlock(world, basePos, 5, 0, 3, darkBricks);
		addBlock(world, basePos, 5, 0, 4, darkBricks);
		addBlock(world, basePos, 5, 0, 5, darkBricks);
		addBlock(world, basePos, 5, 0, 6, darkBricks);
		addBlock(world, basePos, 6, 0, 0, darkBricks);
		addBlock(world, basePos, 6, 0, 1, darkBricks);
		addBlock(world, basePos, 6, 0, 2, darkBricks);
		addBlock(world, basePos, 6, 0, 3, darkBricks);
		addBlock(world, basePos, 6, 0, 4, darkBricks);
		addBlock(world, basePos, 6, 0, 5, darkBricks);
		addBlock(world, basePos, 6, 0, 6, darkBricks);
		addBlock(world, basePos, 0, 1, 0, darkBricks);
		addBlock(world, basePos, 0, 1, 6, darkBricks);
		addBlock(world, basePos, 6, 1, 0, darkBricks);
		addBlock(world, basePos, 6, 1, 6, darkBricks);
		addBlock(world, basePos, 0, 2, 0, darkBricks);
		addBlock(world, basePos, 0, 2, 6, darkBricks);
		addBlock(world, basePos, 6, 2, 0, darkBricks);
		addBlock(world, basePos, 6, 2, 6, darkBricks);
		addBlock(world, basePos, 0, 3, 0, post);
		addBlock(world, basePos, 0, 3, 6, post);
		addBlock(world, basePos, 6, 3, 0, post);
		addBlock(world, basePos, 6, 3, 6, post);
	}
}
