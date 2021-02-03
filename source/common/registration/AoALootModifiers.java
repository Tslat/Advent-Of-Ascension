package net.tslat.aoa3.common.registration;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.loot.modifiers.RollExtraTablesLootModifier;
import net.tslat.aoa3.library.loot.modifiers.HarvestDropsLootModifier;

import java.util.function.Supplier;

public class AoALootModifiers {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<GlobalLootModifierSerializer<HarvestDropsLootModifier>> HARVEST_DROPS_EVENT = registerSerializer("harvest_drops_event", HarvestDropsLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<RollExtraTablesLootModifier>> ROLL_EXTRA_TABLES = registerSerializer("roll_extra_tables", RollExtraTablesLootModifier.Serializer::new);

	private static <T extends LootModifier> RegistryObject<GlobalLootModifierSerializer<T>> registerSerializer(String id, Supplier<GlobalLootModifierSerializer<T>> serializer) {
		return LOOT_MODIFIERS.register(id, serializer);
	}
}
