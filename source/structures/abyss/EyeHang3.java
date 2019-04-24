package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class EyeHang3 extends WorldGenerator {

	public EyeHang3() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 5, k + 0, Blockizer.WoodBlood);
		world.setBlock(i + 0, j + 5, k + 1, Blockizer.WoodBlood);
		world.setBlock(i + 0, j + 5, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 0, j + 5, k + 3, Blockizer.WoodBlood);
		world.setBlock(i + 0, j + 5, k + 4, Blockizer.WoodBlood);
		world.setBlock(i + 1, j + 5, k + 0, Blockizer.WoodBlood);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.EyeBlock);
		world.setBlock(i + 1, j + 5, k + 2, Blockizer.EyeBlock);
		world.setBlock(i + 1, j + 5, k + 3, Blockizer.EyeBlock);
		world.setBlock(i + 1, j + 5, k + 4, Blockizer.WoodBlood);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.WoodBlood);
		world.setBlock(i + 1, j + 6, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 1, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 2, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 3, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 4, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 5, k + 0, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.EyeBlock);
		world.setBlock(i + 2, j + 5, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 5, k + 3, Blockizer.EyeBlock);
		world.setBlock(i + 2, j + 5, k + 4, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 6, k + 1, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 6, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 2, j + 6, k + 3, Blockizer.WoodBlood);
		world.setBlock(i + 3, j + 5, k + 0, Blockizer.WoodBlood);
		world.setBlock(i + 3, j + 5, k + 1, Blockizer.EyeBlock);
		world.setBlock(i + 3, j + 5, k + 2, Blockizer.EyeBlock);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.EyeBlock);
		world.setBlock(i + 3, j + 5, k + 4, Blockizer.WoodBlood);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.WoodBlood);
		world.setBlock(i + 3, j + 6, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 3, j + 6, k + 3, Blockizer.WoodBlood);
		world.setBlock(i + 4, j + 5, k + 0, Blockizer.WoodBlood);
		world.setBlock(i + 4, j + 5, k + 1, Blockizer.WoodBlood);
		world.setBlock(i + 4, j + 5, k + 2, Blockizer.WoodBlood);
		world.setBlock(i + 4, j + 5, k + 3, Blockizer.WoodBlood);
		world.setBlock(i + 4, j + 5, k + 4, Blockizer.WoodBlood);

		return true;
	}
}