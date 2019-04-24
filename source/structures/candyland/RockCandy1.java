package net.nevermine.structures.candyland;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class RockCandy1 extends WorldGenerator {
	public RockCandy1() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {
		Block candy;
		int pick = rand.nextInt(4);
		if (pick == 1)
			candy = Blockizer.RockCandyBlue;
		else if (pick == 2)
			candy = Blockizer.RockCandyPink;
		else if (pick == 3)
			candy = Blockizer.RockCandyPurple;
		else
			candy = Blockizer.RockCandyGreen;

		world.setBlock(i + 0, j + 0, k + 1, candy);
		world.setBlock(i + 0, j + 2, k + 2, candy);
		world.setBlock(i + 0, j + 3, k + 1, candy);
		world.setBlock(i + 0, j + 4, k + 2, candy);
		world.setBlock(i + 0, j + 6, k + 1, candy);
		world.setBlock(i + 1, j + 0, k + 1, candy);
		world.setBlock(i + 1, j + 0, k + 2, candy);
		world.setBlock(i + 1, j + 0, k + 3, candy);
		world.setBlock(i + 1, j + 1, k + 1, candy);
		world.setBlock(i + 1, j + 1, k + 2, candy);
		world.setBlock(i + 1, j + 2, k + 0, candy);
		world.setBlock(i + 1, j + 2, k + 1, candy);
		world.setBlock(i + 1, j + 2, k + 2, candy);
		world.setBlock(i + 1, j + 3, k + 1, candy);
		world.setBlock(i + 1, j + 3, k + 2, candy);
		world.setBlock(i + 1, j + 4, k + 0, candy);
		world.setBlock(i + 1, j + 4, k + 1, candy);
		world.setBlock(i + 1, j + 4, k + 2, candy);
		world.setBlock(i + 1, j + 5, k + 1, candy);
		world.setBlock(i + 1, j + 5, k + 2, candy);
		world.setBlock(i + 1, j + 5, k + 3, candy);
		world.setBlock(i + 1, j + 6, k + 1, candy);
		world.setBlock(i + 1, j + 6, k + 2, candy);
		world.setBlock(i + 2, j + 0, k + 1, candy);
		world.setBlock(i + 2, j + 0, k + 2, candy);
		world.setBlock(i + 2, j + 1, k + 0, candy);
		world.setBlock(i + 2, j + 1, k + 1, candy);
		world.setBlock(i + 2, j + 1, k + 2, candy);
		world.setBlock(i + 2, j + 2, k + 1, candy);
		world.setBlock(i + 2, j + 2, k + 2, candy);
		world.setBlock(i + 2, j + 2, k + 3, candy);
		world.setBlock(i + 2, j + 3, k + 1, candy);
		world.setBlock(i + 2, j + 3, k + 2, candy);
		world.setBlock(i + 2, j + 4, k + 1, candy);
		world.setBlock(i + 2, j + 4, k + 2, candy);
		world.setBlock(i + 2, j + 4, k + 3, candy);
		world.setBlock(i + 2, j + 5, k + 0, candy);
		world.setBlock(i + 2, j + 5, k + 1, candy);
		world.setBlock(i + 2, j + 5, k + 2, candy);
		world.setBlock(i + 2, j + 6, k + 1, candy);
		world.setBlock(i + 2, j + 6, k + 2, candy);
		world.setBlock(i + 2, j + 7, k + 2, candy);
		world.setBlock(i + 3, j + 1, k + 2, candy);
		world.setBlock(i + 3, j + 3, k + 1, candy);
		world.setBlock(i + 3, j + 5, k + 2, candy);

		return true;
	}
}