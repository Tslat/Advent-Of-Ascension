package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

import java.util.Random;

public class MatchEntityId implements LootCondition {
	private final boolean isInverted;
	private final String entityId;
	private final LootContext.EntityTarget target;

	public MatchEntityId(boolean isInverted, String entityId, LootContext.EntityTarget targetType) {
		this.isInverted = isInverted;
		this.entityId = entityId;
		this.target = targetType;
	}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		Entity entity = context.getEntity(target);
		ResourceLocation id;

		if (entity == null || (id = EntityList.getKey(entity)) == null)
			return entityId.equalsIgnoreCase("null");

		return id.toString().equals(entityId);
	}

	public static class Serializer extends LootCondition.Serializer<MatchEntityId> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "match_entity_id"), MatchEntityId.class);
		}

		@Override
		public void serialize(JsonObject json, MatchEntityId value, JsonSerializationContext context) {
			if (value.isInverted)
				json.addProperty("inverted", true);

			json.addProperty("id", value.entityId);
			json.add("entity", context.serialize(value.target));
		}

		@Override
		public MatchEntityId deserialize(JsonObject json, JsonDeserializationContext context) {
			boolean inverted = JsonUtils.getBoolean(json, "inverted", false);
			String entityId = JsonUtils.getString(json, "id", "null");

			return new MatchEntityId(inverted, entityId, JsonUtils.deserializeClass(json, "entity", context, LootContext.EntityTarget.class));
		}
	}
}
