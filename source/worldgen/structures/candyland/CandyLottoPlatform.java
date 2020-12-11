package net.tslat.aoa3.worldgen.structures.candyland;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.lottoman.CandiedLottomanEntity;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class CandyLottoPlatform extends AoAStructure { //StructureSize: 13x12x13
	private static final BlockState redCandy = AoABlocks.RED_CANDY.get().getDefaultState();
	private static final BlockState whiteCandy = AoABlocks.WHITE_CANDY.get().getDefaultState();

	public CandyLottoPlatform() {
		super("CandyLottoPlatform");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 6, 0, 6, whiteCandy);
		addBlock(world, basePos, 6, 1, 6, redCandy);
		addBlock(world, basePos, 6, 2, 6, whiteCandy);
		addBlock(world, basePos, 6, 3, 6, redCandy);
		addBlock(world, basePos, 6, 4, 6, whiteCandy);
		addBlock(world, basePos, 6, 5, 6, redCandy);
		addBlock(world, basePos, 6, 6, 6, whiteCandy);
		addBlock(world, basePos, 6, 7, 6, redCandy);
		addBlock(world, basePos, 2, 8, 2, whiteCandy);
		addBlock(world, basePos, 2, 8, 3, whiteCandy);
		addBlock(world, basePos, 2, 8, 4, whiteCandy);
		addBlock(world, basePos, 2, 8, 5, whiteCandy);
		addBlock(world, basePos, 2, 8, 6, whiteCandy);
		addBlock(world, basePos, 2, 8, 7, whiteCandy);
		addBlock(world, basePos, 2, 8, 8, whiteCandy);
		addBlock(world, basePos, 2, 8, 9, whiteCandy);
		addBlock(world, basePos, 2, 8, 10, whiteCandy);
		addBlock(world, basePos, 3, 8, 2, whiteCandy);
		addBlock(world, basePos, 3, 8, 3, redCandy);
		addBlock(world, basePos, 3, 8, 4, redCandy);
		addBlock(world, basePos, 3, 8, 5, redCandy);
		addBlock(world, basePos, 3, 8, 6, redCandy);
		addBlock(world, basePos, 3, 8, 7, redCandy);
		addBlock(world, basePos, 3, 8, 8, redCandy);
		addBlock(world, basePos, 3, 8, 9, redCandy);
		addBlock(world, basePos, 3, 8, 10, whiteCandy);
		addBlock(world, basePos, 4, 8, 2, whiteCandy);
		addBlock(world, basePos, 4, 8, 3, redCandy);
		addBlock(world, basePos, 4, 8, 4, whiteCandy);
		addBlock(world, basePos, 4, 8, 5, whiteCandy);
		addBlock(world, basePos, 4, 8, 6, whiteCandy);
		addBlock(world, basePos, 4, 8, 7, whiteCandy);
		addBlock(world, basePos, 4, 8, 8, whiteCandy);
		addBlock(world, basePos, 4, 8, 9, redCandy);
		addBlock(world, basePos, 4, 8, 10, whiteCandy);
		addBlock(world, basePos, 5, 8, 2, whiteCandy);
		addBlock(world, basePos, 5, 8, 3, redCandy);
		addBlock(world, basePos, 5, 8, 4, whiteCandy);
		addBlock(world, basePos, 5, 8, 5, redCandy);
		addBlock(world, basePos, 5, 8, 6, redCandy);
		addBlock(world, basePos, 5, 8, 7, redCandy);
		addBlock(world, basePos, 5, 8, 8, whiteCandy);
		addBlock(world, basePos, 5, 8, 9, redCandy);
		addBlock(world, basePos, 5, 8, 10, whiteCandy);
		addBlock(world, basePos, 6, 8, 2, whiteCandy);
		addBlock(world, basePos, 6, 8, 3, redCandy);
		addBlock(world, basePos, 6, 8, 4, whiteCandy);
		addBlock(world, basePos, 6, 8, 5, redCandy);
		addBlock(world, basePos, 6, 8, 6, whiteCandy);
		addBlock(world, basePos, 6, 8, 7, redCandy);
		addBlock(world, basePos, 6, 8, 8, whiteCandy);
		addBlock(world, basePos, 6, 8, 9, redCandy);
		addBlock(world, basePos, 6, 8, 10, whiteCandy);
		addBlock(world, basePos, 7, 8, 2, whiteCandy);
		addBlock(world, basePos, 7, 8, 3, redCandy);
		addBlock(world, basePos, 7, 8, 4, whiteCandy);
		addBlock(world, basePos, 7, 8, 5, redCandy);
		addBlock(world, basePos, 7, 8, 6, redCandy);
		addBlock(world, basePos, 7, 8, 7, redCandy);
		addBlock(world, basePos, 7, 8, 8, whiteCandy);
		addBlock(world, basePos, 7, 8, 9, redCandy);
		addBlock(world, basePos, 7, 8, 10, whiteCandy);
		addBlock(world, basePos, 8, 8, 2, whiteCandy);
		addBlock(world, basePos, 8, 8, 3, redCandy);
		addBlock(world, basePos, 8, 8, 4, whiteCandy);
		addBlock(world, basePos, 8, 8, 5, whiteCandy);
		addBlock(world, basePos, 8, 8, 6, whiteCandy);
		addBlock(world, basePos, 8, 8, 7, whiteCandy);
		addBlock(world, basePos, 8, 8, 8, whiteCandy);
		addBlock(world, basePos, 8, 8, 9, redCandy);
		addBlock(world, basePos, 8, 8, 10, whiteCandy);
		addBlock(world, basePos, 9, 8, 2, whiteCandy);
		addBlock(world, basePos, 9, 8, 3, redCandy);
		addBlock(world, basePos, 9, 8, 4, redCandy);
		addBlock(world, basePos, 9, 8, 5, redCandy);
		addBlock(world, basePos, 9, 8, 6, redCandy);
		addBlock(world, basePos, 9, 8, 7, redCandy);
		addBlock(world, basePos, 9, 8, 8, redCandy);
		addBlock(world, basePos, 9, 8, 9, redCandy);
		addBlock(world, basePos, 9, 8, 10, whiteCandy);
		addBlock(world, basePos, 10, 8, 2, whiteCandy);
		addBlock(world, basePos, 10, 8, 3, whiteCandy);
		addBlock(world, basePos, 10, 8, 4, whiteCandy);
		addBlock(world, basePos, 10, 8, 5, whiteCandy);
		addBlock(world, basePos, 10, 8, 6, whiteCandy);
		addBlock(world, basePos, 10, 8, 7, whiteCandy);
		addBlock(world, basePos, 10, 8, 8, whiteCandy);
		addBlock(world, basePos, 10, 8, 9, whiteCandy);
		addBlock(world, basePos, 10, 8, 10, whiteCandy);
		addBlock(world, basePos, 2, 9, 2, whiteCandy);
		addBlock(world, basePos, 2, 9, 10, whiteCandy);
		addBlock(world, basePos, 10, 9, 2, whiteCandy);
		addBlock(world, basePos, 10, 9, 10, whiteCandy);
		addBlock(world, basePos, 0, 10, 2, redCandy);
		addBlock(world, basePos, 0, 10, 10, redCandy);
		addBlock(world, basePos, 2, 10, 0, redCandy);
		addBlock(world, basePos, 2, 10, 2, redCandy);
		addBlock(world, basePos, 2, 10, 10, redCandy);
		addBlock(world, basePos, 2, 10, 12, redCandy);
		addBlock(world, basePos, 10, 10, 0, redCandy);
		addBlock(world, basePos, 10, 10, 2, redCandy);
		addBlock(world, basePos, 10, 10, 10, redCandy);
		addBlock(world, basePos, 10, 10, 12, redCandy);
		addBlock(world, basePos, 12, 10, 2, redCandy);
		addBlock(world, basePos, 12, 10, 10, redCandy);
		addBlock(world, basePos, 0, 11, 2, whiteCandy);
		addBlock(world, basePos, 0, 11, 10, whiteCandy);
		addBlock(world, basePos, 1, 11, 2, redCandy);
		addBlock(world, basePos, 1, 11, 10, redCandy);
		addBlock(world, basePos, 2, 11, 0, whiteCandy);
		addBlock(world, basePos, 2, 11, 1, redCandy);
		addBlock(world, basePos, 2, 11, 2, whiteCandy);
		addBlock(world, basePos, 2, 11, 10, whiteCandy);
		addBlock(world, basePos, 2, 11, 11, redCandy);
		addBlock(world, basePos, 2, 11, 12, whiteCandy);
		addBlock(world, basePos, 10, 11, 0, whiteCandy);
		addBlock(world, basePos, 10, 11, 1, redCandy);
		addBlock(world, basePos, 10, 11, 2, whiteCandy);
		addBlock(world, basePos, 10, 11, 10, whiteCandy);
		addBlock(world, basePos, 10, 11, 11, redCandy);
		addBlock(world, basePos, 10, 11, 12, whiteCandy);
		addBlock(world, basePos, 11, 11, 2, redCandy);
		addBlock(world, basePos, 11, 11, 10, redCandy);
		addBlock(world, basePos, 12, 11, 2, whiteCandy);
		addBlock(world, basePos, 12, 11, 10, whiteCandy);
	}

	@Override
	protected void spawnEntities(IWorld world, Random rand, BlockPos basePos) {
		CandiedLottomanEntity lottoman = new CandiedLottomanEntity(AoAEntities.NPCs.CANDIED_LOTTOMAN.get(), world.getWorld());

		lottoman.setLocationAndAngles(basePos.getX() + 6, basePos.getY() + 9, basePos.getZ() + 6, rand.nextFloat() * 360, 0);
		world.addEntity(lottoman);
	}
}
