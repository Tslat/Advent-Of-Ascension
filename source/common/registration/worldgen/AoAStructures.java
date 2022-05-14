package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.structure.YPosStructure;

import java.util.function.Supplier;

public final class AoAStructures {
	public static final RegistryObject<YPosStructure> RUINED_TELEPORTER_FRAME = register("ruined_teleporter_frame", YPosStructure::new);

	public static void init() {}

	private static <C extends FeatureConfiguration, F extends StructureFeature<C>> RegistryObject<F> register(String id, Supplier<F> structure) {
		return AoARegistries.STRUCTURES.register(id, structure);
	}
}
