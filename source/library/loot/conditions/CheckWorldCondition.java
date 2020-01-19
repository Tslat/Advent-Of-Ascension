package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

import java.util.Random;

public class CheckWorldCondition implements LootCondition {
	private final boolean isInverted;
	private final String worldName;

	public CheckWorldCondition(boolean isInverted, String worldName) {
		this.isInverted = isInverted;
		this.worldName = worldName;
	}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		if (context.getLootedEntity() != null) {
			return context.getLootedEntity().world.provider.getDimensionType().getName().equals(worldName) == isInverted;
		}
		else if (context.getKillerPlayer() != null) {
			return context.getKillerPlayer().world.provider.getDimensionType().getName().equals(worldName) == isInverted;
		}
		else if (context.getKiller() != null) {
			return context.getKiller().world.provider.getDimensionType().getName().equals(worldName) == isInverted;
		}

		return false;
	}

	public static class Serializer extends LootCondition.Serializer<CheckWorldCondition> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "world_condition"), CheckWorldCondition.class);
		}

		@Override
		public void serialize(JsonObject json, CheckWorldCondition value, JsonSerializationContext context) {
			if (!value.isInverted)
				json.addProperty("is_in_world", false);

			json.addProperty("world_name", value.worldName);
		}

		@Override
		public CheckWorldCondition deserialize(JsonObject json, JsonDeserializationContext context) {
			return new CheckWorldCondition(JsonUtils.getBoolean(json, "is_in_world", true), JsonUtils.getString(json, "world_name"));
		}
	}
}
