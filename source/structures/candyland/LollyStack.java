package net.nevermine.structures.candyland;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class LollyStack extends WorldGenerator {
	public LollyStack() {
	}

	private int pick;

	public boolean generate(World world, Random rand, int i, int j, int k) {
		for (int y = j; y < j + 5; y++) {
			pick = rand.nextInt(3);
			if (pick == 2)
				world.setBlock(i, y, k, Blockizer.LollypopYellow);
			else if (pick == 1)
				world.setBlock(i, y, k, Blockizer.LollypopRed);
			else
				world.setBlock(i, y, k, Blockizer.LollypopBlue);
			if ((rand.nextInt(2) == 1) || y == j + 3) {
				break;
			}
		}
		return true;
	}
}