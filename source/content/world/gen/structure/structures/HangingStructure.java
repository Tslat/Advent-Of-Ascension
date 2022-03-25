package net.tslat.aoa3.content.world.gen.structure.structures;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.tslat.aoa3.content.world.gen.feature.features.config.IntRangeConfig;

import java.util.Random;

public class HangingStructure extends AoAStructureBase<IntRangeConfig> {
	public HangingStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(IntRangeConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<IntRangeConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<IntRangeConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected boolean shouldGenerateOnWorldSurface() {
				return false;
			}

			@Override
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.Mutable chunkCenter, Biome biome, IntRangeConfig config) {
				IBlockReader blockReader = chunkGenerator.getBaseColumn(chunkCenter.getX(), chunkCenter.getZ());

				chunkCenter.setY(config.getValue(random));

				if (blockReader.getBlockState(chunkCenter).getMaterial().isReplaceable()) {
					while (chunkCenter.getY() < blockReader.getMaxBuildHeight() && blockReader.getBlockState(chunkCenter.move(Direction.UP)).getMaterial().isReplaceable()) {
						;
					}

					chunkCenter.move(Direction.DOWN);

					return chunkCenter.getY() < blockReader.getMaxBuildHeight();
				}

				return false;
			}

			@Override
			protected void doPostPlacementOperations(int maxDepth, ChunkGenerator chunkGenerator, BlockPos originPos, Random rand) {
				int topY = 0;

				for (StructurePiece piece : pieces) {
					if (piece.getBoundingBox().y1 > topY)
						topY = piece.getBoundingBox().y1;
				}

				int offset = originPos.getY() - topY;

				for (StructurePiece piece : pieces) {
					piece.move(0, offset, 0);
				}

				calculateBoundingBox();
			}
		};
	}
}
