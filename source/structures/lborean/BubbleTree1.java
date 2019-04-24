package net.nevermine.structures.lborean;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BubbleTree1 extends WorldGenerator {

	public BubbleTree1() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 7, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 7, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 7, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 8, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 8, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 8, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 9, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 9, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 0, j + 9, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 4, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 7, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.CoralBlue);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 8, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 8, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 8, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 9, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 9, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 1, j + 9, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 7, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 7, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 7, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 8, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 8, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 8, k + 2, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 9, k + 0, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 9, k + 1, Blockizer.LeavesBubble);
		world.setBlock(i + 2, j + 9, k + 2, Blockizer.LeavesBubble);

		return true;
	}
}