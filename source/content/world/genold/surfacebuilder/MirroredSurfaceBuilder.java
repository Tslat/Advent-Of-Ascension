/*
package net.tslat.aoa3.content.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkAccess;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import java.util.Random;

public class MirroredSurfaceBuilder extends SurfaceBuilder<MirroredSurfaceBuilder.Config> {
	public MirroredSurfaceBuilder(Codec<Config> codec) {
		super(codec);
	}

	@Override
	public void apply(Random rand, ChunkAccess chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState fillerBlock, BlockState defaultFluid, int seaLevel, long seed, Config config) {
		BlockState surfaceBlock = config.getTopMaterial();
		BlockState subSurfaceBlock = config.getUnderMaterial();
		BlockState invertedSurfaceBlock = config.getInvertedTop();
		BlockState invertedSubsurfaceBlock = config.getInvertedUnder();
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		int height = (int)(noise / 3d + 3d + rand.nextDouble() * 0.25d);
		int subSurfaceBlockLayers = -1;
		int bottomYPos = 0;
		int topYPos = startHeight;
		int chunkX = x & 15;
		int chunkZ = z & 15;

		for (int y = startHeight; y >= 0; y--) {
			mutablePos.set(chunkX, y, chunkZ);

			BlockState yBlock = chunk.getBlockState(mutablePos);

			if (yBlock.isAir()) {
				subSurfaceBlockLayers = -1;
			}
			else if (yBlock.getBlock() == fillerBlock.getBlock()) {
				if (subSurfaceBlockLayers == -1) {
					if (height <= 0) {
						surfaceBlock = Blocks.AIR.defaultBlockState();
						subSurfaceBlock = fillerBlock;
					}

					chunk.setBlockState(mutablePos, surfaceBlock, false);

					topYPos = y;
					subSurfaceBlockLayers = height;
				}
				else if (subSurfaceBlockLayers > 0) {
					subSurfaceBlockLayers--;

					chunk.setBlockState(mutablePos, subSurfaceBlock, false);
				}

				bottomYPos = y;
			}
		}

		subSurfaceBlockLayers = -1;

		for (int y = bottomYPos; y <= topYPos; y++) {
			mutablePos.set(chunkX, y, chunkZ);

			BlockState yBlock = chunk.getBlockState(mutablePos);

			if (yBlock.isAir()) {
				subSurfaceBlockLayers = -1;
			}
			else if (yBlock.getBlock() == fillerBlock.getBlock()) {
				if (subSurfaceBlockLayers == -1) {
					if (height <= 0) {
						invertedSurfaceBlock = Blocks.AIR.defaultBlockState();
						subSurfaceBlock = fillerBlock;
					}

					chunk.setBlockState(mutablePos, invertedSurfaceBlock, false);

					subSurfaceBlockLayers = height;
				}
				else if (subSurfaceBlockLayers > 0) {
					subSurfaceBlockLayers--;

					chunk.setBlockState(mutablePos, invertedSubsurfaceBlock, false);
				}
			}
			else if (yBlock.getBlock() == subSurfaceBlock.getBlock()) {
				return;
			}
		}
	}

	public static class Config implements ISurfaceBuilderConfig {
		public static final Codec<Config> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
				BlockState.CODEC.fieldOf("surface_block").forGetter((config) -> config.surfaceBlock),
				BlockState.CODEC.fieldOf("inverted_surface_block").forGetter((config) -> config.invertedSurfaceBlock),
				BlockState.CODEC.fieldOf("subsurface_block").forGetter((config) -> config.subsurfaceBlock),
				BlockState.CODEC.fieldOf("inverted_subsurface_block").forGetter((config) -> config.invertedSubsurfaceBlock))
				.apply(instance, Config::new));

		private final BlockState surfaceBlock;
		private final BlockState subsurfaceBlock;
		private final BlockState invertedSurfaceBlock;
		private final BlockState invertedSubsurfaceBlock;

		public Config(BlockState surfaceBlock, BlockState invertedSurfaceBlock, BlockState subsurfaceBlock, BlockState invertedSubsurfaceBlock) {
			this.surfaceBlock = surfaceBlock;
			this.subsurfaceBlock = subsurfaceBlock;
			this.invertedSurfaceBlock = invertedSurfaceBlock;
			this.invertedSubsurfaceBlock = invertedSubsurfaceBlock;
		}

		@Override
		public BlockState getTopMaterial() {
			return this.surfaceBlock;
		}

		@Override
		public BlockState getUnderMaterial() {
			return this.subsurfaceBlock;
		}

		public BlockState getInvertedTop() {
			return this.invertedSurfaceBlock;
		}

		public BlockState getInvertedUnder() {
			return this.invertedSubsurfaceBlock;
		}
	}
}
*/
