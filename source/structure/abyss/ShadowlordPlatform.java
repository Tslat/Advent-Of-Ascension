package net.tslat.aoa3.structure.abyss;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.lamps.LampBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class ShadowlordPlatform extends AoAStructure { //StructureSize: 5x7x5
	private static final IBlockState planks = BlockRegister.SHADOW_PLANKS.getDefaultState();
	private static final IBlockState altar = BlockRegister.SHADOW_ALTAR.getDefaultState();
	private static final IBlockState logShadowX = BlockRegister.SHADOW_LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
	private static final IBlockState logShadow = BlockRegister.SHADOW_LOG.getDefaultState();
	private static final IBlockState shadowFence = BlockRegister.SHADOW_FENCE.getDefaultState();
	private static final IBlockState logShadowZ = BlockRegister.SHADOW_LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
	private static final IBlockState bloodstoneLamp = BlockRegister.BLOODSTONE_LAMP.getDefaultState().withProperty(LampBlock.FIXED_LAMP, true);

	public ShadowlordPlatform() {
		super("ShadowlordPlatform");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = basePos.getY() - 1;
		BlockPos blockPos;

		while (y > 0 && world.getBlockState((blockPos = new BlockPos(basePos.getX(), y, basePos.getZ() + 2))).getMaterial().isReplaceable()) {
			world.setBlockState(blockPos, logShadow);

			y--;
		}

		y = basePos.getY() - 1;

		while (y > 0 && world.getBlockState((blockPos = new BlockPos(basePos.getX() + 4, y, basePos.getZ() + 2))).getMaterial().isReplaceable()) {
			world.setBlockState(blockPos, logShadow);

			y--;
		}

		y = basePos.getY() - 1;

		while (y > 0 && world.getBlockState((blockPos = new BlockPos(basePos.getX() + 2, y, basePos.getZ()))).getMaterial().isReplaceable()) {
			world.setBlockState(blockPos, logShadow);

			y--;
		}

		y = basePos.getY() - 1;

		while (y > 0 && world.getBlockState((blockPos = new BlockPos(basePos.getX() + 2, y, basePos.getZ() + 4))).getMaterial().isReplaceable()) {
			world.setBlockState(blockPos, logShadow);

			y--;
		}

		addBlock(world, basePos, 0, 0, 2, logShadow);
		addBlock(world, basePos, 2, 0, 0, logShadow);
		addBlock(world, basePos, 2, 0, 2, shadowFence);
		addBlock(world, basePos, 2, 0, 4, logShadow);
		addBlock(world, basePos, 4, 0, 2, logShadow);
		addBlock(world, basePos, 0, 1, 2, logShadow);
		addBlock(world, basePos, 2, 1, 0, logShadow);
		addBlock(world, basePos, 2, 1, 2, shadowFence);
		addBlock(world, basePos, 2, 1, 4, logShadow);
		addBlock(world, basePos, 4, 1, 2, logShadow);
		addBlock(world, basePos, 0, 2, 2, logShadow);
		addBlock(world, basePos, 1, 2, 2, shadowFence);
		addBlock(world, basePos, 2, 2, 0, logShadow);
		addBlock(world, basePos, 2, 2, 1, shadowFence);
		addBlock(world, basePos, 2, 2, 2, shadowFence);
		addBlock(world, basePos, 2, 2, 3, shadowFence);
		addBlock(world, basePos, 2, 2, 4, logShadow);
		addBlock(world, basePos, 3, 2, 2, shadowFence);
		addBlock(world, basePos, 4, 2, 2, logShadow);
		addBlock(world, basePos, 0, 3, 2, logShadow);
		addBlock(world, basePos, 2, 3, 0, logShadow);
		addBlock(world, basePos, 2, 3, 2, shadowFence);
		addBlock(world, basePos, 2, 3, 4, logShadow);
		addBlock(world, basePos, 4, 3, 2, logShadow);
		addBlock(world, basePos, 0, 4, 0, logShadow);
		addBlock(world, basePos, 0, 4, 1, logShadowZ);
		addBlock(world, basePos, 0, 4, 2, logShadowZ);
		addBlock(world, basePos, 0, 4, 3, logShadowZ);
		addBlock(world, basePos, 0, 4, 4, logShadow);
		addBlock(world, basePos, 1, 4, 0, logShadowX);
		addBlock(world, basePos, 1, 4, 1, planks);
		addBlock(world, basePos, 1, 4, 2, planks);
		addBlock(world, basePos, 1, 4, 3, planks);
		addBlock(world, basePos, 1, 4, 4, logShadowX);
		addBlock(world, basePos, 2, 4, 0, logShadowX);
		addBlock(world, basePos, 2, 4, 1, planks);
		addBlock(world, basePos, 2, 4, 2, shadowFence);
		addBlock(world, basePos, 2, 4, 3, planks);
		addBlock(world, basePos, 2, 4, 4, logShadowX);
		addBlock(world, basePos, 3, 4, 0, logShadowX);
		addBlock(world, basePos, 3, 4, 1, planks);
		addBlock(world, basePos, 3, 4, 2, planks);
		addBlock(world, basePos, 3, 4, 3, planks);
		addBlock(world, basePos, 3, 4, 4, logShadowX);
		addBlock(world, basePos, 4, 4, 0, logShadow);
		addBlock(world, basePos, 4, 4, 1, logShadowZ);
		addBlock(world, basePos, 4, 4, 2, logShadowZ);
		addBlock(world, basePos, 4, 4, 3, logShadowZ);
		addBlock(world, basePos, 4, 4, 4, logShadow);
		addBlock(world, basePos, 0, 5, 0, shadowFence);
		addBlock(world, basePos, 0, 5, 4, shadowFence);
		addBlock(world, basePos, 2, 5, 2, altar);
		addBlock(world, basePos, 4, 5, 0, shadowFence);
		addBlock(world, basePos, 4, 5, 4, shadowFence);
		addBlock(world, basePos, 0, 6, 0, bloodstoneLamp);
		addBlock(world, basePos, 0, 6, 4, bloodstoneLamp);
		addBlock(world, basePos, 4, 6, 0, bloodstoneLamp);
		addBlock(world, basePos, 4, 6, 4, bloodstoneLamp);
	}
}
