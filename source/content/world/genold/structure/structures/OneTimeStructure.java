/*
package net.tslat.aoa3.content.world.gen.structure.structures;

import net.minecraft.core.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.tslat.aoa3.content.world.gen.feature.features.config.BlockPosConfig;

public class OneTimeStructure extends AoAStructureBase<BlockPosConfig> {
	public OneTimeStructure(GenerationStep.Decoration decorationStage, String templatePoolPath) {
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
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.MutableBlockPos chunkCenter, Biome biome, BlockPosConfig config) {
				chunkCenter.set(config.pos);

				return config.isInChunk(new ChunkPos(getChunkX(), getChunkZ()));
			}
		};
	}
}
*/
