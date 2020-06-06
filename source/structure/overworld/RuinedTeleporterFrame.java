package net.tslat.aoa3.structure.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.npcs.trader.EntityCorruptedTraveller;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class RuinedTeleporterFrame extends AoAStructure { //StructureSize: 11x9x15
	private static final IBlockState air = Blocks.AIR.getDefaultState();
	private static final IBlockState ancientRock = BlockRegister.ANCIENT_ROCK.getDefaultState();
	private static final IBlockState carvedRuneOfDirection = BlockRegister.CARVED_RUNE_DIRECTION.getDefaultState();
	private static final IBlockState carvedRuneOfSpace = BlockRegister.CARVED_RUNE_SPACE.getDefaultState();
	private static final IBlockState carvedRuneOfReality = BlockRegister.CARVED_RUNE_REALITY.getDefaultState();
	private static final IBlockState carvedRuneOfTravel = BlockRegister.CARVED_RUNE_TRAVEL.getDefaultState();

	public RuinedTeleporterFrame() {
		super("RuinedTeleporterFrame");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		boolean alteration1 = rand.nextBoolean();
		boolean alteration2 = rand.nextBoolean();
		boolean alteration3 = rand.nextBoolean();
		boolean alteration4 = rand.nextBoolean();
		boolean alteration5 = rand.nextBoolean();
		boolean alteration6 = rand.nextBoolean();


		addBlock(world, basePos, 0, 0, 0, ancientRock);
		addBlock(world, basePos, 0, 0, 1, ancientRock);
		addBlock(world, basePos, 0, 0, 2, ancientRock);
		addBlock(world, basePos, 0, 0, 3, ancientRock);
		addBlock(world, basePos, 0, 0, 4, ancientRock);
		addBlock(world, basePos, 0, 0, 5, ancientRock);
		addBlock(world, basePos, 0, 0, 6, ancientRock);
		addBlock(world, basePos, 0, 0, 7, ancientRock);
		addBlock(world, basePos, 0, 0, 8, ancientRock);
		addBlock(world, basePos, 0, 0, 9, ancientRock);

		if (alteration1)
			addBlock(world, basePos, 0, 0, 10, ancientRock);

		addBlock(world, basePos, 0, 0, 11, ancientRock);
		addBlock(world, basePos, 0, 0, 12, ancientRock);
		addBlock(world, basePos, 0, 0, 13, ancientRock);
		addBlock(world, basePos, 0, 0, 14, ancientRock);
		addBlock(world, basePos, 1, 0, 0, ancientRock);
		addBlock(world, basePos, 1, 0, 1, ancientRock);
		addBlock(world, basePos, 1, 0, 2, ancientRock);
		addBlock(world, basePos, 1, 0, 3, ancientRock);
		addBlock(world, basePos, 1, 0, 4, ancientRock);
		addBlock(world, basePos, 1, 0, 5, ancientRock);
		addBlock(world, basePos, 1, 0, 6, ancientRock);
		addBlock(world, basePos, 1, 0, 7, ancientRock);

		if (alteration4)
			addBlock(world, basePos, 1, 0, 8, ancientRock);

		addBlock(world, basePos, 1, 0, 9, ancientRock);
		addBlock(world, basePos, 1, 0, 10, ancientRock);
		addBlock(world, basePos, 1, 0, 11, ancientRock);
		addBlock(world, basePos, 1, 0, 12, ancientRock);
		addBlock(world, basePos, 1, 0, 13, ancientRock);
		addBlock(world, basePos, 1, 0, 14, ancientRock);
		addBlock(world, basePos, 2, 0, 0, ancientRock);
		addBlock(world, basePos, 2, 0, 1, ancientRock);
		addBlock(world, basePos, 2, 0, 2, ancientRock);
		addBlock(world, basePos, 2, 0, 3, ancientRock);
		addBlock(world, basePos, 2, 0, 4, ancientRock);
		addBlock(world, basePos, 2, 0, 5, ancientRock);
		addBlock(world, basePos, 2, 0, 6, ancientRock);
		addBlock(world, basePos, 2, 0, 7, ancientRock);
		addBlock(world, basePos, 2, 0, 8, ancientRock);
		addBlock(world, basePos, 2, 0, 9, ancientRock);
		addBlock(world, basePos, 2, 0, 10, ancientRock);
		addBlock(world, basePos, 2, 0, 11, ancientRock);
		addBlock(world, basePos, 2, 0, 12, ancientRock);
		addBlock(world, basePos, 2, 0, 13, ancientRock);
		addBlock(world, basePos, 2, 0, 14, ancientRock);
		addBlock(world, basePos, 3, 0, 0, ancientRock);
		addBlock(world, basePos, 3, 0, 1, ancientRock);

		if (alteration6) {
			addBlock(world, basePos, 3, 0, 2, ancientRock);
			addBlock(world, basePos, 3, 0, 3, ancientRock);
			addBlock(world, basePos, 3, 0, 4, ancientRock);
			addBlock(world, basePos, 3, 0, 5, ancientRock);
			addBlock(world, basePos, 3, 0, 6, ancientRock);
			addBlock(world, basePos, 3, 0, 7, ancientRock);
		}

		addBlock(world, basePos, 3, 0, 8, ancientRock);
		addBlock(world, basePos, 3, 0, 9, ancientRock);
		addBlock(world, basePos, 3, 0, 10, ancientRock);
		addBlock(world, basePos, 3, 0, 11, ancientRock);
		addBlock(world, basePos, 3, 0, 12, ancientRock);
		addBlock(world, basePos, 3, 0, 13, ancientRock);
		addBlock(world, basePos, 3, 0, 14, ancientRock);
		addBlock(world, basePos, 4, 0, 0, ancientRock);
		addBlock(world, basePos, 4, 0, 1, ancientRock);
		addBlock(world, basePos, 4, 0, 2, ancientRock);
		addBlock(world, basePos, 4, 0, 3, ancientRock);
		addBlock(world, basePos, 4, 0, 4, ancientRock);
		addBlock(world, basePos, 4, 0, 5, ancientRock);
		addBlock(world, basePos, 4, 0, 6, ancientRock);
		addBlock(world, basePos, 4, 0, 7, ancientRock);
		addBlock(world, basePos, 4, 0, 8, ancientRock);
		addBlock(world, basePos, 4, 0, 9, ancientRock);
		addBlock(world, basePos, 4, 0, 10, ancientRock);
		addBlock(world, basePos, 4, 0, 11, ancientRock);
		addBlock(world, basePos, 4, 0, 12, ancientRock);
		addBlock(world, basePos, 4, 0, 13, ancientRock);
		addBlock(world, basePos, 4, 0, 14, ancientRock);
		addBlock(world, basePos, 5, 0, 0, ancientRock);
		addBlock(world, basePos, 5, 0, 1, ancientRock);

		if (alteration3) {
			addBlock(world, basePos, 5, 0, 2, ancientRock);
			addBlock(world, basePos, 5, 0, 3, ancientRock);
			addBlock(world, basePos, 5, 0, 4, ancientRock);
		}

		addBlock(world, basePos, 5, 0, 5, ancientRock);
		addBlock(world, basePos, 5, 0, 6, ancientRock);
		addBlock(world, basePos, 5, 0, 7, ancientRock);
		addBlock(world, basePos, 5, 0, 8, ancientRock);
		addBlock(world, basePos, 5, 0, 9, ancientRock);
		addBlock(world, basePos, 5, 0, 10, ancientRock);
		addBlock(world, basePos, 5, 0, 11, ancientRock);
		addBlock(world, basePos, 5, 0, 12, ancientRock);
		addBlock(world, basePos, 5, 0, 13, ancientRock);
		addBlock(world, basePos, 5, 0, 14, ancientRock);
		addBlock(world, basePos, 6, 0, 0, ancientRock);
		addBlock(world, basePos, 6, 0, 1, ancientRock);
		addBlock(world, basePos, 6, 0, 2, ancientRock);
		addBlock(world, basePos, 6, 0, 3, ancientRock);
		addBlock(world, basePos, 6, 0, 4, ancientRock);
		addBlock(world, basePos, 6, 0, 5, ancientRock);
		addBlock(world, basePos, 6, 0, 6, ancientRock);
		addBlock(world, basePos, 6, 0, 7, ancientRock);
		addBlock(world, basePos, 6, 0, 8, ancientRock);
		addBlock(world, basePos, 6, 0, 9, ancientRock);
		addBlock(world, basePos, 6, 0, 10, ancientRock);
		addBlock(world, basePos, 6, 0, 11, ancientRock);
		addBlock(world, basePos, 6, 0, 12, ancientRock);
		addBlock(world, basePos, 6, 0, 13, ancientRock);
		addBlock(world, basePos, 6, 0, 14, ancientRock);
		addBlock(world, basePos, 7, 0, 0, ancientRock);
		addBlock(world, basePos, 7, 0, 1, ancientRock);
		addBlock(world, basePos, 7, 0, 2, ancientRock);
		addBlock(world, basePos, 7, 0, 3, ancientRock);

		if (alteration2)
			addBlock(world, basePos, 7, 0, 4, ancientRock);

		addBlock(world, basePos, 7, 0, 5, ancientRock);
		addBlock(world, basePos, 7, 0, 6, ancientRock);
		addBlock(world, basePos, 7, 0, 7, ancientRock);
		addBlock(world, basePos, 7, 0, 8, ancientRock);
		addBlock(world, basePos, 7, 0, 9, ancientRock);
		addBlock(world, basePos, 7, 0, 10, ancientRock);
		addBlock(world, basePos, 7, 0, 11, ancientRock);
		addBlock(world, basePos, 7, 0, 12, ancientRock);
		addBlock(world, basePos, 7, 0, 13, ancientRock);
		addBlock(world, basePos, 7, 0, 14, ancientRock);
		addBlock(world, basePos, 8, 0, 0, ancientRock);
		addBlock(world, basePos, 8, 0, 1, ancientRock);

		if (alteration4)
			addBlock(world, basePos, 8, 0, 2, ancientRock);

		addBlock(world, basePos, 8, 0, 3, ancientRock);
		addBlock(world, basePos, 8, 0, 4, ancientRock);
		addBlock(world, basePos, 8, 0, 5, ancientRock);
		addBlock(world, basePos, 8, 0, 6, ancientRock);
		addBlock(world, basePos, 8, 0, 7, ancientRock);

		if (alteration6) {
			addBlock(world, basePos, 8, 0, 8, ancientRock);
			addBlock(world, basePos, 8, 0, 9, ancientRock);
			addBlock(world, basePos, 8, 0, 10, ancientRock);
			addBlock(world, basePos, 8, 0, 11, ancientRock);
			addBlock(world, basePos, 8, 0, 12, ancientRock);
		}
		addBlock(world, basePos, 8, 0, 13, ancientRock);
		addBlock(world, basePos, 8, 0, 14, ancientRock);
		addBlock(world, basePos, 9, 0, 0, ancientRock);
		addBlock(world, basePos, 9, 0, 1, ancientRock);
		addBlock(world, basePos, 9, 0, 2, ancientRock);
		addBlock(world, basePos, 9, 0, 3, ancientRock);
		addBlock(world, basePos, 9, 0, 4, ancientRock);
		addBlock(world, basePos, 9, 0, 5, ancientRock);
		addBlock(world, basePos, 9, 0, 6, ancientRock);
		addBlock(world, basePos, 9, 0, 7, ancientRock);
		addBlock(world, basePos, 9, 0, 8, ancientRock);
		addBlock(world, basePos, 9, 0, 9, ancientRock);
		addBlock(world, basePos, 9, 0, 10, ancientRock);
		addBlock(world, basePos, 9, 0, 11, ancientRock);
		addBlock(world, basePos, 9, 0, 12, ancientRock);
		addBlock(world, basePos, 9, 0, 13, ancientRock);
		addBlock(world, basePos, 9, 0, 14, ancientRock);
		addBlock(world, basePos, 10, 0, 0, ancientRock);
		addBlock(world, basePos, 10, 0, 1, ancientRock);

		if (alteration4) {
			addBlock(world, basePos, 10, 0, 2, ancientRock);
			addBlock(world, basePos, 10, 0, 3, ancientRock);
			addBlock(world, basePos, 10, 0, 4, ancientRock);
			addBlock(world, basePos, 10, 0, 5, ancientRock);
		}
		addBlock(world, basePos, 10, 0, 6, ancientRock);
		addBlock(world, basePos, 10, 0, 7, ancientRock);
		addBlock(world, basePos, 10, 0, 8, ancientRock);
		addBlock(world, basePos, 10, 0, 9, ancientRock);
		addBlock(world, basePos, 10, 0, 10, ancientRock);
		addBlock(world, basePos, 10, 0, 11, ancientRock);
		addBlock(world, basePos, 10, 0, 12, ancientRock);
		addBlock(world, basePos, 10, 0, 13, ancientRock);
		addBlock(world, basePos, 10, 0, 14, ancientRock);
		addBlock(world, basePos, 0, 1, 0, ancientRock);
		addBlock(world, basePos, 0, 1, 1, ancientRock);
		addBlock(world, basePos, 0, 1, 2, ancientRock);

		if (alteration1) {
			addBlock(world, basePos, 0, 1, 3, ancientRock);
			addBlock(world, basePos, 0, 1, 4, ancientRock);
			addBlock(world, basePos, 0, 1, 5, ancientRock);
			addBlock(world, basePos, 0, 1, 6, ancientRock);
		}

		addBlock(world, basePos, 0, 1, 7, ancientRock);
		addBlock(world, basePos, 0, 1, 8, ancientRock);
		addBlock(world, basePos, 0, 1, 9, ancientRock);
		addBlock(world, basePos, 0, 1, 10, ancientRock);
		addBlock(world, basePos, 0, 1, 11, ancientRock);
		addBlock(world, basePos, 0, 1, 12, ancientRock);
		addBlock(world, basePos, 0, 1, 13, ancientRock);
		addBlock(world, basePos, 0, 1, 14, ancientRock);
		addBlock(world, basePos, 1, 1, 0, ancientRock);
		addBlock(world, basePos, 1, 1, 1, air);
		addBlock(world, basePos, 1, 1, 6, ancientRock);
		addBlock(world, basePos, 1, 1, 7, ancientRock);
		addBlock(world, basePos, 1, 1, 8, ancientRock);
		addBlock(world, basePos, 1, 1, 9, air);
		addBlock(world, basePos, 1, 1, 10, air);
		addBlock(world, basePos, 1, 1, 11, air);
		addBlock(world, basePos, 1, 1, 12, air);
		addBlock(world, basePos, 1, 1, 13, air);
		addBlock(world, basePos, 1, 1, 14, ancientRock);
		addBlock(world, basePos, 2, 1, 0, ancientRock);
		addBlock(world, basePos, 2, 1, 1, air);
		addBlock(world, basePos, 2, 1, 2, air);
		addBlock(world, basePos, 2, 1, 6, ancientRock);
		addBlock(world, basePos, 2, 1, 7, ancientRock);

		if (alteration6)
			addBlock(world, basePos, 2, 1, 8, ancientRock);

		addBlock(world, basePos, 2, 1, 9, air);
		addBlock(world, basePos, 2, 1, 10, air);
		addBlock(world, basePos, 2, 1, 11, air);
		addBlock(world, basePos, 2, 1, 12, air);
		addBlock(world, basePos, 2, 1, 13, air);
		addBlock(world, basePos, 2, 1, 14, ancientRock);
		addBlock(world, basePos, 3, 1, 0, ancientRock);
		addBlock(world, basePos, 3, 1, 1, air);
		addBlock(world, basePos, 3, 1, 2, air);
		addBlock(world, basePos, 3, 1, 3, ancientRock);
		addBlock(world, basePos, 3, 1, 4, ancientRock);
		addBlock(world, basePos, 3, 1, 5, ancientRock);
		addBlock(world, basePos, 3, 1, 6, ancientRock);

		if (alteration5) {
			addBlock(world, basePos, 3, 1, 7, ancientRock);
			addBlock(world, basePos, 3, 1, 8, ancientRock);
			addBlock(world, basePos, 3, 1, 9, ancientRock);
			addBlock(world, basePos, 3, 1, 10, ancientRock);
		}
		addBlock(world, basePos, 3, 1, 11, ancientRock);
		addBlock(world, basePos, 3, 1, 12, air);
		addBlock(world, basePos, 3, 1, 13, air);
		addBlock(world, basePos, 3, 1, 14, ancientRock);
		addBlock(world, basePos, 4, 1, 0, ancientRock);
		addBlock(world, basePos, 4, 1, 1, air);
		addBlock(world, basePos, 4, 1, 2, air);
		addBlock(world, basePos, 4, 1, 3, ancientRock);
		addBlock(world, basePos, 4, 1, 4, ancientRock);
		addBlock(world, basePos, 4, 1, 5, ancientRock);
		addBlock(world, basePos, 4, 1, 6, ancientRock);
		addBlock(world, basePos, 4, 1, 7, ancientRock);
		addBlock(world, basePos, 4, 1, 8, ancientRock);
		addBlock(world, basePos, 4, 1, 9, ancientRock);
		addBlock(world, basePos, 4, 1, 10, ancientRock);
		addBlock(world, basePos, 4, 1, 11, ancientRock);
		addBlock(world, basePos, 4, 1, 12, air);
		addBlock(world, basePos, 4, 1, 13, air);
		addBlock(world, basePos, 4, 1, 14, ancientRock);
		addBlock(world, basePos, 5, 1, 0, ancientRock);
		addBlock(world, basePos, 5, 1, 1, air);
		addBlock(world, basePos, 5, 1, 2, air);
		addBlock(world, basePos, 5, 1, 3, ancientRock);
		addBlock(world, basePos, 5, 1, 4, ancientRock);
		addBlock(world, basePos, 5, 1, 5, ancientRock);
		addBlock(world, basePos, 5, 1, 6, ancientRock);
		addBlock(world, basePos, 5, 1, 7, ancientRock);
		addBlock(world, basePos, 5, 1, 8, ancientRock);

		if (alteration6)
			addBlock(world, basePos, 5, 1, 9, ancientRock);

		addBlock(world, basePos, 5, 1, 10, ancientRock);
		addBlock(world, basePos, 5, 1, 11, ancientRock);
		addBlock(world, basePos, 5, 1, 12, air);
		addBlock(world, basePos, 5, 1, 13, air);
		addBlock(world, basePos, 5, 1, 14, ancientRock);
		addBlock(world, basePos, 6, 1, 0, ancientRock);
		addBlock(world, basePos, 6, 1, 2, air);
		addBlock(world, basePos, 6, 1, 3, ancientRock);
		addBlock(world, basePos, 6, 1, 4, ancientRock);
		addBlock(world, basePos, 6, 1, 5, ancientRock);
		addBlock(world, basePos, 6, 1, 6, ancientRock);
		addBlock(world, basePos, 6, 1, 7, ancientRock);
		addBlock(world, basePos, 6, 1, 8, ancientRock);
		addBlock(world, basePos, 6, 1, 9, ancientRock);
		addBlock(world, basePos, 6, 1, 10, ancientRock);
		addBlock(world, basePos, 6, 1, 11, ancientRock);
		addBlock(world, basePos, 6, 1, 12, air);
		addBlock(world, basePos, 6, 1, 13, air);
		addBlock(world, basePos, 6, 1, 14, ancientRock);
		addBlock(world, basePos, 7, 1, 0, ancientRock);
		addBlock(world, basePos, 7, 1, 1, air);
		addBlock(world, basePos, 7, 1, 2, air);
		addBlock(world, basePos, 7, 1, 3, ancientRock);
		addBlock(world, basePos, 7, 1, 4, ancientRock);
		addBlock(world, basePos, 7, 1, 5, ancientRock);
		addBlock(world, basePos, 7, 1, 6, ancientRock);

		if (alteration1) {
			addBlock(world, basePos, 7, 1, 7, ancientRock);
			addBlock(world, basePos, 7, 1, 8, ancientRock);
			addBlock(world, basePos, 7, 1, 9, ancientRock);
			addBlock(world, basePos, 7, 1, 10, ancientRock);
		}
		addBlock(world, basePos, 7, 1, 11, ancientRock);
		addBlock(world, basePos, 7, 1, 12, air);
		addBlock(world, basePos, 7, 1, 13, air);
		addBlock(world, basePos, 7, 1, 14, ancientRock);
		addBlock(world, basePos, 8, 1, 0, ancientRock);
		addBlock(world, basePos, 8, 1, 1, air);
		addBlock(world, basePos, 8, 1, 2, air);
		addBlock(world, basePos, 8, 1, 3, air);
		addBlock(world, basePos, 8, 1, 4, air);
		addBlock(world, basePos, 8, 1, 5, air);
		addBlock(world, basePos, 8, 1, 6, air);
		addBlock(world, basePos, 8, 1, 7, air);
		addBlock(world, basePos, 8, 1, 8, air);
		addBlock(world, basePos, 8, 1, 12, air);
		addBlock(world, basePos, 8, 1, 13, air);
		addBlock(world, basePos, 8, 1, 14, ancientRock);
		addBlock(world, basePos, 9, 1, 0, ancientRock);
		addBlock(world, basePos, 9, 1, 1, air);
		addBlock(world, basePos, 9, 1, 2, air);
		addBlock(world, basePos, 9, 1, 3, air);
		addBlock(world, basePos, 9, 1, 4, air);
		addBlock(world, basePos, 9, 1, 5, air);
		addBlock(world, basePos, 9, 1, 6, air);
		addBlock(world, basePos, 9, 1, 7, air);
		addBlock(world, basePos, 9, 1, 8, air);
		addBlock(world, basePos, 9, 1, 11, air);
		addBlock(world, basePos, 9, 1, 12, air);
		addBlock(world, basePos, 9, 1, 13, air);
		addBlock(world, basePos, 9, 1, 14, ancientRock);
		addBlock(world, basePos, 10, 1, 0, ancientRock);
		addBlock(world, basePos, 10, 1, 1, ancientRock);
		addBlock(world, basePos, 10, 1, 2, ancientRock);
		addBlock(world, basePos, 10, 1, 3, ancientRock);
		addBlock(world, basePos, 10, 1, 4, ancientRock);
		addBlock(world, basePos, 10, 1, 5, ancientRock);
		addBlock(world, basePos, 10, 1, 6, ancientRock);
		addBlock(world, basePos, 10, 1, 7, ancientRock);
		addBlock(world, basePos, 10, 1, 8, ancientRock);
		addBlock(world, basePos, 10, 1, 9, ancientRock);
		addBlock(world, basePos, 10, 1, 10, ancientRock);
		addBlock(world, basePos, 10, 1, 11, ancientRock);

		if (alteration5) {
			addBlock(world, basePos, 10, 1, 12, ancientRock);
			addBlock(world, basePos, 10, 1, 13, ancientRock);
			addBlock(world, basePos, 10, 1, 14, ancientRock);
		}

		addBlock(world, basePos, 0, 2, 1, air);
		addBlock(world, basePos, 0, 2, 2, ancientRock);
		addBlock(world, basePos, 0, 2, 3, air);
		addBlock(world, basePos, 0, 2, 5, air);
		addBlock(world, basePos, 0, 2, 6, ancientRock);
		addBlock(world, basePos, 0, 2, 7, air);
		addBlock(world, basePos, 0, 2, 8, ancientRock);
		addBlock(world, basePos, 0, 2, 9, air);
		addBlock(world, basePos, 0, 2, 12, ancientRock);
		addBlock(world, basePos, 0, 2, 13, air);
		addBlock(world, basePos, 1, 2, 0, air);
		addBlock(world, basePos, 1, 2, 1, air);
		addBlock(world, basePos, 1, 2, 2, air);
		addBlock(world, basePos, 1, 2, 3, air);
		addBlock(world, basePos, 1, 2, 5, air);
		addBlock(world, basePos, 1, 2, 6, air);
		addBlock(world, basePos, 1, 2, 7, air);
		addBlock(world, basePos, 1, 2, 8, air);
		addBlock(world, basePos, 1, 2, 9, air);
		addBlock(world, basePos, 1, 2, 10, air);
		addBlock(world, basePos, 1, 2, 11, air);
		addBlock(world, basePos, 1, 2, 12, air);
		addBlock(world, basePos, 1, 2, 13, air);
		addBlock(world, basePos, 1, 2, 14, air);
		addBlock(world, basePos, 2, 2, 0, ancientRock);
		addBlock(world, basePos, 2, 2, 1, air);
		addBlock(world, basePos, 2, 2, 2, air);
		addBlock(world, basePos, 2, 2, 3, air);
		addBlock(world, basePos, 2, 2, 4, air);
		addBlock(world, basePos, 2, 2, 5, air);
		addBlock(world, basePos, 2, 2, 6, air);
		addBlock(world, basePos, 2, 2, 7, air);
		addBlock(world, basePos, 2, 2, 8, air);
		addBlock(world, basePos, 2, 2, 9, air);
		addBlock(world, basePos, 2, 2, 10, air);
		addBlock(world, basePos, 2, 2, 11, air);
		addBlock(world, basePos, 2, 2, 12, air);
		addBlock(world, basePos, 2, 2, 13, air);
		addBlock(world, basePos, 2, 2, 14, ancientRock);
		addBlock(world, basePos, 3, 2, 0, air);
		addBlock(world, basePos, 3, 2, 1, air);
		addBlock(world, basePos, 3, 2, 2, air);
		addBlock(world, basePos, 3, 2, 3, air);
		addBlock(world, basePos, 3, 2, 4, air);
		addBlock(world, basePos, 3, 2, 5, air);
		addBlock(world, basePos, 3, 2, 6, air);
		addBlock(world, basePos, 3, 2, 7, air);
		addBlock(world, basePos, 3, 2, 8, air);
		addBlock(world, basePos, 3, 2, 9, air);
		addBlock(world, basePos, 3, 2, 10, air);
		addBlock(world, basePos, 3, 2, 11, air);
		addBlock(world, basePos, 3, 2, 12, air);
		addBlock(world, basePos, 3, 2, 13, air);
		addBlock(world, basePos, 3, 2, 14, air);
		addBlock(world, basePos, 4, 2, 0, air);
		addBlock(world, basePos, 4, 2, 1, air);
		addBlock(world, basePos, 4, 2, 2, air);
		addBlock(world, basePos, 4, 2, 3, air);
		addBlock(world, basePos, 4, 2, 4, ancientRock);
		addBlock(world, basePos, 4, 2, 5, ancientRock);
		addBlock(world, basePos, 4, 2, 6, ancientRock);
		addBlock(world, basePos, 4, 2, 7, ancientRock);
		addBlock(world, basePos, 4, 2, 8, ancientRock);
		addBlock(world, basePos, 4, 2, 9, ancientRock);
		addBlock(world, basePos, 4, 2, 10, ancientRock);
		addBlock(world, basePos, 4, 2, 11, air);
		addBlock(world, basePos, 4, 2, 12, air);
		addBlock(world, basePos, 4, 2, 13, air);
		addBlock(world, basePos, 4, 2, 14, air);
		addBlock(world, basePos, 5, 2, 0, air);
		addBlock(world, basePos, 5, 2, 1, air);
		addBlock(world, basePos, 5, 2, 2, air);
		addBlock(world, basePos, 5, 2, 3, air);
		addBlock(world, basePos, 5, 2, 4, ancientRock);
		addBlock(world, basePos, 5, 2, 5, ancientRock);
		addBlock(world, basePos, 5, 2, 6, ancientRock);
		addBlock(world, basePos, 5, 2, 7, ancientRock);

		if (alteration3)
			addBlock(world, basePos, 5, 2, 8, ancientRock);

		addBlock(world, basePos, 5, 2, 9, ancientRock);
		addBlock(world, basePos, 5, 2, 10, ancientRock);
		addBlock(world, basePos, 5, 2, 11, air);
		addBlock(world, basePos, 5, 2, 12, air);
		addBlock(world, basePos, 5, 2, 13, air);
		addBlock(world, basePos, 5, 2, 14, air);
		addBlock(world, basePos, 6, 2, 2, air);
		addBlock(world, basePos, 6, 2, 3, air);
		addBlock(world, basePos, 6, 2, 4, ancientRock);
		addBlock(world, basePos, 6, 2, 5, ancientRock);
		addBlock(world, basePos, 6, 2, 6, ancientRock);
		addBlock(world, basePos, 6, 2, 7, ancientRock);
		addBlock(world, basePos, 6, 2, 8, ancientRock);
		addBlock(world, basePos, 6, 2, 9, ancientRock);
		addBlock(world, basePos, 6, 2, 10, ancientRock);
		addBlock(world, basePos, 6, 2, 11, air);
		addBlock(world, basePos, 6, 2, 12, air);
		addBlock(world, basePos, 6, 2, 13, air);
		addBlock(world, basePos, 6, 2, 14, air);
		addBlock(world, basePos, 7, 2, 0, air);
		addBlock(world, basePos, 7, 2, 1, air);
		addBlock(world, basePos, 7, 2, 2, air);
		addBlock(world, basePos, 7, 2, 3, air);
		addBlock(world, basePos, 7, 2, 4, air);
		addBlock(world, basePos, 7, 2, 5, air);
		addBlock(world, basePos, 7, 2, 6, air);
		addBlock(world, basePos, 7, 2, 7, air);
		addBlock(world, basePos, 7, 2, 8, air);
		addBlock(world, basePos, 7, 2, 9, air);
		addBlock(world, basePos, 7, 2, 10, air);
		addBlock(world, basePos, 7, 2, 11, air);
		addBlock(world, basePos, 7, 2, 12, air);
		addBlock(world, basePos, 7, 2, 13, air);
		addBlock(world, basePos, 7, 2, 14, air);
		addBlock(world, basePos, 8, 2, 0, ancientRock);
		addBlock(world, basePos, 8, 2, 1, air);
		addBlock(world, basePos, 8, 2, 2, air);
		addBlock(world, basePos, 8, 2, 3, air);
		addBlock(world, basePos, 8, 2, 4, air);
		addBlock(world, basePos, 8, 2, 5, air);
		addBlock(world, basePos, 8, 2, 6, air);
		addBlock(world, basePos, 8, 2, 7, air);
		addBlock(world, basePos, 8, 2, 8, air);
		addBlock(world, basePos, 8, 2, 9, air);
		addBlock(world, basePos, 8, 2, 10, air);
		addBlock(world, basePos, 8, 2, 11, air);
		addBlock(world, basePos, 8, 2, 12, air);
		addBlock(world, basePos, 8, 2, 13, air);
		addBlock(world, basePos, 8, 2, 14, ancientRock);
		addBlock(world, basePos, 9, 2, 0, air);
		addBlock(world, basePos, 9, 2, 1, air);
		addBlock(world, basePos, 9, 2, 2, air);
		addBlock(world, basePos, 9, 2, 3, air);
		addBlock(world, basePos, 9, 2, 4, air);
		addBlock(world, basePos, 9, 2, 5, air);
		addBlock(world, basePos, 9, 2, 6, air);
		addBlock(world, basePos, 9, 2, 10, air);
		addBlock(world, basePos, 9, 2, 11, air);
		addBlock(world, basePos, 9, 2, 12, air);
		addBlock(world, basePos, 9, 2, 13, air);
		addBlock(world, basePos, 9, 2, 14, air);
		addBlock(world, basePos, 10, 2, 0, air);
		addBlock(world, basePos, 10, 2, 1, air);

		if (alteration2)
			addBlock(world, basePos, 10, 2, 2, ancientRock);

		addBlock(world, basePos, 10, 2, 3, air);
		addBlock(world, basePos, 10, 2, 4, air);
		addBlock(world, basePos, 10, 2, 5, air);
		addBlock(world, basePos, 10, 2, 6, air);
		addBlock(world, basePos, 10, 2, 10, air);
		addBlock(world, basePos, 10, 2, 11, air);
		addBlock(world, basePos, 10, 2, 12, ancientRock);
		addBlock(world, basePos, 10, 2, 13, air);
		addBlock(world, basePos, 10, 2, 14, air);
		addBlock(world, basePos, 0, 3, 6, ancientRock);
		addBlock(world, basePos, 0, 3, 8, ancientRock);
		addBlock(world, basePos, 1, 3, 1, air);
		addBlock(world, basePos, 1, 3, 2, air);
		addBlock(world, basePos, 1, 3, 3, air);
		addBlock(world, basePos, 1, 3, 4, air);
		addBlock(world, basePos, 1, 3, 5, air);
		addBlock(world, basePos, 1, 3, 6, air);
		addBlock(world, basePos, 1, 3, 7, air);
		addBlock(world, basePos, 1, 3, 8, air);
		addBlock(world, basePos, 1, 3, 9, air);
		addBlock(world, basePos, 1, 3, 10, air);
		addBlock(world, basePos, 1, 3, 11, air);
		addBlock(world, basePos, 1, 3, 12, air);
		addBlock(world, basePos, 1, 3, 13, air);
		addBlock(world, basePos, 2, 3, 1, air);
		addBlock(world, basePos, 2, 3, 2, air);
		addBlock(world, basePos, 2, 3, 3, air);
		addBlock(world, basePos, 2, 3, 4, air);
		addBlock(world, basePos, 2, 3, 5, air);
		addBlock(world, basePos, 2, 3, 6, air);
		addBlock(world, basePos, 2, 3, 7, air);
		addBlock(world, basePos, 2, 3, 8, air);
		addBlock(world, basePos, 2, 3, 9, air);
		addBlock(world, basePos, 2, 3, 10, air);
		addBlock(world, basePos, 2, 3, 11, air);
		addBlock(world, basePos, 2, 3, 12, air);
		addBlock(world, basePos, 2, 3, 13, air);
		addBlock(world, basePos, 3, 3, 1, air);
		addBlock(world, basePos, 3, 3, 2, air);
		addBlock(world, basePos, 3, 3, 3, air);
		addBlock(world, basePos, 3, 3, 4, air);
		addBlock(world, basePos, 3, 3, 5, air);
		addBlock(world, basePos, 3, 3, 6, air);
		addBlock(world, basePos, 3, 3, 7, air);
		addBlock(world, basePos, 3, 3, 8, air);
		addBlock(world, basePos, 3, 3, 9, air);
		addBlock(world, basePos, 3, 3, 10, air);
		addBlock(world, basePos, 3, 3, 11, air);
		addBlock(world, basePos, 3, 3, 12, air);
		addBlock(world, basePos, 3, 3, 13, air);
		addBlock(world, basePos, 4, 3, 1, air);
		addBlock(world, basePos, 4, 3, 2, air);
		addBlock(world, basePos, 4, 3, 3, air);
		addBlock(world, basePos, 4, 3, 4, air);
		addBlock(world, basePos, 4, 3, 5, air);
		addBlock(world, basePos, 4, 3, 6, air);
		addBlock(world, basePos, 4, 3, 7, air);
		addBlock(world, basePos, 4, 3, 8, air);
		addBlock(world, basePos, 4, 3, 9, air);
		addBlock(world, basePos, 4, 3, 10, air);
		addBlock(world, basePos, 4, 3, 11, air);
		addBlock(world, basePos, 4, 3, 12, air);
		addBlock(world, basePos, 4, 3, 13, air);
		addBlock(world, basePos, 5, 3, 2, air);
		addBlock(world, basePos, 5, 3, 3, air);
		addBlock(world, basePos, 5, 3, 4, air);

		if (rand.nextInt(10) == 0)
			addBlock(world, basePos, 5, 3, 5, carvedRuneOfDirection);

		addBlock(world, basePos, 5, 3, 6, ancientRock);
		addBlock(world, basePos, 5, 3, 7, ancientRock);
		addBlock(world, basePos, 5, 3, 8, ancientRock);

		if (rand.nextInt(10) == 0)
			addBlock(world, basePos, 5, 3, 9, carvedRuneOfSpace);

		addBlock(world, basePos, 5, 3, 10, air);
		addBlock(world, basePos, 5, 3, 11, air);
		addBlock(world, basePos, 5, 3, 12, air);
		addBlock(world, basePos, 5, 3, 13, air);
		addBlock(world, basePos, 6, 3, 2, air);
		addBlock(world, basePos, 6, 3, 3, air);
		addBlock(world, basePos, 6, 3, 4, air);
		addBlock(world, basePos, 6, 3, 5, air);
		addBlock(world, basePos, 6, 3, 6, air);
		addBlock(world, basePos, 6, 3, 7, air);
		addBlock(world, basePos, 6, 3, 8, air);
		addBlock(world, basePos, 6, 3, 9, air);
		addBlock(world, basePos, 6, 3, 10, air);
		addBlock(world, basePos, 6, 3, 11, air);
		addBlock(world, basePos, 6, 3, 12, air);
		addBlock(world, basePos, 6, 3, 13, air);
		addBlock(world, basePos, 7, 3, 1, air);
		addBlock(world, basePos, 7, 3, 2, air);
		addBlock(world, basePos, 7, 3, 3, air);
		addBlock(world, basePos, 7, 3, 4, air);
		addBlock(world, basePos, 7, 3, 5, air);
		addBlock(world, basePos, 7, 3, 6, air);
		addBlock(world, basePos, 7, 3, 7, air);
		addBlock(world, basePos, 7, 3, 8, air);
		addBlock(world, basePos, 7, 3, 9, air);
		addBlock(world, basePos, 7, 3, 10, air);
		addBlock(world, basePos, 7, 3, 11, air);
		addBlock(world, basePos, 7, 3, 12, air);
		addBlock(world, basePos, 7, 3, 13, air);
		addBlock(world, basePos, 8, 3, 1, air);
		addBlock(world, basePos, 8, 3, 2, air);
		addBlock(world, basePos, 8, 3, 3, air);
		addBlock(world, basePos, 8, 3, 4, air);
		addBlock(world, basePos, 8, 3, 5, air);
		addBlock(world, basePos, 8, 3, 6, air);
		addBlock(world, basePos, 8, 3, 7, air);
		addBlock(world, basePos, 8, 3, 8, air);
		addBlock(world, basePos, 8, 3, 9, air);
		addBlock(world, basePos, 8, 3, 10, air);
		addBlock(world, basePos, 8, 3, 11, air);
		addBlock(world, basePos, 8, 3, 12, air);
		addBlock(world, basePos, 8, 3, 13, air);
		addBlock(world, basePos, 9, 3, 1, air);
		addBlock(world, basePos, 9, 3, 2, air);
		addBlock(world, basePos, 9, 3, 3, air);
		addBlock(world, basePos, 9, 3, 4, air);
		addBlock(world, basePos, 9, 3, 5, air);
		addBlock(world, basePos, 9, 3, 6, air);
		addBlock(world, basePos, 9, 3, 7, air);
		addBlock(world, basePos, 9, 3, 10, air);
		addBlock(world, basePos, 9, 3, 11, air);
		addBlock(world, basePos, 9, 3, 12, air);
		addBlock(world, basePos, 9, 3, 13, air);
		addBlock(world, basePos, 2, 4, 4, air);
		addBlock(world, basePos, 2, 4, 5, air);
		addBlock(world, basePos, 2, 4, 6, air);
		addBlock(world, basePos, 2, 4, 7, air);
		addBlock(world, basePos, 2, 4, 8, air);
		addBlock(world, basePos, 2, 4, 9, air);
		addBlock(world, basePos, 2, 4, 10, air);
		addBlock(world, basePos, 3, 4, 3, air);
		addBlock(world, basePos, 3, 4, 4, air);
		addBlock(world, basePos, 3, 4, 5, air);
		addBlock(world, basePos, 3, 4, 6, air);
		addBlock(world, basePos, 3, 4, 7, air);
		addBlock(world, basePos, 3, 4, 8, air);
		addBlock(world, basePos, 3, 4, 9, air);
		addBlock(world, basePos, 3, 4, 10, air);
		addBlock(world, basePos, 3, 4, 11, air);
		addBlock(world, basePos, 4, 4, 2, air);
		addBlock(world, basePos, 4, 4, 3, air);
		addBlock(world, basePos, 4, 4, 4, air);
		addBlock(world, basePos, 4, 4, 5, air);
		addBlock(world, basePos, 4, 4, 6, air);
		addBlock(world, basePos, 4, 4, 7, air);
		addBlock(world, basePos, 4, 4, 8, air);
		addBlock(world, basePos, 4, 4, 9, air);
		addBlock(world, basePos, 4, 4, 10, air);
		addBlock(world, basePos, 4, 4, 11, air);
		addBlock(world, basePos, 4, 4, 12, air);
		addBlock(world, basePos, 5, 4, 2, air);
		addBlock(world, basePos, 5, 4, 3, air);
		addBlock(world, basePos, 5, 4, 4, air);
		addBlock(world, basePos, 5, 4, 5, ancientRock);
		addBlock(world, basePos, 5, 4, 6, air);
		addBlock(world, basePos, 5, 4, 7, air);
		addBlock(world, basePos, 5, 4, 8, air);
		addBlock(world, basePos, 5, 4, 9, ancientRock);
		addBlock(world, basePos, 5, 4, 10, air);
		addBlock(world, basePos, 5, 4, 11, air);
		addBlock(world, basePos, 5, 4, 12, air);
		addBlock(world, basePos, 6, 4, 2, air);
		addBlock(world, basePos, 6, 4, 3, air);
		addBlock(world, basePos, 6, 4, 4, air);
		addBlock(world, basePos, 6, 4, 5, air);
		addBlock(world, basePos, 6, 4, 6, air);
		addBlock(world, basePos, 6, 4, 7, air);
		addBlock(world, basePos, 6, 4, 8, air);
		addBlock(world, basePos, 6, 4, 9, air);
		addBlock(world, basePos, 6, 4, 10, air);
		addBlock(world, basePos, 6, 4, 11, air);
		addBlock(world, basePos, 6, 4, 12, air);
		addBlock(world, basePos, 7, 4, 3, air);
		addBlock(world, basePos, 7, 4, 4, air);
		addBlock(world, basePos, 7, 4, 5, air);
		addBlock(world, basePos, 7, 4, 6, air);
		addBlock(world, basePos, 7, 4, 7, air);
		addBlock(world, basePos, 7, 4, 8, air);
		addBlock(world, basePos, 7, 4, 9, air);
		addBlock(world, basePos, 7, 4, 10, air);
		addBlock(world, basePos, 7, 4, 11, air);
		addBlock(world, basePos, 8, 4, 4, air);
		addBlock(world, basePos, 8, 4, 5, air);
		addBlock(world, basePos, 8, 4, 6, air);
		addBlock(world, basePos, 8, 4, 7, air);
		addBlock(world, basePos, 8, 4, 8, air);
		addBlock(world, basePos, 8, 4, 9, air);
		addBlock(world, basePos, 8, 4, 10, air);
		addBlock(world, basePos, 3, 5, 4, air);
		addBlock(world, basePos, 3, 5, 5, air);
		addBlock(world, basePos, 3, 5, 6, air);
		addBlock(world, basePos, 3, 5, 7, air);
		addBlock(world, basePos, 3, 5, 8, air);
		addBlock(world, basePos, 3, 5, 9, air);
		addBlock(world, basePos, 3, 5, 10, air);
		addBlock(world, basePos, 4, 5, 3, air);
		addBlock(world, basePos, 4, 5, 4, air);
		addBlock(world, basePos, 4, 5, 5, air);
		addBlock(world, basePos, 4, 5, 6, air);
		addBlock(world, basePos, 4, 5, 7, air);
		addBlock(world, basePos, 4, 5, 8, air);
		addBlock(world, basePos, 4, 5, 9, air);
		addBlock(world, basePos, 4, 5, 10, air);
		addBlock(world, basePos, 4, 5, 11, air);
		addBlock(world, basePos, 5, 5, 3, air);
		addBlock(world, basePos, 5, 5, 4, air);
		addBlock(world, basePos, 5, 5, 5, ancientRock);
		addBlock(world, basePos, 5, 5, 6, air);
		addBlock(world, basePos, 5, 5, 7, air);
		addBlock(world, basePos, 5, 5, 8, air);

		if (alteration1)
			addBlock(world, basePos, 5, 5, 9, ancientRock);

		addBlock(world, basePos, 5, 5, 10, air);
		addBlock(world, basePos, 5, 5, 11, air);
		addBlock(world, basePos, 6, 5, 3, air);
		addBlock(world, basePos, 6, 5, 4, air);
		addBlock(world, basePos, 6, 5, 5, air);
		addBlock(world, basePos, 6, 5, 6, air);
		addBlock(world, basePos, 6, 5, 7, air);
		addBlock(world, basePos, 6, 5, 8, air);
		addBlock(world, basePos, 6, 5, 9, air);
		addBlock(world, basePos, 6, 5, 10, air);
		addBlock(world, basePos, 6, 5, 11, air);
		addBlock(world, basePos, 7, 5, 4, air);
		addBlock(world, basePos, 7, 5, 5, air);
		addBlock(world, basePos, 7, 5, 6, air);
		addBlock(world, basePos, 7, 5, 7, air);
		addBlock(world, basePos, 7, 5, 8, air);
		addBlock(world, basePos, 7, 5, 9, air);
		addBlock(world, basePos, 7, 5, 10, air);
		addBlock(world, basePos, 4, 6, 4, air);
		addBlock(world, basePos, 4, 6, 5, air);
		addBlock(world, basePos, 4, 6, 6, air);
		addBlock(world, basePos, 4, 6, 7, air);
		addBlock(world, basePos, 4, 6, 8, air);
		addBlock(world, basePos, 4, 6, 9, air);
		addBlock(world, basePos, 4, 6, 10, air);
		addBlock(world, basePos, 5, 6, 4, air);
		addBlock(world, basePos, 5, 6, 5, ancientRock);
		addBlock(world, basePos, 5, 6, 6, air);
		addBlock(world, basePos, 5, 6, 7, air);
		addBlock(world, basePos, 5, 6, 8, air);
		addBlock(world, basePos, 5, 6, 9, ancientRock);
		addBlock(world, basePos, 5, 6, 10, air);
		addBlock(world, basePos, 6, 6, 4, air);
		addBlock(world, basePos, 6, 6, 5, air);
		addBlock(world, basePos, 6, 6, 6, air);
		addBlock(world, basePos, 6, 6, 7, air);
		addBlock(world, basePos, 6, 6, 8, air);
		addBlock(world, basePos, 6, 6, 9, air);
		addBlock(world, basePos, 6, 6, 10, air);
		addBlock(world, basePos, 4, 7, 4, air);
		addBlock(world, basePos, 4, 7, 5, air);
		addBlock(world, basePos, 4, 7, 6, air);
		addBlock(world, basePos, 4, 7, 7, air);
		addBlock(world, basePos, 4, 7, 8, air);
		addBlock(world, basePos, 4, 7, 9, air);
		addBlock(world, basePos, 4, 7, 10, air);
		addBlock(world, basePos, 5, 7, 4, air);
		addBlock(world, basePos, 5, 7, 5, ancientRock);
		addBlock(world, basePos, 5, 7, 6, air);
		addBlock(world, basePos, 5, 7, 7, air);
		addBlock(world, basePos, 5, 7, 8, air);
		addBlock(world, basePos, 5, 7, 9, ancientRock);
		addBlock(world, basePos, 5, 7, 10, air);
		addBlock(world, basePos, 6, 7, 4, air);
		addBlock(world, basePos, 6, 7, 5, air);
		addBlock(world, basePos, 6, 7, 6, air);
		addBlock(world, basePos, 6, 7, 7, air);
		addBlock(world, basePos, 6, 7, 8, air);
		addBlock(world, basePos, 6, 7, 9, air);
		addBlock(world, basePos, 6, 7, 10, air);
		addBlock(world, basePos, 4, 8, 5, air);
		addBlock(world, basePos, 4, 8, 6, air);
		addBlock(world, basePos, 4, 8, 7, air);
		addBlock(world, basePos, 4, 8, 8, air);
		addBlock(world, basePos, 4, 8, 9, air);
		addBlock(world, basePos, 5, 8, 4, air);

		if (rand.nextInt(10) == 0)
			addBlock(world, basePos, 5, 8, 5, carvedRuneOfReality);

		addBlock(world, basePos, 5, 8, 6, ancientRock);
		addBlock(world, basePos, 5, 8, 7, ancientRock);
		addBlock(world, basePos, 5, 8, 8, ancientRock);

		if (rand.nextInt(10) == 0)
			addBlock(world, basePos, 5, 8, 9, carvedRuneOfTravel);

		addBlock(world, basePos, 5, 8, 10, air);
		addBlock(world, basePos, 6, 8, 5, air);
		addBlock(world, basePos, 6, 8, 6, air);
		addBlock(world, basePos, 6, 8, 7, air);
		addBlock(world, basePos, 6, 8, 8, air);
		addBlock(world, basePos, 6, 8, 9, air);
	}

	@Override
	protected void spawnEntities(World world, Random rand, BlockPos basePos) {
		EntityCorruptedTraveller corruptedTraveller = new EntityCorruptedTraveller(world);

		corruptedTraveller.setLocationAndAngles(basePos.getX() + 5, basePos.getY() + 4, basePos.getZ() + 7, rand.nextFloat() * 360, 0);
		world.spawnEntity(corruptedTraveller);
	}
}
