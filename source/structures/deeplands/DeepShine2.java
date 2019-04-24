package net.nevermine.structures.deeplands;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class DeepShine2 extends WorldGenerator {

	public DeepShine2() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 3, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 3, k + 1, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 3, k + 2, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 3, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 6, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 6, k + 1, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 6, k + 2, Blockizer.Deepshine);
		world.setBlock(i + 0, j + 6, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 1, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 2, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 3, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 3, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 3, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 1, j + 4, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 4, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 5, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 6, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 6, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 1, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 1, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 2, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 2, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 3, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 2, j + 3, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 3, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 3, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 2, j + 4, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 4, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 5, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 6, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 6, k + 2, Blockizer.DeepRock);
		world.setBlock(i + 2, j + 6, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 3, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 3, k + 1, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 3, k + 2, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 3, k + 3, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 6, k + 0, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 6, k + 2, Blockizer.Deepshine);
		world.setBlock(i + 3, j + 6, k + 3, Blockizer.Deepshine);

		return true;
	}
}