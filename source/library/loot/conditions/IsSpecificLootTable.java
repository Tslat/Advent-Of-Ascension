package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoALootOperations;

public class IsSpecificLootTable implements ILootCondition {
	private final ResourceLocation[] tableIds;

	public IsSpecificLootTable(ResourceLocation... tableIds) {
		this.tableIds = tableIds;
	}

	@Override
	public LootConditionType getType() {
		return AoALootOperations.LootConditions.IS_SPECIFIC_TABLE;
	}

	@Override
	public boolean test(LootContext lootContext) {
		for (ResourceLocation id : tableIds) {
			if (lootContext.getQueriedLootTableId().equals(id))
				return true;
		}

		return false;
	}

	public static class Serializer implements ILootSerializer<IsSpecificLootTable> {
		@Override
		public void serialize(JsonObject json, IsSpecificLootTable instance, JsonSerializationContext jsonSerializationContext) {
			JsonArray ids = new JsonArray();

			for (ResourceLocation id : instance.tableIds) {
				ids.add(id.toString());
			}

			json.add("table_ids", ids);
		}

		@Override
		public IsSpecificLootTable deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			JsonArray ids = json.get("table_ids").getAsJsonArray();
			ResourceLocation[] tableIds = new ResourceLocation[ids.size()];

			for (int i = 0; i < ids.size(); i++) {
				tableIds[i] = new ResourceLocation(ids.get(i).getAsString());
			}

			return new IsSpecificLootTable(tableIds);
		}
	}
}
