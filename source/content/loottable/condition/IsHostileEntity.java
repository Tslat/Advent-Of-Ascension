package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.AoALootOperations;

public class IsHostileEntity implements LootItemCondition {
	@Override
	public boolean test(LootContext lootContext) {
		return lootContext.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Enemy;
	}

	@Override
	public LootItemConditionType getType() {
		return AoALootOperations.LootConditions.IS_HOSTILE_ENTITY.get();
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<IsHostileEntity> {
		@Override
		public void serialize(JsonObject json, IsHostileEntity instance, JsonSerializationContext jsonSerializationContext) {}

		@Override
		public IsHostileEntity deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new IsHostileEntity();
		}
	}
}
