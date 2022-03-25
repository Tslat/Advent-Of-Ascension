package net.tslat.aoa3.content.world.gen.feature.placement.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class OffsetPlacementConfig implements IPlacementConfig {
	public static final Codec<OffsetPlacementConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.STRING.fieldOf("direction").xmap(Direction::byName, Direction::getName).forGetter(config -> config.direction),
			Codec.INT.fieldOf("amount").forGetter(config -> config.amount))
			.apply(builder, OffsetPlacementConfig::new));

	public final Direction direction;
	public final int amount;

	public OffsetPlacementConfig(Direction direction, int amount) {
		this.direction = direction;
		this.amount = amount;
	}
}
