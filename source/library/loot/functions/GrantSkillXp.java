package net.tslat.aoa3.library.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

public class GrantSkillXp extends LootFunction {
	private final Skills skill;
	private final float xp;

	protected GrantSkillXp(ILootCondition[] lootConditions, Skills skill, float xp) {
		super(lootConditions);

		this.skill = skill;
		this.xp	= xp;
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context) {
		Entity entity = context.get(LootParameters.KILLER_ENTITY);

		if (!(entity instanceof PlayerEntity))
			entity = context.get(LootParameters.THIS_ENTITY);

		if (entity instanceof ServerPlayerEntity)
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity).stats().addXp(skill, xp, false, false);

		return stack;
	}

	public static Builder<?> builder(Skills skill, float xp) {
		return builder((conditions) -> new GrantSkillXp(conditions, skill, xp));
	}

	public static class Serializer extends LootFunction.Serializer<GrantSkillXp> {
		public Serializer() {
			super(new ResourceLocation(AdventOfAscension.MOD_ID, "grant_skill_xp"), GrantSkillXp.class);
		}

		@Override
		public void serialize(JsonObject object, GrantSkillXp function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.addProperty("skill", function.skill.toString().toLowerCase());
			object.addProperty("xp", function.xp);
		}

		@Override
		public GrantSkillXp deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditions) {
			return new GrantSkillXp(conditions, Skills.valueOf(JSONUtils.getString(object, "skill").toUpperCase()), JSONUtils.getFloat(object, "xp"));
		}
	}
}
