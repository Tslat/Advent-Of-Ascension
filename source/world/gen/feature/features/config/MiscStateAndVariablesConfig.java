package net.tslat.aoa3.world.gen.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.tslat.aoa3.util.misc.IntValueProvider;

public class MiscStateAndVariablesConfig implements IFeatureConfig {
	public static final Codec<MiscStateAndVariablesConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			IntValueProvider.CODEC.optionalFieldOf("count", new IntValueProvider(1)).forGetter(config -> config.count),
			IntValueProvider.CODEC.optionalFieldOf("x", new IntValueProvider(0)).forGetter(config -> config.x),
			IntValueProvider.CODEC.optionalFieldOf("y", new IntValueProvider(0)).forGetter(config -> config.y),
			IntValueProvider.CODEC.optionalFieldOf("z", new IntValueProvider(0)).forGetter(config -> config.z),
			Codec.BOOL.optionalFieldOf("inverted", false).forGetter(config -> config.inverted),
			BlockStateProvider.CODEC.optionalFieldOf("states", new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState())).forGetter(config -> config.stateProvider))
			.apply(builder, MiscStateAndVariablesConfig::new));
	public final IntValueProvider count;
	public final IntValueProvider x;
	public final IntValueProvider y;
	public final IntValueProvider z;
	public final boolean inverted;
	public final BlockStateProvider stateProvider;

	public MiscStateAndVariablesConfig(IntValueProvider count, IntValueProvider x, IntValueProvider y, IntValueProvider z, boolean inverted, BlockStateProvider stateProvider) {
		this.count = count;
		this.x = x;
		this.y = y;
		this.z = z;
		this.inverted = inverted;
		this.stateProvider = stateProvider;
	}

	public static class Builder {
		private IntValueProvider count = new IntValueProvider(1);
		private IntValueProvider x = new IntValueProvider(0);
		private IntValueProvider y = new IntValueProvider(0);
		private IntValueProvider z = new IntValueProvider(0);
		private boolean inverted = false;
		private BlockStateProvider stateProvider = new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState());

		public Builder amount(int amount) {
			return amount(amount, amount);
		}

		public Builder amount(int min, int max) {
			this.count = new IntValueProvider(min, max);

			return this;
		}

		public Builder x(int amount) {
			return x(amount, amount);
		}

		public Builder x(int min, int max) {
			this.x = new IntValueProvider(min, max);

			return this;
		}

		public Builder y(int amount) {
			return y(amount, amount);
		}

		public Builder y(int min, int max) {
			this.y = new IntValueProvider(min, max);

			return this;
		}

		public Builder z(int amount) {
			return z(amount, amount);
		}

		public Builder z(int min, int max) {
			this.z = new IntValueProvider(min, max);

			return this;
		}

		public Builder generateDownwards() {
			this.inverted = true;

			return this;
		}

		public Builder singleBlock(BlockState state) {
			this.stateProvider = new SimpleBlockStateProvider(state);

			return this;
		}

		public Builder withBlock(BlockState state, int weight) {
			if (!(this.stateProvider instanceof WeightedBlockStateProvider))
				this.stateProvider = new WeightedBlockStateProvider();

			((WeightedBlockStateProvider)this.stateProvider).add(state, weight);

			return this;
		}

		public MiscStateAndVariablesConfig build() {
			return new MiscStateAndVariablesConfig(count, x, y, z, inverted, stateProvider);
		}
	}
}
