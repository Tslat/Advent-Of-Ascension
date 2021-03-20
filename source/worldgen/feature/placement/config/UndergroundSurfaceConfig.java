package net.tslat.aoa3.worldgen.feature.placement.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.placement.IPlacementConfig;

import java.util.Random;

public class UndergroundSurfaceConfig implements IPlacementConfig {
	public static final Codec<UndergroundSurfaceConfig> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
			Codec.INT.optionalFieldOf("bottom_offset", 0).forGetter(config -> config.bottomOffset),
			Codec.INT.optionalFieldOf("top_offset", 0).forGetter(config -> config.topOffset),
			Codec.INT.optionalFieldOf("maximum", 0).forGetter(config -> config.maximum),
			Codec.BOOL.optionalFieldOf("on_surface", false).forGetter(config -> config.onSurface),
			Codec.BOOL.optionalFieldOf("on_ceiling", false).forGetter(config -> config.onCeiling))
			.apply(builder, UndergroundSurfaceConfig::new));
	public final int bottomOffset;
	public final int topOffset;
	public final int maximum;
	public final boolean onSurface;
	public final boolean onCeiling;

	public UndergroundSurfaceConfig(int bottomOffset, int topOffset, int maximum, boolean onSurface, boolean onCeiling) {
		this.bottomOffset = bottomOffset;
		this.topOffset = topOffset;
		this.maximum = maximum;
		this.onSurface = onSurface;
		this.onCeiling = onCeiling;
	}

	public int getRandomValue(Random rand) {
		return bottomOffset + rand.nextInt(Math.max(1, maximum - bottomOffset - topOffset));
	}
}
