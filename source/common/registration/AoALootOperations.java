package net.tslat.aoa3.common.registration;

import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.content.loottable.condition.*;
import net.tslat.aoa3.content.loottable.function.EnchantSpecific;
import net.tslat.aoa3.content.loottable.function.GrantSkillXp;

public final class AoALootOperations {
	public static final class LootFunctions {
		public static void init() {}

		public static final RegistryObject<LootItemFunctionType> ENCHANT_SPECIFIC = register("enchant_specific", new EnchantSpecific.Serializer());
		public static final RegistryObject<LootItemFunctionType> GRANT_SKILL_XP = register("grant_skill_xp", new GrantSkillXp.Serializer());

		private static RegistryObject<LootItemFunctionType> register(String id, Serializer<? extends LootItemFunction> serializer) {
			return AoARegistries.LOOT_FUNCTIONS.register(id, () -> new LootItemFunctionType(serializer));
		}
	}

	public static final class LootConditions {
		public static void init() {}

		public static final RegistryObject<LootItemConditionType> HOLDING_ITEM = register("holding_item", new HoldingItem.Serializer());
		public static final RegistryObject<LootItemConditionType> PLAYER_HAS_LEVEL = register("player_has_level", new PlayerHasLevel.Serializer());
		public static final RegistryObject<LootItemConditionType> PLAYER_HAS_RESOURCE = register("player_has_resource", new PlayerHasResource.Serializer());
		public static final RegistryObject<LootItemConditionType> HAS_BLOCK_TAG = register("has_block_tag", new BlockHasTag.Serializer());
		public static final RegistryObject<LootItemConditionType> IS_HOSTILE_ENTITY = register("is_hostile_entity", new IsHostileEntity.Serializer());

		private static RegistryObject<LootItemConditionType> register(String id, Serializer<? extends LootItemCondition> serializer) {
			return AoARegistries.LOOT_CONDITIONS.register(id, () -> new LootItemConditionType(serializer));
		}
	}
}
