package net.tslat.aoa3.worldgen.structures.crystevia;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class YellowCrystalChunk1 extends AoAStructure { //StructureSize: 3x4x3
	private static final BlockState yellowCrystal = AoABlocks.YELLOW_CRYSTAL_BLOCK.get().getDefaultState();

	public YellowCrystalChunk1() {
		super("YellowCrystalChunk1");
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, yellowCrystal);
		addBlock(world, basePos, 0, 0, 1, yellowCrystal);
		addBlock(world, basePos, 0, 0, 2, yellowCrystal);
		addBlock(world, basePos, 0, 1, 0, yellowCrystal);
		addBlock(world, basePos, 0, 1, 1, yellowCrystal);
		addBlock(world, basePos, 0, 1, 2, yellowCrystal);
		addBlock(world, basePos, 0, 2, 0, yellowCrystal);
		addBlock(world, basePos, 0, 2, 1, yellowCrystal);
		addBlock(world, basePos, 0, 2, 2, yellowCrystal);
		addBlock(world, basePos, 1, 0, 0, yellowCrystal);
		addBlock(world, basePos, 1, 0, 1, yellowCrystal);
		addBlock(world, basePos, 1, 0, 2, yellowCrystal);
		addBlock(world, basePos, 1, 1, 0, yellowCrystal);
		addBlock(world, basePos, 1, 1, 1, yellowCrystal);
		addBlock(world, basePos, 1, 1, 2, yellowCrystal);
		addBlock(world, basePos, 1, 2, 0, yellowCrystal);
		addBlock(world, basePos, 1, 2, 1, yellowCrystal);
		addBlock(world, basePos, 1, 2, 2, yellowCrystal);
		addBlock(world, basePos, 1, 3, 1, yellowCrystal);
		addBlock(world, basePos, 2, 0, 0, yellowCrystal);
		addBlock(world, basePos, 2, 0, 1, yellowCrystal);
		addBlock(world, basePos, 2, 0, 2, yellowCrystal);
		addBlock(world, basePos, 2, 1, 0, yellowCrystal);
		addBlock(world, basePos, 2, 1, 1, yellowCrystal);
		addBlock(world, basePos, 2, 1, 2, yellowCrystal);
		addBlock(world, basePos, 2, 2, 0, yellowCrystal);
		addBlock(world, basePos, 2, 2, 1, yellowCrystal);
		addBlock(world, basePos, 2, 2, 2, yellowCrystal);
	}
}
