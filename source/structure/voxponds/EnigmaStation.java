package net.tslat.aoa3.structure.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class EnigmaStation extends AoAStructure { //StructureSize: 12x2x12
	private static final IBlockState degradedSteel = BlockRegister.degradedSteel.getDefaultState();
	private static final IBlockState toxicStone = BlockRegister.stoneToxic.getDefaultState();
	private static final IBlockState enigmaTable = BlockRegister.enigmaTable.getDefaultState();

	public EnigmaStation() {
		super("EnigmaStation");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, degradedSteel);
		addBlock(world, basePos, 0, 0, 1, degradedSteel);
		addBlock(world, basePos, 0, 0, 2, degradedSteel);
		addBlock(world, basePos, 0, 0, 3, degradedSteel);
		addBlock(world, basePos, 0, 0, 4, degradedSteel);
		addBlock(world, basePos, 0, 0, 5, degradedSteel);
		addBlock(world, basePos, 0, 0, 6, degradedSteel);
		addBlock(world, basePos, 0, 0, 7, degradedSteel);
		addBlock(world, basePos, 0, 0, 8, degradedSteel);
		addBlock(world, basePos, 0, 0, 9, degradedSteel);
		addBlock(world, basePos, 0, 0, 10, degradedSteel);
		addBlock(world, basePos, 0, 0, 11, degradedSteel);
		addBlock(world, basePos, 1, 0, 0, degradedSteel);
		addBlock(world, basePos, 1, 0, 2, degradedSteel);
		addBlock(world, basePos, 1, 0, 4, degradedSteel);
		addBlock(world, basePos, 1, 0, 7, degradedSteel);
		addBlock(world, basePos, 1, 0, 9, degradedSteel);
		addBlock(world, basePos, 1, 0, 11, degradedSteel);
		addBlock(world, basePos, 2, 0, 0, degradedSteel);
		addBlock(world, basePos, 2, 0, 1, degradedSteel);
		addBlock(world, basePos, 2, 0, 2, degradedSteel);
		addBlock(world, basePos, 2, 0, 3, degradedSteel);
		addBlock(world, basePos, 2, 0, 4, degradedSteel);
		addBlock(world, basePos, 2, 0, 5, degradedSteel);
		addBlock(world, basePos, 2, 0, 6, degradedSteel);
		addBlock(world, basePos, 2, 0, 7, degradedSteel);
		addBlock(world, basePos, 2, 0, 8, degradedSteel);
		addBlock(world, basePos, 2, 0, 9, degradedSteel);
		addBlock(world, basePos, 2, 0, 10, degradedSteel);
		addBlock(world, basePos, 2, 0, 11, degradedSteel);
		addBlock(world, basePos, 3, 0, 0, degradedSteel);
		addBlock(world, basePos, 3, 0, 2, degradedSteel);
		addBlock(world, basePos, 3, 0, 3, toxicStone);
		addBlock(world, basePos, 3, 0, 4, toxicStone);
		addBlock(world, basePos, 3, 0, 5, toxicStone);
		addBlock(world, basePos, 3, 0, 6, toxicStone);
		addBlock(world, basePos, 3, 0, 7, toxicStone);
		addBlock(world, basePos, 3, 0, 8, toxicStone);
		addBlock(world, basePos, 3, 0, 9, degradedSteel);
		addBlock(world, basePos, 3, 0, 11, degradedSteel);
		addBlock(world, basePos, 4, 0, 0, degradedSteel);
		addBlock(world, basePos, 4, 0, 1, degradedSteel);
		addBlock(world, basePos, 4, 0, 2, degradedSteel);
		addBlock(world, basePos, 4, 0, 3, toxicStone);
		addBlock(world, basePos, 4, 0, 4, toxicStone);
		addBlock(world, basePos, 4, 0, 5, toxicStone);
		addBlock(world, basePos, 4, 0, 6, toxicStone);
		addBlock(world, basePos, 4, 0, 7, toxicStone);
		addBlock(world, basePos, 4, 0, 8, toxicStone);
		addBlock(world, basePos, 4, 0, 9, degradedSteel);
		addBlock(world, basePos, 4, 0, 10, degradedSteel);
		addBlock(world, basePos, 4, 0, 11, degradedSteel);
		addBlock(world, basePos, 5, 0, 0, degradedSteel);
		addBlock(world, basePos, 5, 0, 2, degradedSteel);
		addBlock(world, basePos, 5, 0, 3, toxicStone);
		addBlock(world, basePos, 5, 0, 4, toxicStone);
		addBlock(world, basePos, 5, 0, 5, toxicStone);
		addBlock(world, basePos, 5, 0, 6, toxicStone);
		addBlock(world, basePos, 5, 0, 7, toxicStone);
		addBlock(world, basePos, 5, 0, 8, toxicStone);
		addBlock(world, basePos, 5, 0, 9, degradedSteel);
		addBlock(world, basePos, 5, 0, 11, degradedSteel);
		addBlock(world, basePos, 6, 0, 0, degradedSteel);
		addBlock(world, basePos, 6, 0, 2, degradedSteel);
		addBlock(world, basePos, 6, 0, 3, toxicStone);
		addBlock(world, basePos, 6, 0, 4, toxicStone);
		addBlock(world, basePos, 6, 0, 5, toxicStone);
		addBlock(world, basePos, 6, 0, 6, toxicStone);
		addBlock(world, basePos, 6, 0, 7, toxicStone);
		addBlock(world, basePos, 6, 0, 8, toxicStone);
		addBlock(world, basePos, 6, 0, 9, degradedSteel);
		addBlock(world, basePos, 6, 0, 11, degradedSteel);
		addBlock(world, basePos, 7, 0, 0, degradedSteel);
		addBlock(world, basePos, 7, 0, 1, degradedSteel);
		addBlock(world, basePos, 7, 0, 2, degradedSteel);
		addBlock(world, basePos, 7, 0, 3, toxicStone);
		addBlock(world, basePos, 7, 0, 4, toxicStone);
		addBlock(world, basePos, 7, 0, 5, toxicStone);
		addBlock(world, basePos, 7, 0, 6, toxicStone);
		addBlock(world, basePos, 7, 0, 7, toxicStone);
		addBlock(world, basePos, 7, 0, 8, toxicStone);
		addBlock(world, basePos, 7, 0, 9, degradedSteel);
		addBlock(world, basePos, 7, 0, 10, degradedSteel);
		addBlock(world, basePos, 7, 0, 11, degradedSteel);
		addBlock(world, basePos, 8, 0, 0, degradedSteel);
		addBlock(world, basePos, 8, 0, 2, degradedSteel);
		addBlock(world, basePos, 8, 0, 3, toxicStone);
		addBlock(world, basePos, 8, 0, 4, toxicStone);
		addBlock(world, basePos, 8, 0, 5, toxicStone);
		addBlock(world, basePos, 8, 0, 6, toxicStone);
		addBlock(world, basePos, 8, 0, 7, toxicStone);
		addBlock(world, basePos, 8, 0, 8, toxicStone);
		addBlock(world, basePos, 8, 0, 9, degradedSteel);
		addBlock(world, basePos, 8, 0, 11, degradedSteel);
		addBlock(world, basePos, 9, 0, 0, degradedSteel);
		addBlock(world, basePos, 9, 0, 1, degradedSteel);
		addBlock(world, basePos, 9, 0, 2, degradedSteel);
		addBlock(world, basePos, 9, 0, 3, degradedSteel);
		addBlock(world, basePos, 9, 0, 4, degradedSteel);
		addBlock(world, basePos, 9, 0, 5, degradedSteel);
		addBlock(world, basePos, 9, 0, 6, degradedSteel);
		addBlock(world, basePos, 9, 0, 7, degradedSteel);
		addBlock(world, basePos, 9, 0, 8, degradedSteel);
		addBlock(world, basePos, 9, 0, 9, degradedSteel);
		addBlock(world, basePos, 9, 0, 10, degradedSteel);
		addBlock(world, basePos, 9, 0, 11, degradedSteel);
		addBlock(world, basePos, 10, 0, 0, degradedSteel);
		addBlock(world, basePos, 10, 0, 2, degradedSteel);
		addBlock(world, basePos, 10, 0, 4, degradedSteel);
		addBlock(world, basePos, 10, 0, 7, degradedSteel);
		addBlock(world, basePos, 10, 0, 9, degradedSteel);
		addBlock(world, basePos, 10, 0, 11, degradedSteel);
		addBlock(world, basePos, 11, 0, 0, degradedSteel);
		addBlock(world, basePos, 11, 0, 1, degradedSteel);
		addBlock(world, basePos, 11, 0, 2, degradedSteel);
		addBlock(world, basePos, 11, 0, 3, degradedSteel);
		addBlock(world, basePos, 11, 0, 4, degradedSteel);
		addBlock(world, basePos, 11, 0, 5, degradedSteel);
		addBlock(world, basePos, 11, 0, 6, degradedSteel);
		addBlock(world, basePos, 11, 0, 7, degradedSteel);
		addBlock(world, basePos, 11, 0, 8, degradedSteel);
		addBlock(world, basePos, 11, 0, 9, degradedSteel);
		addBlock(world, basePos, 11, 0, 10, degradedSteel);
		addBlock(world, basePos, 11, 0, 11, degradedSteel);
		addBlock(world, basePos, 5, 1, 5, enigmaTable);
		addBlock(world, basePos, 5, 1, 6, enigmaTable);
		addBlock(world, basePos, 6, 1, 5, enigmaTable);
		addBlock(world, basePos, 6, 1, 6, enigmaTable);
	}
}
