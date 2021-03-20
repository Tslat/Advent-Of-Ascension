package net.tslat.aoa3.library.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Random;

public class IntValueProvider {
	public static final Codec<IntValueProvider> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("min", 0).forGetter(config -> config.min),
			Codec.INT.optionalFieldOf("max", 0).forGetter(config -> config.max))
			.apply(builder, IntValueProvider::new));
	public final int min;
	public final int max;

	public IntValueProvider(int value) {
		this.min = value;
		this.max = value;
	}

	public IntValueProvider(int min, int max) {
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
	}

	public int getValue(Random rand) {
		if (min == max)
			return max;

		return rand.nextInt(1 + max - min) + min;
	}
}
