package net.tslat.aoa3.worldgen.structure.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.tslat.aoa3.worldgen.feature.features.config.IntRangeConfig;

import net.tslat.aoa3.worldgen.structure.structures.AoAStructureBase.AoAStartFactory;

public class YPosStructure extends AoAStructureBase<IntRangeConfig> {
	public YPosStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(IntRangeConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<IntRangeConfig> getStructureStart() {
		return Start::new;
	}

	public static class Start extends AoAStructureStart<IntRangeConfig> {
		public Start(AoAStructureBase<IntRangeConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
			super(structure, chunkX, chunkZ, boundingBox, references, seed);
		}

		@Override
		public void generatePieces(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, IntRangeConfig config) {
			final BlockPos chunkCenter = new BlockPos((chunkX << 4) + 7, config.getValue(random), (chunkZ << 4) + 7);
			IBlockReader blockReader = chunkGenerator.getBaseColumn(chunkCenter.getX(), chunkCenter.getZ());

			if (config.ignoreObstructions || blockReader.getBlockState(chunkCenter).getMaterial().isReplaceable()) {
				JigsawManager.addPieces(
						dynamicRegistry,
						new VillageConfig(() -> dynamicRegistry.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(getFeature().getTemplatePoolPath()), 10),
						AbstractVillagePiece::new,
						chunkGenerator,
						templateManager,
						chunkCenter,
						pieces,
						random,
						false,
						false);

				calculateBoundingBox();
			}
		}
	}
}
