package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.structure.AoAStructure;
import net.tslat.aoa3.content.world.gen.structure.SinglePlacementStructure;
import net.tslat.aoa3.content.world.gen.structure.TopAlignedStructure;
import net.tslat.aoa3.content.world.gen.structure.special.NethengeicPit;

public final class AoAStructureTypes {
	public static final DeferredHolder<StructureType<?>, StructureType<AoAStructure>> AOA_DEFAULT = register("aoa_default_structure", AoAStructure.DEFAULT_CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<SinglePlacementStructure>> SINGLE_PLACEMENT = register("single_placement", SinglePlacementStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<TopAlignedStructure>> TOP_ALIGNED = register("top_aligned", TopAlignedStructure.CODEC);

	public static final DeferredHolder<StructureType<?>, StructureType<NethengeicPit>> NETHENGEIC_PIT = register("nethengeic_pit", NethengeicPit.CODEC);

	public static void init() {}

	private static <T extends Structure> DeferredHolder<StructureType<?>, StructureType<T>> register(String id, Codec<T> codec) {
		return AoARegistries.STRUCTURE_TYPES.register(id, () -> () -> codec);
	}
}
