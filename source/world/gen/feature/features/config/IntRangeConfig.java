package net.tslat.aoa3.world.gen.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Random;

public class IntRangeConfig implements IFeatureConfig {
	public static final Codec<IntRangeConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("min", 0).forGetter(config -> config.min),
			Codec.INT.optionalFieldOf("max", 0).forGetter(config -> config.max),
			Codec.BOOL.optionalFieldOf("ignore_obstructions", false).forGetter(config -> config.ignoreObstructions))
			.apply(builder, IntRangeConfig::new));
	public final int min;
	public final int max;
	public final boolean ignoreObstructions;

	public IntRangeConfig(int value) {
		this(value, value);
	}

	public IntRangeConfig(int min, int max) {
		this(min, max, false);
	}

	public IntRangeConfig(int min, int max, boolean ignoreObstructions) {
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
		this.ignoreObstructions = ignoreObstructions;
	}

	public int getValue(Random rand) {
		if (min == max)
			return max;

		return rand.nextInt(1 + max - min) + min;
	}
}
