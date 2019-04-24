package net.nevermine.structures.candyland;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;
import net.nevermine.npc.entity.lottoman.EntityLottomanCandyland;

import java.util.Random;

public class CandylandLottomanStructure extends WorldGenerator {

	public CandylandLottomanStructure() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 10, k + 2, Blockizer.CandylandRedCandy);
		world.setBlock(i + 0, j + 10, k + 10, Blockizer.CandylandRedCandy);
		world.setBlock(i + 0, j + 11, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 0, j + 11, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 1, j + 11, k + 2, Blockizer.CandylandRedCandy);
		world.setBlock(i + 1, j + 11, k + 10, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 3, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 5, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 7, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 9, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 9, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 9, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 10, k + 0, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 10, k + 2, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 10, k + 10, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 10, k + 12, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 11, k + 0, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 11, k + 1, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 11, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 11, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 2, j + 11, k + 11, Blockizer.CandylandRedCandy);
		world.setBlock(i + 2, j + 11, k + 12, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 3, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 3, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 4, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 5, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 7, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 8, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 3, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 4, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 5, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 7, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 4, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 4, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 5, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 5, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 5, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 5, j + 8, k + 5, Blockizer.CandylandRedCandy);
		world.setBlock(i + 5, j + 8, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 5, j + 8, k + 7, Blockizer.CandylandRedCandy);
		world.setBlock(i + 5, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 5, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 5, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 0, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 1, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 2, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 3, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 4, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 5, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 6, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 7, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 8, k + 5, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 8, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 8, k + 7, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 6, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 6, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		if (!world.isRemote) {
			EntityLottomanCandyland var2 = new EntityLottomanCandyland(world);
			var2.setLocationAndAngles(i + 6, j + 10, k + 6, rand.nextFloat() * 360.0F, 0.0F);
			world.spawnEntityInWorld(var2);
		}
		world.setBlock(i + 7, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 7, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 7, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 7, j + 8, k + 5, Blockizer.CandylandRedCandy);
		world.setBlock(i + 7, j + 8, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 7, j + 8, k + 7, Blockizer.CandylandRedCandy);
		world.setBlock(i + 7, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 7, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 7, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 8, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 5, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 7, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 8, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 8, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 9, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 9, j + 8, k + 3, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 4, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 5, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 6, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 7, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 8, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 9, Blockizer.CandylandRedCandy);
		world.setBlock(i + 9, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 3, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 4, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 5, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 6, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 7, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 8, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 9, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 8, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 9, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 9, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 10, k + 0, Blockizer.CandylandRedCandy);
		world.setBlock(i + 10, j + 10, k + 2, Blockizer.CandylandRedCandy);
		world.setBlock(i + 10, j + 10, k + 10, Blockizer.CandylandRedCandy);
		world.setBlock(i + 10, j + 10, k + 12, Blockizer.CandylandRedCandy);
		world.setBlock(i + 10, j + 11, k + 0, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 11, k + 1, Blockizer.CandylandRedCandy);
		world.setBlock(i + 10, j + 11, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 11, k + 10, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 10, j + 11, k + 11, Blockizer.CandylandRedCandy);
		world.setBlock(i + 10, j + 11, k + 12, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 11, j + 11, k + 2, Blockizer.CandylandRedCandy);
		world.setBlock(i + 11, j + 11, k + 10, Blockizer.CandylandRedCandy);
		world.setBlock(i + 12, j + 10, k + 2, Blockizer.CandylandRedCandy);
		world.setBlock(i + 12, j + 10, k + 10, Blockizer.CandylandRedCandy);
		world.setBlock(i + 12, j + 11, k + 2, Blockizer.CandylandWhiteCandy);
		world.setBlock(i + 12, j + 11, k + 10, Blockizer.CandylandWhiteCandy);

		return true;
	}
}