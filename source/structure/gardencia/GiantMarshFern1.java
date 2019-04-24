package net.tslat.aoa3.structure.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GiantMarshFern1 extends AoAStructure { //StructureSize: 7x7x7
	private static final IBlockState orangePetals = BlockRegister.petalsOrange.getDefaultState();
	private static final IBlockState stem = BlockRegister.plantStem.getDefaultState();

	public GiantMarshFern1() {
		super("GiantMarshFern1");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int tiers = 1 + rand.nextInt(3);
		
		while (tiers > 0) {
			addBlock(world, basePos, 3, 0, 3, stem);
			addBlock(world, basePos, 3, 1, 3, stem);
			addBlock(world, basePos, 3, 2, 3, stem);
			addBlock(world, basePos, 3, 3, 3, stem);
			addBlock(world, basePos, 2, 4, 2, orangePetals);
			addBlock(world, basePos, 2, 4, 3, orangePetals);
			addBlock(world, basePos, 2, 4, 4, orangePetals);
			addBlock(world, basePos, 3, 4, 2, orangePetals);
			addBlock(world, basePos, 3, 4, 3, stem);
			addBlock(world, basePos, 3, 4, 4, orangePetals);
			addBlock(world, basePos, 4, 4, 2, orangePetals);
			addBlock(world, basePos, 4, 4, 3, orangePetals);
			addBlock(world, basePos, 4, 4, 4, orangePetals);
			addBlock(world, basePos, 1, 5, 1, orangePetals);
			addBlock(world, basePos, 1, 5, 2, orangePetals);
			addBlock(world, basePos, 1, 5, 4, orangePetals);
			addBlock(world, basePos, 1, 5, 5, orangePetals);
			addBlock(world, basePos, 2, 5, 1, orangePetals);
			addBlock(world, basePos, 2, 5, 5, orangePetals);
			addBlock(world, basePos, 3, 5, 3, stem);
			addBlock(world, basePos, 4, 5, 1, orangePetals);
			addBlock(world, basePos, 4, 5, 5, orangePetals);
			addBlock(world, basePos, 5, 5, 1, orangePetals);
			addBlock(world, basePos, 5, 5, 2, orangePetals);
			addBlock(world, basePos, 5, 5, 4, orangePetals);
			addBlock(world, basePos, 5, 5, 5, orangePetals);
			addBlock(world, basePos, 0, 6, 0, orangePetals);
			addBlock(world, basePos, 0, 6, 1, orangePetals);
			addBlock(world, basePos, 0, 6, 5, orangePetals);
			addBlock(world, basePos, 0, 6, 6, orangePetals);
			addBlock(world, basePos, 1, 6, 0, orangePetals);
			addBlock(world, basePos, 1, 6, 6, orangePetals);
			addBlock(world, basePos, 3, 6, 3, stem);
			addBlock(world, basePos, 5, 6, 0, orangePetals);
			addBlock(world, basePos, 5, 6, 6, orangePetals);
			addBlock(world, basePos, 6, 6, 0, orangePetals);
			addBlock(world, basePos, 6, 6, 1, orangePetals);
			addBlock(world, basePos, 6, 6, 5, orangePetals);
			addBlock(world, basePos, 6, 6, 6, orangePetals);

			tiers--;
			basePos = basePos.up(7);
		}
	}
}
