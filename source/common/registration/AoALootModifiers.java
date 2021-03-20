package net.tslat.aoa3.common.registration;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.loot.modifiers.*;

import java.util.function.Supplier;

public class AoALootModifiers {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<GlobalLootModifierSerializer<RollExtraTablesLootModifier>> ROLL_EXTRA_TABLES = registerSerializer("roll_extra_tables", RollExtraTablesLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<InjectOrReplaceLootModifier>> INJECT_OR_REPLACE = registerSerializer("inject_or_replace", InjectOrReplaceLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<LootModifyingItemLootModifier>> LOOT_MODIFYING_ITEM = registerSerializer("loot_modifying_items", LootModifyingItemLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<ForagingLootModifier>> FORAGING = registerSerializer("foraging", ForagingLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<LoggingLootModifier>> LOGGING = registerSerializer("logging", LoggingLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<HavenLootModifier>> HAVEN = registerSerializer("haven", HavenLootModifier.Serializer::new);

	private static <T extends LootModifier> RegistryObject<GlobalLootModifierSerializer<T>> registerSerializer(String id, Supplier<GlobalLootModifierSerializer<T>> serializer) {
		return LOOT_MODIFIERS.register(id, serializer);
	}
}
