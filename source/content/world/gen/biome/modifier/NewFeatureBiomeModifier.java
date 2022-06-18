package net.tslat.aoa3.content.world.gen.biome.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.tslat.aoa3.content.world.gen.BiomeMatcher;

public record NewFeatureBiomeModifier(BiomeMatcher biomeMatcher, GenerationStep.Decoration step, HolderSet<PlacedFeature> features) implements BiomeModifier {
	public static final Codec<NewFeatureBiomeModifier> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			BiomeMatcher.CODEC.fieldOf("biomes").forGetter(NewFeatureBiomeModifier::biomeMatcher),
			GenerationStep.Decoration.CODEC.optionalFieldOf("gen_step", GenerationStep.Decoration.SURFACE_STRUCTURES).forGetter(NewFeatureBiomeModifier::step),
			PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(NewFeatureBiomeModifier::features)
	).apply(codec, NewFeatureBiomeModifier::new));

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase != Phase.ADD)
			return;

		if (biomeMatcher.test(biome))
			features.forEach(feature -> builder.getGenerationSettings().addFeature(step, feature));
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return CODEC;
	}
}
