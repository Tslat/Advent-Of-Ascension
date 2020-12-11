package net.tslat.aoa3.worldgen.structures.crystevia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class PurpleCrystalChunk3 extends AoAStructure { //StructureSize: 3x7x3
	private static final BlockState purpleCrystal = AoABlocks.PURPLE_CRYSTAL_BLOCK.get().getDefaultState();

	public PurpleCrystalChunk3() {
		super("PurpleCrystalChunk3");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, purpleCrystal);
		addBlock(world, basePos, 0, 0, 1, purpleCrystal);
		addBlock(world, basePos, 0, 0, 2, purpleCrystal);
		addBlock(world, basePos, 0, 1, 0, purpleCrystal);
		addBlock(world, basePos, 0, 1, 1, purpleCrystal);
		addBlock(world, basePos, 0, 1, 2, purpleCrystal);
		addBlock(world, basePos, 0, 2, 0, purpleCrystal);
		addBlock(world, basePos, 0, 2, 1, purpleCrystal);
		addBlock(world, basePos, 0, 2, 2, purpleCrystal);
		addBlock(world, basePos, 0, 3, 1, purpleCrystal);
		addBlock(world, basePos, 0, 4, 1, purpleCrystal);
		addBlock(world, basePos, 1, 0, 0, purpleCrystal);
		addBlock(world, basePos, 1, 0, 1, purpleCrystal);
		addBlock(world, basePos, 1, 0, 2, purpleCrystal);
		addBlock(world, basePos, 1, 1, 0, purpleCrystal);
		addBlock(world, basePos, 1, 1, 1, purpleCrystal);
		addBlock(world, basePos, 1, 1, 2, purpleCrystal);
		addBlock(world, basePos, 1, 2, 0, purpleCrystal);
		addBlock(world, basePos, 1, 2, 1, purpleCrystal);
		addBlock(world, basePos, 1, 2, 2, purpleCrystal);
		addBlock(world, basePos, 1, 3, 0, purpleCrystal);
		addBlock(world, basePos, 1, 3, 1, purpleCrystal);
		addBlock(world, basePos, 1, 3, 2, purpleCrystal);
		addBlock(world, basePos, 1, 4, 0, purpleCrystal);
		addBlock(world, basePos, 1, 4, 1, purpleCrystal);
		addBlock(world, basePos, 1, 4, 2, purpleCrystal);
		addBlock(world, basePos, 1, 5, 1, purpleCrystal);
		addBlock(world, basePos, 1, 6, 1, purpleCrystal);
		addBlock(world, basePos, 2, 0, 0, purpleCrystal);
		addBlock(world, basePos, 2, 0, 1, purpleCrystal);
		addBlock(world, basePos, 2, 0, 2, purpleCrystal);
		addBlock(world, basePos, 2, 1, 0, purpleCrystal);
		addBlock(world, basePos, 2, 1, 1, purpleCrystal);
		addBlock(world, basePos, 2, 1, 2, purpleCrystal);
		addBlock(world, basePos, 2, 2, 0, purpleCrystal);
		addBlock(world, basePos, 2, 2, 1, purpleCrystal);
		addBlock(world, basePos, 2, 2, 2, purpleCrystal);
		addBlock(world, basePos, 2, 3, 1, purpleCrystal);
		addBlock(world, basePos, 2, 4, 1, purpleCrystal);
	}
}
