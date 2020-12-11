package net.tslat.aoa3.worldgen.structures.runandor;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.functional.light.LampBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class SpectralCage extends AoAStructure { //StructureSize: 17x5x11
	private static final BlockState stone = AoABlocks.RUNIC_STONE.get().getDefaultState();
	private static final BlockState ironBars = Blocks.IRON_BARS.getDefaultState();
	private static final BlockState whitewashBricks = AoABlocks.WHITEWASH_BRICKS.get().getDefaultState();
	private static final BlockState spectralWizardSpawner = Blocks.SPAWNER.getDefaultState();
	private static final BlockState neonLapisLamp = AoABlocks.NEON_LAPIS_LAMP.get().getDefaultState().with(LampBlock.LIT, false);

	public SpectralCage() {
		super("SpectralCage");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, stone);
		addBlock(world, basePos, 0, 0, 1, ironBars);
		addBlock(world, basePos, 0, 0, 2, ironBars);
		addBlock(world, basePos, 0, 0, 3, ironBars);
		addBlock(world, basePos, 0, 0, 4, ironBars);
		addBlock(world, basePos, 0, 0, 5, ironBars);
		addBlock(world, basePos, 0, 0, 6, ironBars);
		addBlock(world, basePos, 0, 0, 7, ironBars);
		addBlock(world, basePos, 0, 0, 8, ironBars);
		addBlock(world, basePos, 0, 0, 9, ironBars);
		addBlock(world, basePos, 0, 0, 10, stone);
		addBlock(world, basePos, 1, 0, 0, ironBars);
		addBlock(world, basePos, 1, 0, 10, ironBars);
		addBlock(world, basePos, 2, 0, 0, ironBars);
		addBlock(world, basePos, 2, 0, 10, ironBars);
		addBlock(world, basePos, 3, 0, 0, ironBars);
		addBlock(world, basePos, 3, 0, 10, ironBars);
		addBlock(world, basePos, 4, 0, 0, ironBars);
		addBlock(world, basePos, 4, 0, 5, whitewashBricks);
		addBlock(world, basePos, 4, 0, 10, ironBars);
		addBlock(world, basePos, 5, 0, 0, ironBars);
		addBlock(world, basePos, 5, 0, 10, ironBars);
		addBlock(world, basePos, 6, 0, 0, ironBars);
		addBlock(world, basePos, 6, 0, 10, ironBars);
		addBlock(world, basePos, 7, 0, 0, ironBars);
		addBlock(world, basePos, 7, 0, 10, ironBars);
		addBlock(world, basePos, 8, 0, 0, stone);
		addBlock(world, basePos, 8, 0, 1, ironBars);
		addBlock(world, basePos, 8, 0, 2, ironBars);
		addBlock(world, basePos, 8, 0, 3, stone);
		addBlock(world, basePos, 8, 0, 7, stone);
		addBlock(world, basePos, 8, 0, 8, ironBars);
		addBlock(world, basePos, 8, 0, 9, ironBars);
		addBlock(world, basePos, 8, 0, 10, stone);
		addBlock(world, basePos, 9, 0, 3, ironBars);
		addBlock(world, basePos, 9, 0, 7, ironBars);
		addBlock(world, basePos, 10, 0, 3, ironBars);
		addBlock(world, basePos, 10, 0, 7, ironBars);
		addBlock(world, basePos, 11, 0, 3, ironBars);
		addBlock(world, basePos, 11, 0, 7, ironBars);
		addBlock(world, basePos, 12, 0, 3, ironBars);
		addBlock(world, basePos, 12, 0, 7, ironBars);
		addBlock(world, basePos, 13, 0, 3, ironBars);
		addBlock(world, basePos, 13, 0, 7, ironBars);
		addBlock(world, basePos, 14, 0, 3, ironBars);
		addBlock(world, basePos, 14, 0, 7, ironBars);
		addBlock(world, basePos, 15, 0, 3, ironBars);
		addBlock(world, basePos, 15, 0, 7, ironBars);
		addBlock(world, basePos, 16, 0, 3, stone);
		addBlock(world, basePos, 16, 0, 7, stone);
		addBlock(world, basePos, 0, 1, 0, stone);
		addBlock(world, basePos, 0, 1, 1, ironBars);
		addBlock(world, basePos, 0, 1, 2, ironBars);
		addBlock(world, basePos, 0, 1, 3, ironBars);
		addBlock(world, basePos, 0, 1, 4, ironBars);
		addBlock(world, basePos, 0, 1, 5, ironBars);
		addBlock(world, basePos, 0, 1, 6, ironBars);
		addBlock(world, basePos, 0, 1, 7, ironBars);
		addBlock(world, basePos, 0, 1, 8, ironBars);
		addBlock(world, basePos, 0, 1, 9, ironBars);
		addBlock(world, basePos, 0, 1, 10, stone);
		addBlock(world, basePos, 1, 1, 0, ironBars);
		addBlock(world, basePos, 1, 1, 10, ironBars);
		addBlock(world, basePos, 2, 1, 0, ironBars);
		addBlock(world, basePos, 2, 1, 10, ironBars);
		addBlock(world, basePos, 3, 1, 0, ironBars);
		addBlock(world, basePos, 3, 1, 10, ironBars);
		addBlock(world, basePos, 4, 1, 0, ironBars);
		addBlock(world, basePos, 4, 1, 5, neonLapisLamp);
		addBlock(world, basePos, 4, 1, 10, ironBars);
		addBlock(world, basePos, 5, 1, 0, ironBars);
		addBlock(world, basePos, 5, 1, 10, ironBars);
		addBlock(world, basePos, 6, 1, 0, ironBars);
		addBlock(world, basePos, 6, 1, 10, ironBars);
		addBlock(world, basePos, 7, 1, 0, ironBars);
		addBlock(world, basePos, 7, 1, 10, ironBars);
		addBlock(world, basePos, 8, 1, 0, stone);
		addBlock(world, basePos, 8, 1, 1, ironBars);
		addBlock(world, basePos, 8, 1, 2, ironBars);
		addBlock(world, basePos, 8, 1, 3, stone);
		addBlock(world, basePos, 8, 1, 7, stone);
		addBlock(world, basePos, 8, 1, 8, ironBars);
		addBlock(world, basePos, 8, 1, 9, ironBars);
		addBlock(world, basePos, 8, 1, 10, stone);
		addBlock(world, basePos, 9, 1, 3, ironBars);
		addBlock(world, basePos, 9, 1, 7, ironBars);
		addBlock(world, basePos, 10, 1, 3, ironBars);
		addBlock(world, basePos, 10, 1, 7, ironBars);
		addBlock(world, basePos, 11, 1, 3, ironBars);
		addBlock(world, basePos, 11, 1, 7, ironBars);
		addBlock(world, basePos, 12, 1, 3, ironBars);
		addBlock(world, basePos, 12, 1, 7, ironBars);
		addBlock(world, basePos, 13, 1, 3, ironBars);
		addBlock(world, basePos, 13, 1, 7, ironBars);
		addBlock(world, basePos, 14, 1, 3, ironBars);
		addBlock(world, basePos, 14, 1, 7, ironBars);
		addBlock(world, basePos, 15, 1, 3, ironBars);
		addBlock(world, basePos, 15, 1, 7, ironBars);
		addBlock(world, basePos, 16, 1, 3, stone);
		addBlock(world, basePos, 16, 1, 7, stone);
		addBlock(world, basePos, 0, 2, 0, stone);
		addBlock(world, basePos, 0, 2, 1, stone);
		addBlock(world, basePos, 0, 2, 2, stone);
		addBlock(world, basePos, 0, 2, 3, stone);
		addBlock(world, basePos, 0, 2, 4, stone);
		addBlock(world, basePos, 0, 2, 5, stone);
		addBlock(world, basePos, 0, 2, 6, stone);
		addBlock(world, basePos, 0, 2, 7, stone);
		addBlock(world, basePos, 0, 2, 8, stone);
		addBlock(world, basePos, 0, 2, 9, stone);
		addBlock(world, basePos, 0, 2, 10, stone);
		addBlock(world, basePos, 1, 2, 0, stone);
		addBlock(world, basePos, 1, 2, 10, stone);
		addBlock(world, basePos, 2, 2, 0, stone);
		addBlock(world, basePos, 2, 2, 10, stone);
		addBlock(world, basePos, 3, 2, 0, stone);
		addBlock(world, basePos, 3, 2, 10, stone);
		addBlock(world, basePos, 4, 2, 0, stone);
		addBlock(world, basePos, 4, 2, 5, spectralWizardSpawner);
		addBlock(world, basePos, 4, 2, 10, stone);
		addBlock(world, basePos, 5, 2, 0, stone);
		addBlock(world, basePos, 5, 2, 10, stone);
		addBlock(world, basePos, 6, 2, 0, stone);
		addBlock(world, basePos, 6, 2, 10, stone);
		addBlock(world, basePos, 7, 2, 0, stone);
		addBlock(world, basePos, 7, 2, 10, stone);
		addBlock(world, basePos, 8, 2, 0, stone);
		addBlock(world, basePos, 8, 2, 1, stone);
		addBlock(world, basePos, 8, 2, 2, stone);
		addBlock(world, basePos, 8, 2, 3, stone);
		addBlock(world, basePos, 8, 2, 7, stone);
		addBlock(world, basePos, 8, 2, 8, stone);
		addBlock(world, basePos, 8, 2, 9, stone);
		addBlock(world, basePos, 8, 2, 10, stone);
		addBlock(world, basePos, 9, 2, 3, stone);
		addBlock(world, basePos, 9, 2, 7, stone);
		addBlock(world, basePos, 10, 2, 3, stone);
		addBlock(world, basePos, 10, 2, 7, stone);
		addBlock(world, basePos, 11, 2, 3, stone);
		addBlock(world, basePos, 11, 2, 7, stone);
		addBlock(world, basePos, 12, 2, 3, stone);
		addBlock(world, basePos, 12, 2, 7, stone);
		addBlock(world, basePos, 13, 2, 3, stone);
		addBlock(world, basePos, 13, 2, 7, stone);
		addBlock(world, basePos, 14, 2, 3, stone);
		addBlock(world, basePos, 14, 2, 7, stone);
		addBlock(world, basePos, 15, 2, 3, stone);
		addBlock(world, basePos, 15, 2, 7, stone);
		addBlock(world, basePos, 16, 2, 3, stone);
		addBlock(world, basePos, 16, 2, 7, stone);
		addBlock(world, basePos, 4, 3, 5, neonLapisLamp);
		addBlock(world, basePos, 4, 4, 5, whitewashBricks);
	}

	@Override
	protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {
		initSpawner(world, basePos.add(4, 2, 5), AoAEntities.Mobs.SPECTRAL_WIZARD.get());
	}
}
