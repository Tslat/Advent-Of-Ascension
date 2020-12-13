package net.tslat.aoa3.worldgen.structures.iromine;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.lottoman.GoldenLottomanEntity;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class IroPillar extends AoAStructure { //StructureSize: 7x19x7
	private static final BlockState glass = AoABlocks.IRO_GLASS.get().getDefaultState();
	private static final BlockState stripedBrick = AoABlocks.IRO_STRIPED_BRICKS.get().getDefaultState();
	private static final BlockState iropole = AoABlocks.IROPOLE.get().getDefaultState();

	public IroPillar() {
		super("IroPillar");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, stripedBrick);
		addBlock(world, basePos, 3, 1, 3, iropole);
		addBlock(world, basePos, 3, 2, 3, iropole);
		addBlock(world, basePos, 3, 3, 3, iropole);
		addBlock(world, basePos, 3, 4, 3, iropole);
		addBlock(world, basePos, 3, 5, 3, stripedBrick);
		addBlock(world, basePos, 3, 6, 3, iropole);
		addBlock(world, basePos, 3, 7, 3, iropole);
		addBlock(world, basePos, 1, 8, 3, stripedBrick);
		addBlock(world, basePos, 2, 8, 3, stripedBrick);
		addBlock(world, basePos, 3, 8, 1, stripedBrick);
		addBlock(world, basePos, 3, 8, 2, stripedBrick);
		addBlock(world, basePos, 3, 8, 3, stripedBrick);
		addBlock(world, basePos, 3, 8, 4, stripedBrick);
		addBlock(world, basePos, 3, 8, 5, stripedBrick);
		addBlock(world, basePos, 4, 8, 3, stripedBrick);
		addBlock(world, basePos, 5, 8, 3, stripedBrick);
		addBlock(world, basePos, 1, 9, 3, iropole);
		addBlock(world, basePos, 3, 9, 1, iropole);
		addBlock(world, basePos, 3, 9, 5, iropole);
		addBlock(world, basePos, 5, 9, 3, iropole);
		addBlock(world, basePos, 1, 10, 3, iropole);
		addBlock(world, basePos, 3, 10, 1, iropole);
		addBlock(world, basePos, 3, 10, 5, iropole);
		addBlock(world, basePos, 5, 10, 3, iropole);
		addBlock(world, basePos, 1, 11, 3, iropole);
		addBlock(world, basePos, 3, 11, 1, iropole);
		addBlock(world, basePos, 3, 11, 5, iropole);
		addBlock(world, basePos, 5, 11, 3, iropole);
		addBlock(world, basePos, 1, 12, 3, iropole);
		addBlock(world, basePos, 3, 12, 1, iropole);
		addBlock(world, basePos, 3, 12, 5, iropole);
		addBlock(world, basePos, 5, 12, 3, iropole);
		addBlock(world, basePos, 1, 13, 1, stripedBrick);
		addBlock(world, basePos, 1, 13, 2, stripedBrick);
		addBlock(world, basePos, 1, 13, 3, stripedBrick);
		addBlock(world, basePos, 1, 13, 4, stripedBrick);
		addBlock(world, basePos, 1, 13, 5, stripedBrick);
		addBlock(world, basePos, 2, 13, 1, stripedBrick);
		addBlock(world, basePos, 2, 13, 2, glass);
		addBlock(world, basePos, 2, 13, 3, glass);
		addBlock(world, basePos, 2, 13, 4, glass);
		addBlock(world, basePos, 2, 13, 5, stripedBrick);
		addBlock(world, basePos, 3, 13, 1, stripedBrick);
		addBlock(world, basePos, 3, 13, 2, glass);
		addBlock(world, basePos, 3, 13, 3, stripedBrick);
		addBlock(world, basePos, 3, 13, 4, glass);
		addBlock(world, basePos, 3, 13, 5, stripedBrick);
		addBlock(world, basePos, 4, 13, 1, stripedBrick);
		addBlock(world, basePos, 4, 13, 2, glass);
		addBlock(world, basePos, 4, 13, 3, glass);
		addBlock(world, basePos, 4, 13, 4, glass);
		addBlock(world, basePos, 4, 13, 5, stripedBrick);
		addBlock(world, basePos, 5, 13, 1, stripedBrick);
		addBlock(world, basePos, 5, 13, 2, stripedBrick);
		addBlock(world, basePos, 5, 13, 3, stripedBrick);
		addBlock(world, basePos, 5, 13, 4, stripedBrick);
		addBlock(world, basePos, 5, 13, 5, stripedBrick);
		addBlock(world, basePos, 0, 14, 0, stripedBrick);
		addBlock(world, basePos, 0, 14, 1, stripedBrick);
		addBlock(world, basePos, 0, 14, 2, stripedBrick);
		addBlock(world, basePos, 0, 14, 3, stripedBrick);
		addBlock(world, basePos, 0, 14, 4, stripedBrick);
		addBlock(world, basePos, 0, 14, 5, stripedBrick);
		addBlock(world, basePos, 0, 14, 6, stripedBrick);
		addBlock(world, basePos, 1, 14, 0, stripedBrick);
		addBlock(world, basePos, 1, 14, 6, stripedBrick);
		addBlock(world, basePos, 2, 14, 0, stripedBrick);
		addBlock(world, basePos, 2, 14, 6, stripedBrick);
		addBlock(world, basePos, 3, 14, 0, stripedBrick);
		addBlock(world, basePos, 3, 14, 6, stripedBrick);
		addBlock(world, basePos, 4, 14, 0, stripedBrick);
		addBlock(world, basePos, 4, 14, 6, stripedBrick);
		addBlock(world, basePos, 5, 14, 0, stripedBrick);
		addBlock(world, basePos, 5, 14, 6, stripedBrick);
		addBlock(world, basePos, 6, 14, 0, stripedBrick);
		addBlock(world, basePos, 6, 14, 1, stripedBrick);
		addBlock(world, basePos, 6, 14, 2, stripedBrick);
		addBlock(world, basePos, 6, 14, 3, stripedBrick);
		addBlock(world, basePos, 6, 14, 4, stripedBrick);
		addBlock(world, basePos, 6, 14, 5, stripedBrick);
		addBlock(world, basePos, 6, 14, 6, stripedBrick);
		addBlock(world, basePos, 0, 15, 0, stripedBrick);
		addBlock(world, basePos, 0, 15, 1, glass);
		addBlock(world, basePos, 0, 15, 2, glass);
		addBlock(world, basePos, 0, 15, 3, glass);
		addBlock(world, basePos, 0, 15, 4, glass);
		addBlock(world, basePos, 0, 15, 5, glass);
		addBlock(world, basePos, 0, 15, 6, stripedBrick);
		addBlock(world, basePos, 1, 15, 0, glass);
		addBlock(world, basePos, 1, 15, 6, glass);
		addBlock(world, basePos, 2, 15, 0, glass);
		addBlock(world, basePos, 2, 15, 6, glass);
		addBlock(world, basePos, 3, 15, 0, glass);
		addBlock(world, basePos, 3, 15, 6, glass);
		addBlock(world, basePos, 4, 15, 0, glass);
		addBlock(world, basePos, 4, 15, 6, glass);
		addBlock(world, basePos, 5, 15, 0, glass);
		addBlock(world, basePos, 5, 15, 6, glass);
		addBlock(world, basePos, 6, 15, 0, stripedBrick);
		addBlock(world, basePos, 6, 15, 1, glass);
		addBlock(world, basePos, 6, 15, 2, glass);
		addBlock(world, basePos, 6, 15, 3, glass);
		addBlock(world, basePos, 6, 15, 4, glass);
		addBlock(world, basePos, 6, 15, 5, glass);
		addBlock(world, basePos, 6, 15, 6, stripedBrick);
		addBlock(world, basePos, 0, 16, 0, stripedBrick);
		addBlock(world, basePos, 0, 16, 6, stripedBrick);
		addBlock(world, basePos, 6, 16, 0, stripedBrick);
		addBlock(world, basePos, 6, 16, 6, stripedBrick);
		addBlock(world, basePos, 0, 17, 0, stripedBrick);
		addBlock(world, basePos, 0, 17, 6, stripedBrick);
		addBlock(world, basePos, 6, 17, 0, stripedBrick);
		addBlock(world, basePos, 6, 17, 6, stripedBrick);
		addBlock(world, basePos, 0, 18, 0, glass);
		addBlock(world, basePos, 0, 18, 6, glass);
		addBlock(world, basePos, 6, 18, 0, glass);
		addBlock(world, basePos, 6, 18, 6, glass);
	}

	@Override
	protected void spawnEntities(IWorld world, Random rand, BlockPos basePos) {
		GoldenLottomanEntity lottoman = new GoldenLottomanEntity(AoAEntities.NPCs.GOLDEN_LOTTOMAN.get(), world.getWorld());

		lottoman.setLocationAndAngles(basePos.getX() + 4, basePos.getY() + 14, basePos.getZ() + 4, rand.nextFloat() * 360, 0);
		world.addEntity(lottoman);
	}
}
