package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BubbleTree3 extends AoAStructure { //StructureSize: 3x13x3
	private static final IBlockState bubbleLeaves = BlockRegister.leavesBubble.getDefaultState();
	private static final IBlockState blueCoral = BlockRegister.coralBlue.getDefaultState();

	public BubbleTree3() {
		super("BubbleTree3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, blueCoral);
		addBlock(world, basePos, 1, 1, 1, blueCoral);
		addBlock(world, basePos, 1, 2, 1, blueCoral);
		addBlock(world, basePos, 1, 3, 1, blueCoral);
		addBlock(world, basePos, 1, 4, 1, blueCoral);
		addBlock(world, basePos, 0, 5, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 5, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 5, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 5, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 5, 1, blueCoral);
		addBlock(world, basePos, 1, 5, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 5, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 5, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 5, 2, bubbleLeaves);
		addBlock(world, basePos, 0, 6, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 6, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 6, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 6, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 6, 1, blueCoral);
		addBlock(world, basePos, 1, 6, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 6, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 6, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 6, 2, bubbleLeaves);
		addBlock(world, basePos, 0, 7, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 7, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 7, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 7, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 7, 1, blueCoral);
		addBlock(world, basePos, 1, 7, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 7, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 7, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 7, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 8, 1, blueCoral);
		addBlock(world, basePos, 1, 9, 1, blueCoral);
		addBlock(world, basePos, 0, 10, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 10, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 10, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 10, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 10, 1, blueCoral);
		addBlock(world, basePos, 1, 10, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 10, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 10, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 10, 2, bubbleLeaves);
		addBlock(world, basePos, 0, 11, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 11, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 11, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 11, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 11, 1, blueCoral);
		addBlock(world, basePos, 1, 11, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 11, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 11, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 11, 2, bubbleLeaves);
		addBlock(world, basePos, 0, 12, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 12, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 12, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 12, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 12, 1, bubbleLeaves);
		addBlock(world, basePos, 1, 12, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 12, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 12, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 12, 2, bubbleLeaves);
	}
}
