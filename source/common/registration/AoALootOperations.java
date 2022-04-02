package net.tslat.aoa3.common.registration;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.loottable.condition.BlockHasTag;
import net.tslat.aoa3.content.loottable.condition.HoldingItem;
import net.tslat.aoa3.content.loottable.condition.PlayerHasLevel;
import net.tslat.aoa3.content.loottable.condition.PlayerHasResource;
import net.tslat.aoa3.content.loottable.function.EnchantSpecific;
import net.tslat.aoa3.content.loottable.function.GrantSkillXp;

public final class AoALootOperations {
	public static final class LootFunctions {
		private static void init() {}

		public static final LootItemFunctionType ENCHANT_SPECIFIC = register("enchant_specific", new EnchantSpecific.Serializer());
		public static final LootItemFunctionType GRANT_SKILL_XP = register("grant_skill_xp", new GrantSkillXp.Serializer());

		private static LootItemFunctionType register(String id, Serializer<? extends LootItemFunction> serializer) {
			return Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(AdventOfAscension.MOD_ID, id), new LootItemFunctionType(serializer));
		}
	}

	public static final class LootConditions {
		private static void init() {}

		public static final LootItemConditionType HOLDING_ITEM = register("holding_item", new HoldingItem.Serializer());
		public static final LootItemConditionType PLAYER_HAS_LEVEL = register("player_has_level", new PlayerHasLevel.Serializer());
		public static final LootItemConditionType PLAYER_HAS_RESOURCE = register("player_has_resource", new PlayerHasResource.Serializer());
		public static final LootItemConditionType HAS_BLOCK_TAG = register("has_block_tag", new BlockHasTag.Serializer());

		private static LootItemConditionType register(String id, Serializer<? extends LootItemCondition> serializer) {
			return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(AdventOfAscension.MOD_ID, id), new LootItemConditionType(serializer));
		}
	}

	public static void init() {
		LootFunctions.init();
		LootConditions.init();
	}
}
