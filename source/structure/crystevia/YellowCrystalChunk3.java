package net.tslat.aoa3.structure.crystevia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class YellowCrystalChunk3 extends AoAStructure { //StructureSize: 3x7x3
	private static final IBlockState yellowCrystal = BlockRegister.crystalYellow.getDefaultState();

	public YellowCrystalChunk3() {
		super("YellowCrystalChunk3");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, yellowCrystal);
		addBlock(world, basePos, 0, 0, 1, yellowCrystal);
		addBlock(world, basePos, 0, 0, 2, yellowCrystal);
		addBlock(world, basePos, 0, 1, 0, yellowCrystal);
		addBlock(world, basePos, 0, 1, 1, yellowCrystal);
		addBlock(world, basePos, 0, 1, 2, yellowCrystal);
		addBlock(world, basePos, 0, 2, 0, yellowCrystal);
		addBlock(world, basePos, 0, 2, 1, yellowCrystal);
		addBlock(world, basePos, 0, 2, 2, yellowCrystal);
		addBlock(world, basePos, 0, 3, 1, yellowCrystal);
		addBlock(world, basePos, 0, 4, 1, yellowCrystal);
		addBlock(world, basePos, 1, 0, 0, yellowCrystal);
		addBlock(world, basePos, 1, 0, 1, yellowCrystal);
		addBlock(world, basePos, 1, 0, 2, yellowCrystal);
		addBlock(world, basePos, 1, 1, 0, yellowCrystal);
		addBlock(world, basePos, 1, 1, 1, yellowCrystal);
		addBlock(world, basePos, 1, 1, 2, yellowCrystal);
		addBlock(world, basePos, 1, 2, 0, yellowCrystal);
		addBlock(world, basePos, 1, 2, 1, yellowCrystal);
		addBlock(world, basePos, 1, 2, 2, yellowCrystal);
		addBlock(world, basePos, 1, 3, 0, yellowCrystal);
		addBlock(world, basePos, 1, 3, 1, yellowCrystal);
		addBlock(world, basePos, 1, 3, 2, yellowCrystal);
		addBlock(world, basePos, 1, 4, 0, yellowCrystal);
		addBlock(world, basePos, 1, 4, 1, yellowCrystal);
		addBlock(world, basePos, 1, 4, 2, yellowCrystal);
		addBlock(world, basePos, 1, 5, 1, yellowCrystal);
		addBlock(world, basePos, 1, 6, 1, yellowCrystal);
		addBlock(world, basePos, 2, 0, 0, yellowCrystal);
		addBlock(world, basePos, 2, 0, 1, yellowCrystal);
		addBlock(world, basePos, 2, 0, 2, yellowCrystal);
		addBlock(world, basePos, 2, 1, 0, yellowCrystal);
		addBlock(world, basePos, 2, 1, 1, yellowCrystal);
		addBlock(world, basePos, 2, 1, 2, yellowCrystal);
		addBlock(world, basePos, 2, 2, 0, yellowCrystal);
		addBlock(world, basePos, 2, 2, 1, yellowCrystal);
		addBlock(world, basePos, 2, 2, 2, yellowCrystal);
		addBlock(world, basePos, 2, 3, 1, yellowCrystal);
		addBlock(world, basePos, 2, 4, 1, yellowCrystal);
	}
}
