package net.tslat.aoa3.content.world.gen.feature.placement.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class BlockStatePlacementConfig implements IPlacementConfig {
	public static final Codec<BlockStatePlacementConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			BlockState.CODEC.fieldOf("block").forGetter(config -> config.block))
			.apply(builder, BlockStatePlacementConfig::new));

	public final BlockState block;

	public BlockStatePlacementConfig(BlockState block) {
		this.block = block;
	}
}
