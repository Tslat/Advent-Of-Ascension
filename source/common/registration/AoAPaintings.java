package net.tslat.aoa3.common.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoAPaintings {
	public static void init() {}

	public static final ResourceKey<PaintingVariant> CAE = register("cae");

	private static ResourceKey<PaintingVariant> register(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, AdventOfAscension.id(name));
	}
}
