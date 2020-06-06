package net.tslat.aoa3.structure.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class MarshLilly1 extends AoAStructure {
	private static final IBlockState magentaPetals = BlockRegister.MAGENTA_PETALS.getDefaultState();
	private static final IBlockState stem = BlockRegister.PLANT_STEM.getDefaultState();

	public MarshLilly1() {
		super("MarshLilly1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 7, 0, magentaPetals);
		addBlock(world, basePos, 0, 7, 1, magentaPetals);
		addBlock(world, basePos, 0, 7, 2, magentaPetals);
		addBlock(world, basePos, 0, 8, 0, magentaPetals);
		addBlock(world, basePos, 0, 8, 1, magentaPetals);
		addBlock(world, basePos, 0, 8, 2, magentaPetals);
		addBlock(world, basePos, 0, 9, 0, magentaPetals);
		addBlock(world, basePos, 0, 9, 1, magentaPetals);
		addBlock(world, basePos, 0, 9, 2, magentaPetals);
		addBlock(world, basePos, 0, 10, 1, magentaPetals);
		addBlock(world, basePos, 0, 11, 1, magentaPetals);
		addBlock(world, basePos, 1, 0, 1, stem);
		addBlock(world, basePos, 1, 1, 1, stem);
		addBlock(world, basePos, 1, 2, 1, stem);
		addBlock(world, basePos, 1, 3, 1, stem);
		addBlock(world, basePos, 1, 4, 1, stem);
		addBlock(world, basePos, 1, 5, 1, stem);
		addBlock(world, basePos, 1, 6, 1, stem);
		addBlock(world, basePos, 1, 7, 0, magentaPetals);
		addBlock(world, basePos, 1, 7, 1, magentaPetals);
		addBlock(world, basePos, 1, 7, 2, magentaPetals);
		addBlock(world, basePos, 1, 8, 0, magentaPetals);
		addBlock(world, basePos, 1, 8, 1, magentaPetals);
		addBlock(world, basePos, 1, 8, 2, magentaPetals);
		addBlock(world, basePos, 1, 9, 0, magentaPetals);
		addBlock(world, basePos, 1, 9, 1, magentaPetals);
		addBlock(world, basePos, 1, 9, 2, magentaPetals);
		addBlock(world, basePos, 1, 10, 0, magentaPetals);
		addBlock(world, basePos, 1, 10, 1, magentaPetals);
		addBlock(world, basePos, 1, 10, 2, magentaPetals);
		addBlock(world, basePos, 1, 11, 0, magentaPetals);
		addBlock(world, basePos, 1, 11, 1, magentaPetals);
		addBlock(world, basePos, 1, 11, 2, magentaPetals);
		addBlock(world, basePos, 1, 12, 1, magentaPetals);
		addBlock(world, basePos, 2, 7, 0, magentaPetals);
		addBlock(world, basePos, 2, 7, 1, magentaPetals);
		addBlock(world, basePos, 2, 7, 2, magentaPetals);
		addBlock(world, basePos, 2, 8, 0, magentaPetals);
		addBlock(world, basePos, 2, 8, 1, magentaPetals);
		addBlock(world, basePos, 2, 8, 2, magentaPetals);
		addBlock(world, basePos, 2, 9, 0, magentaPetals);
		addBlock(world, basePos, 2, 9, 1, magentaPetals);
		addBlock(world, basePos, 2, 9, 2, magentaPetals);
		addBlock(world, basePos, 2, 10, 1, magentaPetals);
		addBlock(world, basePos, 2, 11, 1, magentaPetals);
	}
}
