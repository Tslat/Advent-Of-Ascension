package net.tslat.aoa3.content.world.genold.feature.placement.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class BlockStatePlacementConfig implements FeatureConfiguration {
	public static final Codec<BlockStatePlacementConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			BlockState.CODEC.fieldOf("block").forGetter(config -> config.block))
			.apply(builder, BlockStatePlacementConfig::new));

	public final BlockState block;

	public BlockStatePlacementConfig(BlockState block) {
		this.block = block;
	}
}
