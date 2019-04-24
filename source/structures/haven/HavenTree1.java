package net.nevermine.structures.haven;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class HavenTree1 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 3, j - 1, k + 3) != Blockizer.GrassHaven) {
			return false;
		}
		if (world.getBlock(i + 3, j - 0, k + 3) != Blocks.air) {
			return false;
		}
		world.setBlock(i + 0, j + 4, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 0, j + 5, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 0, j + 6, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 1, j + 6, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 1, j + 6, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 1, j + 7, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 4, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 5, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 5, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 5, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 6, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 6, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 6, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 6, k + 5, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 7, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 7, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 7, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 2, j + 8, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 0, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 1, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 2, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 3, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 4, k + 0, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 4, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 4, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 4, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 4, k + 6, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 5, k + 0, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 5, k + 1, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 5, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 5, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 5, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 5, k + 5, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 5, k + 6, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 6, k + 0, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 6, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 6, k + 3, Blocks.log);
		world.setBlock(i + 3, j + 6, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 6, k + 5, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 6, k + 6, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 7, k + 1, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 7, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 7, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 7, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 7, k + 5, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 8, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 8, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 8, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 3, j + 9, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 4, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 5, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 5, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 5, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 6, k + 1, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 6, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 6, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 6, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 6, k + 5, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 7, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 7, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 7, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 4, j + 7, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 5, j + 5, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 5, j + 6, k + 2, Blockizer.HavenLeavesPink);
		world.setBlock(i + 5, j + 6, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 5, j + 6, k + 4, Blockizer.HavenLeavesPink);
		world.setBlock(i + 5, j + 7, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 6, j + 4, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 6, j + 5, k + 3, Blockizer.HavenLeavesPink);
		world.setBlock(i + 6, j + 6, k + 3, Blockizer.HavenLeavesPink);
		return true;
	}
}
