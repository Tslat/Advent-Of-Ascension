package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class ShadowlordSpawn extends WorldGenerator {
	protected int[] GetValidSpawnBlocks() {
		return new int[0];
	}

	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 1, j + 5, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 1, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 1, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 2, k + 0, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 2, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 4, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 4, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 5, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 6, k + 0, Blockizer.BloodstoneBricks);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 1, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 1, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 2, k + 0, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 2, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 3, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 3, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 4, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 4, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 6, k + 0, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 7, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 7, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 7, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 7, k + 4, Blockizer.BloodstoneBricks);
		world.setBlock(i + 3, j + 7, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 3, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 3, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 7, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 7, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 7, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 7, k + 4, Blockizer.BloodstoneBricks);
		world.setBlock(i + 4, j + 7, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 5, j + 7, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 5, j + 7, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 5, j + 7, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 5, j + 7, k + 4, Blockizer.BloodstoneBricks);
		world.setBlock(i + 5, j + 7, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 6, j + 7, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 6, j + 7, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 6, j + 7, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 6, j + 7, k + 4, Blockizer.BloodstoneBricks);
		world.setBlock(i + 6, j + 7, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 6, j + 8, k + 4, Blockizer.ShadowAltar);
		world.setBlock(i + 7, j + 1, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 2, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 3, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 4, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 5, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 6, k + 5, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 7, k + 1, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 7, k + 2, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 7, k + 3, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 7, k + 4, Blockizer.BloodstoneBricks);
		world.setBlock(i + 7, j + 7, k + 5, Blockizer.BloodstoneBricks);
		return true;
	}
}
