package net.tslat.aoa3.content.world.gen.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.tslat.aoa3.content.world.gen.structure.config.VariableHeightStructureConfig;

public class YPosStructure extends GenericAoAStructure<VariableHeightStructureConfig> {
	public YPosStructure() {
		super(VariableHeightStructureConfig.CODEC, YPosStructure.createPieceGenerator());
	}

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}

	public static PieceGeneratorSupplier<VariableHeightStructureConfig> createPieceGenerator() {
		return new PieceGen<>() {
			@Override
			protected boolean genOnWorldSurface(Context<VariableHeightStructureConfig> genContext) {
				return false;
			}

			@Override
			protected boolean checkAndAdjustGeneration(Context<VariableHeightStructureConfig> genContext, WorldgenRandom rand, BlockPos.MutableBlockPos currentPos) {
				VariableHeightStructureConfig config = genContext.config();
				NoiseColumn noiseColumn = genContext.chunkGenerator().getBaseColumn(currentPos.getX(), currentPos.getZ(), genContext.heightAccessor());

				currentPos.setY(config.getHeight().sample(rand, new WorldGenerationContext(genContext.chunkGenerator(), genContext.heightAccessor())));

				if (config.dropToNearestFloor()) {
					int minHeight = genContext.heightAccessor().getMinBuildHeight();
					int y = currentPos.getY();

					while (noiseColumn.getBlock(y).isAir() && y-- >= minHeight) {}

					if (y <= minHeight)
						return false;

					currentPos.setY(y);
				}

				if (!genContext.validBiome().test(genContext.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(currentPos.getX()), QuartPos.fromBlock(currentPos.getY()), QuartPos.fromBlock(currentPos.getZ()))))
					return false;

				return config.ignoreObstructions() || noiseColumn.getBlock(currentPos.getY()).getMaterial().isReplaceable();
			}
		};
	}
}
