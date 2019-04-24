package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class ShadeBush1 extends WorldGenerator {

	public ShadeBush1() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.LeavesShadow);

		return true;
	}
}