package net.tslat.aoa3.content.world.gen.biome.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.tslat.aoa3.content.world.gen.BiomeMatcher;

import java.util.List;

public record AddMobSpawnBiomeModifier(BiomeMatcher biomeMatcher, List<MobSpawnSettings.SpawnerData> spawn) implements BiomeModifier {
	public static final Codec<AddMobSpawnBiomeModifier> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			BiomeMatcher.CODEC.fieldOf("biomes").forGetter(AddMobSpawnBiomeModifier::biomeMatcher),
			MobSpawnSettings.SpawnerData.CODEC.listOf().fieldOf("spawn").forGetter(AddMobSpawnBiomeModifier::spawn)
	).apply(codec, AddMobSpawnBiomeModifier::new));

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase != Phase.ADD)
			return;

		if (biomeMatcher.test(biome))
			spawn.forEach(data -> builder.getMobSpawnSettings().addSpawn(data.type.getCategory(), data));
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return CODEC;
	}
}
