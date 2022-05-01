package net.tslat.aoa3.common.registration;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.content.loottable.modifier.*;

import java.util.function.Supplier;

public final class AoALootModifiers {
	public static void init() {}

	public static final RegistryObject<GlobalLootModifierSerializer<RollExtraTablesLootModifier>> ROLL_EXTRA_TABLES = registerSerializer("roll_extra_tables", RollExtraTablesLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<AddItemsLootModifier>> ADD_ITEMS = registerSerializer("add_items", AddItemsLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<LootModifyingItemLootModifier>> LOOT_MODIFYING_ITEM = registerSerializer("loot_modifying_items", LootModifyingItemLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<PlayerEventListenerLootModifier>> PLAYER_EVENT_LISTENER = registerSerializer("player_event_listener", PlayerEventListenerLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<HavenLootModifier>> HAVEN = registerSerializer("haven", HavenLootModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<FertilisedFarmlandLootModifier>> FERTILISED_FARMLAND = registerSerializer("fertilised_farmland", FertilisedFarmlandLootModifier.Serializer::new);

	private static <T extends LootModifier> RegistryObject<GlobalLootModifierSerializer<T>> registerSerializer(String id, Supplier<GlobalLootModifierSerializer<T>> serializer) {
		return AoARegistries.LOOT_MODIFIERS.register(id, serializer);
	}
}
