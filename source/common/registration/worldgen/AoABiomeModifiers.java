package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.biome.modifier.AddMobSpawnBiomeModifier;
import net.tslat.aoa3.content.world.gen.biome.modifier.NewFeatureBiomeModifier;

public class AoABiomeModifiers {
	public static void init() {}

	public static final DeferredHolder<Codec<? extends BiomeModifier>, Codec<NewFeatureBiomeModifier>> NEW_FEATURE = register("new_feature", NewFeatureBiomeModifier.CODEC);
	public static final DeferredHolder<Codec<? extends BiomeModifier>, Codec<AddMobSpawnBiomeModifier>> NEW_MOB_SPAWN = register("new_mob_spawn", AddMobSpawnBiomeModifier.CODEC);

	private static <T extends BiomeModifier> DeferredHolder<Codec<? extends BiomeModifier>, Codec<T>> register(String id, Codec<T> codec) {
		return AoARegistries.BIOME_MODIFIERS.register(id, () -> codec);
	}
}
