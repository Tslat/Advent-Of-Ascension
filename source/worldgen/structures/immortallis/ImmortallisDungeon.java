package net.tslat.aoa3.worldgen.structures.immortallis;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.worldgen.WorldGenerator;

import java.util.Random;

public class ImmortallisDungeon extends WorldGenerator {
	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		boolean result;

		result = new ImmortallisRoom1().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom2().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom3().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom4().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom5().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom6().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom7().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom8().setChunkPos(chunkPos).generate(world, rand, pos);
		result = result && new ImmortallisRoom9().setChunkPos(chunkPos).generate(world, rand, pos);

		return result;
	}
}
