package net.nevermine.structures.dustopia;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class DustTree2 extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 1, j - 1, k + 1) != Blockizer.GrassDustopia && world.getBlock(i + 1, j - 1, k + 1) != Blockizer.DirtDustopia) {
			return false;
		}
		world.setBlock(i + 0, j + 2, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 0, j + 6, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 0, j + 12, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 0, j + 14, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 0, j + 18, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 1, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 2, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 3, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 3, k + 2, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 4, k + 0, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 4, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 5, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 6, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 8, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 9, k + 0, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 9, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 10, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 11, k + 0, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 11, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 11, k + 2, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 12, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 13, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 14, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 14, k + 2, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 15, k + 0, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 15, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 16, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 17, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 17, k + 2, Blockizer.LeavesDawn);
		world.setBlock(i + 1, j + 18, k + 1, Blockizer.WoodDawn);
		world.setBlock(i + 1, j + 19, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 2, j + 2, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 2, j + 5, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 2, j + 9, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 2, j + 12, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 2, j + 16, k + 1, Blockizer.LeavesDawn);
		world.setBlock(i + 2, j + 18, k + 1, Blockizer.LeavesDawn);
		return true;
	}
}
