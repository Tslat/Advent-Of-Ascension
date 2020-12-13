package net.tslat.aoa3.worldgen.structures.crystevia;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class PowerStation extends AoAStructure { //StructureSize: 7x5x7
	private static final BlockState crystevianBricks = AoABlocks.CRYSTEVIA_BRICKS.get().getDefaultState();
	private static final BlockState purpleCrystal = AoABlocks.PURPLE_CRYSTAL_BLOCK.get().getDefaultState();
	private static final BlockState powerStation = AoABlocks.POWER_STATION.get().getDefaultState();
	private static final BlockState redCrystal = AoABlocks.RED_CRYSTAL_BLOCK.get().getDefaultState();

	public PowerStation() {
		super("PowerStation");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		int y = -1;

		while (world.getBlockState(basePos.add(3, y, 3)).getBlock() == Blocks.AIR) {
			addBlock(world, basePos, 3, y, 3, redCrystal);
			y--;
		}

		addBlock(world, basePos, 0, 0, 0, crystevianBricks);
		addBlock(world, basePos, 0, 0, 1, crystevianBricks);
		addBlock(world, basePos, 0, 0, 2, crystevianBricks);
		addBlock(world, basePos, 0, 0, 3, crystevianBricks);
		addBlock(world, basePos, 0, 0, 4, crystevianBricks);
		addBlock(world, basePos, 0, 0, 5, crystevianBricks);
		addBlock(world, basePos, 0, 0, 6, crystevianBricks);
		addBlock(world, basePos, 1, 0, 0, crystevianBricks);
		addBlock(world, basePos, 1, 0, 1, purpleCrystal);
		addBlock(world, basePos, 1, 0, 2, purpleCrystal);
		addBlock(world, basePos, 1, 0, 3, purpleCrystal);
		addBlock(world, basePos, 1, 0, 4, purpleCrystal);
		addBlock(world, basePos, 1, 0, 5, purpleCrystal);
		addBlock(world, basePos, 1, 0, 6, crystevianBricks);
		addBlock(world, basePos, 2, 0, 0, crystevianBricks);
		addBlock(world, basePos, 2, 0, 1, purpleCrystal);
		addBlock(world, basePos, 2, 0, 2, crystevianBricks);
		addBlock(world, basePos, 2, 0, 3, crystevianBricks);
		addBlock(world, basePos, 2, 0, 4, crystevianBricks);
		addBlock(world, basePos, 2, 0, 5, purpleCrystal);
		addBlock(world, basePos, 2, 0, 6, crystevianBricks);
		addBlock(world, basePos, 3, 0, 0, crystevianBricks);
		addBlock(world, basePos, 3, 0, 1, purpleCrystal);
		addBlock(world, basePos, 3, 0, 2, crystevianBricks);
		addBlock(world, basePos, 3, 0, 3, crystevianBricks);
		addBlock(world, basePos, 3, 0, 4, crystevianBricks);
		addBlock(world, basePos, 3, 0, 5, purpleCrystal);
		addBlock(world, basePos, 3, 0, 6, crystevianBricks);
		addBlock(world, basePos, 4, 0, 0, crystevianBricks);
		addBlock(world, basePos, 4, 0, 1, purpleCrystal);
		addBlock(world, basePos, 4, 0, 2, crystevianBricks);
		addBlock(world, basePos, 4, 0, 3, crystevianBricks);
		addBlock(world, basePos, 4, 0, 4, crystevianBricks);
		addBlock(world, basePos, 4, 0, 5, purpleCrystal);
		addBlock(world, basePos, 4, 0, 6, crystevianBricks);
		addBlock(world, basePos, 5, 0, 0, crystevianBricks);
		addBlock(world, basePos, 5, 0, 1, purpleCrystal);
		addBlock(world, basePos, 5, 0, 2, purpleCrystal);
		addBlock(world, basePos, 5, 0, 3, purpleCrystal);
		addBlock(world, basePos, 5, 0, 4, purpleCrystal);
		addBlock(world, basePos, 5, 0, 5, purpleCrystal);
		addBlock(world, basePos, 5, 0, 6, crystevianBricks);
		addBlock(world, basePos, 6, 0, 0, crystevianBricks);
		addBlock(world, basePos, 6, 0, 1, crystevianBricks);
		addBlock(world, basePos, 6, 0, 2, crystevianBricks);
		addBlock(world, basePos, 6, 0, 3, crystevianBricks);
		addBlock(world, basePos, 6, 0, 4, crystevianBricks);
		addBlock(world, basePos, 6, 0, 5, crystevianBricks);
		addBlock(world, basePos, 6, 0, 6, crystevianBricks);
		addBlock(world, basePos, 0, 1, 3, crystevianBricks);
		addBlock(world, basePos, 3, 1, 0, crystevianBricks);
		addBlock(world, basePos, 3, 1, 3, crystevianBricks);
		addBlock(world, basePos, 3, 1, 6, crystevianBricks);
		addBlock(world, basePos, 6, 1, 3, crystevianBricks);
		addBlock(world, basePos, 1, 2, 3, crystevianBricks);
		addBlock(world, basePos, 3, 2, 1, crystevianBricks);
		addBlock(world, basePos, 3, 2, 3, purpleCrystal);
		addBlock(world, basePos, 3, 2, 5, crystevianBricks);
		addBlock(world, basePos, 5, 2, 3, crystevianBricks);
		addBlock(world, basePos, 2, 3, 2, crystevianBricks);
		addBlock(world, basePos, 2, 3, 3, crystevianBricks);
		addBlock(world, basePos, 2, 3, 4, crystevianBricks);
		addBlock(world, basePos, 3, 3, 2, crystevianBricks);
		addBlock(world, basePos, 3, 3, 3, crystevianBricks);
		addBlock(world, basePos, 3, 3, 4, crystevianBricks);
		addBlock(world, basePos, 4, 3, 2, crystevianBricks);
		addBlock(world, basePos, 4, 3, 3, crystevianBricks);
		addBlock(world, basePos, 4, 3, 4, crystevianBricks);
		addBlock(world, basePos, 3, 4, 3, powerStation);
	}
}
