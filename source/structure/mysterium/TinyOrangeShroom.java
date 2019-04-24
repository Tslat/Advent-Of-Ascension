package net.tslat.aoa3.structure.mysterium;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class TinyOrangeShroom extends AoAStructure {
	public TinyOrangeShroom() {
		super("TinyOrangeMushroom");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		if (!world.getBlockState(basePos.down()).isOpaqueCube()) {
			world.setBlockState(basePos.down(), BlockRegister.dirtMysterium.getDefaultState());
		}

		if (rand.nextInt(3) == 0) {
			world.setBlockState(basePos, BlockRegister.shroomStem.getDefaultState());
			world.setBlockState(basePos.up(), BlockRegister.shroomStem.getDefaultState());
			world.setBlockState(basePos.up(2), BlockRegister.shroomOrange.getDefaultState());
		}
		else if (rand.nextBoolean()){
			world.setBlockState(basePos, BlockRegister.shroomStem.getDefaultState());
			world.setBlockState(basePos.up(), BlockRegister.shroomOrange.getDefaultState());
		}
		else {
			world.setBlockState(basePos, BlockRegister.shroomOrange.getDefaultState());
		}
	}
}
