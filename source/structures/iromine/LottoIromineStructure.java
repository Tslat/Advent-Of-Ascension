package net.nevermine.structures.iromine;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.npc.entity.lottoman.EntityLottomanIromine;

import java.util.Random;

public class LottoIromineStructure extends WorldGenerator {

	public LottoIromineStructure() {
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 14, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 14, k + 2, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 14, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 14, k + 4, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 14, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 15, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 15, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 15, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 15, k + 3, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 15, k + 4, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 15, k + 5, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 15, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 16, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 16, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 17, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 17, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 0, j + 18, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 0, j + 18, k + 6, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 8, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 9, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 1, j + 10, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 1, j + 11, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 1, j + 12, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 1, j + 13, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 13, k + 2, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 13, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 13, k + 4, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 13, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 1, j + 15, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 1, j + 15, k + 6, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 8, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 2, j + 13, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 2, j + 13, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 13, k + 3, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 13, k + 4, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 13, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 2, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 2, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 2, j + 15, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 2, j + 15, k + 6, Blockizer.IroGlass);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 1, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 2, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 3, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 4, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 6, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 7, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 8, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 8, k + 2, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 8, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 8, k + 4, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 8, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 9, k + 1, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 9, k + 5, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 10, k + 1, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 10, k + 5, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 11, k + 1, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 11, k + 5, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 12, k + 1, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 12, k + 5, SpecialBlockizer.Iropole);
		world.setBlock(i + 3, j + 13, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 13, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 3, j + 13, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 13, k + 4, Blockizer.IroGlass);
		world.setBlock(i + 3, j + 13, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 3, j + 15, k + 0, Blockizer.IroGlass);
		if (!world.isRemote) {
			EntityLottomanIromine var2 = new EntityLottomanIromine(world);
			var2.setLocationAndAngles(i + 3, j + 15, k + 3, rand.nextFloat() * 360.0F, 0.0F);
			world.spawnEntityInWorld(var2);
		}
		world.setBlock(i + 3, j + 15, k + 6, Blockizer.IroGlass);
		world.setBlock(i + 4, j + 8, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 4, j + 13, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 4, j + 13, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 4, j + 13, k + 3, Blockizer.IroGlass);
		world.setBlock(i + 4, j + 13, k + 4, Blockizer.IroGlass);
		world.setBlock(i + 4, j + 13, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 4, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 4, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 4, j + 15, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 4, j + 15, k + 6, Blockizer.IroGlass);
		world.setBlock(i + 5, j + 8, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 9, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 5, j + 10, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 5, j + 11, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 5, j + 12, k + 3, SpecialBlockizer.Iropole);
		world.setBlock(i + 5, j + 13, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 13, k + 2, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 13, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 13, k + 4, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 13, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 5, j + 15, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 5, j + 15, k + 6, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 14, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 14, k + 1, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 14, k + 2, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 14, k + 3, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 14, k + 4, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 14, k + 5, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 14, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 15, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 15, k + 1, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 15, k + 2, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 15, k + 3, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 15, k + 4, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 15, k + 5, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 15, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 16, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 16, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 17, k + 0, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 17, k + 6, Blockizer.IrobrickStriped);
		world.setBlock(i + 6, j + 18, k + 0, Blockizer.IroGlass);
		world.setBlock(i + 6, j + 18, k + 6, Blockizer.IroGlass);

		return true;
	}
}