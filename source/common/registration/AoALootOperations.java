package net.tslat.aoa3.common.registration;

import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.loottable.condition.BlockHasTag;
import net.tslat.aoa3.content.loottable.condition.HoldingItem;
import net.tslat.aoa3.content.loottable.condition.PlayerHasLevel;
import net.tslat.aoa3.content.loottable.condition.PlayerHasResource;
import net.tslat.aoa3.content.loottable.function.EnchantSpecific;
import net.tslat.aoa3.content.loottable.function.GrantSkillXp;

public final class AoALootOperations {
	public static final class LootFunctions {
		public static void init() {}

		public static final LootFunctionType ENCHANT_SPECIFIC = register("enchant_specific", new EnchantSpecific.Serializer());
		public static final LootFunctionType GRANT_SKILL_XP = register("grant_skill_xp", new GrantSkillXp.Serializer());

		private static LootFunctionType register(String id, LootFunction.Serializer<? extends ILootFunction> serializer) {
			return Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(AdventOfAscension.MOD_ID, id), new LootFunctionType(serializer));
		}
	}

	public static final class LootConditions {
		public static void init() {}

		public static final LootConditionType HOLDING_ITEM = register("holding_item", new HoldingItem.Serializer());
		public static final LootConditionType PLAYER_HAS_LEVEL = register("player_has_level", new PlayerHasLevel.Serializer());
		public static final LootConditionType PLAYER_HAS_RESOURCE = register("player_has_resource", new PlayerHasResource.Serializer());
		public static final LootConditionType HAS_BLOCK_TAG = register("has_block_tag", new BlockHasTag.Serializer());

		private static LootConditionType register(String id, ILootSerializer<? extends ILootCondition> serializer) {
			return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(AdventOfAscension.MOD_ID, id), new LootConditionType(serializer));
		}
	}

	public static void init() {
		LootFunctions.init();
		LootConditions.init();
	}
}
