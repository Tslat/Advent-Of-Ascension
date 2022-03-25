package net.tslat.aoa3.content.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import java.util.Random;

public class OceanlessSurfaceBuilder extends SurfaceBuilder<OceanlessSurfaceBuilder.Config> {
	public OceanlessSurfaceBuilder(Codec<Config> codec) {
		super(codec);
	}

	@Override
	public void apply(Random rand, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState fillerBlock, BlockState defaultFluid, int seaLevel, long seed, Config config) {
		BlockState surfaceBlock = config.getTopMaterial();
		BlockState subSurfaceBlock = config.getUnderMaterial();
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		int height = (int)(noise / 3d + 3d + rand.nextDouble() * 0.25d);
		int subSurfaceBlockLayers = -1;
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

					subSurfaceBlockLayers = height;
				}
				else if (subSurfaceBlockLayers > 0) {
					subSurfaceBlockLayers--;

					chunk.setBlockState(mutablePos, subSurfaceBlock, false);
				}
			}
		}
	}

	public static class Config implements ISurfaceBuilderConfig {
		public static final Codec<Config> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
				BlockState.CODEC.fieldOf("surface_block").forGetter((config) -> config.surfaceBlock),
				BlockState.CODEC.fieldOf("subsurface_block").forGetter((config) -> config.subsurfaceBlock))
				.apply(instance, Config::new));

		private final BlockState surfaceBlock;
		private final BlockState subsurfaceBlock;

		public Config(BlockState surfaceBlock, BlockState subsurfaceBlock) {
			this.surfaceBlock = surfaceBlock;
			this.subsurfaceBlock = subsurfaceBlock;
		}

		@Override
		public BlockState getTopMaterial() {
			return this.surfaceBlock;
		}

		@Override
		public BlockState getUnderMaterial() {
			return this.subsurfaceBlock;
		}
	}
}
