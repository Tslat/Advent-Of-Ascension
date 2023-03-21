package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.content.loottable.modifier.*;

public final class AoALootModifiers {
	public static void init() {}

	public static final RegistryObject<Codec<RollExtraTablesLootModifier>> ROLL_EXTRA_TABLES = registerSerializer("roll_extra_tables", RollExtraTablesLootModifier.CODEC);
	public static final RegistryObject<Codec<AddItemsLootModifier>> ADD_ITEMS = registerSerializer("add_items", AddItemsLootModifier.CODEC);
	public static final RegistryObject<Codec<ReplaceItemsLootModifier>> REPLACE_ITEMS = registerSerializer("replace_items", ReplaceItemsLootModifier.CODEC);
	public static final RegistryObject<Codec<LootModifyingItemLootModifier>> LOOT_MODIFYING_ITEM = registerSerializer("loot_modifying_items", LootModifyingItemLootModifier.CODEC);
	public static final RegistryObject<Codec<PlayerEventListenerLootModifier>> PLAYER_EVENT_LISTENER = registerSerializer("player_event_listener", PlayerEventListenerLootModifier.CODEC);
	public static final RegistryObject<Codec<HavenLootModifier>> HAVEN = registerSerializer("haven", HavenLootModifier.CODEC);
	public static final RegistryObject<Codec<FertilisedFarmlandLootModifier>> FERTILISED_FARMLAND = registerSerializer("fertilised_farmland", FertilisedFarmlandLootModifier.CODEC);
	public static final RegistryObject<Codec<RollEntityWorldTableLootModifier>> ROLL_ENTITY_WORLD_TABLE = registerSerializer("roll_entity_world_table", RollEntityWorldTableLootModifier.CODEC);

	private static <T extends LootModifier> RegistryObject<Codec<T>> registerSerializer(String id, Codec<T> serializer) {
		return AoARegistries.LOOT_MODIFIERS.register(id, () -> serializer);
	}
}
