package net.tslat.aoa3.common.registration.loot;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.loottable.condition.*;

public final class AoALootConditions {
	public static void init() {}

	public static final DeferredHolder<LootItemConditionType, LootItemConditionType> HOLDING_ITEM = register("holding_item", WearingOrHoldingItem.CODEC);
	public static final DeferredHolder<LootItemConditionType, LootItemConditionType> PLAYER_HAS_LEVEL = register("player_has_level", PlayerHasLevel.CODEC);
	public static final DeferredHolder<LootItemConditionType, LootItemConditionType> PLAYER_HAS_RESOURCE = register("player_has_resource", PlayerHasResource.CODEC);
	public static final DeferredHolder<LootItemConditionType, LootItemConditionType> HAS_BLOCK_TAG = register("has_block_tag", BlockHasTag.CODEC);
	public static final DeferredHolder<LootItemConditionType, LootItemConditionType> IS_HOSTILE_ENTITY = register("is_hostile_entity", IsHostileEntity.CODEC);
	public static final DeferredHolder<LootItemConditionType, LootItemConditionType> IS_BABY = register("is_baby", IsBaby.CODEC);

	private static DeferredHolder<LootItemConditionType, LootItemConditionType> register(String id, Codec<? extends LootItemCondition> codec) {
		return AoARegistries.LOOT_CONDITIONS.register(id, () -> new LootItemConditionType(codec));
	}
}