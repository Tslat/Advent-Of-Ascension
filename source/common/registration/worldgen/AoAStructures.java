package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;

import java.util.function.Supplier;

public final class AoAStructures {
	//public static final RegistryObject<YPosStructure> RUINED_TELEPORTER_FRAME = register("ruined_teleporter_frame", YPosStructure::new);

	public static void init() {}

	private static <C extends FeatureConfiguration, F extends Structure> RegistryObject<F> register(String id, Supplier<F> structure) {
		return AoARegistries.STRUCTURES.register(id, structure);
	}
}
