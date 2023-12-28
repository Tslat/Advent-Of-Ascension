package net.tslat.aoa3.content.world.gen.feature.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class FluidChuteFeature extends Feature<FluidChuteFeature.Configuration> {
	public FluidChuteFeature(Codec<Configuration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<Configuration> context) {
		final BlockPos startPos = context.origin();
		final Configuration config = context.config();
		final RandomSource rand = context.random();
		final int depth = config.maxDepth().sample(rand);
		final int fluidRadius = config.fluidDiameter().sample(rand) / 2;
		final int chuteRadius = config.chuteDiameter().sample(rand) / 2;
		final BlockPos.MutableBlockPos pos = startPos.mutable();
		final WorldGenLevel level = context.level();
		final int fluidHypot = fluidRadius * fluidRadius;
		final int chuteHypot = chuteRadius * chuteRadius;
		final int chuteDepth = Math.min(depth, pos.getY() - level.getMinBuildHeight());

		for (int i = 0; i < chuteDepth; i++) {
			for (int x = -fluidRadius; x <= fluidRadius; x++) {
				for (int z = -fluidRadius; z <= fluidRadius; z++) {
					final int radius = x * x + z * z;

					if (radius < fluidHypot + rand.nextGaussian() * 4 + 1) {
						final BlockState state = level.getBlockState(pos.setWithOffset(startPos, x, -i, z));

						if (state.isAir() || (radius < chuteHypot + rand.nextGaussian() * 4 + 1 && state.getDestroySpeed(level, pos) >= 0)) {
							final BlockState aboveState = level.getBlockState(pos.above());

							if (aboveState.isAir()) {
								while (pos.getY() - 1 > level.getMinBuildHeight() && level.getBlockState(pos.below()).isAir()) {
									pos.move(0, -1, 0);
								}
							}

							final BlockState fluid = config.fluid().getState(rand, pos);

							level.setBlock(pos, fluid, Block.UPDATE_CLIENTS);

							if (aboveState.isAir()) {
								level.scheduleTick(pos.immutable(), fluid.getBlock(), 0);
								markAboveForPostProcessing(level, pos.immutable());
							}
						}
					}
				}
			}
		}

		return true;
	}

	public record Configuration(BlockStateProvider fluid, IntProvider fluidDiameter, IntProvider chuteDiameter, IntProvider maxDepth) implements FeatureConfiguration {
		public static final Codec<Configuration> CODEC = RecordCodecBuilder.create(instance ->
				instance.group(BlockStateProvider.CODEC.fieldOf("fluid").forGetter(FluidChuteFeature.Configuration::fluid),
								IntProvider.CODEC.fieldOf("fluid_diameter").forGetter(FluidChuteFeature.Configuration::fluidDiameter),
								IntProvider.CODEC.fieldOf("chute_diameter").forGetter(FluidChuteFeature.Configuration::chuteDiameter),
								IntProvider.CODEC.fieldOf("max_depth").forGetter(FluidChuteFeature.Configuration::maxDepth))
						.apply(instance, FluidChuteFeature.Configuration::new));
	}
}
