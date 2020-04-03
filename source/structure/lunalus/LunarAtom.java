package net.tslat.aoa3.structure.lunalus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.tslat.aoa3.block.functional.lamps.LampBlock.FIXED_LAMP;

public class LunarAtom extends AoAStructure { //StructureSize: 11x11x11
	private static final IBlockState lunarLamp = BlockRegister.lampLunar.getDefaultState().withProperty(FIXED_LAMP, true);
	private static final IBlockState lunarBricks = BlockRegister.bricksLunar.getDefaultState();

	public LunarAtom() {
		super("LunarAtom");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 1, 0, 1, lunarLamp);
		addBlock(world, basePos, 1, 0, 9, lunarLamp);
		addBlock(world, basePos, 5, 0, 5, lunarLamp);
		addBlock(world, basePos, 9, 0, 1, lunarLamp);
		addBlock(world, basePos, 9, 0, 9, lunarLamp);
		addBlock(world, basePos, 1, 1, 1, lunarBricks);
		addBlock(world, basePos, 1, 1, 9, lunarBricks);
		addBlock(world, basePos, 2, 1, 2, lunarBricks);
		addBlock(world, basePos, 2, 1, 8, lunarBricks);
		addBlock(world, basePos, 5, 1, 5, lunarBricks);
		addBlock(world, basePos, 8, 1, 2, lunarBricks);
		addBlock(world, basePos, 8, 1, 8, lunarBricks);
		addBlock(world, basePos, 9, 1, 1, lunarBricks);
		addBlock(world, basePos, 9, 1, 9, lunarBricks);
		addBlock(world, basePos, 2, 2, 2, lunarBricks);
		addBlock(world, basePos, 2, 2, 8, lunarBricks);
		addBlock(world, basePos, 3, 2, 3, lunarBricks);
		addBlock(world, basePos, 3, 2, 7, lunarBricks);
		addBlock(world, basePos, 5, 2, 5, lunarBricks);
		addBlock(world, basePos, 7, 2, 3, lunarBricks);
		addBlock(world, basePos, 7, 2, 7, lunarBricks);
		addBlock(world, basePos, 8, 2, 2, lunarBricks);
		addBlock(world, basePos, 8, 2, 8, lunarBricks);
		addBlock(world, basePos, 3, 3, 3, lunarBricks);
		addBlock(world, basePos, 3, 3, 7, lunarBricks);
		addBlock(world, basePos, 4, 3, 4, lunarBricks);
		addBlock(world, basePos, 4, 3, 6, lunarBricks);
		addBlock(world, basePos, 5, 3, 5, lunarBricks);
		addBlock(world, basePos, 6, 3, 4, lunarBricks);
		addBlock(world, basePos, 6, 3, 6, lunarBricks);
		addBlock(world, basePos, 7, 3, 3, lunarBricks);
		addBlock(world, basePos, 7, 3, 7, lunarBricks);
		addBlock(world, basePos, 4, 4, 4, lunarBricks);
		addBlock(world, basePos, 4, 4, 6, lunarBricks);
		addBlock(world, basePos, 5, 4, 5, lunarBricks);
		addBlock(world, basePos, 6, 4, 4, lunarBricks);
		addBlock(world, basePos, 6, 4, 6, lunarBricks);
		addBlock(world, basePos, 0, 5, 5, lunarLamp);
		addBlock(world, basePos, 1, 5, 5, lunarBricks);
		addBlock(world, basePos, 2, 5, 5, lunarBricks);
		addBlock(world, basePos, 3, 5, 5, lunarBricks);
		addBlock(world, basePos, 4, 5, 5, lunarBricks);
		addBlock(world, basePos, 5, 5, 0, lunarLamp);
		addBlock(world, basePos, 5, 5, 1, lunarBricks);
		addBlock(world, basePos, 5, 5, 2, lunarBricks);
		addBlock(world, basePos, 5, 5, 3, lunarBricks);
		addBlock(world, basePos, 5, 5, 4, lunarBricks);
		addBlock(world, basePos, 5, 5, 5, lunarBricks);
		addBlock(world, basePos, 5, 5, 6, lunarBricks);
		addBlock(world, basePos, 5, 5, 7, lunarBricks);
		addBlock(world, basePos, 5, 5, 8, lunarBricks);
		addBlock(world, basePos, 5, 5, 9, lunarBricks);
		addBlock(world, basePos, 5, 5, 10, lunarLamp);
		addBlock(world, basePos, 6, 5, 5, lunarBricks);
		addBlock(world, basePos, 7, 5, 5, lunarBricks);
		addBlock(world, basePos, 8, 5, 5, lunarBricks);
		addBlock(world, basePos, 9, 5, 5, lunarBricks);
		addBlock(world, basePos, 10, 5, 5, lunarLamp);
		addBlock(world, basePos, 4, 6, 4, lunarBricks);
		addBlock(world, basePos, 4, 6, 6, lunarBricks);
		addBlock(world, basePos, 5, 6, 5, lunarBricks);
		addBlock(world, basePos, 6, 6, 4, lunarBricks);
		addBlock(world, basePos, 6, 6, 6, lunarBricks);
		addBlock(world, basePos, 3, 7, 3, lunarBricks);
		addBlock(world, basePos, 3, 7, 7, lunarBricks);
		addBlock(world, basePos, 4, 7, 4, lunarBricks);
		addBlock(world, basePos, 4, 7, 6, lunarBricks);
		addBlock(world, basePos, 5, 7, 5, lunarBricks);
		addBlock(world, basePos, 6, 7, 4, lunarBricks);
		addBlock(world, basePos, 6, 7, 6, lunarBricks);
		addBlock(world, basePos, 7, 7, 3, lunarBricks);
		addBlock(world, basePos, 7, 7, 7, lunarBricks);
		addBlock(world, basePos, 2, 8, 2, lunarBricks);
		addBlock(world, basePos, 2, 8, 8, lunarBricks);
		addBlock(world, basePos, 3, 8, 3, lunarBricks);
		addBlock(world, basePos, 3, 8, 7, lunarBricks);
		addBlock(world, basePos, 5, 8, 5, lunarBricks);
		addBlock(world, basePos, 7, 8, 3, lunarBricks);
		addBlock(world, basePos, 7, 8, 7, lunarBricks);
		addBlock(world, basePos, 8, 8, 2, lunarBricks);
		addBlock(world, basePos, 8, 8, 8, lunarBricks);
		addBlock(world, basePos, 1, 9, 1, lunarBricks);
		addBlock(world, basePos, 1, 9, 9, lunarBricks);
		addBlock(world, basePos, 2, 9, 2, lunarBricks);
		addBlock(world, basePos, 2, 9, 8, lunarBricks);
		addBlock(world, basePos, 5, 9, 5, lunarBricks);
		addBlock(world, basePos, 8, 9, 2, lunarBricks);
		addBlock(world, basePos, 8, 9, 8, lunarBricks);
		addBlock(world, basePos, 9, 9, 1, lunarBricks);
		addBlock(world, basePos, 9, 9, 9, lunarBricks);
		addBlock(world, basePos, 1, 10, 1, lunarLamp);
		addBlock(world, basePos, 1, 10, 9, lunarLamp);
		addBlock(world, basePos, 5, 10, 5, lunarLamp);
		addBlock(world, basePos, 9, 10, 1, lunarLamp);
		addBlock(world, basePos, 9, 10, 9, lunarLamp);
	}
}
