package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.biome.modifier.AddMobSpawnBiomeModifier;
import net.tslat.aoa3.content.world.gen.biome.modifier.NewFeatureBiomeModifier;

public class AoABiomeModifiers {
	public static void init() {}

	public static final RegistryObject<Codec<NewFeatureBiomeModifier>> NEW_FEATURE = register("new_feature", NewFeatureBiomeModifier.CODEC);
	public static final RegistryObject<Codec<AddMobSpawnBiomeModifier>> NEW_MOB_SPAWN = register("new_mob_spawn", AddMobSpawnBiomeModifier.CODEC);

	private static <T extends BiomeModifier> RegistryObject<Codec<T>> register(String id, Codec<T> codec) {
		return AoARegistries.BIOME_MODIFIERS.register(id, () -> codec);
	}
}
