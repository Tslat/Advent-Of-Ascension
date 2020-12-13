package net.tslat.aoa3.worldgen.structures.ancientcavern;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.worldgen.WorldGenerator;

import java.util.Random;

public class AncientCavern extends WorldGenerator {
	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		boolean result;

		result = new AncientCavernRoom1().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernRoom2().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernRoom3().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernRoom4().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernRoom5().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernRoom6().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernConiferonRoom().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernHoronRoom().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernGoldorthRoom().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new AncientCavernPenumbraRoom().setChunkPos(chunkPos).generate(world, rand, pos);

		return result;
	}
}
