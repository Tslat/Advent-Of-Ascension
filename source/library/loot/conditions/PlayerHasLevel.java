package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Random;

public class PlayerHasLevel implements LootCondition {
	private final Enums.Skills skill;
	private final int level;

	public PlayerHasLevel(Enums.Skills skill, int level) {
		this.skill = skill;
		this.level = level;
	}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		return context.getKillerPlayer() != null && PlayerUtil.getAdventPlayer((EntityPlayer)context.getKillerPlayer()).stats().getLevel(skill) >= level;
	}

	public static class Serializer extends LootCondition.Serializer<PlayerHasLevel> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "player_has_level"), PlayerHasLevel.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHasLevel value, JsonSerializationContext context) {
			json.addProperty("skill", value.skill.toString().toLowerCase());
			json.addProperty("level", value.level);
		}

		@Override
		public PlayerHasLevel deserialize(JsonObject json, JsonDeserializationContext context) {
			return new PlayerHasLevel(Enums.Skills.valueOf(JsonUtils.getString(json, "skill").toUpperCase()), JsonUtils.getInt(json, "level"));
		}
	}
}
