package net.tslat.aoa3.structure.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GiantMarshFern2 extends AoAStructure { //StructureSize: 5x7x5
	private static final IBlockState orangePetals = BlockRegister.petalsOrange.getDefaultState();
	private static final IBlockState stem = BlockRegister.plantStem.getDefaultState();

	public GiantMarshFern2() {
		super("GiantMarshFern2");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		int tiers = 1 + rand.nextInt(3);
		
		while (tiers > 0) {
			addBlock(world, basePos, 2, 0, 2, stem);
			addBlock(world, basePos, 2, 1, 2, stem);
			addBlock(world, basePos, 2, 2, 2, stem);
			addBlock(world, basePos, 2, 3, 2, stem);
			addBlock(world, basePos, 1, 4, 2, orangePetals);
			addBlock(world, basePos, 2, 4, 1, orangePetals);
			addBlock(world, basePos, 2, 4, 2, stem);
			addBlock(world, basePos, 2, 4, 3, orangePetals);
			addBlock(world, basePos, 3, 4, 2, orangePetals);
			addBlock(world, basePos, 0, 5, 2, orangePetals);
			addBlock(world, basePos, 1, 5, 2, orangePetals);
			addBlock(world, basePos, 2, 5, 0, orangePetals);
			addBlock(world, basePos, 2, 5, 1, orangePetals);
			addBlock(world, basePos, 2, 5, 2, stem);
			addBlock(world, basePos, 2, 5, 3, orangePetals);
			addBlock(world, basePos, 2, 5, 4, orangePetals);
			addBlock(world, basePos, 3, 5, 2, orangePetals);
			addBlock(world, basePos, 4, 5, 2, orangePetals);
			addBlock(world, basePos, 0, 6, 2, orangePetals);
			addBlock(world, basePos, 2, 6, 0, orangePetals);
			addBlock(world, basePos, 2, 6, 2, stem);
			addBlock(world, basePos, 2, 6, 4, orangePetals);
			addBlock(world, basePos, 4, 6, 2, orangePetals);

			tiers--;
			basePos = basePos.up(7);
		}
	}
}
