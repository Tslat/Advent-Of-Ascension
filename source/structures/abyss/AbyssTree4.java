package net.nevermine.structures.abyss;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class AbyssTree4 extends WorldGenerator {
	protected int[] GetValidSpawnBlocks() {
		return new int[0];
	}

	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		world.setBlock(i + 0, j + 3, k + 3, Blockizer.EyeBlock);
		world.setBlock(i + 0, j + 4, k + 3, Blockizer.BloodStrands);
		world.setBlock(i + 0, j + 5, k + 3, Blockizer.BloodStrands);
		world.setBlock(i + 0, j + 6, k + 3, Blockizer.BloodStrands);
		world.setBlock(i + 0, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 0, j + 7, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 0, j + 7, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 1, j + 6, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 1, j + 7, k + 1, Blockizer.LeavesShadowblood);
		world.setBlock(i + 1, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 1, j + 7, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 1, j + 7, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 1, j + 7, k + 5, Blockizer.LeavesShadowblood);
		world.setBlock(i + 1, j + 8, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 6, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 2, j + 7, k + 0, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 7, k + 1, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 7, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 7, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 7, k + 5, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 7, k + 6, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 8, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 8, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 2, j + 8, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 2, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 3, k + 0, Blockizer.EyeBlock);
		world.setBlock(i + 3, j + 3, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 3, k + 6, Blockizer.EyeBlock);
		world.setBlock(i + 3, j + 4, k + 0, Blockizer.BloodStrands);
		world.setBlock(i + 3, j + 4, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 4, k + 6, Blockizer.BloodStrands);
		world.setBlock(i + 3, j + 5, k + 0, Blockizer.BloodStrands);
		world.setBlock(i + 3, j + 5, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 5, k + 6, Blockizer.BloodStrands);
		world.setBlock(i + 3, j + 6, k + 0, Blockizer.BloodStrands);
		world.setBlock(i + 3, j + 6, k + 1, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 6, k + 2, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 6, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 6, k + 4, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 6, k + 5, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 6, k + 6, Blockizer.BloodStrands);
		world.setBlock(i + 3, j + 7, k + 0, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 7, k + 1, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 7, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 7, k + 5, Blockizer.WoodShadow);
		world.setBlock(i + 3, j + 7, k + 6, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 8, k + 1, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 8, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 8, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 8, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 8, k + 5, Blockizer.LeavesShadowblood);
		world.setBlock(i + 3, j + 9, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 6, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 4, j + 7, k + 0, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 7, k + 1, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 7, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 7, k + 5, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 7, k + 6, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 8, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 8, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 4, j + 8, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 5, j + 6, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 5, j + 7, k + 1, Blockizer.LeavesShadowblood);
		world.setBlock(i + 5, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 5, j + 7, k + 3, Blockizer.WoodShadow);
		world.setBlock(i + 5, j + 7, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 5, j + 7, k + 5, Blockizer.LeavesShadowblood);
		world.setBlock(i + 5, j + 8, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 6, j + 3, k + 3, Blockizer.EyeBlock);
		world.setBlock(i + 6, j + 4, k + 3, Blockizer.BloodStrands);
		world.setBlock(i + 6, j + 5, k + 3, Blockizer.BloodStrands);
		world.setBlock(i + 6, j + 6, k + 3, Blockizer.BloodStrands);
		world.setBlock(i + 6, j + 7, k + 2, Blockizer.LeavesShadowblood);
		world.setBlock(i + 6, j + 7, k + 3, Blockizer.LeavesShadowblood);
		world.setBlock(i + 6, j + 7, k + 4, Blockizer.LeavesShadowblood);
		world.setBlock(i + 7, j + 7, k + 3, Blockizer.LeavesShadowblood);
		return true;
	}
}
