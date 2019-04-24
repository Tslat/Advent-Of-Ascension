package net.tslat.aoa3.structure.deeplands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GiantDeepshroom3 extends AoAStructure { //StructureSize: 7x8x7 variable
	private static final IBlockState mushroom = BlockRegister.mushroomBlack.getDefaultState();
	private static final IBlockState mushroomStem = BlockRegister.mushroomStemBlack.getDefaultState();

	public GiantDeepshroom3() {
		super("GiantDeepshroom3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int tiers = 1 + rand.nextInt(3);
		
		while (tiers > 0) {
			addBlock(world, basePos, 3, 0, 3, mushroomStem);
			addBlock(world, basePos, 3, 1, 3, mushroomStem);
			addBlock(world, basePos, 3, 2, 3, mushroomStem);
			addBlock(world, basePos, 3, 3, 3, mushroomStem);
			addBlock(world, basePos, 0, 4, 2, mushroom);
			addBlock(world, basePos, 0, 4, 3, mushroom);
			addBlock(world, basePos, 0, 4, 4, mushroom);
			addBlock(world, basePos, 1, 4, 1, mushroom);
			addBlock(world, basePos, 1, 4, 2, mushroom);
			addBlock(world, basePos, 1, 4, 3, mushroom);
			addBlock(world, basePos, 1, 4, 4, mushroom);
			addBlock(world, basePos, 1, 4, 5, mushroom);
			addBlock(world, basePos, 2, 4, 0, mushroom);
			addBlock(world, basePos, 2, 4, 1, mushroom);
			addBlock(world, basePos, 2, 4, 2, mushroom);
			addBlock(world, basePos, 2, 4, 3, mushroom);
			addBlock(world, basePos, 2, 4, 4, mushroom);
			addBlock(world, basePos, 2, 4, 5, mushroom);
			addBlock(world, basePos, 2, 4, 6, mushroom);
			addBlock(world, basePos, 3, 4, 0, mushroom);
			addBlock(world, basePos, 3, 4, 1, mushroom);
			addBlock(world, basePos, 3, 4, 2, mushroom);
			addBlock(world, basePos, 3, 4, 3, mushroomStem);
			addBlock(world, basePos, 3, 4, 4, mushroom);
			addBlock(world, basePos, 3, 4, 5, mushroom);
			addBlock(world, basePos, 3, 4, 6, mushroom);
			addBlock(world, basePos, 4, 4, 0, mushroom);
			addBlock(world, basePos, 4, 4, 1, mushroom);
			addBlock(world, basePos, 4, 4, 2, mushroom);
			addBlock(world, basePos, 4, 4, 3, mushroom);
			addBlock(world, basePos, 4, 4, 4, mushroom);
			addBlock(world, basePos, 4, 4, 5, mushroom);
			addBlock(world, basePos, 4, 4, 6, mushroom);
			addBlock(world, basePos, 5, 4, 1, mushroom);
			addBlock(world, basePos, 5, 4, 2, mushroom);
			addBlock(world, basePos, 5, 4, 3, mushroom);
			addBlock(world, basePos, 5, 4, 4, mushroom);
			addBlock(world, basePos, 5, 4, 5, mushroom);
			addBlock(world, basePos, 6, 4, 2, mushroom);
			addBlock(world, basePos, 6, 4, 3, mushroom);
			addBlock(world, basePos, 6, 4, 4, mushroom);
			addBlock(world, basePos, 3, 5, 3, mushroomStem);
			addBlock(world, basePos, 3, 6, 3, mushroomStem);
			addBlock(world, basePos, 1, 7, 2, mushroom);
			addBlock(world, basePos, 1, 7, 3, mushroom);
			addBlock(world, basePos, 1, 7, 4, mushroom);
			addBlock(world, basePos, 2, 7, 1, mushroom);
			addBlock(world, basePos, 2, 7, 2, mushroom);
			addBlock(world, basePos, 2, 7, 3, mushroom);
			addBlock(world, basePos, 2, 7, 4, mushroom);
			addBlock(world, basePos, 2, 7, 5, mushroom);
			addBlock(world, basePos, 3, 7, 1, mushroom);
			addBlock(world, basePos, 3, 7, 2, mushroom);
			addBlock(world, basePos, 3, 7, 3, mushroomStem);
			addBlock(world, basePos, 3, 7, 4, mushroom);
			addBlock(world, basePos, 3, 7, 5, mushroom);
			addBlock(world, basePos, 4, 7, 1, mushroom);
			addBlock(world, basePos, 4, 7, 2, mushroom);
			addBlock(world, basePos, 4, 7, 3, mushroom);
			addBlock(world, basePos, 4, 7, 4, mushroom);
			addBlock(world, basePos, 4, 7, 5, mushroom);
			addBlock(world, basePos, 5, 7, 2, mushroom);
			addBlock(world, basePos, 5, 7, 3, mushroom);
			addBlock(world, basePos, 5, 7, 4, mushroom);

			tiers--;
			basePos = basePos.up(8);
		}
	}
}
