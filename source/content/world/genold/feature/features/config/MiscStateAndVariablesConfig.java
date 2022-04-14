package net.tslat.aoa3.content.world.genold.feature.features.config;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.tslat.aoa3.library.object.IntValueProvider;

import java.util.ArrayList;

public class MiscStateAndVariablesConfig implements FeatureConfiguration {
	public static final Codec<MiscStateAndVariablesConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			IntValueProvider.CODEC.optionalFieldOf("count", new IntValueProvider(1)).forGetter(config -> config.count),
			IntValueProvider.CODEC.optionalFieldOf("x", new IntValueProvider(0)).forGetter(config -> config.x),
			IntValueProvider.CODEC.optionalFieldOf("y", new IntValueProvider(0)).forGetter(config -> config.y),
			IntValueProvider.CODEC.optionalFieldOf("z", new IntValueProvider(0)).forGetter(config -> config.z),
			Codec.BOOL.optionalFieldOf("inverted", false).forGetter(config -> config.inverted),
			BlockStateProvider.CODEC.optionalFieldOf("states", new SimpleStateProvider(Blocks.AIR.defaultBlockState())).forGetter(config -> config.stateProvider))
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
		private final ArrayList<Pair<BlockState, Integer>> blocks = new ArrayList<>();

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

		public Builder withBlock(BlockState state) {
			return withBlock(state, 1);
		}

		public Builder withBlock(BlockState state, int weight) {
			this.blocks.add(Pair.of(state, weight));

			return this;
		}

		public MiscStateAndVariablesConfig build() {
			BlockStateProvider stateProvider;

			if (this.blocks.isEmpty())
				throw new IllegalArgumentException("No blocks provider for misc state and variables config!");

			if (this.blocks.size() == 1) {
				stateProvider = new SimpleStateProvider(this.blocks.get(0).getFirst());
			}
			else {
				SimpleWeightedRandomList.Builder<BlockState> blockListBuilder = new SimpleWeightedRandomList.Builder<>();

				for (Pair<BlockState, Integer> pair : this.blocks) {
					blockListBuilder.add(pair.getFirst(), pair.getSecond());
				}

				stateProvider = new WeightedStateProvider(blockListBuilder);
			}

			return new MiscStateAndVariablesConfig(count, x, y, z, inverted, stateProvider);
		}
	}
}
