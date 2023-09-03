package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.AoALootOperations;

public class IsBaby implements LootItemCondition {
	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		return entity instanceof AgeableMob ageable && ageable.getAge() < 0;
	}

	public static LootItemCondition.Builder instance() {
		return IsBaby::new;
	}

	@Override
	public LootItemConditionType getType() {
		return AoALootOperations.LootConditions.IS_BABY.get();
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<IsBaby> {
		@Override
		public void serialize(JsonObject json, IsBaby instance, JsonSerializationContext jsonSerializationContext) {}

		@Override
		public IsBaby deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new IsBaby();
		}
	}
}
