package net.tslat.aoa3.structure.crystevia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.npcs.lottoman.EntityCrystalLottoman;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class CrystalLottoOverlook extends AoAStructure { //StructureSize: 9x7x9
	private static final IBlockState crystevianBricks = BlockRegister.CRYSTEVIA_BRICKS.getDefaultState();
	private static final IBlockState greenCrystal = BlockRegister.GREEN_CRYSTAL_BLOCK.getDefaultState();

	public CrystalLottoOverlook() {
		super("CrystalLottoOverlook");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int y = -1;

		while (world.getBlockState(basePos.add(4, y, 4)).getBlock() == Blocks.AIR) {
			addBlock(world, basePos, 4, y, 4, greenCrystal);
			y--;
		}

		addBlock(world, basePos, 0, 0, 4, crystevianBricks);
		addBlock(world, basePos, 1, 0, 1, crystevianBricks);
		addBlock(world, basePos, 1, 0, 2, crystevianBricks);
		addBlock(world, basePos, 1, 0, 3, crystevianBricks);
		addBlock(world, basePos, 1, 0, 4, crystevianBricks);
		addBlock(world, basePos, 1, 0, 5, crystevianBricks);
		addBlock(world, basePos, 1, 0, 6, crystevianBricks);
		addBlock(world, basePos, 1, 0, 7, crystevianBricks);
		addBlock(world, basePos, 2, 0, 1, crystevianBricks);
		addBlock(world, basePos, 2, 0, 2, greenCrystal);
		addBlock(world, basePos, 2, 0, 3, greenCrystal);
		addBlock(world, basePos, 2, 0, 4, crystevianBricks);
		addBlock(world, basePos, 2, 0, 5, greenCrystal);
		addBlock(world, basePos, 2, 0, 6, greenCrystal);
		addBlock(world, basePos, 2, 0, 7, crystevianBricks);
		addBlock(world, basePos, 3, 0, 1, crystevianBricks);
		addBlock(world, basePos, 3, 0, 2, greenCrystal);
		addBlock(world, basePos, 3, 0, 3, greenCrystal);
		addBlock(world, basePos, 3, 0, 4, crystevianBricks);
		addBlock(world, basePos, 3, 0, 5, greenCrystal);
		addBlock(world, basePos, 3, 0, 6, greenCrystal);
		addBlock(world, basePos, 3, 0, 7, crystevianBricks);
		addBlock(world, basePos, 4, 0, 0, crystevianBricks);
		addBlock(world, basePos, 4, 0, 1, crystevianBricks);
		addBlock(world, basePos, 4, 0, 2, crystevianBricks);
		addBlock(world, basePos, 4, 0, 3, crystevianBricks);
		addBlock(world, basePos, 4, 0, 4, crystevianBricks);
		addBlock(world, basePos, 4, 0, 5, crystevianBricks);
		addBlock(world, basePos, 4, 0, 6, crystevianBricks);
		addBlock(world, basePos, 4, 0, 7, crystevianBricks);
		addBlock(world, basePos, 4, 0, 8, crystevianBricks);
		addBlock(world, basePos, 5, 0, 1, crystevianBricks);
		addBlock(world, basePos, 5, 0, 2, greenCrystal);
		addBlock(world, basePos, 5, 0, 3, greenCrystal);
		addBlock(world, basePos, 5, 0, 4, crystevianBricks);
		addBlock(world, basePos, 5, 0, 5, greenCrystal);
		addBlock(world, basePos, 5, 0, 6, greenCrystal);
		addBlock(world, basePos, 5, 0, 7, crystevianBricks);
		addBlock(world, basePos, 6, 0, 1, crystevianBricks);
		addBlock(world, basePos, 6, 0, 2, greenCrystal);
		addBlock(world, basePos, 6, 0, 3, greenCrystal);
		addBlock(world, basePos, 6, 0, 4, crystevianBricks);
		addBlock(world, basePos, 6, 0, 5, greenCrystal);
		addBlock(world, basePos, 6, 0, 6, greenCrystal);
		addBlock(world, basePos, 6, 0, 7, crystevianBricks);
		addBlock(world, basePos, 7, 0, 1, crystevianBricks);
		addBlock(world, basePos, 7, 0, 2, crystevianBricks);
		addBlock(world, basePos, 7, 0, 3, crystevianBricks);
		addBlock(world, basePos, 7, 0, 4, crystevianBricks);
		addBlock(world, basePos, 7, 0, 5, crystevianBricks);
		addBlock(world, basePos, 7, 0, 6, crystevianBricks);
		addBlock(world, basePos, 7, 0, 7, crystevianBricks);
		addBlock(world, basePos, 8, 0, 4, crystevianBricks);
		addBlock(world, basePos, 0, 1, 4, crystevianBricks);
		addBlock(world, basePos, 4, 1, 0, crystevianBricks);
		addBlock(world, basePos, 4, 1, 8, crystevianBricks);
		addBlock(world, basePos, 8, 1, 4, crystevianBricks);
		addBlock(world, basePos, 0, 2, 4, crystevianBricks);
		addBlock(world, basePos, 4, 2, 0, crystevianBricks);
		addBlock(world, basePos, 4, 2, 8, crystevianBricks);
		addBlock(world, basePos, 8, 2, 4, crystevianBricks);
		addBlock(world, basePos, 0, 3, 4, crystevianBricks);
		addBlock(world, basePos, 4, 3, 0, crystevianBricks);
		addBlock(world, basePos, 4, 3, 8, crystevianBricks);
		addBlock(world, basePos, 8, 3, 4, crystevianBricks);
		addBlock(world, basePos, 0, 4, 4, crystevianBricks);
		addBlock(world, basePos, 1, 4, 4, crystevianBricks);
		addBlock(world, basePos, 4, 4, 0, crystevianBricks);
		addBlock(world, basePos, 4, 4, 1, crystevianBricks);
		addBlock(world, basePos, 4, 4, 7, crystevianBricks);
		addBlock(world, basePos, 4, 4, 8, crystevianBricks);
		addBlock(world, basePos, 7, 4, 4, crystevianBricks);
		addBlock(world, basePos, 8, 4, 4, crystevianBricks);
		addBlock(world, basePos, 1, 5, 4, crystevianBricks);
		addBlock(world, basePos, 4, 5, 1, crystevianBricks);
		addBlock(world, basePos, 4, 5, 7, crystevianBricks);
		addBlock(world, basePos, 7, 5, 4, crystevianBricks);
		addBlock(world, basePos, 1, 6, 4, crystevianBricks);
		addBlock(world, basePos, 2, 6, 2, crystevianBricks);
		addBlock(world, basePos, 2, 6, 3, crystevianBricks);
		addBlock(world, basePos, 2, 6, 4, crystevianBricks);
		addBlock(world, basePos, 2, 6, 5, crystevianBricks);
		addBlock(world, basePos, 2, 6, 6, crystevianBricks);
		addBlock(world, basePos, 3, 6, 2, crystevianBricks);
		addBlock(world, basePos, 3, 6, 3, greenCrystal);
		addBlock(world, basePos, 3, 6, 4, greenCrystal);
		addBlock(world, basePos, 3, 6, 5, greenCrystal);
		addBlock(world, basePos, 3, 6, 6, crystevianBricks);
		addBlock(world, basePos, 4, 6, 1, crystevianBricks);
		addBlock(world, basePos, 4, 6, 2, crystevianBricks);
		addBlock(world, basePos, 4, 6, 3, greenCrystal);
		addBlock(world, basePos, 4, 6, 4, crystevianBricks);
		addBlock(world, basePos, 4, 6, 5, greenCrystal);
		addBlock(world, basePos, 4, 6, 6, crystevianBricks);
		addBlock(world, basePos, 4, 6, 7, crystevianBricks);
		addBlock(world, basePos, 5, 6, 2, crystevianBricks);
		addBlock(world, basePos, 5, 6, 3, greenCrystal);
		addBlock(world, basePos, 5, 6, 4, greenCrystal);
		addBlock(world, basePos, 5, 6, 5, greenCrystal);
		addBlock(world, basePos, 5, 6, 6, crystevianBricks);
		addBlock(world, basePos, 6, 6, 2, crystevianBricks);
		addBlock(world, basePos, 6, 6, 3, crystevianBricks);
		addBlock(world, basePos, 6, 6, 4, crystevianBricks);
		addBlock(world, basePos, 6, 6, 5, crystevianBricks);
		addBlock(world, basePos, 6, 6, 6, crystevianBricks);
		addBlock(world, basePos, 7, 6, 4, crystevianBricks);
	}

	@Override
	protected void spawnEntities(World world, Random rand, BlockPos basePos) {
		EntityCrystalLottoman lottoman = new EntityCrystalLottoman(world);

		lottoman.setLocationAndAngles(basePos.getX() + 4, basePos.getY() + 1, basePos.getZ() + 4, rand.nextFloat() * 360, 0);
		world.spawnEntity(lottoman);
	}
}
