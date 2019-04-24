package net.nevermine.structures.greckon;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class SkullPost extends WorldGenerator {

	public SkullPost() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 0, k + 0, Blocks.fence);
		world.setBlock(i + 0, j + 1, k + 0, Blocks.fence);
		if (rand.nextInt(3) == 1)
			world.setBlock(i + 0, j + 2, k + 0, Blockizer.SkullBlock);
		else if (rand.nextInt(2) == 1)
			world.setBlock(i + 0, j + 2, k + 0, Blockizer.SkullBlockDark);
		else
			world.setBlock(i + 0, j + 2, k + 0, Blockizer.HauntedOrb);

		return true;
	}
}