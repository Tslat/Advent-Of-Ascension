package net.tslat.aoa3.worldgen.structures.lunalus;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.functional.light.LampBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.trader.ZalVendorEntity;
import net.tslat.aoa3.worldgen.structures.AoAStructure;

import java.util.Random;

public class LunaradeStand extends AoAStructure { //StructureSize: 7x6x7
	private static final BlockState lunarLamp = AoABlocks.LUNAR_LAMP.get().getDefaultState().with(LampBlock.TOGGLEABLE, false);
	private static final BlockState lunarBricks = AoABlocks.LUNAR_BRICKS.get().getDefaultState();
	private static final BlockState lunidePlanks = AoABlocks.LUNIDE_PLANKS.get().getDefaultState();
	private static final BlockState stainedGlass = Blocks.PURPLE_STAINED_GLASS.getDefaultState();
	private static final BlockState chest = Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.EAST);

	public LunaradeStand() {
		super("LunaradeStand");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(IWorld world, Random rand, BlockPos basePos) {
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
	protected void spawnEntities(IWorld world, Random rand, BlockPos basePos) {
		ZalVendorEntity lunaradeVendor = new ZalVendorEntity(AoAEntities.NPCs.ZAL_VENDOR.get(), world.getWorld());

		lunaradeVendor.setLocationAndAngles(basePos.getX() + 2, basePos.getY() + 2, basePos.getZ() + 3, rand.nextFloat() * 360, 0);
		world.addEntity(lunaradeVendor);
	}

	@Override
	protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {
		assignLootChests(world, rand, new ResourceLocation(AdventOfAscension.MOD_ID, "structures/lunarade_stand"), basePos.add(1, 1, 4));
	}
}
