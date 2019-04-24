package net.nevermine.structures.iromine;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SilvroFloater3 extends WorldGenerator {

	public SilvroFloater3() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.SilvroBox);
		world.setBlock(i + 0, j + 1, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.SilvroBox);
		world.setBlock(i + 0, j + 2, k + 2, Blockizer.LeavesSilvro);
		world.setBlock(i + 0, j + 2, k + 3, Blockizer.SilvroBox);
		world.setBlock(i + 0, j + 2, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 3, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 3, k + 2, Blockizer.SilvroBox);
		world.setBlock(i + 0, j + 3, k + 4, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 4, k + 0, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 4, k + 1, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 4, k + 2, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 4, k + 3, Blockizer.LeavesIrodust);
		world.setBlock(i + 0, j + 4, k + 4, Blockizer.LeavesIrodust);

		return true;
	}
}