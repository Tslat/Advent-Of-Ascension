package net.tslat.aoa3.worldgen.structures.abyss;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class EyeBulbGrotto extends AoAStructure { //StructureSize: 4x12x5
	private static final BlockState toxicBlock = AoABlocks.TOXIC_BLOCK.get().getDefaultState();
	private static final BlockState grass = AoABlocks.ABYSSAL_GRASS.get().getDefaultState();
	private static final BlockState water = Blocks.WATER.getDefaultState().with(FlowingFluidBlock.LEVEL, 15);
	private static final BlockState air = Blocks.AIR.getDefaultState();
	private static final BlockState bulbStockCap = AoABlocks.BULB_STOCK_CAP.get().getDefaultState();
	private static final BlockState bulbStock = AoABlocks.BULB_STOCK.get().getDefaultState();

	public EyeBulbGrotto() {
		super("EyeBulbGrotto");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
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
