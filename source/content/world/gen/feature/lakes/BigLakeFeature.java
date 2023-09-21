package net.tslat.aoa3.content.world.gen.feature.lakes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.tslat.aoa3.library.object.PositionTableMap;
import net.tslat.smartbrainlib.util.RandomUtil;

public class BigLakeFeature extends Feature<BigLakeFeature.Configuration> {
	private static final int AIR = 1;
	private static final int FLUID = 2;
	private static final int BARRIER = 3;

	public BigLakeFeature(Codec<Configuration> codec) {
		super(codec);
	}

	@Override
	public boolean place(final FeaturePlaceContext<BigLakeFeature.Configuration> context) {
		final WorldGenLevel level = context.level();
		final RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(context.random());
		final BigLakeFeature.Configuration config = context.config();
		final int depth = config.maxFluidDepth().sample(random);
		final BlockPos pos = context.origin().below(depth);

		if (pos.getY() <= level.getMinBuildHeight())
			return false;

		final int blobCount = config.sampleBlobCount().sample(random);
		final int width = config.maxWidth().sample(random);
		final int airGap = 1;

		final PositionTableMap lakePositions = PositionTableMap.of(width, depth + airGap);

		// Generate central sphere-blob for lake

		for(int i = 0; i < blobCount; ++i) {
			final float maxBlobWidthX = config.sampleBlobWidth().sample(random);
			final float maxBlobHeight = config.sampleBlobHeight().sample(random);
			final float maxBlobWidthZ = config.sampleBlobWidth().sample(random);
			final float maxRadiusX = maxBlobWidthX / 2f;
			final float maxRadiusY = maxBlobHeight / 2f;
			final float maxRadiusZ = maxBlobWidthZ / 2f;
			final double blobOffsetX = random.nextDouble() * (width - maxBlobWidthX - 2d) + 1d + maxRadiusX;
			final double blobOffsetY = random.nextDouble() * ((depth + 2) - maxBlobHeight - 2d) + 2d + maxRadiusY;
			final double blobOffsetZ = random.nextDouble() * (width - maxBlobWidthZ - 2d) + 1d + maxRadiusZ;

			for(int x = 1; x < width - 1; ++x) {
				for(int z = 1; z < width - 1; ++z) {
					for(int y = 1; y < airGap + depth; ++y) {
						final double xRadius = (x - blobOffsetX) / maxRadiusX;
						final double yRadius = (y - blobOffsetY) / maxRadiusY;
						final double zRadius = (z - blobOffsetZ) / maxRadiusZ;

						if (xRadius * xRadius + yRadius * yRadius + zRadius * zRadius < 1)
							lakePositions.set(x, y, z, y >= depth ? AIR : FLUID);
					}
				}
			}
		}

		final BlockState fluidState = config.fluid().getState(random, pos);

		// Cancel lake entirely if spheroid-edge block is liquid or isn't solid & isn't the config fluid

		for (Vec3i emptyPos : lakePositions.emptyPositions()) {
			if (!lakePositions.isAtEdgeOfRegion(emptyPos.getX(), emptyPos.getY(), emptyPos.getZ()) && lakePositions.isAdjacentFilled(emptyPos.getX(), emptyPos.getY(), emptyPos.getZ(), FLUID)) {
				final BlockState barrierPreState = level.getBlockState(pos.offset(emptyPos));

				if (emptyPos.getY() >= depth) {
					if (barrierPreState.liquid() && barrierPreState != fluidState)
						return false;
				}
				else if (!barrierPreState.isSolid() && barrierPreState != fluidState) {
					return false;
				}

				if (barrierPreState.isSolid() && !barrierPreState.is(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE))
					lakePositions.set(emptyPos, BARRIER);
			}
		}

		placeFluid(level, config, pos, depth, lakePositions);
		placeAir(level, config, pos, depth, lakePositions);
		placeBarrierBlocks(level, config, pos, depth, lakePositions);

		if (fluidState.getFluidState().is(FluidTags.WATER))
			freezeSurfaceWater(level, config, depth, pos, lakePositions);

		return true;
	}

	protected void placeBarrierBlocks(final WorldGenLevel level, final Configuration config, final BlockPos centerPos, int depth, final PositionTableMap lakePositions) {
		final BlockState barrierState = config.barrier().getState(level.getRandom(), centerPos);

		if (barrierState.isAir())
			return;

		for (Vec3i pos : lakePositions.positionsForValue(BARRIER)) {
			final BlockPos barrierPos = centerPos.offset(pos);

			if (pos.getY() < depth || level.getRandom().nextBoolean()) {
				level.setBlock(barrierPos, barrierState, Block.UPDATE_CLIENTS);
				markAboveForPostProcessing(level, barrierPos);
			}
		}
	}

	protected void placeAir(final WorldGenLevel level, final Configuration config, final BlockPos centerPos, int depth, final PositionTableMap lakePositions) {
		final BlockState air = Blocks.CAVE_AIR.defaultBlockState();

		for (Vec3i pos : lakePositions.positionsForValue(AIR)) {
			final BlockPos airPos = centerPos.offset(pos);

			if (canReplaceBlock(level.getBlockState(airPos))) {
				level.setBlock(airPos, air, Block.UPDATE_CLIENTS);
				level.scheduleTick(airPos, air.getBlock(), 0);
				markAboveForPostProcessing(level, airPos);
			}
		}
	}

	protected void placeFluid(final WorldGenLevel level, final Configuration config, final BlockPos centerPos, int depth, final PositionTableMap lakePositions) {
		final BlockState fluidState = config.fluid().getState(level.getRandom(), centerPos);

		for (Vec3i pos : lakePositions.positionsForValue(FLUID)) {
			final BlockPos fluidPos = centerPos.offset(pos);

			if (canReplaceBlock(level.getBlockState(fluidPos))) {
				level.setBlock(fluidPos, fluidState, Block.UPDATE_CLIENTS);

				if (pos.getY() >= depth)
					markAboveForPostProcessing(level, fluidPos);
			}
		}
	}

	protected void freezeSurfaceWater(final WorldGenLevel level, final Configuration config, int depth, final BlockPos centerPos, final PositionTableMap lakePositions) {
		final BlockState ice = Blocks.ICE.defaultBlockState();

		for (Vec3i pos : lakePositions.positionsForValue(FLUID)) {
			if (pos.getY() < depth)
				continue;

			final BlockPos icePos = centerPos.offset(pos);

			if (canReplaceBlock(level.getBlockState(icePos)) && level.getBiome(icePos).value().shouldFreeze(level, icePos, false))
				level.setBlock(icePos, ice, Block.UPDATE_CLIENTS);
		}
	}

	private boolean canReplaceBlock(BlockState state) {
		return !state.is(BlockTags.FEATURES_CANNOT_REPLACE);
	}

	public record Configuration(BlockStateProvider fluid, BlockStateProvider barrier, IntProvider maxWidth, IntProvider maxFluidDepth, IntProvider sampleBlobCount, FloatProvider sampleBlobWidth, FloatProvider sampleBlobHeight) implements FeatureConfiguration {
		public Configuration(final BlockStateProvider fluid, final BlockStateProvider barrier) {
			this(fluid, barrier, ConstantInt.of(16), ConstantInt.of(4), UniformInt.of(4, 8), UniformFloat.of(3, 9), UniformFloat.of(2, 6));
		}

		public static final Codec<BigLakeFeature.Configuration> CODEC = RecordCodecBuilder.create(instance ->
				instance.group(BlockStateProvider.CODEC.fieldOf("fluid").forGetter(BigLakeFeature.Configuration::fluid),
								BlockStateProvider.CODEC.fieldOf("barrier").forGetter(BigLakeFeature.Configuration::barrier),
								IntProvider.CODEC.optionalFieldOf("max_width", ConstantInt.of(16)).forGetter(BigLakeFeature.Configuration::maxWidth),
								IntProvider.CODEC.optionalFieldOf("max_fluid_depth", ConstantInt.of(4)).forGetter(BigLakeFeature.Configuration::maxFluidDepth),
								IntProvider.CODEC.optionalFieldOf("sample_blob_count", UniformInt.of(4, 8)).forGetter(BigLakeFeature.Configuration::sampleBlobCount),
								FloatProvider.CODEC.optionalFieldOf("sample_blob_width", UniformFloat.of(3, 9)).forGetter(BigLakeFeature.Configuration::sampleBlobWidth),
								FloatProvider.CODEC.optionalFieldOf("sample_blob_height", UniformFloat.of(2, 6)).forGetter(BigLakeFeature.Configuration::sampleBlobHeight))
						.apply(instance, BigLakeFeature.Configuration::new));
	}
}
