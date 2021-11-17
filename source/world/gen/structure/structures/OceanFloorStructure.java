package net.tslat.aoa3.world.gen.structure.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class OceanFloorStructure extends AoAStructureBase<NoFeatureConfig> {
	public OceanFloorStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(NoFeatureConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<NoFeatureConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<NoFeatureConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected boolean shouldGenerateOnWorldSurface() {
				return false;
			}

			@Override
			public void generatePieces(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
				int x = (chunkX << 4) + 7;
				int z = (chunkZ << 4) + 7;

				final BlockPos.Mutable chunkCenter = new BlockPos.Mutable(x, chunkGenerator.getFirstFreeHeight(x, z, Heightmap.Type.OCEAN_FLOOR_WG) + 1, z);

				if (checkAndAdjustGeneration(chunkGenerator, chunkCenter, biome, config))
					generateStructurePieces(dynamicRegistry, getStructurePieceDepth(), chunkGenerator, templateManager, chunkCenter, getRandom(), false, shouldGenerateOnWorldSurface(), config);
			}
		};
	}
}
