package net.nevermine.structures.mysterium;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class MysticFernPatch extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (world.getBlock(i + 0, j - 1, k + 0) != Blockizer.GrassMysterium && world.getBlock(i + 0, j - 1, k + 0) != Blockizer.DirtMysterium) {
			return false;
		}
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.MysticFerns);
		return true;
	}
}
