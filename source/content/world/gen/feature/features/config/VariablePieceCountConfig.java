package net.tslat.aoa3.content.world.gen.feature.features.config;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.List;

public class VariablePieceCountConfig implements IFeatureConfig {
	public static final Codec<VariablePieceCountConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.pair(
					Codec.INT.fieldOf("max_y").codec(),
					Codec.INT.fieldOf("max_pieces").codec()
			).listOf().fieldOf("tiers").forGetter(config -> config.tiers))
			.apply(builder, VariablePieceCountConfig::new));
	public final List<Pair<Integer, Integer>> tiers;

	public VariablePieceCountConfig(List<Pair<Integer, Integer>> tiers) {
		this.tiers = tiers;
	}

	public int getPiecesForStartPosition(int yPos, int defaultPieceCount) {
		for (Pair<Integer, Integer> layer : tiers) {
			if (yPos <= layer.getFirst())
				return layer.getSecond();
		}

		return defaultPieceCount;
	}
}
