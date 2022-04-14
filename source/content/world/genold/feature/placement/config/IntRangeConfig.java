/*
package net.tslat.aoa3.content.world.gen.feature.placement.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class IntRangeConfig implements IPlacementConfig {
	public static final Codec<IntRangeConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("min", 0).forGetter(config -> config.min),
			Codec.INT.optionalFieldOf("max", 0).forGetter(config -> config.max))
			.apply(builder, IntRangeConfig::new));
	public final int min;
	public final int max;

	public IntRangeConfig(int min, int max) {
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
	}

	public int getValue(Random rand) {
		if (min == max)
			return max;

		return rand.nextInt(1 + max - min) + min;
	}
}
*/
