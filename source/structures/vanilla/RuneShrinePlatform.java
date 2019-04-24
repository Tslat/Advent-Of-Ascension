package net.nevermine.structures.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class RuneShrinePlatform extends WorldGenerator {
	private int post;
	Block postBlock;

	public RuneShrinePlatform(final int whichpost) {
		post = whichpost;
	}

	public boolean generate(final World world, final Random rand, final int i, final int j, final int k) {
		if (post == 1) {
			postBlock = Blockizer.RunePostFire;
		}
		else if (post == 2) {
			postBlock = Blockizer.RunePostWater;
		}
		else if (post == 3) {
			postBlock = Blockizer.RunePostLife;
		}
		else if (post == 4) {
			postBlock = Blockizer.RunePostEnergy;
		}
		else if (post == 5) {
			postBlock = Blockizer.RunePostPower;
		}
		else if (post == 6) {
			postBlock = Blockizer.RunePostLunar;
		}
		else if (post == 7) {
			postBlock = Blockizer.RunePostKinetic;
		}
		else if (post == 8) {
			postBlock = Blockizer.RunePostStorm;
		}
		else if (post == 9) {
			postBlock = Blockizer.RunePostDistortion;
		}
		else if (post == 10) {
			postBlock = Blockizer.RunePostStrike;
		}
		else if (post == 11) {
			postBlock = Blockizer.RunePostPoison;
		}
		else if (post == 12) {
			postBlock = Blockizer.RunePostWither;
		}
		else if (post == 13) {
			postBlock = Blockizer.RunePostCompass;
		}
		else {
			postBlock = Blockizer.RunePostWind;
		}
		world.setBlock(i + 0, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 1, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 1, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 2, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 2, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 0, j + 3, k + 0, postBlock);
		world.setBlock(i + 0, j + 3, k + 6, postBlock);
		world.setBlock(i + 1, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 1, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 1, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 1, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 1, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 1, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 1, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 2, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 3, j + 1, k + 3, Blockizer.RuneShrine);
		world.setBlock(i + 4, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 4, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 4, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 4, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 4, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 4, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 4, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 5, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 1, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 2, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 3, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 4, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 5, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 0, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 1, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 1, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 2, k + 0, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 2, k + 6, Blockizer.DarkBricks);
		world.setBlock(i + 6, j + 3, k + 0, postBlock);
		world.setBlock(i + 6, j + 3, k + 6, postBlock);
		return true;
	}
}
