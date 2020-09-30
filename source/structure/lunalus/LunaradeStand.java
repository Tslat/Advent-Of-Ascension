package net.tslat.aoa3.structure.lunalus;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.npcs.trader.EntityZalVendor;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

import static net.tslat.aoa3.block.functional.lamps.LampBlock.FIXED_LAMP;

public class LunaradeStand extends AoAStructure { //StructureSize: 7x6x7
	private static final IBlockState lunarLamp = BlockRegister.LUNAR_LAMP.getDefaultState().withProperty(FIXED_LAMP, true);
	private static final IBlockState lunarBricks = BlockRegister.LUNAR_BRICKS.getDefaultState();
	private static final IBlockState lunidePlanks = BlockRegister.LUNIDE_PLANKS.getDefaultState();
	private static final IBlockState stainedGlass = Blocks.STAINED_GLASS.getDefaultState();
	private static final IBlockState chest = Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.EAST);

	public LunaradeStand() {
		super("LunaradeStand");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, lunarBricks);
		addBlock(world, basePos, 0, 0, 1, lunarBricks);
		addBlock(world, basePos, 0, 0, 2, lunarBricks);
		addBlock(world, basePos, 0, 0, 3, lunarBricks);
		addBlock(world, basePos, 0, 0, 4, lunarBricks);
		addBlock(world, basePos, 0, 0, 5, lunarBricks);
		addBlock(world, basePos, 0, 0, 6, lunarBricks);
		addBlock(world, basePos, 1, 0, 0, lunarBricks);
		addBlock(world, basePos, 1, 0, 1, lunarBricks);
		addBlock(world, basePos, 1, 0, 2, lunarBricks);
		addBlock(world, basePos, 1, 0, 3, lunarBricks);
		addBlock(world, basePos, 1, 0, 4, lunarBricks);
		addBlock(world, basePos, 1, 0, 5, lunarBricks);
		addBlock(world, basePos, 1, 0, 6, lunarBricks);
		addBlock(world, basePos, 2, 0, 0, lunarBricks);
		addBlock(world, basePos, 2, 0, 1, lunarBricks);
		addBlock(world, basePos, 2, 0, 2, lunarBricks);
		addBlock(world, basePos, 2, 0, 3, lunarBricks);
		addBlock(world, basePos, 2, 0, 4, lunarBricks);
		addBlock(world, basePos, 2, 0, 5, lunarBricks);
		addBlock(world, basePos, 2, 0, 6, lunarBricks);
		addBlock(world, basePos, 3, 0, 0, lunarBricks);
		addBlock(world, basePos, 3, 0, 1, lunarBricks);
		addBlock(world, basePos, 3, 0, 2, lunarBricks);
		addBlock(world, basePos, 3, 0, 3, lunarBricks);
		addBlock(world, basePos, 3, 0, 4, lunarBricks);
		addBlock(world, basePos, 3, 0, 5, lunarBricks);
		addBlock(world, basePos, 3, 0, 6, lunarBricks);
		addBlock(world, basePos, 4, 0, 0, lunarBricks);
		addBlock(world, basePos, 4, 0, 1, lunarBricks);
		addBlock(world, basePos, 4, 0, 2, lunarBricks);
		addBlock(world, basePos, 4, 0, 3, lunarBricks);
		addBlock(world, basePos, 4, 0, 4, lunarBricks);
		addBlock(world, basePos, 4, 0, 5, lunarBricks);
		addBlock(world, basePos, 4, 0, 6, lunarBricks);
		addBlock(world, basePos, 5, 0, 0, lunarBricks);
		addBlock(world, basePos, 5, 0, 1, lunarBricks);
		addBlock(world, basePos, 5, 0, 2, lunarBricks);
		addBlock(world, basePos, 5, 0, 3, lunarBricks);
		addBlock(world, basePos, 5, 0, 4, lunarBricks);
		addBlock(world, basePos, 5, 0, 5, lunarBricks);
		addBlock(world, basePos, 5, 0, 6, lunarBricks);
		addBlock(world, basePos, 6, 0, 1, lunarBricks);
		addBlock(world, basePos, 6, 0, 2, lunarBricks);
		addBlock(world, basePos, 6, 0, 3, lunarBricks);
		addBlock(world, basePos, 6, 0, 4, lunarBricks);
		addBlock(world, basePos, 6, 0, 5, lunarBricks);
		addBlock(world, basePos, 0, 1, 0, lunarBricks);
		addBlock(world, basePos, 0, 1, 1, stainedGlass);
		addBlock(world, basePos, 0, 1, 2, stainedGlass);
		addBlock(world, basePos, 0, 1, 3, lunarBricks);
		addBlock(world, basePos, 0, 1, 4, stainedGlass);
		addBlock(world, basePos, 0, 1, 5, stainedGlass);
		addBlock(world, basePos, 0, 1, 6, lunarBricks);
		addBlock(world, basePos, 1, 1, 0, stainedGlass);
		addBlock(world, basePos, 1, 1, 4, chest);
		addBlock(world, basePos, 1, 1, 6, stainedGlass);
		addBlock(world, basePos, 2, 1, 0, stainedGlass);
		addBlock(world, basePos, 2, 1, 6, stainedGlass);
		addBlock(world, basePos, 3, 1, 0, stainedGlass);
		addBlock(world, basePos, 3, 1, 2, lunidePlanks);
		addBlock(world, basePos, 3, 1, 3, lunidePlanks);
		addBlock(world, basePos, 3, 1, 4, lunidePlanks);
		addBlock(world, basePos, 3, 1, 6, stainedGlass);
		addBlock(world, basePos, 4, 1, 0, stainedGlass);
		addBlock(world, basePos, 4, 1, 6, stainedGlass);
		addBlock(world, basePos, 5, 1, 0, lunarBricks);
		addBlock(world, basePos, 5, 1, 6, lunarBricks);
		addBlock(world, basePos, 0, 2, 3, lunarBricks);
		addBlock(world, basePos, 0, 3, 3, lunarBricks);
		addBlock(world, basePos, 0, 4, 3, lunarBricks);
		addBlock(world, basePos, 0, 5, 3, lunarLamp);
	}

	@Override
	protected void spawnEntities(World world, Random rand, BlockPos basePos) {
		EntityZalVendor lunaradeVendor = new EntityZalVendor(world);

		lunaradeVendor.setLocationAndAngles(basePos.getX() + 2, basePos.getY() + 2, basePos.getZ() + 3, rand.nextFloat() * 360, 0);
		world.spawnEntity(lunaradeVendor);
	}

	@Override
	protected void doPostBuildOps(World world, Random rand, BlockPos basePos) {
		assignLootChests(world, rand, LootSystemRegister.structureLunaradeStand, basePos.add(1, 1, 4));
	}
}
