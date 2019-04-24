package net.nevermine.structures.iromine;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SilvroFloater2 extends WorldGenerator {

	public SilvroFloater2() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 3, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 4, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 1, j + 2, k + 0, Blockizer.SilvroBox);
		world.setBlock(i + 1, j + 4, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 2, j + 1, k + 0, Blockizer.SilvroBox);
		world.setBlock(i + 2, j + 2, k + 0, Blockizer.LeavesSilvro);
		world.setBlock(i + 2, j + 3, k + 0, Blockizer.SilvroBox);
		world.setBlock(i + 2, j + 4, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 3, j + 2, k + 0, Blockizer.SilvroBox);
		world.setBlock(i + 3, j + 4, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 1, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 2, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 3, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 4, j + 4, k + 0, Blockizer.LeavesIrodust);

		return true;
	}
}