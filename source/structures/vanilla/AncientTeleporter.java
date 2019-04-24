package net.nevermine.structures.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Blockizer;
import net.nevermine.npc.entity.EntityCorruptedTraveller;

import java.util.Random;

public class AncientTeleporter extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		boolean bool1 = nevermine.rand.nextBoolean();
		boolean bool2 = nevermine.rand.nextBoolean();
		boolean bool3 = nevermine.rand.nextBoolean();
		boolean bool4 = nevermine.rand.nextBoolean();

		for (int x = i; x <= i + 10; x++) {
			for (int y = j; y <= j + 6; y++) {
				for (int z = k; z <= k + 15; z++) {
					world.setBlock(x, y, z, Blocks.air);
				}
			}
		}

		world.setBlock(i + 0, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 0, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 7, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 0, j + 0, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 0, k + 13, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 0, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 4, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 0, j + 1, k + 5, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 0, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 12, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 0, j + 1, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 2, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 2, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 2, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 2, k + 12, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 0, j + 3, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 0, j + 3, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 1, j + 0, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 8, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 1, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 1, k + 0, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 1, j + 1, k + 6, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 1, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 1, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 5, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 2, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 11, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 2, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 1, k + 14, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 2, j + 2, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 2, j + 2, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 3, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 6, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 3, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 11, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 3, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 0, k + 13, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 3, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 5, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 3, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 9, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 3, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 3, j + 1, k + 14, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 4, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 5, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 4, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 8, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 4, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 0, k + 14, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 4, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 4, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 4, j + 1, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 7, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 4, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 9, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 4, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 2, k + 4, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 4, j + 2, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 2, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 2, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 2, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 2, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 4, j + 2, k + 10, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 5, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 1, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 5, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 3, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 5, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 7, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 5, j + 0, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 12, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 5, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 0, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 5, j + 1, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 6, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 5, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 8, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 5, j + 1, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 1, k + 14, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 5, j + 2, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 2, k + 5, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 5, j + 2, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 2, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 2, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 2, k + 9, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 5, j + 2, k + 10, Blockizer.AncientRock);
		if (rand.nextInt(10) == 0)
			world.setBlock(i + 5, j + 3, k + 5, Blockizer.CarvedRune6);
		world.setBlock(i + 5, j + 3, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 3, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 3, k + 8, Blockizer.AncientRock);
		if (rand.nextInt(10) == 0)
			world.setBlock(i + 5, j + 3, k + 9, Blockizer.CarvedRune3);
		world.setBlock(i + 5, j + 4, k + 5, Blockizer.AncientRock);
		if (!world.isRemote) {
			final EntityCorruptedTraveller var2 = new EntityCorruptedTraveller(world);
			var2.setLocationAndAngles((double)(i + 5), (double)(j + 4), (double)(k + 7), rand.nextFloat() * 360.0f, 0.0f);
			world.spawnEntityInWorld(var2);
		}
		world.setBlock(i + 5, j + 4, k + 9, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 5, j + 5, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 5, k + 9, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 5, j + 6, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 6, k + 9, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 5, j + 7, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 7, k + 9, Blockizer.AncientRock);
		if (rand.nextInt(10) == 0)
			world.setBlock(i + 5, j + 8, k + 5, Blockizer.CarvedRune1);
		world.setBlock(i + 5, j + 8, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 8, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 5, j + 8, k + 8, Blockizer.AncientRock);
		if (rand.nextInt(10) == 0)
			world.setBlock(i + 5, j + 8, k + 9, Blockizer.CarvedRune2);
		if (bool4)
			world.setBlock(i + 6, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 3, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 6, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 8, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 6, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 0, k + 12, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 6, j + 0, k + 13, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 6, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 3, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 6, j + 1, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 7, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 6, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 1, k + 14, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 6, j + 2, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 2, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 2, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 2, k + 7, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 6, j + 2, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 2, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 6, j + 2, k + 10, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 7, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 3, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 7, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 6, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 7, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 9, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 7, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 12, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 7, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 0, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 7, j + 1, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 5, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 7, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 9, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 7, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 7, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 1, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 8, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 6, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 8, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 8, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 8, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 0, k + 14, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 8, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 2, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 8, j + 2, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 3, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 9, j + 0, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 8, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 9, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 0, k + 13, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 9, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 9, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 0, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 10, j + 0, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 4, Blockizer.AncientRock);
		if (bool2)
			world.setBlock(i + 10, j + 0, k + 5, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 9, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 10, Blockizer.AncientRock);
		if (bool4)
			world.setBlock(i + 10, j + 0, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 13, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 0, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 0, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 1, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 3, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 4, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 5, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 10, j + 1, k + 6, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 7, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 8, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 9, Blockizer.AncientRock);
		if (bool1)
			world.setBlock(i + 10, j + 1, k + 10, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 11, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 12, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 1, k + 13, Blockizer.AncientRock);
		if (bool3)
			world.setBlock(i + 10, j + 1, k + 14, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 2, k + 2, Blockizer.AncientRock);
		world.setBlock(i + 10, j + 2, k + 12, Blockizer.AncientRock);
		return true;
	}
}
