package net.tslat.aoa3.common.registration.loot;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.loottable.modifier.*;

public final class AoALootModifiers {
	public static void init() {}

	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<RollExtraTablesLootModifier>> ROLL_EXTRA_TABLES = registerSerializer("roll_extra_tables", RollExtraTablesLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<AddItemsLootModifier>> ADD_ITEMS = registerSerializer("add_items", AddItemsLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ReplaceItemsLootModifier>> REPLACE_ITEMS = registerSerializer("replace_items", ReplaceItemsLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<LootModifyingItemLootModifier>> LOOT_MODIFYING_ITEM = registerSerializer("loot_modifying_items", LootModifyingItemLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<PlayerEventListenerLootModifier>> PLAYER_EVENT_LISTENER = registerSerializer("player_event_listener", PlayerEventListenerLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<HavenLootModifier>> HAVEN = registerSerializer("haven", HavenLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<FertilisedFarmlandLootModifier>> FERTILISED_FARMLAND = registerSerializer("fertilised_farmland", FertilisedFarmlandLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<RollEntityWorldTableLootModifier>> ROLL_ENTITY_WORLD_TABLE = registerSerializer("roll_entity_world_table", RollEntityWorldTableLootModifier.CODEC);
	public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ExplosiveIdolBarteringModifier>> EXPLOSIVE_IDOL_BARTERING = registerSerializer("explosive_idol_bartering", ExplosiveIdolBarteringModifier.CODEC);

	private static <T extends LootModifier> DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<T>> registerSerializer(String id, Codec<T> codec) {
		return AoARegistries.LOOT_MODIFIERS.register(id, () -> codec);
	}
}
