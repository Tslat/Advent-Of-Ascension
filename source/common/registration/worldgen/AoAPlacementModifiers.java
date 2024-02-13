package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.placementmodifier.ChunkHighPoint;
import net.tslat.aoa3.content.world.gen.placementmodifier.PercentChance;

public final class AoAPlacementModifiers {
	public static void init() {}

	public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<PercentChance>> PERCENT_CHANCE = register("percent_chance", PercentChance.CODEC);
	public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<ChunkHighPoint>> CHUNK_HIGH_POINT = register("chunk_high_point", ChunkHighPoint.CODEC);

	private static <T extends PlacementModifier> DeferredHolder<PlacementModifierType<?>, PlacementModifierType<T>> register(String id, Codec<T> codec) {
		return AoARegistries.PLACEMENT_MODIFIERS.register(id, () -> () -> codec);
	}
}
