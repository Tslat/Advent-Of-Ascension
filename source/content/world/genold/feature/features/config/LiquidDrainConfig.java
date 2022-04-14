package net.tslat.aoa3.content.world.genold.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiquidDrainConfig implements FeatureConfiguration {
	public static final Codec<LiquidDrainConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			FluidState.CODEC.optionalFieldOf("fluid", Fluids.WATER.getFlowing(8, true)).forGetter(config -> config.fluid),
			Codec.BOOL.optionalFieldOf("fill_with_liquid", false).forGetter(config -> config.fill),
			Codec.BOOL.optionalFieldOf("carves_upwards", false).forGetter(config -> config.inverseDirection),
			BlockState.CODEC.listOf().optionalFieldOf("stop_at_blocks", Collections.singletonList(Blocks.AIR.defaultBlockState())).forGetter(config -> config.stopBlocks)
	).apply(instance, LiquidDrainConfig::new));

	public final FluidState fluid;
	public final boolean fill;
	public final boolean inverseDirection;
	public final List<BlockState> stopBlocks;

	public LiquidDrainConfig(FluidState fluid, boolean fill, boolean inverseDirection, List<BlockState> stopBlocks) {
		this.fluid = fluid;
		this.fill = fill;
		this.inverseDirection = inverseDirection;
		this.stopBlocks = stopBlocks;
	}

	public static class Builder {
		private FluidState fluid = Fluids.WATER.defaultFluidState();
		private boolean fill = false;
		private boolean inverseDirection = false;
		private List<BlockState> stopBlocks = Collections.singletonList(Blocks.AIR.defaultBlockState());

		public Builder fluid(FluidState fluid) {
			this.fluid = fluid;

			return this;
		}

		public Builder fillWithFluid() {
			this.fill = true;

			return this;
		}

		public Builder carveUpwards() {
			this.inverseDirection = true;

			return this;
		}

		public Builder stopAt(BlockState... states) {
			this.stopBlocks = Arrays.asList(states);

			return this;
		}

		public LiquidDrainConfig build() {
			return new LiquidDrainConfig(fluid, fill, inverseDirection, stopBlocks);
		}
	}
}
