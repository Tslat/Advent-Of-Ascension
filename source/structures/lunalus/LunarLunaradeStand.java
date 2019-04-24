package net.nevermine.structures.lunalus;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;
import net.nevermine.npc.entity.zal.EntityZalLunarade;

import java.util.Random;

public class LunarLunaradeStand extends WorldGenerator {
	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 0, k + 5, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 0, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 1, k + 1, Blocks.stained_glass);
		world.setBlock(i + 0, j + 1, k + 2, Blocks.stained_glass);
		world.setBlock(i + 0, j + 1, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 1, k + 4, Blocks.stained_glass);
		world.setBlock(i + 0, j + 1, k + 5, Blocks.stained_glass);
		world.setBlock(i + 0, j + 1, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 2, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 3, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 4, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 0, j + 5, k + 3, Blockizer.LampLunar);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 0, k + 5, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 0, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 1, j + 1, k + 0, Blocks.stained_glass);
		world.setBlock(i + 1, j + 1, k + 6, Blocks.stained_glass);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 0, k + 5, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 0, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 2, j + 1, k + 0, Blocks.stained_glass);
		world.setBlock(i + 2, j + 1, k + 6, Blocks.stained_glass);
		if (!world.isRemote) {
			final EntityZalLunarade var2 = new EntityZalLunarade(world);
			var2.setLocationAndAngles((double)(i + 2), (double)(j + 2), (double)(k + 3), rand.nextFloat() * 360.0f, 0.0f);
			world.spawnEntityInWorld(var2);
		}
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 0, k + 5, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 0, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 3, j + 1, k + 0, Blocks.stained_glass);
		world.setBlock(i + 3, j + 1, k + 2, Blockizer.planksLunide);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.planksLunide);
		world.setBlock(i + 3, j + 1, k + 4, Blockizer.planksLunide);
		world.setBlock(i + 3, j + 1, k + 6, Blocks.stained_glass);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 0, k + 5, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 0, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 4, j + 1, k + 0, Blocks.stained_glass);
		world.setBlock(i + 4, j + 1, k + 6, Blocks.stained_glass);
		world.setBlock(i + 5, j + 0, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 0, k + 5, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 0, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 1, k + 0, Blockizer.LunarBricks);
		world.setBlock(i + 5, j + 1, k + 6, Blockizer.LunarBricks);
		world.setBlock(i + 6, j + 0, k + 1, Blockizer.LunarBricks);
		world.setBlock(i + 6, j + 0, k + 2, Blockizer.LunarBricks);
		world.setBlock(i + 6, j + 0, k + 3, Blockizer.LunarBricks);
		world.setBlock(i + 6, j + 0, k + 4, Blockizer.LunarBricks);
		world.setBlock(i + 6, j + 0, k + 5, Blockizer.LunarBricks);
		return true;
	}
}
