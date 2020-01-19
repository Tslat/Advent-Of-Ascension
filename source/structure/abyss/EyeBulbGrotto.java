package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class EyeBulbGrotto extends AoAStructure { //StructureSize: 4x12x5
	private static final IBlockState toxicBlock = BlockRegister.toxicBlock.getDefaultState();
	private static final IBlockState grass = BlockRegister.grassAbyss.getDefaultState();
	private static final IBlockState water = Blocks.FLOWING_WATER.getDefaultState();
	private static final IBlockState air = Blocks.AIR.getDefaultState();
	private static final IBlockState bulbStockCap = BlockRegister.plantBulbStockCap.getDefaultState();
	private static final IBlockState bulbStock = BlockRegister.plantBulbStock.getDefaultState();

	public EyeBulbGrotto() {
		super("EyeBulbGrotto");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, -11, 3, toxicBlock);
		addBlock(world, basePos, 1, -11, 4, toxicBlock);
		addBlock(world, basePos, 2, -11, 4, toxicBlock);
		addBlock(world, basePos, 1, -10, 2, water);
		addBlock(world, basePos, 1, -10, 3, water);
		addBlock(world, basePos, 1, -10, 4, water);
		addBlock(world, basePos, 2, -10, 1, grass);
		addBlock(world, basePos, 2, -10, 2, water);
		addBlock(world, basePos, 2, -10, 3, water);
		addBlock(world, basePos, 2, -10, 4, water);
		addBlock(world, basePos, 3, -10, 1, grass);
		addBlock(world, basePos, 3, -10, 3, water);
		addBlock(world, basePos, 3, -10, 4, water);
		addBlock(world, basePos, 1, -9, 2, air);
		addBlock(world, basePos, 1, -9, 3, air);
		addBlock(world, basePos, 1, -9, 4, air);
		addBlock(world, basePos, 2, -9, 1, bulbStockCap);
		addBlock(world, basePos, 2, -9, 2, air);
		addBlock(world, basePos, 2, -9, 3, air);
		addBlock(world, basePos, 2, -9, 4, air);
		addBlock(world, basePos, 3, -9, 1, bulbStock);
		addBlock(world, basePos, 3, -9, 2, air);
		addBlock(world, basePos, 3, -9, 3, air);
		addBlock(world, basePos, 0, -8, 3, air);
		addBlock(world, basePos, 1, -8, 3, air);
		addBlock(world, basePos, 2, -8, 1, air);
		addBlock(world, basePos, 2, -8, 2, air);
		addBlock(world, basePos, 2, -8, 3, air);
		addBlock(world, basePos, 2, -8, 4, air);
		addBlock(world, basePos, 3, -8, 1, bulbStockCap);
		addBlock(world, basePos, 3, -8, 2, air);
		addBlock(world, basePos, 3, -8, 3, air);
		addBlock(world, basePos, 3, -8, 4, air);
		addBlock(world, basePos, 0, -7, 2, air);
		addBlock(world, basePos, 0, -7, 3, air);
		addBlock(world, basePos, 3, -7, 3, air);
		addBlock(world, basePos, 3, -7, 4, air);
		addBlock(world, basePos, 0, -6, 1, air);
		addBlock(world, basePos, 0, -6, 2, air);
		addBlock(world, basePos, 0, -5, 1, air);
		addBlock(world, basePos, 1, -5, 1, air);
		addBlock(world, basePos, 1, -4, 0, air);
		addBlock(world, basePos, 1, -4, 1, air);
		addBlock(world, basePos, 2, -4, 0, air);
		addBlock(world, basePos, 1, -3, 1, air);
		addBlock(world, basePos, 2, -3, 2, air);
		addBlock(world, basePos, 1, -2, 1, air);
		addBlock(world, basePos, 1, -2, 2, air);
		addBlock(world, basePos, 2, -2, 2, air);
		addBlock(world, basePos, 1, -1, 1, air);
		addBlock(world, basePos, 1, 0, 1, water);
	}
}
