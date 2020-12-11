package net.tslat.aoa3.worldgen.structures.gardencia;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class WizardFlower extends AoAStructure { //StructureSize: 11x10x11
	private static final BlockState gardencianBricks = AoABlocks.GARDENCIA_BRICKS.get().getDefaultState();
	private static final BlockState rosidianBricks = AoABlocks.ROSIDIAN_BRICKS.get().getDefaultState();
	private static final BlockState bluePetals = AoABlocks.BLUE_PETALS.get().getDefaultState();
	private static final BlockState vineWizardSpawner = Blocks.SPAWNER.getDefaultState();

	public WizardFlower() {
		super("WizardFlower");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 4, gardencianBricks);
		addBlock(world, basePos, 0, 0, 5, gardencianBricks);
		addBlock(world, basePos, 0, 0, 6, gardencianBricks);
		addBlock(world, basePos, 1, 0, 3, gardencianBricks);
		addBlock(world, basePos, 1, 0, 4, gardencianBricks);
		addBlock(world, basePos, 1, 0, 5, rosidianBricks);
		addBlock(world, basePos, 1, 0, 6, gardencianBricks);
		addBlock(world, basePos, 1, 0, 7, gardencianBricks);
		addBlock(world, basePos, 2, 0, 2, gardencianBricks);
		addBlock(world, basePos, 2, 0, 3, gardencianBricks);
		addBlock(world, basePos, 2, 0, 4, rosidianBricks);
		addBlock(world, basePos, 2, 0, 5, gardencianBricks);
		addBlock(world, basePos, 2, 0, 6, rosidianBricks);
		addBlock(world, basePos, 2, 0, 7, gardencianBricks);
		addBlock(world, basePos, 2, 0, 8, gardencianBricks);
		addBlock(world, basePos, 3, 0, 1, gardencianBricks);
		addBlock(world, basePos, 3, 0, 2, gardencianBricks);
		addBlock(world, basePos, 3, 0, 3, rosidianBricks);
		addBlock(world, basePos, 3, 0, 4, gardencianBricks);
		addBlock(world, basePos, 3, 0, 5, rosidianBricks);
		addBlock(world, basePos, 3, 0, 6, gardencianBricks);
		addBlock(world, basePos, 3, 0, 7, rosidianBricks);
		addBlock(world, basePos, 3, 0, 8, gardencianBricks);
		addBlock(world, basePos, 3, 0, 9, gardencianBricks);
		addBlock(world, basePos, 4, 0, 0, gardencianBricks);
		addBlock(world, basePos, 4, 0, 1, gardencianBricks);
		addBlock(world, basePos, 4, 0, 2, rosidianBricks);
		addBlock(world, basePos, 4, 0, 3, gardencianBricks);
		addBlock(world, basePos, 4, 0, 4, rosidianBricks);
		addBlock(world, basePos, 4, 0, 5, gardencianBricks);
		addBlock(world, basePos, 4, 0, 6, rosidianBricks);
		addBlock(world, basePos, 4, 0, 7, gardencianBricks);
		addBlock(world, basePos, 4, 0, 8, rosidianBricks);
		addBlock(world, basePos, 4, 0, 9, gardencianBricks);
		addBlock(world, basePos, 4, 0, 10, gardencianBricks);
		addBlock(world, basePos, 5, 0, 0, gardencianBricks);
		addBlock(world, basePos, 5, 0, 1, rosidianBricks);
		addBlock(world, basePos, 5, 0, 2, gardencianBricks);
		addBlock(world, basePos, 5, 0, 3, rosidianBricks);
		addBlock(world, basePos, 5, 0, 4, gardencianBricks);
		addBlock(world, basePos, 5, 0, 5, gardencianBricks);
		addBlock(world, basePos, 5, 0, 6, gardencianBricks);
		addBlock(world, basePos, 5, 0, 7, rosidianBricks);
		addBlock(world, basePos, 5, 0, 8, gardencianBricks);
		addBlock(world, basePos, 5, 0, 9, rosidianBricks);
		addBlock(world, basePos, 5, 0, 10, gardencianBricks);
		addBlock(world, basePos, 6, 0, 0, gardencianBricks);
		addBlock(world, basePos, 6, 0, 1, gardencianBricks);
		addBlock(world, basePos, 6, 0, 2, rosidianBricks);
		addBlock(world, basePos, 6, 0, 3, gardencianBricks);
		addBlock(world, basePos, 6, 0, 4, rosidianBricks);
		addBlock(world, basePos, 6, 0, 5, gardencianBricks);
		addBlock(world, basePos, 6, 0, 6, rosidianBricks);
		addBlock(world, basePos, 6, 0, 7, gardencianBricks);
		addBlock(world, basePos, 6, 0, 8, rosidianBricks);
		addBlock(world, basePos, 6, 0, 9, gardencianBricks);
		addBlock(world, basePos, 6, 0, 10, gardencianBricks);
		addBlock(world, basePos, 7, 0, 1, gardencianBricks);
		addBlock(world, basePos, 7, 0, 2, gardencianBricks);
		addBlock(world, basePos, 7, 0, 3, rosidianBricks);
		addBlock(world, basePos, 7, 0, 4, gardencianBricks);
		addBlock(world, basePos, 7, 0, 5, rosidianBricks);
		addBlock(world, basePos, 7, 0, 6, gardencianBricks);
		addBlock(world, basePos, 7, 0, 7, rosidianBricks);
		addBlock(world, basePos, 7, 0, 8, gardencianBricks);
		addBlock(world, basePos, 7, 0, 9, gardencianBricks);
		addBlock(world, basePos, 8, 0, 2, gardencianBricks);
		addBlock(world, basePos, 8, 0, 3, gardencianBricks);
		addBlock(world, basePos, 8, 0, 4, rosidianBricks);
		addBlock(world, basePos, 8, 0, 5, gardencianBricks);
		addBlock(world, basePos, 8, 0, 6, rosidianBricks);
		addBlock(world, basePos, 8, 0, 7, gardencianBricks);
		addBlock(world, basePos, 8, 0, 8, gardencianBricks);
		addBlock(world, basePos, 9, 0, 3, gardencianBricks);
		addBlock(world, basePos, 9, 0, 4, gardencianBricks);
		addBlock(world, basePos, 9, 0, 5, rosidianBricks);
		addBlock(world, basePos, 9, 0, 6, gardencianBricks);
		addBlock(world, basePos, 9, 0, 7, gardencianBricks);
		addBlock(world, basePos, 10, 0, 4, gardencianBricks);
		addBlock(world, basePos, 10, 0, 5, gardencianBricks);
		addBlock(world, basePos, 10, 0, 6, gardencianBricks);
		addBlock(world, basePos, 4, 1, 5, rosidianBricks);
		addBlock(world, basePos, 5, 1, 4, rosidianBricks);
		addBlock(world, basePos, 5, 1, 5, rosidianBricks);
		addBlock(world, basePos, 5, 1, 6, rosidianBricks);
		addBlock(world, basePos, 6, 1, 5, rosidianBricks);
		addBlock(world, basePos, 5, 2, 5, vineWizardSpawner);
		addBlock(world, basePos, 5, 3, 5, rosidianBricks);
		addBlock(world, basePos, 5, 4, 5, rosidianBricks);
		addBlock(world, basePos, 2, 5, 5, bluePetals);
		addBlock(world, basePos, 3, 5, 4, bluePetals);
		addBlock(world, basePos, 3, 5, 5, bluePetals);
		addBlock(world, basePos, 3, 5, 6, bluePetals);
		addBlock(world, basePos, 4, 5, 3, bluePetals);
		addBlock(world, basePos, 4, 5, 4, bluePetals);
		addBlock(world, basePos, 4, 5, 5, bluePetals);
		addBlock(world, basePos, 4, 5, 6, bluePetals);
		addBlock(world, basePos, 4, 5, 7, bluePetals);
		addBlock(world, basePos, 5, 5, 2, bluePetals);
		addBlock(world, basePos, 5, 5, 3, bluePetals);
		addBlock(world, basePos, 5, 5, 4, bluePetals);
		addBlock(world, basePos, 5, 5, 5, rosidianBricks);
		addBlock(world, basePos, 5, 5, 6, bluePetals);
		addBlock(world, basePos, 5, 5, 7, bluePetals);
		addBlock(world, basePos, 5, 5, 8, bluePetals);
		addBlock(world, basePos, 6, 5, 3, bluePetals);
		addBlock(world, basePos, 6, 5, 4, bluePetals);
		addBlock(world, basePos, 6, 5, 5, bluePetals);
		addBlock(world, basePos, 6, 5, 6, bluePetals);
		addBlock(world, basePos, 6, 5, 7, bluePetals);
		addBlock(world, basePos, 7, 5, 4, bluePetals);
		addBlock(world, basePos, 7, 5, 5, bluePetals);
		addBlock(world, basePos, 7, 5, 6, bluePetals);
		addBlock(world, basePos, 8, 5, 5, bluePetals);
		addBlock(world, basePos, 2, 6, 5, bluePetals);
		addBlock(world, basePos, 3, 6, 5, bluePetals);
		addBlock(world, basePos, 4, 6, 4, bluePetals);
		addBlock(world, basePos, 4, 6, 5, bluePetals);
		addBlock(world, basePos, 4, 6, 6, bluePetals);
		addBlock(world, basePos, 5, 6, 2, bluePetals);
		addBlock(world, basePos, 5, 6, 3, bluePetals);
		addBlock(world, basePos, 5, 6, 4, bluePetals);
		addBlock(world, basePos, 5, 6, 5, bluePetals);
		addBlock(world, basePos, 5, 6, 6, bluePetals);
		addBlock(world, basePos, 5, 6, 7, bluePetals);
		addBlock(world, basePos, 5, 6, 8, bluePetals);
		addBlock(world, basePos, 6, 6, 4, bluePetals);
		addBlock(world, basePos, 6, 6, 5, bluePetals);
		addBlock(world, basePos, 6, 6, 6, bluePetals);
		addBlock(world, basePos, 7, 6, 5, bluePetals);
		addBlock(world, basePos, 8, 6, 5, bluePetals);
		addBlock(world, basePos, 1, 7, 5, bluePetals);
		addBlock(world, basePos, 2, 7, 5, bluePetals);
		addBlock(world, basePos, 3, 7, 5, bluePetals);
		addBlock(world, basePos, 4, 7, 5, bluePetals);
		addBlock(world, basePos, 5, 7, 1, bluePetals);
		addBlock(world, basePos, 5, 7, 2, bluePetals);
		addBlock(world, basePos, 5, 7, 3, bluePetals);
		addBlock(world, basePos, 5, 7, 4, bluePetals);
		addBlock(world, basePos, 5, 7, 5, bluePetals);
		addBlock(world, basePos, 5, 7, 6, bluePetals);
		addBlock(world, basePos, 5, 7, 7, bluePetals);
		addBlock(world, basePos, 5, 7, 8, bluePetals);
		addBlock(world, basePos, 5, 7, 9, bluePetals);
		addBlock(world, basePos, 6, 7, 5, bluePetals);
		addBlock(world, basePos, 7, 7, 5, bluePetals);
		addBlock(world, basePos, 8, 7, 5, bluePetals);
		addBlock(world, basePos, 9, 7, 5, bluePetals);
		addBlock(world, basePos, 1, 8, 5, bluePetals);
		addBlock(world, basePos, 2, 8, 5, bluePetals);
		addBlock(world, basePos, 3, 8, 5, bluePetals);
		addBlock(world, basePos, 4, 8, 5, bluePetals);
		addBlock(world, basePos, 5, 8, 1, bluePetals);
		addBlock(world, basePos, 5, 8, 2, bluePetals);
		addBlock(world, basePos, 5, 8, 3, bluePetals);
		addBlock(world, basePos, 5, 8, 4, bluePetals);
		addBlock(world, basePos, 5, 8, 6, bluePetals);
		addBlock(world, basePos, 5, 8, 7, bluePetals);
		addBlock(world, basePos, 5, 8, 8, bluePetals);
		addBlock(world, basePos, 5, 8, 9, bluePetals);
		addBlock(world, basePos, 6, 8, 5, bluePetals);
		addBlock(world, basePos, 7, 8, 5, bluePetals);
		addBlock(world, basePos, 8, 8, 5, bluePetals);
		addBlock(world, basePos, 9, 8, 5, bluePetals);
		addBlock(world, basePos, 2, 9, 5, bluePetals);
		addBlock(world, basePos, 3, 9, 5, bluePetals);
		addBlock(world, basePos, 5, 9, 2, bluePetals);
		addBlock(world, basePos, 5, 9, 3, bluePetals);
		addBlock(world, basePos, 5, 9, 7, bluePetals);
		addBlock(world, basePos, 5, 9, 8, bluePetals);
		addBlock(world, basePos, 7, 9, 5, bluePetals);
		addBlock(world, basePos, 8, 9, 5, bluePetals);
	}

	@Override
	protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {
		initSpawner(world, basePos.add(5, 2, 5), AoAEntities.Mobs.VINE_WIZARD.get());
	}
}
