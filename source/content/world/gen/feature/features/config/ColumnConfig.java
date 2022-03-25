package net.tslat.aoa3.content.world.gen.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Random;

public class ColumnConfig implements IFeatureConfig {
	public static final Codec<ColumnConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockStateProvider.CODEC.fieldOf("block").forGetter(config -> config.block),
			Codec.INT.fieldOf("min_size").forGetter(config -> config.minSize),
			Codec.INT.fieldOf("max_size").forGetter(config -> config.maxSize),
			Codec.BOOL.optionalFieldOf("uniform_height_distribution", true).forGetter(config -> config.uniformHeightDistribution)
	).apply(instance, ColumnConfig::new));

	public final BlockStateProvider block;
	public final int minSize;
	public final int maxSize;
	public final boolean uniformHeightDistribution;

	public ColumnConfig(BlockStateProvider block, int minSize, int maxSize, boolean uniformHeightDistribution) {
		this.block = block;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.uniformHeightDistribution = uniformHeightDistribution;
	}

	public int getHeight(Random rand) {
		if (minSize == maxSize)
			return maxSize;

		return rand.nextInt(1 + maxSize - minSize) + minSize;
	}
}
