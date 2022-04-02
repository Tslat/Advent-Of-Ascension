package net.tslat.aoa3.content.world.gen.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class BlockPosConfig implements FeatureConfiguration {
	public static final Codec<BlockPosConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			BlockPos.CODEC.fieldOf("position").forGetter(config -> config.pos))
			.apply(builder, BlockPosConfig::new));
	public final BlockPos pos;

	public BlockPosConfig(BlockPos pos) {
		this.pos = pos;
	}

	public boolean isInChunk(ChunkPos chunkPos) {
		return pos.getX() >= chunkPos.getMinBlockX() && pos.getX() <= chunkPos.getMaxBlockX() &&
				pos.getZ() >= chunkPos.getMinBlockZ() && pos.getZ() <= chunkPos.getMaxBlockZ();
	}

	public BlockPos getChunkRelativePos() {
		return new BlockPos(pos.getX() & 15, pos.getY(), pos.getZ() & 15);
	}
}
