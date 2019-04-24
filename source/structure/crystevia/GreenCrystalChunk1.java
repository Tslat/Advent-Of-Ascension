package net.tslat.aoa3.structure.crystevia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GreenCrystalChunk1 extends AoAStructure { //StructureSize: 3x4x3
	private static final IBlockState greenCrystal = BlockRegister.crystalGreen.getDefaultState();

	public GreenCrystalChunk1() {
		super("GreenCrystalChunk1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, greenCrystal);
		addBlock(world, basePos, 0, 0, 1, greenCrystal);
		addBlock(world, basePos, 0, 0, 2, greenCrystal);
		addBlock(world, basePos, 0, 1, 0, greenCrystal);
		addBlock(world, basePos, 0, 1, 1, greenCrystal);
		addBlock(world, basePos, 0, 1, 2, greenCrystal);
		addBlock(world, basePos, 0, 2, 0, greenCrystal);
		addBlock(world, basePos, 0, 2, 1, greenCrystal);
		addBlock(world, basePos, 0, 2, 2, greenCrystal);
		addBlock(world, basePos, 1, 0, 0, greenCrystal);
		addBlock(world, basePos, 1, 0, 1, greenCrystal);
		addBlock(world, basePos, 1, 0, 2, greenCrystal);
		addBlock(world, basePos, 1, 1, 0, greenCrystal);
		addBlock(world, basePos, 1, 1, 1, greenCrystal);
		addBlock(world, basePos, 1, 1, 2, greenCrystal);
		addBlock(world, basePos, 1, 2, 0, greenCrystal);
		addBlock(world, basePos, 1, 2, 1, greenCrystal);
		addBlock(world, basePos, 1, 2, 2, greenCrystal);
		addBlock(world, basePos, 1, 3, 1, greenCrystal);
		addBlock(world, basePos, 2, 0, 0, greenCrystal);
		addBlock(world, basePos, 2, 0, 1, greenCrystal);
		addBlock(world, basePos, 2, 0, 2, greenCrystal);
		addBlock(world, basePos, 2, 1, 0, greenCrystal);
		addBlock(world, basePos, 2, 1, 1, greenCrystal);
		addBlock(world, basePos, 2, 1, 2, greenCrystal);
		addBlock(world, basePos, 2, 2, 0, greenCrystal);
		addBlock(world, basePos, 2, 2, 1, greenCrystal);
		addBlock(world, basePos, 2, 2, 2, greenCrystal);
	}
}
