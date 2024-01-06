package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.structure.processor.TerrainAdaptionProcessor;

public final class AoAStructureProcessors {
	public static final RegistryObject<StructureProcessorType<TerrainAdaptionProcessor>> TERRAIN_ADAPTION = register("terrain_adaption", TerrainAdaptionProcessor.CODEC);

	public static void init() {}

	private static <T extends StructureProcessor> RegistryObject<StructureProcessorType<T>> register(String id, Codec<T> codec) {
		return AoARegistries.STRUCTURE_PROCESSORS.register(id, () -> () -> codec);
	}
}
