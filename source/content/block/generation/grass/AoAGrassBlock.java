package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class AoAGrassBlock extends GrassBlock {
	protected final Supplier<? extends Block> soilBlock;
	protected final ResourceKey<PlacedFeature> bonemealFeature;

	public AoAGrassBlock(Properties properties, Supplier<? extends Block> soilBlock) {
		this(properties, soilBlock, null);
	}

	public AoAGrassBlock(Properties properties, Supplier<? extends Block> soilBlock, @Nullable ResourceKey<PlacedFeature> bonemealFeature) {
		super(properties);

		this.soilBlock = soilBlock;
		this.bonemealFeature = bonemealFeature;
	}

	public Block getSoilBlock() {
		return this.soilBlock.get();
	}

	@Nullable
	protected ResourceKey<PlacedFeature> getBonemealGrowthFeature(LevelReader level, BlockState state, BlockPos pos) {
		return this.bonemealFeature;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean clientSide) {
		return getBonemealGrowthFeature(level, state, pos) != null && super.isValidBonemealTarget(level, pos, state, clientSide);
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return getBonemealGrowthFeature(level, state, pos) != null;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (checkRevertToSoil(level, state, pos, random))
			return;

		checkMakeSnowy(level, state, pos, random);
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		BlockPos abovePos = pos.above();
		ResourceKey<PlacedFeature> bonemealFeatureKey = getBonemealGrowthFeature(level, state, pos);

		if (bonemealFeatureKey == null)
			return;

		Optional<Holder.Reference<PlacedFeature>> boneMealFeature = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(bonemealFeatureKey);

		placementLoop:
		for(int i = 0; i < 128; ++i) {
			BlockPos adjacentPos = abovePos;

			for(int j = 0; j < i / 16; ++j) {
				adjacentPos = adjacentPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);

				if (!level.getBlockState(adjacentPos.below()).is(this) || level.getBlockState(adjacentPos).isCollisionShapeFullBlock(level, adjacentPos)) {
					continue placementLoop;
				}
			}

			BlockState adjacentState = level.getBlockState(adjacentPos);

			if (adjacentState.is(this) && random.nextInt(10) == 0)
				performBonemeal(level, random, adjacentPos, adjacentState);

			if (adjacentState.isAir()) {
				if (random.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> flowerFeatures = level.getBiome(adjacentPos).value().getGenerationSettings().getFlowerFeatures();

					if (!flowerFeatures.isEmpty())
						((RandomPatchConfiguration)flowerFeatures.get(0).config()).feature().value().place(level, level.getChunkSource().getGenerator(), random, adjacentPos);
				}
				else {
					if (boneMealFeature.isPresent())
						boneMealFeature.get().value().place(level, level.getChunkSource().getGenerator(), random, adjacentPos);
				}
			}
		}
	}

	protected boolean checkRevertToSoil(ServerLevel level, BlockState state, BlockPos pos, RandomSource random) {
		if (canBeGrass(level, state, pos))
			return false;

		if (level.isAreaLoaded(pos, 1))
			level.setBlockAndUpdate(pos, getSoilBlock().defaultBlockState());

		return true;
	}

	protected boolean checkMakeSnowy(ServerLevel level, BlockState state, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 3))
			return false;

		boolean madeSnowy = false;

		if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
			for(int i = 0; i < 4; ++i) {
				BlockPos nearbyPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

				if (level.getBlockState(nearbyPos).is(getSoilBlock()) && canSpread(level, defaultBlockState(), nearbyPos)) {
					level.setBlockAndUpdate(nearbyPos, defaultBlockState().setValue(SNOWY, Boolean.valueOf(level.getBlockState(nearbyPos.above()).is(Blocks.SNOW))));

					madeSnowy = true;
				}
			}
		}

		return madeSnowy;
	}

	public static boolean canSpread(LevelReader level, BlockState state, BlockPos pos) {
		return canBeGrass(level, state, pos) && level.getFluidState(pos.above()).isEmpty();
	}

	public static boolean canBeGrass(LevelReader level, BlockState state, BlockPos pos) {
		BlockPos abovePos = pos.above();
		BlockState aboveState = level.getBlockState(abovePos);

		if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1)
			return true;

		if (aboveState.getFluidState().getAmount() == 8)
			return false;

		return LightEngine.getLightBlockInto(level, state, pos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(level, abovePos)) < level.getMaxLightLevel();
	}
}
