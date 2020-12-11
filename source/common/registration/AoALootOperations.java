package net.tslat.aoa3.common.registration;

import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.tslat.aoa3.library.loot.conditions.HoldingItem;
import net.tslat.aoa3.library.loot.conditions.PlayerHasLevel;
import net.tslat.aoa3.library.loot.conditions.PlayerHasResource;
import net.tslat.aoa3.library.loot.conditions.PlayerHasTribute;
import net.tslat.aoa3.library.loot.functions.EnchantSpecific;
import net.tslat.aoa3.library.loot.functions.GrantSkillXp;
import net.tslat.aoa3.library.loot.functions.RandomSelectionFromTag;

public final class AoALootOperations {
	public static void registerAll() {
		registerLootConditions();
		registerLootFunctions();
	}

	private static void registerLootConditions() {
		LootConditionManager.registerCondition(new HoldingItem.Serializer());
		LootConditionManager.registerCondition(new PlayerHasLevel.Serializer());
		LootConditionManager.registerCondition(new PlayerHasResource.Serializer());
		LootConditionManager.registerCondition(new PlayerHasTribute.Serializer());
	}

	private static void registerLootFunctions() {
		LootFunctionManager.registerFunction(new EnchantSpecific.Serializer());
		LootFunctionManager.registerFunction(new RandomSelectionFromTag.Serializer());
		LootFunctionManager.registerFunction(new GrantSkillXp.Serializer());
	}
}
