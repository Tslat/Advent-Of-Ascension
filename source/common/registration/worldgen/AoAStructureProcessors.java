package net.tslat.aoa3.common.registration.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.worldgen.structure.processors.CrysteviaPillarProcessor;
import net.tslat.aoa3.worldgen.structure.processors.TrophyProcessor;

import java.util.Arrays;

public class AoAStructureProcessors {
	public static void postInit() {}

	public static final IStructureProcessorType<CrysteviaPillarProcessor> CRYSTEVIA_PILLAR = registerProcessor("crystevia_pillar", CrysteviaPillarProcessor.CODEC);
	public static final IStructureProcessorType<TrophyProcessor> TROPHY_PROCESSOR = registerProcessor("trophy_processor", TrophyProcessor.CODEC);

	private static StructureProcessorList registerProcessorList(String id, StructureProcessor... processors) {
		return WorldGenRegistries.register(WorldGenRegistries.PROCESSOR_LIST, new ResourceLocation(AdventOfAscension.MOD_ID, id), new StructureProcessorList(ImmutableList.copyOf(Arrays.asList(processors))));
	}

	private static <T extends StructureProcessor> IStructureProcessorType<T> registerProcessor(String id, Codec<T> processor) {
		return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(AdventOfAscension.MOD_ID, id), () -> processor);
	}
}
