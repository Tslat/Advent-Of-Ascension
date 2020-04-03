package net.tslat.aoa3.structure.precasia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.npcs.lottoman.EntityPrecasianLottoman;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class JungleLottoHut extends AoAStructure { //StructureSize: 9x11x9
	private static final IBlockState cycadeLeaves = BlockRegister.leavesCycade.getDefaultState();
	private static final IBlockState cycadeLog = BlockRegister.logCycade.getDefaultState();

	public JungleLottoHut() {
		super("JungleLottoHut");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 2, 0, 2, cycadeLog);
		addBlock(world, basePos, 2, 0, 6, cycadeLog);
		addBlock(world, basePos, 4, 0, 4, cycadeLog);
		addBlock(world, basePos, 6, 0, 2, cycadeLog);
		addBlock(world, basePos, 6, 0, 6, cycadeLog);
		addBlock(world, basePos, 2, 1, 2, cycadeLog);
		addBlock(world, basePos, 2, 1, 6, cycadeLog);
		addBlock(world, basePos, 4, 1, 4, cycadeLog);
		addBlock(world, basePos, 6, 1, 2, cycadeLog);
		addBlock(world, basePos, 6, 1, 6, cycadeLog);
		addBlock(world, basePos, 2, 2, 2, cycadeLog);
		addBlock(world, basePos, 2, 2, 6, cycadeLog);
		addBlock(world, basePos, 4, 2, 4, cycadeLog);
		addBlock(world, basePos, 6, 2, 2, cycadeLog);
		addBlock(world, basePos, 6, 2, 6, cycadeLog);
		addBlock(world, basePos, 2, 3, 2, cycadeLog);
		addBlock(world, basePos, 2, 3, 6, cycadeLog);
		addBlock(world, basePos, 4, 3, 4, cycadeLog);
		addBlock(world, basePos, 6, 3, 2, cycadeLog);
		addBlock(world, basePos, 6, 3, 6, cycadeLog);
		addBlock(world, basePos, 2, 4, 2, cycadeLog);
		addBlock(world, basePos, 2, 4, 3, cycadeLog);
		addBlock(world, basePos, 2, 4, 4, cycadeLog);
		addBlock(world, basePos, 2, 4, 5, cycadeLog);
		addBlock(world, basePos, 2, 4, 6, cycadeLog);
		addBlock(world, basePos, 3, 4, 2, cycadeLog);
		addBlock(world, basePos, 3, 4, 3, cycadeLog);
		addBlock(world, basePos, 3, 4, 4, cycadeLog);
		addBlock(world, basePos, 3, 4, 5, cycadeLog);
		addBlock(world, basePos, 3, 4, 6, cycadeLog);
		addBlock(world, basePos, 4, 4, 2, cycadeLog);
		addBlock(world, basePos, 4, 4, 3, cycadeLog);
		addBlock(world, basePos, 4, 4, 4, cycadeLog);
		addBlock(world, basePos, 4, 4, 5, cycadeLog);
		addBlock(world, basePos, 4, 4, 6, cycadeLog);
		addBlock(world, basePos, 5, 4, 2, cycadeLog);
		addBlock(world, basePos, 5, 4, 3, cycadeLog);
		addBlock(world, basePos, 5, 4, 4, cycadeLog);
		addBlock(world, basePos, 5, 4, 5, cycadeLog);
		addBlock(world, basePos, 5, 4, 6, cycadeLog);
		addBlock(world, basePos, 6, 4, 2, cycadeLog);
		addBlock(world, basePos, 6, 4, 3, cycadeLog);
		addBlock(world, basePos, 6, 4, 4, cycadeLog);
		addBlock(world, basePos, 6, 4, 5, cycadeLog);
		addBlock(world, basePos, 6, 4, 6, cycadeLog);
		addBlock(world, basePos, 1, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 1, 5, 2, cycadeLeaves);
		addBlock(world, basePos, 1, 5, 3, cycadeLeaves);
		addBlock(world, basePos, 1, 5, 4, cycadeLeaves);
		addBlock(world, basePos, 1, 5, 5, cycadeLeaves);
		addBlock(world, basePos, 1, 5, 6, cycadeLeaves);
		addBlock(world, basePos, 1, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 2, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 2, 5, 2, cycadeLog);
		addBlock(world, basePos, 2, 5, 6, cycadeLog);
		addBlock(world, basePos, 2, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 3, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 3, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 4, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 4, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 5, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 5, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 6, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 6, 5, 2, cycadeLog);
		addBlock(world, basePos, 6, 5, 6, cycadeLog);
		addBlock(world, basePos, 6, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 1, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 2, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 3, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 4, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 5, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 6, cycadeLeaves);
		addBlock(world, basePos, 7, 5, 7, cycadeLeaves);
		addBlock(world, basePos, 2, 6, 2, cycadeLog);
		addBlock(world, basePos, 2, 6, 6, cycadeLog);
		addBlock(world, basePos, 6, 6, 2, cycadeLog);
		addBlock(world, basePos, 6, 6, 6, cycadeLog);
		addBlock(world, basePos, 2, 7, 2, cycadeLog);
		addBlock(world, basePos, 2, 7, 6, cycadeLog);
		addBlock(world, basePos, 6, 7, 2, cycadeLog);
		addBlock(world, basePos, 6, 7, 6, cycadeLog);
		addBlock(world, basePos, 0, 8, 2, cycadeLeaves);
		addBlock(world, basePos, 0, 8, 3, cycadeLeaves);
		addBlock(world, basePos, 0, 8, 4, cycadeLeaves);
		addBlock(world, basePos, 0, 8, 5, cycadeLeaves);
		addBlock(world, basePos, 0, 8, 6, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 2, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 3, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 4, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 5, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 6, cycadeLeaves);
		addBlock(world, basePos, 1, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 2, 8, 0, cycadeLeaves);
		addBlock(world, basePos, 2, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 2, 8, 2, cycadeLog);
		addBlock(world, basePos, 2, 8, 6, cycadeLog);
		addBlock(world, basePos, 2, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 2, 8, 8, cycadeLeaves);
		addBlock(world, basePos, 3, 8, 0, cycadeLeaves);
		addBlock(world, basePos, 3, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 3, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 3, 8, 8, cycadeLeaves);
		addBlock(world, basePos, 4, 8, 0, cycadeLeaves);
		addBlock(world, basePos, 4, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 4, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 4, 8, 8, cycadeLeaves);
		addBlock(world, basePos, 5, 8, 0, cycadeLeaves);
		addBlock(world, basePos, 5, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 5, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 5, 8, 8, cycadeLeaves);
		addBlock(world, basePos, 6, 8, 0, cycadeLeaves);
		addBlock(world, basePos, 6, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 6, 8, 2, cycadeLog);
		addBlock(world, basePos, 6, 8, 6, cycadeLog);
		addBlock(world, basePos, 6, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 6, 8, 8, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 1, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 2, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 3, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 4, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 5, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 6, cycadeLeaves);
		addBlock(world, basePos, 7, 8, 7, cycadeLeaves);
		addBlock(world, basePos, 8, 8, 2, cycadeLeaves);
		addBlock(world, basePos, 8, 8, 3, cycadeLeaves);
		addBlock(world, basePos, 8, 8, 4, cycadeLeaves);
		addBlock(world, basePos, 8, 8, 5, cycadeLeaves);
		addBlock(world, basePos, 8, 8, 6, cycadeLeaves);
		addBlock(world, basePos, 2, 9, 2, cycadeLeaves);
		addBlock(world, basePos, 2, 9, 3, cycadeLeaves);
		addBlock(world, basePos, 2, 9, 4, cycadeLeaves);
		addBlock(world, basePos, 2, 9, 5, cycadeLeaves);
		addBlock(world, basePos, 2, 9, 6, cycadeLeaves);
		addBlock(world, basePos, 3, 9, 2, cycadeLeaves);
		addBlock(world, basePos, 3, 9, 6, cycadeLeaves);
		addBlock(world, basePos, 4, 9, 2, cycadeLeaves);
		addBlock(world, basePos, 4, 9, 6, cycadeLeaves);
		addBlock(world, basePos, 5, 9, 2, cycadeLeaves);
		addBlock(world, basePos, 5, 9, 6, cycadeLeaves);
		addBlock(world, basePos, 6, 9, 2, cycadeLeaves);
		addBlock(world, basePos, 6, 9, 3, cycadeLeaves);
		addBlock(world, basePos, 6, 9, 4, cycadeLeaves);
		addBlock(world, basePos, 6, 9, 5, cycadeLeaves);
		addBlock(world, basePos, 6, 9, 6, cycadeLeaves);
		addBlock(world, basePos, 3, 10, 3, cycadeLeaves);
		addBlock(world, basePos, 3, 10, 4, cycadeLeaves);
		addBlock(world, basePos, 3, 10, 5, cycadeLeaves);
		addBlock(world, basePos, 4, 10, 3, cycadeLeaves);
		addBlock(world, basePos, 4, 10, 4, cycadeLeaves);
		addBlock(world, basePos, 4, 10, 5, cycadeLeaves);
		addBlock(world, basePos, 5, 10, 3, cycadeLeaves);
		addBlock(world, basePos, 5, 10, 4, cycadeLeaves);
		addBlock(world, basePos, 5, 10, 5, cycadeLeaves);
	}

	@Override
	protected void spawnEntities(World world, Random rand, BlockPos basePos) {
		EntityPrecasianLottoman lottoman = new EntityPrecasianLottoman(world);

		lottoman.setLocationAndAngles(basePos.getX() + 4, basePos.getY() + 5, basePos.getZ() + 4, rand.nextFloat() * 360, 0);
		world.spawnEntity(lottoman);
	}
}
