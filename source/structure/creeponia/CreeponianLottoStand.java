package net.tslat.aoa3.structure.creeponia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.npcs.lottoman.EntityCreeponiaLottoman;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class CreeponianLottoStand extends AoAStructure { //StructureSize: 8x10x8
	private static final IBlockState creeponiaBricks = BlockRegister.bricksCreeponia.getDefaultState();
	private static final IBlockState stainedGlass = Blocks.STAINED_GLASS.getDefaultState();

	public CreeponianLottoStand() {
		super("CreeponianLottoStand");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 3, 0, 3, creeponiaBricks);
		addBlock(world, basePos, 3, 0, 4, creeponiaBricks);
		addBlock(world, basePos, 4, 0, 3, creeponiaBricks);
		addBlock(world, basePos, 4, 0, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 1, 3, stainedGlass);
		addBlock(world, basePos, 3, 1, 4, stainedGlass);
		addBlock(world, basePos, 4, 1, 3, stainedGlass);
		addBlock(world, basePos, 4, 1, 4, stainedGlass);
		addBlock(world, basePos, 3, 2, 3, creeponiaBricks);
		addBlock(world, basePos, 3, 2, 4, creeponiaBricks);
		addBlock(world, basePos, 4, 2, 3, creeponiaBricks);
		addBlock(world, basePos, 4, 2, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 3, 3, stainedGlass);
		addBlock(world, basePos, 3, 3, 4, stainedGlass);
		addBlock(world, basePos, 4, 3, 3, stainedGlass);
		addBlock(world, basePos, 4, 3, 4, stainedGlass);
		addBlock(world, basePos, 1, 4, 3, creeponiaBricks);
		addBlock(world, basePos, 1, 4, 4, creeponiaBricks);
		addBlock(world, basePos, 2, 4, 3, creeponiaBricks);
		addBlock(world, basePos, 2, 4, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 4, 1, creeponiaBricks);
		addBlock(world, basePos, 3, 4, 2, creeponiaBricks);
		addBlock(world, basePos, 3, 4, 3, creeponiaBricks);
		addBlock(world, basePos, 3, 4, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 4, 5, creeponiaBricks);
		addBlock(world, basePos, 3, 4, 6, creeponiaBricks);
		addBlock(world, basePos, 4, 4, 1, creeponiaBricks);
		addBlock(world, basePos, 4, 4, 2, creeponiaBricks);
		addBlock(world, basePos, 4, 4, 3, creeponiaBricks);
		addBlock(world, basePos, 4, 4, 4, creeponiaBricks);
		addBlock(world, basePos, 4, 4, 5, creeponiaBricks);
		addBlock(world, basePos, 4, 4, 6, creeponiaBricks);
		addBlock(world, basePos, 5, 4, 3, creeponiaBricks);
		addBlock(world, basePos, 5, 4, 4, creeponiaBricks);
		addBlock(world, basePos, 6, 4, 3, creeponiaBricks);
		addBlock(world, basePos, 6, 4, 4, creeponiaBricks);
		addBlock(world, basePos, 1, 5, 3, creeponiaBricks);
		addBlock(world, basePos, 1, 5, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 5, 1, creeponiaBricks);
		addBlock(world, basePos, 3, 5, 6, creeponiaBricks);
		addBlock(world, basePos, 4, 5, 1, creeponiaBricks);
		addBlock(world, basePos, 4, 5, 6, creeponiaBricks);
		addBlock(world, basePos, 6, 5, 3, creeponiaBricks);
		addBlock(world, basePos, 6, 5, 4, creeponiaBricks);
		addBlock(world, basePos, 1, 6, 3, stainedGlass);
		addBlock(world, basePos, 1, 6, 4, stainedGlass);
		addBlock(world, basePos, 3, 6, 1, stainedGlass);
		addBlock(world, basePos, 3, 6, 6, stainedGlass);
		addBlock(world, basePos, 4, 6, 1, stainedGlass);
		addBlock(world, basePos, 4, 6, 6, stainedGlass);
		addBlock(world, basePos, 6, 6, 3, stainedGlass);
		addBlock(world, basePos, 6, 6, 4, stainedGlass);
		addBlock(world, basePos, 1, 7, 3, creeponiaBricks);
		addBlock(world, basePos, 1, 7, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 7, 1, creeponiaBricks);
		addBlock(world, basePos, 3, 7, 6, creeponiaBricks);
		addBlock(world, basePos, 4, 7, 1, creeponiaBricks);
		addBlock(world, basePos, 4, 7, 6, creeponiaBricks);
		addBlock(world, basePos, 6, 7, 3, creeponiaBricks);
		addBlock(world, basePos, 6, 7, 4, creeponiaBricks);
		addBlock(world, basePos, 0, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 0, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 0, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 0, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 1, 8, 1, creeponiaBricks);
		addBlock(world, basePos, 1, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 1, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 1, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 1, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 1, 8, 6, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 0, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 1, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 6, creeponiaBricks);
		addBlock(world, basePos, 2, 8, 7, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 0, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 1, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 6, creeponiaBricks);
		addBlock(world, basePos, 3, 8, 7, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 0, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 1, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 6, creeponiaBricks);
		addBlock(world, basePos, 4, 8, 7, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 0, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 1, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 6, creeponiaBricks);
		addBlock(world, basePos, 5, 8, 7, creeponiaBricks);
		addBlock(world, basePos, 6, 8, 1, creeponiaBricks);
		addBlock(world, basePos, 6, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 6, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 6, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 6, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 6, 8, 6, creeponiaBricks);
		addBlock(world, basePos, 7, 8, 2, creeponiaBricks);
		addBlock(world, basePos, 7, 8, 3, creeponiaBricks);
		addBlock(world, basePos, 7, 8, 4, creeponiaBricks);
		addBlock(world, basePos, 7, 8, 5, creeponiaBricks);
		addBlock(world, basePos, 0, 9, 2, creeponiaBricks);
		addBlock(world, basePos, 0, 9, 3, creeponiaBricks);
		addBlock(world, basePos, 0, 9, 4, creeponiaBricks);
		addBlock(world, basePos, 0, 9, 5, creeponiaBricks);
		addBlock(world, basePos, 1, 9, 1, creeponiaBricks);
		addBlock(world, basePos, 1, 9, 6, creeponiaBricks);
		addBlock(world, basePos, 2, 9, 0, creeponiaBricks);
		addBlock(world, basePos, 2, 9, 7, creeponiaBricks);
		addBlock(world, basePos, 3, 9, 0, creeponiaBricks);
		addBlock(world, basePos, 3, 9, 7, creeponiaBricks);
		addBlock(world, basePos, 4, 9, 0, creeponiaBricks);
		addBlock(world, basePos, 4, 9, 7, creeponiaBricks);
		addBlock(world, basePos, 5, 9, 0, creeponiaBricks);
		addBlock(world, basePos, 5, 9, 7, creeponiaBricks);
		addBlock(world, basePos, 6, 9, 1, creeponiaBricks);
		addBlock(world, basePos, 6, 9, 6, creeponiaBricks);
		addBlock(world, basePos, 7, 9, 2, creeponiaBricks);
		addBlock(world, basePos, 7, 9, 3, creeponiaBricks);
		addBlock(world, basePos, 7, 9, 4, creeponiaBricks);
		addBlock(world, basePos, 7, 9, 5, creeponiaBricks);
	}

	@Override
	protected void spawnEntities(World world, Random rand, BlockPos basePos) {
		EntityCreeponiaLottoman lottoman = new EntityCreeponiaLottoman(world);

		lottoman.setLocationAndAngles(basePos.getX() + 4, basePos.getY() + 9, basePos.getZ() + 4, rand.nextFloat() * 360, 0);
		world.spawnEntity(lottoman);
	}
}
