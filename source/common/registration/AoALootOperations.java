package net.tslat.aoa3.common.registration;

import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.content.loottable.condition.*;
import net.tslat.aoa3.content.loottable.entrytype.CollectionLootEntry;
import net.tslat.aoa3.content.loottable.function.EnchantSpecific;
import net.tslat.aoa3.content.loottable.function.GrantSkillXp;

import java.util.function.Supplier;

public final class AoALootOperations {
	public static final class LootFunctions {
		public static void init() {}

		public static final RegistryObject<LootItemFunctionType> ENCHANT_SPECIFIC = register("enchant_specific", EnchantSpecific.Serializer::new);
		public static final RegistryObject<LootItemFunctionType> GRANT_SKILL_XP = register("grant_skill_xp", GrantSkillXp.Serializer::new);

		private static RegistryObject<LootItemFunctionType> register(String id, Supplier<Serializer<? extends LootItemFunction>> serializer) {
			return AoARegistries.LOOT_FUNCTIONS.register(id, () -> new LootItemFunctionType(serializer.get()));
		}
	}

	public static final class LootConditions {
		public static void init() {}

		public static final RegistryObject<LootItemConditionType> HOLDING_ITEM = register("holding_item", HoldingItem.Serializer::new);
		public static final RegistryObject<LootItemConditionType> PLAYER_HAS_LEVEL = register("player_has_level", PlayerHasLevel.Serializer::new);
		public static final RegistryObject<LootItemConditionType> PLAYER_HAS_RESOURCE = register("player_has_resource", PlayerHasResource.Serializer::new);
		public static final RegistryObject<LootItemConditionType> HAS_BLOCK_TAG = register("has_block_tag", BlockHasTag.Serializer::new);
		public static final RegistryObject<LootItemConditionType> IS_HOSTILE_ENTITY = register("is_hostile_entity", IsHostileEntity.Serializer::new);

		private static RegistryObject<LootItemConditionType> register(String id, Supplier<Serializer<? extends LootItemCondition>> serializer) {
			return AoARegistries.LOOT_CONDITIONS.register(id, () -> new LootItemConditionType(serializer.get()));
		}
	}

	public static final class LootEntryTypes {
		public static void init() {}

		public static final RegistryObject<LootPoolEntryType> COLLECTION = register("collection", CollectionLootEntry.Serializer::new);

		private static RegistryObject<LootPoolEntryType> register(String id, Supplier<Serializer<? extends LootPoolEntryContainer>> serializer) {
			return AoARegistries.LOOT_ENTRY_TYPES.register(id, () -> new LootPoolEntryType(serializer.get()));
		}
	}
}
