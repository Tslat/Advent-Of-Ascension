package net.tslat.aoa3.structure.lborean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class BubbleTree2 extends AoAStructure { //StructureSize: 3x11x3
	private static final IBlockState bubbleLeaves = BlockRegister.leavesBubble.getDefaultState();
	private static final IBlockState blueCoral = BlockRegister.coralBlue.getDefaultState();

	public BubbleTree2() {
		super("BubbleTree2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, blueCoral);
		addBlock(world, basePos, 1, 1, 1, blueCoral);
		addBlock(world, basePos, 1, 2, 1, blueCoral);
		addBlock(world, basePos, 1, 3, 1, blueCoral);
		addBlock(world, basePos, 1, 4, 1, blueCoral);
		addBlock(world, basePos, 1, 5, 1, blueCoral);
		addBlock(world, basePos, 1, 6, 1, blueCoral);
		addBlock(world, basePos, 1, 7, 1, blueCoral);
		addBlock(world, basePos, 0, 8, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 8, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 8, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 8, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 8, 1, blueCoral);
		addBlock(world, basePos, 1, 8, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 8, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 8, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 8, 2, bubbleLeaves);
		addBlock(world, basePos, 0, 9, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 9, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 9, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 9, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 9, 1, bubbleLeaves);
		addBlock(world, basePos, 1, 9, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 9, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 9, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 9, 2, bubbleLeaves);
		addBlock(world, basePos, 0, 10, 0, bubbleLeaves);
		addBlock(world, basePos, 0, 10, 1, bubbleLeaves);
		addBlock(world, basePos, 0, 10, 2, bubbleLeaves);
		addBlock(world, basePos, 1, 10, 0, bubbleLeaves);
		addBlock(world, basePos, 1, 10, 1, bubbleLeaves);
		addBlock(world, basePos, 1, 10, 2, bubbleLeaves);
		addBlock(world, basePos, 2, 10, 0, bubbleLeaves);
		addBlock(world, basePos, 2, 10, 1, bubbleLeaves);
		addBlock(world, basePos, 2, 10, 2, bubbleLeaves);
	}
}
