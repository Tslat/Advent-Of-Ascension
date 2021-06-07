package net.tslat.aoa3.world.gen.structure.structures;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.tslat.aoa3.world.gen.feature.features.config.IntRangeConfig;

public class CrysteviaStructure extends AoAStructureBase<IntRangeConfig> {
	public CrysteviaStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(IntRangeConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<IntRangeConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<IntRangeConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.Mutable chunkCenter, Biome biome, IntRangeConfig config) {
				IBlockReader blockReader = chunkGenerator.getBaseColumn(chunkCenter.getX(), chunkCenter.getZ());

				chunkCenter.setY(config.getValue(random));

				if (blockReader.getBlockState(chunkCenter).getMaterial().isReplaceable()) {
					while (chunkCenter.getY() > 0 && blockReader.getBlockState(chunkCenter.move(Direction.DOWN)).getMaterial().isReplaceable()) {
						;
					}

					return chunkCenter.getY() > 0;
				}

				return false;
			}

			@Override
			protected boolean shouldGenerateOnWorldSurface() {
				return false;
			}
		};
	}
}
