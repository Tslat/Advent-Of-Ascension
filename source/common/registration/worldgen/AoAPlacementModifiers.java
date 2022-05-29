package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.placementmodifier.PercentChance;

public final class AoAPlacementModifiers {
	public static void init() {}

	public static final RegistryObject<PlacementModifierType<PercentChance>> PERCENT_CHANCE = register("percent_chance", PercentChance.CODEC);

	private static <T extends PlacementModifier> RegistryObject<PlacementModifierType<T>> register(String id, Codec<T> codec) {
		return AoARegistries.PLACEMENT_MODIFIERS.register(id, () -> () -> codec);

		//return Registry.register(Registry.PLACEMENT_MODIFIERS, AdventOfAscension.id(id), () -> codec);
	}
}
