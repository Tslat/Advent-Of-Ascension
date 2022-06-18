package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.structure.AoAStructure;

public final class AoAStructureTypes {
	public static final RegistryObject<StructureType<AoAStructure>> AOA_DEFAULT = register("aoa_default_structure", AoAStructure.DEFAULT_CODEC);

	public static void init() {}

	private static <T extends Structure> RegistryObject<StructureType<T>> register(String id, Codec<T> codec) {
		return AoARegistries.STRUCTURE_TYPES.register(id, () -> () -> codec);
	}
}
