package net.tslat.aoa3.worldgen.structures.crystevia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class GreenCrystalChunk4 extends AoAStructure { //StructureSize: 2x7x2
	private static final BlockState greenCrystal = AoABlocks.GREEN_CRYSTAL_BLOCK.get().getDefaultState();

	public GreenCrystalChunk4() {
		super("GreenCrystalChunk4");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, greenCrystal);
		addBlock(world, basePos, 0, 0, 1, greenCrystal);
		addBlock(world, basePos, 0, 1, 0, greenCrystal);
		addBlock(world, basePos, 0, 1, 1, greenCrystal);
		addBlock(world, basePos, 0, 2, 0, greenCrystal);
		addBlock(world, basePos, 0, 2, 1, greenCrystal);
		addBlock(world, basePos, 0, 3, 0, greenCrystal);
		addBlock(world, basePos, 0, 3, 1, greenCrystal);
		addBlock(world, basePos, 0, 4, 0, greenCrystal);
		addBlock(world, basePos, 0, 4, 1, greenCrystal);
		addBlock(world, basePos, 0, 5, 0, greenCrystal);
		addBlock(world, basePos, 0, 5, 1, greenCrystal);
		addBlock(world, basePos, 0, 6, 0, greenCrystal);
		addBlock(world, basePos, 0, 6, 1, greenCrystal);
		addBlock(world, basePos, 1, 0, 0, greenCrystal);
		addBlock(world, basePos, 1, 0, 1, greenCrystal);
		addBlock(world, basePos, 1, 1, 0, greenCrystal);
		addBlock(world, basePos, 1, 1, 1, greenCrystal);
		addBlock(world, basePos, 1, 2, 0, greenCrystal);
		addBlock(world, basePos, 1, 2, 1, greenCrystal);
		addBlock(world, basePos, 1, 3, 0, greenCrystal);
		addBlock(world, basePos, 1, 3, 1, greenCrystal);
		addBlock(world, basePos, 1, 4, 0, greenCrystal);
		addBlock(world, basePos, 1, 4, 1, greenCrystal);
		addBlock(world, basePos, 1, 5, 0, greenCrystal);
		addBlock(world, basePos, 1, 5, 1, greenCrystal);
		addBlock(world, basePos, 1, 6, 0, greenCrystal);
		addBlock(world, basePos, 1, 6, 1, greenCrystal);
	}
}
