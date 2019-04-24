package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class ShadeBush3 extends WorldGenerator {

	public ShadeBush3() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 2, k + 2, Blockizer.LeavesShadow);
		world.setBlock(i + 0, j + 2, k + 3, Blockizer.LeavesShadow);

		return true;
	}
}