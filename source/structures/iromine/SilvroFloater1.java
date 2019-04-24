package net.nevermine.structures.iromine;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SilvroFloater1 extends WorldGenerator {

	public SilvroFloater1() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.SilvroBox);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.SilvroBox);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.LeavesSilvro);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.SilvroBox);
		world.setBlock(i + 2, j + 0, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 3, j + 0, k + 2, Blockizer.SilvroBox);
		world.setBlock(i + 3, j + 0, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 0, k + 2, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.LeavesIrodust);

		return true;
	}
}