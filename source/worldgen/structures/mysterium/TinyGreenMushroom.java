package net.tslat.aoa3.worldgen.structures.mysterium;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class TinyGreenMushroom extends AoAStructure {
	public TinyGreenMushroom() {
		super("TinyGreenMushroom");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		if (!world.getBlockState(basePos.down()).isOpaqueCube(world, basePos.down()))
			world.setBlockState(basePos.down(), AoABlocks.FUNGAL_DIRT.get().getDefaultState(), 2);

		if (rand.nextInt(3) == 0) {
			world.setBlockState(basePos, AoABlocks.SHROOM_STEM.get().getDefaultState(), 2);
			world.setBlockState(basePos.up(), AoABlocks.SHROOM_STEM.get().getDefaultState(), 2);
			world.setBlockState(basePos.up(2), AoABlocks.GREEN_SHROOM.get().getDefaultState(), 2);
		}
		else if (rand.nextBoolean()){
			world.setBlockState(basePos, AoABlocks.SHROOM_STEM.get().getDefaultState(), 2);
			world.setBlockState(basePos.up(), AoABlocks.GREEN_SHROOM.get().getDefaultState(), 2);
		}
		else {
			world.setBlockState(basePos, AoABlocks.GREEN_SHROOM.get().getDefaultState(), 2);
		}
	}
}
