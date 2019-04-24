package net.nevermine.structures.mysterium;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.SpecialBlockizer;

import java.util.Random;

public class MiniMushroom extends WorldGenerator {
	private int pick;

	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		int y = j;
		if (world.getBlock(i, j - 1, k) == Blocks.water) {
			world.setBlock(i, j - 1, k, Blockizer.DirtMysterium);
			world.setBlock(i, j - 2, k, Blockizer.DirtMysterium);
		}

		while (y < j + 2) {
			world.setBlock(i, y, k, SpecialBlockizer.ShroomStem);

			if (rand.nextBoolean() || y == j + 1) {
				pick = rand.nextInt(5);
				if (pick == 1) {
					world.setBlock(i, y + 1, k, SpecialBlockizer.BlueShroom);
					break;
				}
				if (pick == 2) {
					world.setBlock(i, y + 1, k, SpecialBlockizer.GreenShroom);
					break;
				}
				if (pick == 3) {
					world.setBlock(i, y + 1, k, SpecialBlockizer.YellowShroom);
					break;
				}
				if (pick == 4) {
					world.setBlock(i, y + 1, k, SpecialBlockizer.OrangeShroom);
					break;
				}
				world.setBlock(i, y + 1, k, SpecialBlockizer.OrangeShroom);
				break;
			}
			else {
				++y;
			}
		}
		return true;
	}
}
