package net.tslat.aoa3.worldgen.worlds.immortallis;

import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.immortallis.ImmortallisDungeon;

public class ImmortallisBiome extends AoABiome {
	public ImmortallisBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState()))
				.temperature(0)
				.downfall(0)
				.waterColor(NumberUtil.RGB(145, 96, 5))
				.waterFogColor(NumberUtil.RGB(145, 96, 5))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.VOID};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 255);
	}

	@Override
	public void decorate(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> chunkGenerator, IWorld world, long seed, SharedSeedRandom random, BlockPos pos) {
		super.decorate(stage, chunkGenerator, world, seed, random, pos);

		if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			ChunkPos chunkPos = new ChunkPos(pos);

			if (chunkPos.getXStart() >= -16 && chunkPos.getZStart() >= -48 && chunkPos.getXEnd() <= 256 && chunkPos.getZEnd() <= 48)
				new ImmortallisDungeon().setChunkPos(chunkPos).generate(world, world.getRandom(), new BlockPos(-15, 17, -40));
		}
	}
}
