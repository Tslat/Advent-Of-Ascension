package net.tslat.aoa3.worldgen.structures.crystevia;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class CrystalExtensionStation extends AoAStructure { //StructureSize: 11x12x11
	private static final BlockState crystevianBricks = AoABlocks.CRYSTEVIA_BRICKS.get().getDefaultState();
	private static final BlockState yellowCrystal = AoABlocks.YELLOW_CRYSTAL_BLOCK.get().getDefaultState();
	private static final BlockState crystalExtensionShrine = AoABlocks.CRYSTAL_EXTENSION_SHRINE.get().getDefaultState();

	public CrystalExtensionStation() {
		super("CrystalExtensionStation");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		int y = -1;

		while (world.getBlockState(basePos.add(5, y, 5)).getBlock() == Blocks.AIR) {
			addBlock(world, basePos, 5, y, 5, yellowCrystal);
			y--;
		}

		addBlock(world, basePos, 0, 0, 0, crystevianBricks);
		addBlock(world, basePos, 0, 0, 1, crystevianBricks);
		addBlock(world, basePos, 0, 0, 2, crystevianBricks);
		addBlock(world, basePos, 0, 0, 3, crystevianBricks);
		addBlock(world, basePos, 0, 0, 4, crystevianBricks);
		addBlock(world, basePos, 0, 0, 5, crystevianBricks);
		addBlock(world, basePos, 0, 0, 6, crystevianBricks);
		addBlock(world, basePos, 0, 0, 7, crystevianBricks);
		addBlock(world, basePos, 0, 0, 8, crystevianBricks);
		addBlock(world, basePos, 0, 0, 9, crystevianBricks);
		addBlock(world, basePos, 0, 0, 10, crystevianBricks);
		addBlock(world, basePos, 1, 0, 0, crystevianBricks);
		addBlock(world, basePos, 1, 0, 10, crystevianBricks);
		addBlock(world, basePos, 2, 0, 0, crystevianBricks);
		addBlock(world, basePos, 2, 0, 2, crystevianBricks);
		addBlock(world, basePos, 2, 0, 3, crystevianBricks);
		addBlock(world, basePos, 2, 0, 4, crystevianBricks);
		addBlock(world, basePos, 2, 0, 5, crystevianBricks);
		addBlock(world, basePos, 2, 0, 6, crystevianBricks);
		addBlock(world, basePos, 2, 0, 7, crystevianBricks);
		addBlock(world, basePos, 2, 0, 8, crystevianBricks);
		addBlock(world, basePos, 2, 0, 10, crystevianBricks);
		addBlock(world, basePos, 3, 0, 0, crystevianBricks);
		addBlock(world, basePos, 3, 0, 2, crystevianBricks);
		addBlock(world, basePos, 3, 0, 3, yellowCrystal);
		addBlock(world, basePos, 3, 0, 4, yellowCrystal);
		addBlock(world, basePos, 3, 0, 5, yellowCrystal);
		addBlock(world, basePos, 3, 0, 6, yellowCrystal);
		addBlock(world, basePos, 3, 0, 7, yellowCrystal);
		addBlock(world, basePos, 3, 0, 8, crystevianBricks);
		addBlock(world, basePos, 3, 0, 10, crystevianBricks);
		addBlock(world, basePos, 4, 0, 0, crystevianBricks);
		addBlock(world, basePos, 4, 0, 2, crystevianBricks);
		addBlock(world, basePos, 4, 0, 3, yellowCrystal);
		addBlock(world, basePos, 4, 0, 4, crystevianBricks);
		addBlock(world, basePos, 4, 0, 5, crystevianBricks);
		addBlock(world, basePos, 4, 0, 6, crystevianBricks);
		addBlock(world, basePos, 4, 0, 7, yellowCrystal);
		addBlock(world, basePos, 4, 0, 8, crystevianBricks);
		addBlock(world, basePos, 4, 0, 10, crystevianBricks);
		addBlock(world, basePos, 5, 0, 0, crystevianBricks);
		addBlock(world, basePos, 5, 0, 2, crystevianBricks);
		addBlock(world, basePos, 5, 0, 3, yellowCrystal);
		addBlock(world, basePos, 5, 0, 4, crystevianBricks);
		addBlock(world, basePos, 5, 0, 5, crystevianBricks);
		addBlock(world, basePos, 5, 0, 6, crystevianBricks);
		addBlock(world, basePos, 5, 0, 7, yellowCrystal);
		addBlock(world, basePos, 5, 0, 8, crystevianBricks);
		addBlock(world, basePos, 5, 0, 10, crystevianBricks);
		addBlock(world, basePos, 6, 0, 0, crystevianBricks);
		addBlock(world, basePos, 6, 0, 2, crystevianBricks);
		addBlock(world, basePos, 6, 0, 3, yellowCrystal);
		addBlock(world, basePos, 6, 0, 4, crystevianBricks);
		addBlock(world, basePos, 6, 0, 5, crystevianBricks);
		addBlock(world, basePos, 6, 0, 6, crystevianBricks);
		addBlock(world, basePos, 6, 0, 7, yellowCrystal);
		addBlock(world, basePos, 6, 0, 8, crystevianBricks);
		addBlock(world, basePos, 6, 0, 10, crystevianBricks);
		addBlock(world, basePos, 7, 0, 0, crystevianBricks);
		addBlock(world, basePos, 7, 0, 2, crystevianBricks);
		addBlock(world, basePos, 7, 0, 3, yellowCrystal);
		addBlock(world, basePos, 7, 0, 4, yellowCrystal);
		addBlock(world, basePos, 7, 0, 5, yellowCrystal);
		addBlock(world, basePos, 7, 0, 6, yellowCrystal);
		addBlock(world, basePos, 7, 0, 7, yellowCrystal);
		addBlock(world, basePos, 7, 0, 8, crystevianBricks);
		addBlock(world, basePos, 7, 0, 10, crystevianBricks);
		addBlock(world, basePos, 8, 0, 0, crystevianBricks);
		addBlock(world, basePos, 8, 0, 2, crystevianBricks);
		addBlock(world, basePos, 8, 0, 3, crystevianBricks);
		addBlock(world, basePos, 8, 0, 4, crystevianBricks);
		addBlock(world, basePos, 8, 0, 5, crystevianBricks);
		addBlock(world, basePos, 8, 0, 6, crystevianBricks);
		addBlock(world, basePos, 8, 0, 7, crystevianBricks);
		addBlock(world, basePos, 8, 0, 8, crystevianBricks);
		addBlock(world, basePos, 8, 0, 10, crystevianBricks);
		addBlock(world, basePos, 9, 0, 0, crystevianBricks);
		addBlock(world, basePos, 9, 0, 10, crystevianBricks);
		addBlock(world, basePos, 10, 0, 0, crystevianBricks);
		addBlock(world, basePos, 10, 0, 1, crystevianBricks);
		addBlock(world, basePos, 10, 0, 2, crystevianBricks);
		addBlock(world, basePos, 10, 0, 3, crystevianBricks);
		addBlock(world, basePos, 10, 0, 4, crystevianBricks);
		addBlock(world, basePos, 10, 0, 5, crystevianBricks);
		addBlock(world, basePos, 10, 0, 6, crystevianBricks);
		addBlock(world, basePos, 10, 0, 7, crystevianBricks);
		addBlock(world, basePos, 10, 0, 8, crystevianBricks);
		addBlock(world, basePos, 10, 0, 9, crystevianBricks);
		addBlock(world, basePos, 10, 0, 10, crystevianBricks);
		addBlock(world, basePos, 1, 1, 1, crystevianBricks);
		addBlock(world, basePos, 1, 1, 2, crystevianBricks);
		addBlock(world, basePos, 1, 1, 3, crystevianBricks);
		addBlock(world, basePos, 1, 1, 4, crystevianBricks);
		addBlock(world, basePos, 1, 1, 5, crystevianBricks);
		addBlock(world, basePos, 1, 1, 6, crystevianBricks);
		addBlock(world, basePos, 1, 1, 7, crystevianBricks);
		addBlock(world, basePos, 1, 1, 8, crystevianBricks);
		addBlock(world, basePos, 1, 1, 9, crystevianBricks);
		addBlock(world, basePos, 2, 1, 1, crystevianBricks);
		addBlock(world, basePos, 2, 1, 9, crystevianBricks);
		addBlock(world, basePos, 3, 1, 1, crystevianBricks);
		addBlock(world, basePos, 3, 1, 9, crystevianBricks);
		addBlock(world, basePos, 4, 1, 1, crystevianBricks);
		addBlock(world, basePos, 4, 1, 9, crystevianBricks);
		addBlock(world, basePos, 5, 1, 1, crystevianBricks);
		addBlock(world, basePos, 5, 1, 5, crystevianBricks);
		addBlock(world, basePos, 5, 1, 9, crystevianBricks);
		addBlock(world, basePos, 6, 1, 1, crystevianBricks);
		addBlock(world, basePos, 6, 1, 9, crystevianBricks);
		addBlock(world, basePos, 7, 1, 1, crystevianBricks);
		addBlock(world, basePos, 7, 1, 9, crystevianBricks);
		addBlock(world, basePos, 8, 1, 1, crystevianBricks);
		addBlock(world, basePos, 8, 1, 9, crystevianBricks);
		addBlock(world, basePos, 9, 1, 1, crystevianBricks);
		addBlock(world, basePos, 9, 1, 2, crystevianBricks);
		addBlock(world, basePos, 9, 1, 3, crystevianBricks);
		addBlock(world, basePos, 9, 1, 4, crystevianBricks);
		addBlock(world, basePos, 9, 1, 5, crystevianBricks);
		addBlock(world, basePos, 9, 1, 6, crystevianBricks);
		addBlock(world, basePos, 9, 1, 7, crystevianBricks);
		addBlock(world, basePos, 9, 1, 8, crystevianBricks);
		addBlock(world, basePos, 9, 1, 9, crystevianBricks);
		addBlock(world, basePos, 5, 2, 5, crystalExtensionShrine);
		addBlock(world, basePos, 5, 3, 5, crystevianBricks);
		addBlock(world, basePos, 2, 4, 5, crystevianBricks);
		addBlock(world, basePos, 3, 4, 5, yellowCrystal);
		addBlock(world, basePos, 4, 4, 5, crystevianBricks);
		addBlock(world, basePos, 5, 4, 2, crystevianBricks);
		addBlock(world, basePos, 5, 4, 3, yellowCrystal);
		addBlock(world, basePos, 5, 4, 4, crystevianBricks);
		addBlock(world, basePos, 5, 4, 5, crystevianBricks);
		addBlock(world, basePos, 5, 4, 6, crystevianBricks);
		addBlock(world, basePos, 5, 4, 7, yellowCrystal);
		addBlock(world, basePos, 5, 4, 8, crystevianBricks);
		addBlock(world, basePos, 6, 4, 5, crystevianBricks);
		addBlock(world, basePos, 7, 4, 5, yellowCrystal);
		addBlock(world, basePos, 8, 4, 5, crystevianBricks);
		addBlock(world, basePos, 5, 5, 5, crystevianBricks);
		addBlock(world, basePos, 5, 6, 5, yellowCrystal);
		addBlock(world, basePos, 5, 7, 5, crystevianBricks);
		addBlock(world, basePos, 5, 8, 5, yellowCrystal);
		addBlock(world, basePos, 5, 9, 5, crystevianBricks);
		addBlock(world, basePos, 5, 10, 5, yellowCrystal);
		addBlock(world, basePos, 5, 11, 5, crystevianBricks);
	}
}
