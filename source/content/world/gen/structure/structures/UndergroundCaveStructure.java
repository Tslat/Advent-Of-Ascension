package net.tslat.aoa3.content.world.gen.structure.structures;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.tslat.aoa3.content.world.gen.feature.features.config.IntRangeConfig;

import java.util.Random;

public class UndergroundCaveStructure extends AoAStructureBase<IntRangeConfig> {
	public UndergroundCaveStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
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
			protected int getStructurePieceDepth() {
				return 15;
			}

			@Override
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.Mutable chunkCenter, Biome biome, IntRangeConfig config) {
				chunkCenter.setY(config.getValue(random));

				return true;
			}

			@Override
			protected void doPostPlacementOperations(int maxDepth, ChunkGenerator chunkGenerator, BlockPos originPos, Random rand) {
				IBlockReader blockReader = chunkGenerator.getBaseColumn(originPos.getX(), originPos.getZ());
				MutableBoundingBox bounds = getBoundingBox();
				BlockPos.Mutable testPos = new BlockPos.Mutable().set(originPos.getX() - ((bounds.x1 - bounds.x0) / 2), bounds.y0, originPos.getZ() - ((bounds.z1 - bounds.z0) / 2));
				int height = bounds.y1 - bounds.y0;

				if (blockReader.getBlockState(testPos).getMaterial().isReplaceable()) {
					while (blockReader.getBlockState(testPos.move(Direction.DOWN)).getMaterial().isReplaceable() && testPos.getY() > 0) {
						;
					}

					if (testPos.getY() <= 2)
						return;
				}
				else if (blockReader.getBlockState(testPos.above(height)).getMaterial().isReplaceable()) {
					while (blockReader.getBlockState(testPos.move(Direction.UP)).getMaterial().isReplaceable() && testPos.getY() + height < blockReader.getMaxBuildHeight()) {
						;
					}

					if (testPos.getY() + height >= blockReader.getMaxBuildHeight() - 2)
						return;
				}

				int offset = testPos.getY() - originPos.getY();

				for (StructurePiece piece : getPieces()) {
					piece.move(0, offset, 0);
				}

				calculateBoundingBox();
			}
		};
	}
}
