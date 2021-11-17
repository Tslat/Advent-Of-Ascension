package net.tslat.aoa3.world.gen.structure.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.world.gen.feature.features.config.IntRangeConfig;

import java.util.Arrays;
import java.util.List;

public class YPosStructure extends AoAStructureBase<IntRangeConfig> {
	public YPosStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
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

				return config.ignoreObstructions || blockReader.getBlockState(chunkCenter).getMaterial().isReplaceable();
			}
		};
	}
}
