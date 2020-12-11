package net.tslat.aoa3.worldgen.surfacebuilder;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class OceanlessSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	public OceanlessSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> configFactory) {
		super(configFactory);
	}

	@Override
	public void buildSurface(Random rand, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState fillerBlock, BlockState fillerFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		BlockState surfaceBlock = config.getTop();
		BlockState subSurfaceBlock = config.getUnder();
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		int height = (int)(noise / 3d + 3d + rand.nextDouble() * 0.25d);
		int subSurfaceBlockLayers = -1;
		int chunkX = x & 15;
		int chunkZ = z & 15;

		for (int y = startHeight; y >= 0; y--) {
			mutablePos.setPos(chunkX, y, chunkZ);

			BlockState yBlock = chunk.getBlockState(mutablePos);

			if (yBlock.isAir()) {
				subSurfaceBlockLayers = -1;
			}
			else if (yBlock.getBlock() == fillerBlock.getBlock()) {
				if (subSurfaceBlockLayers == -1) {
					if (height <= 0) {
						surfaceBlock = Blocks.AIR.getDefaultState();
						subSurfaceBlock = fillerBlock;
					}

					chunk.setBlockState(mutablePos, surfaceBlock, false);

					subSurfaceBlockLayers = height;
				}
				else if (subSurfaceBlockLayers > 0) {
					subSurfaceBlockLayers--;

					chunk.setBlockState(mutablePos, subSurfaceBlock, false);
				}
			}
		}
	}
}
