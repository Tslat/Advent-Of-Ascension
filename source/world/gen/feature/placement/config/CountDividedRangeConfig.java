package net.tslat.aoa3.world.gen.feature.placement.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class CountDividedRangeConfig implements IPlacementConfig {
	public static final Codec<CountDividedRangeConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.fieldOf("divide_point").forGetter(config -> config.dividePoint),
			Codec.INT.fieldOf("below_divide").forGetter(config -> config.belowDivide),
			Codec.INT.fieldOf("above_divide").forGetter(config -> config.aboveDivide)
	).apply(builder, CountDividedRangeConfig::new));

	public final int dividePoint;
	public final int belowDivide;
	public final int aboveDivide;

	public CountDividedRangeConfig(int dividePoint, int belowDivide, int aboveDivide) {
		this.dividePoint = dividePoint;
		this.belowDivide = belowDivide;
		this.aboveDivide = aboveDivide;
	}

	public int getValue(int worldHeight) {
		return worldHeight < dividePoint ? belowDivide : aboveDivide;
	}
}
