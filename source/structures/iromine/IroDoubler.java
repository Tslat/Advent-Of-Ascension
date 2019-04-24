package net.nevermine.structures.iromine;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class IroDoubler extends WorldGenerator {

	public IroDoubler() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 1, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 1, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 6, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 6, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 6, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 6, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 0, j + 6, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 0, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 1, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 6, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 6, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 1, j + 7, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 1, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 1, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 1, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 2, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 3, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 4, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 5, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 6, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 6, k + 2, Blockizer.WoodIromine);
		world.setBlock(i + 2, j + 6, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 7, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 7, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 2, j + 7, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 0, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 1, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 1, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 6, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 6, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 7, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 7, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 3, j + 7, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 1, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 1, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 1, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 1, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 1, k + 4, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 6, k + 0, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 6, k + 1, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 6, k + 2, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 6, k + 3, Blockizer.LeavesIrogold);
		world.setBlock(i + 4, j + 6, k + 4, Blockizer.LeavesIrogold);

		return true;
	}
}