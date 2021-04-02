package net.tslat.aoa3.worldgen.structure.structures;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class FixedRotationStructure extends AoAStructureBase<NoFeatureConfig> {
	public FixedRotationStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(NoFeatureConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<NoFeatureConfig> getStructureStart() {
		return Start::new;
	}

	public static class Start extends AoAStructureStart<NoFeatureConfig> {
		private final SharedSeedRandom riggedRandom;

		public Start(AoAStructureBase<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
			super(structure, chunkX, chunkZ, boundingBox, references, seed);

			this.riggedRandom = new SharedSeedRandom() {
				 boolean riggedResult = false;
				@Override
				public int nextInt(int bound) {
					if (riggedResult)
						return super.nextInt(bound);

					riggedResult = true;

					return 0;
				}
			};
			this.riggedRandom.setLargeFeatureSeed(seed, chunkX, chunkZ);
		}

		@Override
		public void generatePieces(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
			final BlockPos chunkCenter = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);

			JigsawManager.addPieces(
					dynamicRegistry,
					new VillageConfig(() -> dynamicRegistry.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(getFeature().getTemplatePoolPath()), 10),
					AbstractVillagePiece::new,
					chunkGenerator,
					templateManager,
					chunkCenter,
					pieces,
					riggedRandom,
					false,
					true);

			calculateBoundingBox();
		}
	}
}
