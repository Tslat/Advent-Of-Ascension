package net.tslat.aoa3.worldgen.structure.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.tslat.aoa3.worldgen.feature.features.config.BlockPosConfig;

public class OneTimeStructure extends AoAStructureBase<BlockPosConfig> {
	public OneTimeStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(BlockPosConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<BlockPosConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<BlockPosConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected boolean shouldAvoidRotating() {
				return true;
			}

			@Override
			protected int getStructurePieceDepth() {
				return 15;
			}

			@Override
			protected boolean shouldGenerateOnWorldSurface() {
				return false;
			}

			@Override
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.Mutable chunkCenter, Biome biome, BlockPosConfig config) {
				chunkCenter.set(config.pos);

				return config.isInChunk(new ChunkPos(getChunkX(), getChunkZ()));
			}
		};
	}
}
