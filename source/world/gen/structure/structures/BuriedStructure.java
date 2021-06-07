package net.tslat.aoa3.world.gen.structure.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.world.gen.feature.features.config.IntRangeConfig;

public class BuriedStructure extends AoAStructureBase<IntRangeConfig> {
	public BuriedStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
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
				chunkCenter.setY(chunkGenerator.getFirstFreeHeight(chunkCenter.getX(), chunkCenter.getZ(), Heightmap.Type.WORLD_SURFACE_WG) - config.getValue(random));

				return true;
			}
		};
	}
}
