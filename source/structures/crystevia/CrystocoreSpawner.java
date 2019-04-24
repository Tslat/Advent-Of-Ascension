package net.nevermine.structures.crystevia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class CrystocoreSpawner extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		boolean run = true;
		int y = j;
		while (run) {
			if (world.getBlock(i + 3, y - 1, k + 3) != Blockizer.CrysteviaRock) {
				world.setBlock(i + 3, y - 1, k + 3, Blockizer.CrystalBlockRed);
				--y;
			}
			else {
				run = false;
			}
		}
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 1, j + 0, k + 3, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 1, j + 0, k + 5, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 1, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 2, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 5, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 2, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 3, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 3, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 3, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 3, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 5, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 3, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 1, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 1, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 2, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 2, k + 3, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 3, j + 2, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 3, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 3, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 3, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 4, k + 3, Blockizer.PowerStation);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 4, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 5, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 4, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 3, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 3, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 3, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 1, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 5, j + 0, k + 2, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 5, j + 0, k + 3, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 5, j + 0, k + 4, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 5, j + 0, k + 5, Blockizer.CrystalBlockPurple);
		world.setBlock(i + 5, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 2, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 1, k + 3, Blockizer.CrystevianBricks);
		return true;
	}
}
