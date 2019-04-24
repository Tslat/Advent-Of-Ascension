package net.nevermine.structures.crystevia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;
import net.nevermine.npc.entity.EntityCrystalTrader;

import java.util.Random;

public class CrystalTradingPost extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		boolean run = true;
		int y = j;
		while (run) {
			if (world.getBlock(i + 4, y - 1, k + 4) != Blockizer.CrysteviaRock) {
				world.setBlock(i + 4, y - 1, k + 4, Blockizer.CrystalBlockRed);
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
		world.setBlock(i + 0, j + 0, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.CrystalBlockRed);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 1, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 1, k + 7, Blockizer.CrystalBlockRed);
		world.setBlock(i + 0, j + 1, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 2, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 2, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 2, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 0, j + 2, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 3, Blockizer.CrystalBlockRed);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.CrystalBlockRed);
		world.setBlock(i + 1, j + 0, k + 5, Blockizer.CrystalBlockRed);
		world.setBlock(i + 1, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.CrystalBlockRed);
		world.setBlock(i + 1, j + 1, k + 8, Blockizer.CrystalBlockRed);
		world.setBlock(i + 1, j + 2, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 1, j + 2, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 4, Blockizer.CrystalBlockRed);
		world.setBlock(i + 2, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 1, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 1, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 2, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 2, j + 2, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.CrystalBlockRed);
		world.setBlock(i + 3, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 3, j + 0, k + 7, Blockizer.CrystalBlockRed);
		world.setBlock(i + 3, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.CrystalBlockRed);
		world.setBlock(i + 4, j + 0, k + 2, Blockizer.CrystalBlockRed);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 4, j + 0, k + 6, Blockizer.CrystalBlockRed);
		world.setBlock(i + 4, j + 0, k + 7, Blockizer.CrystalBlockRed);
		world.setBlock(i + 4, j + 0, k + 8, Blockizer.CrystevianBricks);
		if (!world.isRemote) {
			final EntityCrystalTrader var2 = new EntityCrystalTrader(world);
			var2.setLocationAndAngles((double)(i + 4), (double)(j + 2), (double)(k + 4), rand.nextFloat() * 360.0f, 0.0f);
			world.spawnEntityInWorld(var2);
		}
		world.setBlock(i + 5, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 1, Blockizer.CrystalBlockRed);
		world.setBlock(i + 5, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 5, j + 0, k + 7, Blockizer.CrystalBlockRed);
		world.setBlock(i + 5, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 4, Blockizer.CrystalBlockRed);
		world.setBlock(i + 6, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 1, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 1, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 2, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 6, j + 2, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 0, k + 3, Blockizer.CrystalBlockRed);
		world.setBlock(i + 7, j + 0, k + 4, Blockizer.CrystalBlockRed);
		world.setBlock(i + 7, j + 0, k + 5, Blockizer.CrystalBlockRed);
		world.setBlock(i + 7, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 0, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 1, k + 0, Blockizer.CrystalBlockRed);
		world.setBlock(i + 7, j + 1, k + 8, Blockizer.CrystalBlockRed);
		world.setBlock(i + 7, j + 2, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 7, j + 2, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 3, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 4, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 5, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 0, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 1, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 1, k + 1, Blockizer.CrystalBlockRed);
		world.setBlock(i + 8, j + 1, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 1, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 1, k + 7, Blockizer.CrystalBlockRed);
		world.setBlock(i + 8, j + 1, k + 8, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 2, k + 0, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 2, k + 1, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 2, k + 2, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 2, k + 6, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 2, k + 7, Blockizer.CrystevianBricks);
		world.setBlock(i + 8, j + 2, k + 8, Blockizer.CrystevianBricks);
		return true;
	}
}
