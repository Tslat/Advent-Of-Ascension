package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.structure.placement.SingleStructurePlacement;

public final class AoAStructurePlacements {
	public static void init() {}

	public static final RegistryObject<StructurePlacementType<SingleStructurePlacement>> SINGLE_STRUCTURE = register("single_structure", SingleStructurePlacement.CODEC);

	private static <S extends StructurePlacement> RegistryObject<StructurePlacementType<S>> register(String id, Codec<S> placementType) {
		return AoARegistries.STRUCTURE_PLACEMENTS.register(id, () -> () -> placementType);
	}
}
