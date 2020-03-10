package net.tslat.aoa3.library.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Random;

public class GrantXp extends LootFunction {
	private final Enums.Skills skill;
	private final float xp;

	public GrantXp(LootCondition[] conditionsIn, Enums.Skills skill, float xp) {
		super(conditionsIn);

		this.skill = skill;
		this.xp	= xp;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		if (context.getKillerPlayer() instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)context.getKillerPlayer()).stats().addXp(skill, xp, false, false);

		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<GrantXp> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "grant_xp"), GrantXp.class);
		}

		@Override
		public void serialize(JsonObject json, GrantXp functionInstance, JsonSerializationContext serializationContext) {
			json.addProperty("skill", functionInstance.skill.toString().toLowerCase());
			json.addProperty("xp", functionInstance.xp);
		}

		@Override
		public GrantXp deserialize(JsonObject json, JsonDeserializationContext deserializationContext, LootCondition[] lootConditions) {
			return new GrantXp(lootConditions, Enums.Skills.valueOf(JsonUtils.getString(json, "skill").toUpperCase()), JsonUtils.getFloat(json, "xp"));
		}
	}
}
