package net.tslat.aoa3.common.registration.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.template.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.structure.processors.CrysteviaPillarProcessor;

import java.util.Arrays;

public class AoAStructureProcessors {
	public static void postInit() {}

	public static final IStructureProcessorType<CrysteviaPillarProcessor> CRYSTEVIA_PILLAR = registerProcessor("crystevia_pillar", CrysteviaPillarProcessor.CODEC);

	public static final StructureProcessorList DEEP_STONE_DEEP_CASES = registerProcessorList("deep_stone_deep_cases",
			new RuleStructureProcessor(ImmutableList.of(
					new RuleEntry(
							new RandomBlockMatchRuleTest(AoABlocks.DENSE_STONE.get(), 0.008f),
							AlwaysTrueRuleTest.INSTANCE,
							AoABlocks.DEEP_CASE.get().defaultBlockState()))));
	public static final StructureProcessorList BLUE_CRYSTEVIA_PILLAR = registerProcessorList("blue_crystevia_pillar",
			new CrysteviaPillarProcessor(AoABlocks.BLUE_CRYSTAL_BLOCK.get().defaultBlockState()));
	public static final StructureProcessorList GREEN_CRYSTEVIA_PILLAR = registerProcessorList("green_crystevia_pillar",
			new CrysteviaPillarProcessor(AoABlocks.GREEN_CRYSTAL_BLOCK.get().defaultBlockState()));
	public static final StructureProcessorList PURPLE_CRYSTEVIA_PILLAR = registerProcessorList("purple_crystevia_pillar",
			new CrysteviaPillarProcessor(AoABlocks.PURPLE_CRYSTAL_BLOCK.get().defaultBlockState()));
	public static final StructureProcessorList RED_CRYSTEVIA_PILLAR = registerProcessorList("red_crystevia_pillar",
			new CrysteviaPillarProcessor(AoABlocks.RED_CRYSTAL_BLOCK.get().defaultBlockState()));
	public static final StructureProcessorList WHITE_CRYSTEVIA_PILLAR = registerProcessorList("white_crystevia_pillar",
			new CrysteviaPillarProcessor(AoABlocks.WHITE_CRYSTAL_BLOCK.get().defaultBlockState()));
	public static final StructureProcessorList YELLOW_CRYSTEVIA_PILLAR = registerProcessorList("yellow_crystevia_pillar",
			new CrysteviaPillarProcessor(AoABlocks.YELLOW_CRYSTAL_BLOCK.get().defaultBlockState()));

	private static StructureProcessorList registerProcessorList(String id, StructureProcessor... processors) {
		return WorldGenRegistries.register(WorldGenRegistries.PROCESSOR_LIST, new ResourceLocation(AdventOfAscension.MOD_ID, id), new StructureProcessorList(ImmutableList.copyOf(Arrays.asList(processors))));
	}

	private static <T extends StructureProcessor> IStructureProcessorType<T> registerProcessor(String id, Codec<T> processor) {
		return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(AdventOfAscension.MOD_ID, id), () -> processor);
	}
}
