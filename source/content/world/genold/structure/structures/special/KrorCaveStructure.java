/*
package net.tslat.aoa3.content.world.gen.structure.structures.special;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.tslat.aoa3.content.world.gen.feature.features.config.IntRangeConfig;
import net.tslat.aoa3.content.world.gen.structure.structures.AoAStructureBase;
import net.tslat.aoa3.content.world.gen.structure.structures.AoAStructureStart;

public class KrorCaveStructure extends AoAStructureBase<IntRangeConfig> {
	public KrorCaveStructure(GenerationStep.Decoration decorationStage, String templatePoolPath) {
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
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.MutableBlockPos chunkCenter, Biome biome, IntRangeConfig config) {
				BlockGetter blockReader = chunkGenerator.getBaseColumn(chunkCenter.getX(), chunkCenter.getZ());

				chunkCenter.setY(config.getValue(random));

				if (blockReader.getBlockState(chunkCenter).getMaterial().isReplaceable()) {
					while (chunkCenter.getY() > 15 && blockReader.getBlockState(chunkCenter.move(Direction.DOWN)).getMaterial().isReplaceable()) {
						;
					}

					chunkCenter.move(Direction.DOWN, 13);

					return chunkCenter.getY() > 0;
				}

				return false;
			}
		};
	}
}
*/
