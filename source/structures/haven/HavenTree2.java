package net.nevermine.structures.haven;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class HavenTree2 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 2, j - 1, k + 2) != Blockizer.GrassHaven) {
			return false;
		}
		if (world.getBlock(i + 2, j - 0, k + 2) != Blocks.air) {
			return false;
		}
		world.setBlock(i + 0, j + 5, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 0, j + 6, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 0, j + 11, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 0, j + 11, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 0, j + 11, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 0, j + 11, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 0, j + 11, k + 4, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 4, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 5, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 6, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 9, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 10, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 10, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 10, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 11, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 11, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 11, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 11, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 11, k + 4, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 12, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 12, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 1, j + 12, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 0, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 1, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 2, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 3, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 4, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 4, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 4, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 5, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 5, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 5, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 5, k + 4, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 6, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 6, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 6, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 6, k + 4, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 7, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 7, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 7, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 8, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 9, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 9, k + 2, Blocks.log);
		world.setBlock(i + 2, j + 9, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 10, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 10, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 10, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 11, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 11, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 11, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 11, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 11, k + 4, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 12, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 12, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 12, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 2, j + 13, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 4, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 5, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 5, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 6, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 6, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 7, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 9, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 10, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 10, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 10, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 11, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 11, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 11, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 11, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 11, k + 4, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 12, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 12, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 3, j + 12, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 5, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 6, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 11, k + 0, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 11, k + 1, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 11, k + 2, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 11, k + 3, Blockizer.HavenLeavesBlue);
		world.setBlock(i + 4, j + 11, k + 4, Blockizer.HavenLeavesBlue);
		return true;
	}
}
