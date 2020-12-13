package net.tslat.aoa3.worldgen.chunkgenerator;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;

public class AdjustableOceanChunkBuilder extends OverworldChunkGenerator {
	public AdjustableOceanChunkBuilder(IWorld world, BiomeProvider provider, OverworldGenSettings genSettings) {
		super(world, provider, genSettings);
	}

	@Override
	protected void makeBedrock(IChunk chunk, Random rand) {
		ChunkPos chunkPos = chunk.getPos();
		BlockPos.Mutable pos = new BlockPos.Mutable();
		BlockState dimFabric = AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState();

		for (int x = chunkPos.getXStart(); x <= chunkPos.getXEnd(); x++) {
			for (int z = chunkPos.getZStart(); z <= chunkPos.getZEnd(); z++) {
				for (int y = 0; y <= 1; y++) {
					chunk.setBlockState(pos.setPos(x, y, z), dimFabric, false);
				}
			}
		}
	}

	@Override
	public int getSeaLevel() {
		return world.getSeaLevel();
	}
}
