/*
package net.tslat.aoa3.common.registration.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.world.gen.structure.processors.CrysteviaPillarProcessor;
import net.tslat.aoa3.content.world.gen.structure.processors.TrophyProcessor;

import java.util.Arrays;

public class AoAStructureProcessors {
	public static void postInit() {}

	public static final StructureProcessorType<CrysteviaPillarProcessor> CRYSTEVIA_PILLAR = registerProcessor("crystevia_pillar", CrysteviaPillarProcessor.CODEC);
	public static final StructureProcessorType<TrophyProcessor> TROPHY_PROCESSOR = registerProcessor("trophy_processor", TrophyProcessor.CODEC);

	private static StructureProcessorList registerProcessorList(String id, StructureProcessor... processors) {
		return WorldGenRegistries.register(WorldGenRegistries.PROCESSOR_LIST, new ResourceLocation(AdventOfAscension.MOD_ID, id), new StructureProcessorList(ImmutableList.copyOf(Arrays.asList(processors))));
	}

	private static <T extends StructureProcessor> IStructureProcessorType<T> registerProcessor(String id, Codec<T> processor) {
		return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(AdventOfAscension.MOD_ID, id), () -> processor);
	}
}
*/
