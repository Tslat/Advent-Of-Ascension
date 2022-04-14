package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.world.gen.placementmodifier.PercentChance;

public final class AoAPlacementModifiers {
	public static void doVanillaRegistryRegistrations() {}

	public static final PlacementModifierType<PercentChance> PERCENT_CHANCE = register("percent_chance", PercentChance.CODEC);

	private static <T extends PlacementModifier> PlacementModifierType<T> register(String id, Codec<T> codec) {
		return Registry.register(Registry.PLACEMENT_MODIFIERS, AdventOfAscension.id(id), () -> codec);
	}
}
