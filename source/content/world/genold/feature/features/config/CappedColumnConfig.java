package net.tslat.aoa3.content.world.genold.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Random;

public class CappedColumnConfig implements FeatureConfiguration {
	public static final Codec<CappedColumnConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockStateProvider.CODEC.fieldOf("stem_block").forGetter(config -> config.stemBlock),
			BlockStateProvider.CODEC.fieldOf("cap_block").forGetter(config -> config.capBlock),
			Codec.INT.fieldOf("min_stem_size").forGetter(config -> config.minStemSize),
			Codec.INT.fieldOf("max_stem_size").forGetter(config -> config.maxStemSize),
			Codec.BOOL.optionalFieldOf("uniform_height_distribution", true).forGetter(config -> config.uniformHeightDistribution),
			Codec.BOOL.optionalFieldOf("upside_down", false).forGetter(config -> config.upsideDown)
	).apply(instance, CappedColumnConfig::new));

	public final BlockStateProvider stemBlock;
	public final BlockStateProvider capBlock;
	public final int minStemSize;
	public final int maxStemSize;
	public final boolean uniformHeightDistribution;
	public final boolean upsideDown;

	public CappedColumnConfig(BlockStateProvider stemBlock, BlockStateProvider capBlock, int minStemSize, int maxStemSize, boolean uniformHeightDistribution, boolean upsideDown) {
		this.stemBlock = stemBlock;
		this.capBlock = capBlock;
		this.minStemSize = minStemSize;
		this.maxStemSize = maxStemSize;
		this.uniformHeightDistribution = uniformHeightDistribution;
		this.upsideDown = upsideDown;
	}

	public int getStemHeight(Random rand) {
		if (minStemSize == maxStemSize)
			return maxStemSize;

		return rand.nextInt(1 + maxStemSize - minStemSize) + minStemSize;
	}

	public static class Builder {
		private final BlockStateProvider stemBlock;
		private final BlockStateProvider capBlock;
		private int minStemSize = 1;
		private int maxStemSize = 6;
		private boolean uniformHeightDistribution = true;
		private boolean upsideDown = false;

		public Builder(BlockStateProvider stemBlock, BlockStateProvider capBlock) {
			this.stemBlock = stemBlock;
			this.capBlock = capBlock;
		}

		public Builder minStemHeight(int minHeight) {
			this.minStemSize = minHeight;

			return this;
		}

		public Builder maxStemHeight(int maxHeight) {
			this.maxStemSize = maxHeight;

			return this;
		}

		public Builder weightTowardsSmallerColumns() {
			this.uniformHeightDistribution = false;

			return this;
		}

		public Builder buildsHangingDown() {
			this.upsideDown = true;

			return this;
		}

		public CappedColumnConfig build() {
			return new CappedColumnConfig(stemBlock, capBlock, minStemSize, maxStemSize, uniformHeightDistribution, upsideDown);
		}
	}
}
